package windowRunners;

import database.Student;
import windows.AdminStudentWindow;
import javax.swing.JOptionPane;

public class AdminStudentRunner
{
    private AdminStudentWindow asw;
    private Student student;
    
    public AdminStudentRunner(Student student)
    {
        this.student = student;
        asw = new AdminStudentWindow(this, student);
        asw.setVisible(true);
    }
    
    public void clickedGPA()
    {
        JOptionPane.showMessageDialog(null, student.getName() + "'s GPA is " + student.calculateGPA(), "GPA", JOptionPane.INFORMATION_MESSAGE);
    }
    public void clickedEdit()
    {
        asw.dispose();
        AdminEditRunner aew = new AdminEditRunner(student);
    }
    public void clickedDone()
    {
        asw.dispose();
        AdminRunner ar = new AdminRunner();
    }
}