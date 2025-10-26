import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane; // For optional message dialog
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleButtonApp {

    public static void main(String[] args) {
        // Create the main application window
        JFrame frame = new JFrame("Simple Java GUI Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        //-----------------------------------------------------------
        // Center the frame on the screen
        // method 1:
        //-----------------------------------------------------------
        // Get the screen size
        // the common method to locate content in center:
        /*
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate the frame location for centering
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;

        // Set the location of the frame
        frame.setLocation(x, y);
        //*/
        //-----------------------------------------------------------
        // method 2:
        //frame.setLocationRelativeTo(null);
        //-----------------------------------------------------------

        // Create a panel to hold the button
        JPanel panel = new JPanel();
        frame.add(panel);

        // Create the button
        JButton myButton = new JButton("Click Me!");

        // Add an ActionListener to the button
        myButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when the button is clicked
                System.out.println("Button was clicked!");
                JOptionPane.showMessageDialog(frame, "王大同 先生，歡迎您！");
                frame.getContentPane().setBackground(new Color(255, 255, 0));
            }
        });

        // Add the button to the panel
        panel.add(myButton);

        // Make the frame visible
        frame.setVisible(true);
    }
}