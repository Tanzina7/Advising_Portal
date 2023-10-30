import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedCornerButton extends JButton {
    private int arcWidth = 20; // Adjust this value to control the corner curve

    public RoundedCornerButton(String text) {
        super(text);
        setContentAreaFilled(false); // Make the button transparent
        setFocusPainted(false); // Remove the button's focus border
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.gray); // Change color when the button is pressed
        } else {
            g.setColor(getBackground());
        }

        // Draw the rounded rectangle as the button's background
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcWidth));

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcWidth);
    }
}
