import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Dash {
    public Dash()
    {
        JFrame f= new JFrame("Welcome");

        // Create a custom Font with a specific size
        Font customFont = new Font("Arial", Font.BOLD, 20);
        Font customFont2 = new Font("Times New Roman", Font.BOLD, 16);

        JLabel l1 = new JLabel("Welcome to DashBoard");
        l1.setForeground(Color.RED);
        l1.setFont(customFont);
        l1.setBounds(220, 50, 250, 200);
        f.add(l1);

        //JButton b=new JButton("Sign Up");
        RoundedCornerButton b = new RoundedCornerButton("Sign Up");
        //b.setPreferredSize(new Dimension(150, 50));
        b.setBackground(Color.ORANGE);
        b.setForeground(Color.BLUE);
        b.setFont(customFont2);
        b.setBounds(200,240,100,50);
        f.add(b);

        //JLabel jb = new JLabel();
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //jb.setText("Hello World....");
                //jb.setBounds(65, 50, 100, 50);
                Signup s = new Signup();
                f. dispose();

            }
        });

        RoundedCornerButton b2 = new RoundedCornerButton("Login");
        //b.setPreferredSize(new Dimension(150, 50));
        b2.setBackground(Color.ORANGE);
        b2.setForeground(Color.BLUE);
        b2.setFont(customFont2);
        b2.setBounds(350,240,100,50);
        f.add(b2);

        //JLabel jb = new JLabel();
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //jb.setText("Hello World....");
                //jb.setBounds(65, 50, 100, 50);
                Login l = new Login();
                f. dispose();

            }
        });


        //f.add(jb);
        f.setSize(650,500);
        //f.setBackground(Color.red);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args)
    {
        Dash d = new Dash();
    }
}
