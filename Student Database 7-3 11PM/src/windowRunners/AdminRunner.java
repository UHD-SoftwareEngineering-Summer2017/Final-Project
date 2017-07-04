/**
    AdminRunner controls the administrator window that views all the students.
 */

package windowRunners;

import database.Student;
import database.FileInputOutput;
import windows.AdminWindow;

public class AdminRunner
{
    private AdminWindow aw;     // Associated AdminWindow
    private Student[] students; // List of all the students
    
    /**
        Constructor.
    */
    public AdminRunner()
    {
        students = FileInputOutput.getStudentsFromFolder();
        aw = new AdminWindow(this, students);
        aw.setVisible(true);
    }
    
    /**
        clickedAdd opens the window for adding a new student.
    */
    public void clickedAdd()
    {
        aw.dispose();
        AdminAddRunner aar = new AdminAddRunner();
    }
    
    /**
        clickedDone logs out the administrator and returns to the login window.
    */
    public void clickedDone()
    {
        aw.dispose();
        LoginRunner lr = new LoginRunner();
    }
    
    /**
        clickedStudent opens the administrator's student window.
    */
    public void clickedStudent(int studentIndex)
    {
        aw.dispose();
        AdminStudentRunner asr = new AdminStudentRunner(students[studentIndex]);
    }
}