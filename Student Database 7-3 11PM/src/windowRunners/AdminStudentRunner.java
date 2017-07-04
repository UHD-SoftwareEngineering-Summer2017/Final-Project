/**
    AdminStudentRunner controls the administrator's student viewing window.
*/

package windowRunners;

import database.Student;
import windows.AdminStudentWindow;
import javax.swing.JOptionPane;

public class AdminStudentRunner
{
    private AdminStudentWindow asw; // Associated AdminStudentWindow
    private Student student;        // Associated student
    
    /**
        Constructor.
    */
    public AdminStudentRunner(Student student)
    {
        this.student = student;
        asw = new AdminStudentWindow(this, student);
        asw.setVisible(true);
    }
    
    /**
        clickedGPA displays the student's calculated GPA.
     */
    public void clickedGPA()
    {
        JOptionPane.showMessageDialog(null, student.getName() + "'s GPA is " + student.calculateGPA(), "GPA", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
        clickedEdit opens the student editing window.
    */
    public void clickedEdit()
    {
        asw.dispose();
        AdminEditRunner aew = new AdminEditRunner(student);
    }
    
    /**
        clickedDone returns to the students window.
    */
    public void clickedDone()
    {
        asw.dispose();
        AdminRunner ar = new AdminRunner();
    }
}