/**
    StudentRunner controls the student viewing window.
*/

package windowRunners;

import database.Student;
import windows.StudentWindow;
import javax.swing.JOptionPane;

public class StudentRunner
{
    private StudentWindow sw; // Associated StudentWindow
    private Student student;  // Associated student
    
    /**
        Constructor.
    */
    public StudentRunner(Student student)
    {
        this.student = student;
        sw = new StudentWindow(this, student);
        sw.setVisible(true);
    }
    
    /**
        clickedDone logs out the user and returns to the login window.
    */
    public void clickedDone()
    {
        sw.dispose();
        LoginRunner lr = new LoginRunner();
    }
    
    /**
        clickedGPA displays the student's calculated GPA.
    */
    public void clickedGPA()
    {
        JOptionPane.showMessageDialog(null, "Your GPA is " + student.calculateGPA(), "GPA", JOptionPane.INFORMATION_MESSAGE);
    }
}