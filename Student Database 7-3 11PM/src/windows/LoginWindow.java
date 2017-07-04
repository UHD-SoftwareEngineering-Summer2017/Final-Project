/**
    LoginWindow displays the window for logging in students and administrators.
*/

package windows;

import windowRunners.LoginRunner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame implements ActionListener
{
    public static final int WIDTH = 300;  // Width
    public static final int HEIGHT = 150; // Height
    private LoginRunner lr;               // Associated LoginRunner
    
    // Labels and text fields for entering usernames and passwords.
    private JPanel loginPanel;
    private JLabel usernameLabel;
    private JTextField usernameBox;
    private JLabel passwordLabel;
    private JTextField passwordBox;
    
    // Buttons for actions.
    private JPanel buttonPanel;
    private JButton loginButton;
    private JButton cancelButton;
    
    /**
        Constructor.
    */
    public LoginWindow(LoginRunner lr)
    {
        // Setup basic window information.
        super("Database Login");
        this.lr = lr;
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Add labels and text fields for user input.
        loginPanel = new JPanel(new GridLayout(4, 1));
        usernameLabel = new JLabel("Username");
        usernameBox = new JTextField();
        passwordLabel = new JLabel("Password");
        passwordBox = new JTextField();
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameBox);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordBox);
        add(loginPanel, BorderLayout.CENTER);
        
        // Add buttons for actions.
        buttonPanel = new JPanel(new GridLayout(1, 2));
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        buttonPanel.add(loginButton);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
        actionPerformed chooses a LoginRunner action depending on the button clicked.
    */
    public void actionPerformed(ActionEvent e)
    {
        String action = e.getActionCommand();
        if (action.equals("Login"))
            lr.clickedLogin(usernameBox.getText(), passwordBox.getText());
        else if (action.equals("Cancel"))
            lr.clickedCancel();
    }
}