package windowRunners;

import database.Student;
import database.FileInputOutput;
import windows.LoginWindow;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

public class LoginRunner
{
    private LoginWindow lw;
    
    public LoginRunner()
    {
        lw = new LoginWindow(this);
        lw.setVisible(true);
    }
    public void clickedLogin(String username, String password)
    {
        int usernameInt = 0;
        try
        {
            usernameInt = Integer.parseInt(username);
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Username in wrong format.", "Login", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(FileInputOutput.studentIDExists(usernameInt))
        {
            if(FileInputOutput.rightStudentPassword(usernameInt, password))
            {
                try
                {
                    Student student = FileInputOutput.getStudentFromFile(usernameInt);
                    lw.dispose();
                    StudentRunner sr = new StudentRunner(student);
                }
                catch(FileNotFoundException e) {}
            }
            else
                loginWrong();
        }
        
        else if(FileInputOutput.adminIDExists(usernameInt))
        {
            if(FileInputOutput.rightAdminPassword(usernameInt, password))
            {
                lw.dispose();
                AdminRunner ar = new AdminRunner();
            }
            else
                loginWrong();
        }
        
        else
            loginWrong();
    }
    public void clickedCancel()
    {
        System.exit(0);
    }
    public void loginWrong()
    {
        JOptionPane.showMessageDialog(null, "Wrong username and/or password.", "Login", JOptionPane.WARNING_MESSAGE);
    }
}