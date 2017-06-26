package windowRunners;

import database.Student;
import windows.StudentWindow;
import javax.swing.JOptionPane;

public class StudentRunner
{
    private StudentWindow sw;
    private Student student;
    
    public StudentRunner(Student student)
    {
        this.student = student;
        sw = new StudentWindow(this, student);
        sw.setVisible(true);
    }
    public void clickedDone()
    {
        sw.dispose();
        LoginRunner lr = new LoginRunner();
    }
    public void clickedGPA()
    {
        JOptionPane.showMessageDialog(null, "Your GPA is " + student.calculateGPA(), "GPA", JOptionPane.INFORMATION_MESSAGE);
    }
}