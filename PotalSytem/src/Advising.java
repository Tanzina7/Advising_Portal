import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.table.*;

public class Advising
        //extends Signup
{

    JFrame f;
    public Advising()
    {
        //JFrame f;

        Login su = new  Login();
        su.f.dispose();

        JLabel l1 = new JLabel();
        l1.setBounds(50, 20, 100, 100);
       // l1.setText(st.getId());
        l1.setText("ID: " + su.idd);

        JLabel l2 = new JLabel();
        l2.setBounds(250, 20, 100, 100);
       // l2.setText(st.getName());
        l2.setText("Name: " +su.nmm);

        JLabel l3 = new JLabel();
        l3.setBounds(450, 20, 100, 100);
        //l3.setText(String.valueOf(st.getCg()));
        l3.setText("CGPA: " +su.cgg);


        int ncourse = 0;
        //reading all the courses from the CouseList File and loading it on to an array
        try{
            File file1 = new File("CourseList.txt");
            Scanner sc7 = new Scanner( file1);

            while(sc7.hasNextLine())
            {
                sc7.nextLine();
                ncourse++;
            }
            sc7.close();

        }
        catch(IOException e5)
        {
            e5.printStackTrace();
        }
        Course[] arrO = new Course[ncourse];

        try{
            File file2 = new File("CourseList.txt");
            Scanner sc8 = new Scanner( file2);
            for(int i = 0; i< ncourse; i++)
            {
                String oneLine = sc8.nextLine();
                String[] lineArr = oneLine.split("\t");
                arrO[i]= new Course(lineArr[0], lineArr[1], Integer.valueOf(lineArr[2]), Integer.valueOf(lineArr[3]));
            }

            sc8.close();


        }
        catch(IOException e5)
        {
            e5.printStackTrace();
        }

        //now making the JComboBox from info of the array that was made from file
        JFrame f = new JFrame("Courses");
        String[] c = new String[arrO.length];
        for (int i = 0; i < c.length; i++) {
            c[i] = arrO[i].getCode();
        }

        JComboBox<String> cb = new JComboBox<>(c);

        cb.setBounds(100, 100, 90, 30);
        cb.setBackground(Color.yellow);
        cb.setForeground(Color.red);
        cb.setFont(new Font("Arial", Font.BOLD, 14));
        // Set the custom border to the JComboBox
        cb.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
        f.add(cb);



        //List to add all the sections of each course in a jcombobox
        List<TableCellEditor> editors = new ArrayList<TableCellEditor>(ncourse);
        String[] currArray0 = {"Section"};
        JComboBox jbx0 = new JComboBox(currArray0);
        DefaultCellEditor dce0 = new DefaultCellEditor(jbx0);
        editors.add(dce0);

        //creating the table
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("");
        model.addColumn("CourseCode");
        model.addColumn("CourseName");
        model.addColumn("Section");
        model.addColumn("Credit");
        model.addColumn("Fee");
        Object[] colData = {"No","CourseCode", "CourseName", "Section", "Credit", "Fee"};
        model.addRow(colData);
        final int[] count = {0};

        try
        {
            File pf = new File("AdvisingSlip\\"+su.idd +".txt");
            if(pf.exists())
            {
                int line = 0;
                Scanner scc = new Scanner(pf);
                while(scc.hasNextLine())
                {
                    scc.nextLine();
                    line++;
                }
                scc = new Scanner(pf);
                for(int i = 0; i<line; i++)
                {
                    String CurrentLine =  scc.nextLine();
                    if(i>=3 && i<line-2)
                    {
                        String[] arr = CurrentLine.split("\t");
                        count[0] = Integer.valueOf(arr[0]);
                        model.addRow(arr);

                    }
                    else
                        continue;
                }
                scc.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


        JButton b = new JButton("ADD");
        b.setBounds(50, 200, 75, 20);
        f.add(b);
        //static int n = 0;
        //final int[] count = {0};
        b.addActionListener(e -> {

            String selectedCode = cb.getItemAt(cb.getSelectedIndex());
            Boolean exist = false;
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int column = 0; column < model.getColumnCount(); column++) {
                    Object value = model.getValueAt(row, column);
                    //System.out.println("Row " + row + ", Column " + column + ": " + value);
                    if(selectedCode.equals(value))
                    {
                        exist = true;
                        break;
                    }
                }
            }

            if(exist == false)
            {
                // Create an array or vector representing the new row data
                //String selectedCode = cb.getItemAt(cb.getSelectedIndex());
                String SelectedName = "";
                String SelectedCredit = "";
                int TotalSec = 0;
                int Credit = 0;
                //int n = 0;
                for (int i = 0; i < arrO.length; i++) {
                    if (selectedCode.equals(arrO[i].getCode())) {
                        SelectedName = arrO[i].getName();
                        SelectedCredit = Integer.toString(arrO[i].getCredit());
                        TotalSec = arrO[i].getTotal_section();
                        Credit = arrO[i].getCredit();
                        // n++;
                        count[0] = count[0] + 1;
                        break;
                    }
                }
                int bill = Credit * 6500;          //6500 is the fee per credit

                Object[] rowData = {Integer.toString(count[0]), selectedCode, SelectedName,"", SelectedCredit, Integer.toString(bill)};

                // Add the new row data to the DefaultTableModel
                model.addRow(rowData);

                ///adding section jcombobox for that course
                String[] currArray = new String[TotalSec] ;
                for(int j =0; j< TotalSec; j++)
                {
                    currArray[j] = String.valueOf(j+1);
                    System.out.print(currArray[j]+"\t");
                }
                System.out.println("\n");
                JComboBox jbx = new JComboBox(currArray);

                DefaultCellEditor dce = new DefaultCellEditor(jbx);
                editors.add(dce);

            }
            else
            {
                JOptionPane.showMessageDialog(f, "You Already have taken "+selectedCode, "Duplicate Course", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Create the JTable with the DefaultTableModel
        JTable jt = new JTable(model) {
            //  Determine editor to be used by row
            public TableCellEditor getCellEditor(int row, int column) {
                int modelColumn = convertColumnIndexToModel(column);

                if (modelColumn == 3 && row>0 && row < model.getRowCount())
                    return editors.get(row);
                else
                    return super.getCellEditor(row, column);
            }
        };

        //setting custom width of 2 columns
        // Get the TableColumnModel and the TableColumn for the first column
        TableColumnModel columnModel = jt.getColumnModel();
        TableColumn column0 = columnModel.getColumn(0);
        TableColumn column2 = columnModel.getColumn(2);

        // Set the preferred width for the first column
        int preferredWidth0 = 40;
        column0.setPreferredWidth(preferredWidth0);
        int preferredWidth2 = 130;
        column2.setPreferredWidth(preferredWidth2);

        // Update the JTable's layout
        jt.revalidate();
        jt.repaint();


        //setting a color to the 1st row of the table
        // Add custom TableCellRenderer to color the first row
        jt.setDefaultRenderer(Object.class, new CustomRowColorRenderer());

        jt.setBounds(50, 250, 550, 150);
        f.add(jt);

        //JScrollPane scrollPane = new JScrollPane(jt);
       // f.getContentPane().add(scrollPane);


        // f.add(new JScrollPane(jt));

        // Create a JScrollPane to hold the JTable
        //JScrollPane scrollPane = new JScrollPane(jt);
        // Add the JScrollPane to the JFrame
        //f.add(scrollPane);



        // Display the sum in a JOptionPane
        //JOptionPane.showMessageDialog(null, "Sum of Column " + (columnIndexToSum + 1) + ": " + sum);


        JLabel fees = new JLabel();
        //label.setText("Total Tuition Fee = " );
        fees.setHorizontalAlignment(JLabel.CENTER);
        fees.setBounds(250,450,200,25);
        //fees.setSize(750,760);
        f.add(fees);

        JButton sh =new JButton("Show");
        sh.setBounds(480,450,70,25);
        f.add(sh);
        final int[] summ = {0};


        sh.addActionListener(new ActionListener()
                             {
                                 public void actionPerformed(ActionEvent e)
                                 {
                                     // Calculate the sum of a specific column (e.g., Column 2)
                                     int columnIndexToSum = 5; // Change this to the index of the column you want to sum
                                     int rowCount = jt.getRowCount();
                                     int sum = 0;


                                     for (int row = 1; row < rowCount; row++)
                                     {
                                         String stringValue = (String) jt.getValueAt(row, columnIndexToSum);

                                         // Convert the String value to an integer (you can use Double.parseDouble() for doubles)
                                         int numericValue = Integer.parseInt(stringValue);

                                         sum = sum + numericValue;
                                     }



                                     //String data =   cb.getItemAt(cb.getSelectedIndex());
                                     fees.setText("Total Tuition Fee = " + Integer.toString(sum));

                                     summ[0] = sum;
                                     System.out.println(summ[0]);
                                 }
                             }
        );


        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(150, 200, 80, 20);
        f.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jt.getSelectedRow();
                if (selectedRow != -1) {
                    // Remove the selected row data from the DefaultTableModel
                    model.removeRow(selectedRow);
                }
            }
        });


        // Create a button to save the data
        JButton saveButton = new JButton("SAVE");
        saveButton.setBounds(250, 200, 80, 20);
        f.add(saveButton);
        saveButton.addActionListener(e -> {

            try {
                File file = new File("AdvisingSlip\\"+su.idd+".txt");
                FileWriter writer = new FileWriter(file);

                /*
                // Write the column names
                for (int i = 0; i < model.getColumnCount(); i++) {
                    writer.write(model.getColumnName(i));
                    if (i < model.getColumnCount() - 1) {
                        writer.write("\t"); // Use tab as a delimiter
                    }
                }

                 */
                writer.write(l1.getText() + "\t\t"+l2.getText()+ "\t\t"+l3.getText()+"\n\n");



                // Write the data rows
                for (int row = 0; row < model.getRowCount(); row++) {
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        writer.write(model.getValueAt(row, col).toString());
                        if (col < model.getColumnCount() - 1) {
                            writer.write("\t");
                        }
                    }
                    writer.write("\n");
                }

                writer.write("_________________________________________________________________");
                writer.write("\n\t\t\t\t Total Payable amount = " + summ[0]);

                // Close the writer
                writer.close();

                JOptionPane.showMessageDialog(null, "Data saved to " + file.getAbsolutePath(), "Save Successful", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving data: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        RoundedCornerButton b1 = new RoundedCornerButton("logout");
        //b.setPreferredSize(new Dimension(150, 50));
        b1.setBackground(Color.ORANGE);
        b1.setForeground(Color.BLUE);
        Font customFont2 = new Font("Times New Roman", Font.BOLD, 16);
        b1.setFont(customFont2);
        b1.setBounds(100,480,80,30);
        f.add(b1);

        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                f. dispose();
            }
        });


        f.add(l1);
        f.add(l2);
        f.add(l3);

        f.setLayout(null);
        f.setSize(650, 600);
        f.setForeground(Color.BLACK);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }



    //inner class to set each cell of one column of the jtable into jcombobox
    class ComboBoxRenderer extends JComboBox implements TableCellRenderer
    {

        public ComboBoxRenderer()
        {
            setBorder(new EmptyBorder(0, 0, 0, 0));
        }

        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column)
        {
//          setFocusable(false);
            removeAllItems();
            addItem( value );
            return this;
        }
    }



    // Custom TableCellRenderer to color the first row
    static class CustomRowColorRenderer extends DefaultTableCellRenderer
    {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            Component component;


            if (row == 0) {
                component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setBackground(Color.CYAN);
            }
            else
            {
                component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setBackground(Color.WHITE);
            }

            return component;
        }
    }


    public static void main(String[] args) {
        Advising m = new Advising();

    }
}
