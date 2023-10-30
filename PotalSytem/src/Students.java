import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Students extends Person
{
    private String id;
    private double cg;
    private String  gender;

    private String password;
    private static String Uni = "NSU";

    static int count =  0;



    Students()
    {
        count++;
        JFrame f = new JFrame("Sign Up");



        JLabel l1 = new JLabel("Enter ID :  ");
        l1.setBounds(80, 50, 200, 30);
        f.add(l1);

        JTextField t1 = new JTextField();
        t1.setBounds(300,50, 250,30);
        f.add(t1);

        JLabel l2 = new JLabel("Enter Name :  ");
        l2.setBounds(80, 100, 200, 30);
        f.add(l2);

        JTextField t2 = new JTextField();
        t2.setBounds(300,100, 250,30);
        f.add(t2);

        JLabel pl = new JLabel("Enter Password :  ");
        pl.setBounds(80, 150, 200, 30);
        f.add(pl);

        JPasswordField pt = new JPasswordField();
        pt.setBounds(300,150, 250,30);
        f.add(pt);

        JLabel l3 = new JLabel("Enter CGPA :  ");
        l3.setBounds(80, 200, 200, 30);
        f.add(l3);

        JTextField t3 = new JTextField();
        t3.setBounds(300,200, 250,30);
        f.add(t3);

        JRadioButton r1=new JRadioButton("Male");
        JRadioButton r2=new JRadioButton("Female");
        r1.setBounds(80,250,100,30);
        r2.setBounds(300,250,100,30);
        ButtonGroup bg=new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        f.add(r1);
        f.add(r2);





        RoundedCornerButton bb = new RoundedCornerButton("Submit");
        //b.setPreferredSize(new Dimension(150, 50));
        bb.setBackground(Color.ORANGE);
        bb.setForeground(Color.BLUE);
        Font customFont2 = new Font("Times New Roman", Font.BOLD, 16);
        bb.setFont(customFont2);
        bb.setBounds(260,350,100,50);
        f.add(bb);

        bb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                String Id_Check = t1.getText();
                String row = t1.getText() + "\t"+ t2.getText()+ "\t"+ pt.getText()+ "\t"+t3.getText()+ "\t";
                String g ;
                if (r1.isSelected())
                {
                    g = r1.getText();
                    row = row + r1.getText();
                }
                else if (r2.isSelected())
                {
                    g = r2.getText();
                    row = row + r2.getText();
                }
                else
                {
                    g = "";
                    row = row + "NA_Gender";
                }

                //Boolean found = false;
                try
                {
                    File fl = new File("StudentRecords2.txt");
                    Scanner sc = new Scanner(fl);
                    Boolean found = false;

                    while(sc.hasNext())
                    {

                        String id1 = sc.next();
                        String name1 = sc.next();
                        String password1 = sc.next();
                        String cg1 = sc.next();
                        //double cg1 = sc.nextDouble();
                        String  gender1 = sc.next();

                        if(Id_Check.equals(id1))
                        {
                            found = true;
                            break;
                            //JOptionPane.showMessageDialog(null, "ID Already Exists.\n LOGIN","Duplicate ID", JOptionPane.ERROR_MESSAGE);
                            //f.dispose();
                            //Dash d = new Dash();
                        }
                    }
                    sc.close();

                    if(found == false)
                    {
                        setName(t2.getText());
                        setId(t1.getText());
                        setPassword(pt.getText());
                        setCg(Double.valueOf(t3.getText()));
                        setGender(g);


                            File fl2 = new File("StudentRecords2.txt");
                            FileWriter fw = new FileWriter(fl2, true);
                            //if(count == 1)
                            //fw.write("ID\tNAME\tPassword\tCGPA\tGENDER\n");
                            fw.write(row + "\n");
                            fw.close();


                        //Advising s = new Advising();
                        Login l = new Login();
                        f.dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "ID Already Exists.\n LOGIN instead","Duplicate ID", JOptionPane.ERROR_MESSAGE);
                        f.dispose();
                        Dash d = new Dash();
                    }
                }
                catch(IOException e0)
                {
                    e0.printStackTrace();
                }



            }
        });


        f.setSize(650,500);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    Students( String id, double cg, String gender)
    {

        this.id = id;
        this.cg = cg;
        this.gender = gender;

        count++;
    }

    Students( String name, String id, double cg, String gender)
    {
        super(name);

        this.id = id;
        this.cg = cg;
        this.gender = gender;
        count++;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCg(double cg) {
        this.cg = cg;
    }

    public double getCg() {
        return cg;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String toString()
    {
        return  super.toString()+" id = " +id + " current cgpa = " + cg +" Gender = "+gender+ " university name = " + Uni;
    }
}
