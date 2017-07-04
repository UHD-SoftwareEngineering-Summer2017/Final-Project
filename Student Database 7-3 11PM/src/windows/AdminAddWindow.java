/**
    AdminAddWindow displays the window for adding new students.
*/

package windows;

import windowRunners.AdminAddRunner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminAddWindow extends JFrame implements ActionListener
{
    public static final int WIDTH = 300;  // Width
    public static final int HEIGHT = 200; // Height
    private AdminAddRunner aar;           // Associated AdminAddRunner
    
    // Labels and text fields for entering student data.
    private JPanel infoPanel;
    private JLabel usernameLabel;
    private JTextField usernameBox;
    private JLabel passwordLabel;
    private JTextField passwordBox;
    private JLabel nameLabel;
    private JTextField nameBox;
    
    // Buttons for actions.
    private JPanel buttonPanel;
    private JButton addButton;
    private JButton cancelButton;
    
    /**
        Constructor.
    */
    public AdminAddWindow(AdminAddRunner aar)
    {
        // Setup basic window information.
        super("New Student");
        this.aar = aar;
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Add labels and text fields for user input.
        infoPanel = new JPanel(new GridLayout(6, 1));
        usernameLabel = new JLabel("Username");
        usernameBox = new JTextField();
        passwordLabel = new JLabel("Password");
        passwordBox = new JTextField();
        nameLabel = new JLabel("Name");
        nameBox = new JTextField();
        infoPanel.add(usernameLabel);
        infoPanel.add(usernameBox);
        infoPanel.add(passwordLabel);
        infoPanel.add(passwordBox);
        infoPanel.add(nameLabel);
        infoPanel.add(nameBox);
        add(infoPanel, BorderLayout.CENTER);
        
        // Add buttons for actions.
        buttonPanel = new JPanel(new GridLayout(1, 2));
        addButton = new JButton("Add Student");
        addButton.addActionListener(this);
        buttonPanel.add(addButton);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
        actionPerformed chooses an AdminAddRunner action depending on the button clicked.
    */
    public void actionPerformed(ActionEvent e)
    {
        String action = e.getActionCommand();
        if(action.equals("Add Student"))
            aar.clickedAdd(Integer.parseInt(usernameBox.getText()), passwordBox.getText(), nameBox.getText());
        else if(action.equals("Cancel"))
            aar.clickedCancel();
    }
}