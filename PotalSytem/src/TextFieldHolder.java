import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextFieldHolder {
    private JTextField textField;
    String st;

    public TextFieldHolder() {
        JFrame frame = new JFrame("Text Field Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        textField = new JTextField(20); // 20 columns wide

        JButton fetchButton = new JButton("Fetch Text");
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                st = textField.getText();
                //System.out.println("Fetched Text: " + text);
                AnotherClass a = new AnotherClass();


            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(textField);
        panel.add(fetchButton);

        frame.add(panel);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        TextFieldHolder tfs= new TextFieldHolder();
    }

}

