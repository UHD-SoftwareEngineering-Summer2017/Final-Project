/**
    AdminAddRunner controls the window for adding new students.
*/

package windowRunners;

import database.FileInputOutput;
import database.Student;
import windows.AdminAddWindow;
import javax.swing.JOptionPane;

public class AdminAddRunner
{
    private AdminAddWindow aaw; // Associated AdminAddWindow
    
    /**
        Constructor.
    */
    public AdminAddRunner()
    {
        aaw = new AdminAddWindow(this);
        aaw.setVisible(true);
    }
    
    /**
        clickedAdd creates a new student from the entered information.
    */
    public void clickedAdd(int studentID, String password, String name)
    {
        // Save the student in a new file and return to the students window.
        try
        {
            Student student = new Student(studentID, password, name);
            FileInputOutput.createNewStudentFile(student);
            aaw.dispose();
            AdminRunner ar = new AdminRunner();
        }
        
        // Do not proceed if the entered student ID already exists.
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "ID " + studentID + " already exists.", "New Student", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
        clickedCancel returns to the students window.
    */
    public void clickedCancel()
    {
        aaw.dispose();
        AdminRunner ar = new AdminRunner();
    }
}