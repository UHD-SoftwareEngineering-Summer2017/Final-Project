package windowRunners;

import database.Student;
import database.FileInputOutput;
import windows.AdminWindow;

public class AdminRunner
{
    private AdminWindow aw;
    private Student[] students;
    
    public AdminRunner()
    {
        students = FileInputOutput.getStudentsFromFolder();
        aw = new AdminWindow(this, students);
        aw.setVisible(true);
    }
    public void clickedAdd()
    {
        aw.dispose();
        AdminAddRunner aar = new AdminAddRunner();
    }
    public void clickedDone()
    {
        aw.dispose();
        LoginRunner lr = new LoginRunner();
    }
    public void clickedStudent(int studentIndex)
    {
        aw.dispose();
        AdminStudentRunner asr = new AdminStudentRunner(students[studentIndex]);
    }
}