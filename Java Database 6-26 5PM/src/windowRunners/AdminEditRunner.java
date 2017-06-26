package windowRunners;

import database.FileInputOutput;
import database.Student;
import windows.AdminEditWindow;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

public class AdminEditRunner
{
    private AdminEditWindow aew;
    private Student student;
    
    public AdminEditRunner(Student student)
    {
        this.student = student;
        aew = new AdminEditWindow(this, student);
        aew.setVisible(true);
    }
    
    public void clickedSave(String name, String password, String courseData)
    {
        Student updateStudent = new Student(student.getStudentID(), password, name);
        StringTokenizer outerST = new StringTokenizer(courseData, "\n");
        StringTokenizer innerST;
        String courseName;
        
        try
        {
            while(outerST.hasMoreTokens())
            {
                innerST = new StringTokenizer(outerST.nextToken(), " ");
                courseName = innerST.nextToken();
                updateStudent.enrollCourse(courseName);
                while(innerST.hasMoreTokens())
                    updateStudent.addGrade(courseName, Double.parseDouble(innerST.nextToken()));
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Course data is in the wrong format.", "Edit Student", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try
        {
            FileInputOutput.replaceStudentFile(updateStudent);
        }
        catch (FileNotFoundException e) {}
        
        aew.dispose();
        AdminStudentRunner asr = new AdminStudentRunner(updateStudent);
    }
    public void clickedDelete()
    {
        int doDelete = JOptionPane.showConfirmDialog(null, "Delete student " + student.getStudentID() + "?", "Delete Student", JOptionPane.WARNING_MESSAGE);
        if(doDelete == JOptionPane.YES_OPTION)
        {
            try
            {
                FileInputOutput.deleteStudentFile(student);
            }
            catch (FileNotFoundException e) {}
            aew.dispose();
            AdminRunner ar = new AdminRunner();
        }
    }
    public void clickedCancel()
    {
        aew.dispose();
        AdminStudentRunner asr = new AdminStudentRunner(student);
    }
}