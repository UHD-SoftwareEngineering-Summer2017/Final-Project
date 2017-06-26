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
    public static final int WIDTH = 300;
    public static final int HEIGHT = 150;
    private LoginRunner lr;
    
    private JPanel loginPanel;
    private JLabel usernameLabel;
    private JTextField usernameBox;
    private JLabel passwordLabel;
    private JTextField passwordBox;
    
    private JPanel buttonPanel;
    private JButton loginButton;
    private JButton cancelButton;
    
    public LoginWindow(LoginRunner lr)
    {
        super("Database Login");
        this.lr = lr;
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
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
        
        buttonPanel = new JPanel(new GridLayout(1, 2));
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        buttonPanel.add(loginButton);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String action = e.getActionCommand();
        if (action.equals("Login"))
            lr.clickedLogin(usernameBox.getText(), passwordBox.getText());
        else if (action.equals("Cancel"))
            lr.clickedCancel();
    }
}