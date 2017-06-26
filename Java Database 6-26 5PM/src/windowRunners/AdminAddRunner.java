package windowRunners;

import database.FileInputOutput;
import database.Student;
import windows.AdminAddWindow;
import javax.swing.JOptionPane;

public class AdminAddRunner
{
    private AdminAddWindow aaw;
    
    public AdminAddRunner()
    {
        aaw = new AdminAddWindow(this);
        aaw.setVisible(true);
    }
    public void clickedAdd(int studentID, String password, String name)
    {
        try
        {
            Student student = new Student(studentID, password, name);
            FileInputOutput.createNewStudentFile(student);
            aaw.dispose();
            AdminRunner ar = new AdminRunner();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "ID " + studentID + " already exists.", "New Student", JOptionPane.WARNING_MESSAGE);
        }
    }
    public void clickedCancel()
    {
        aaw.dispose();
        AdminRunner ar = new AdminRunner();
    }
}