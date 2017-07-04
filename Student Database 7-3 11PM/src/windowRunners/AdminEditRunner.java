/**
    AdminEditRunner controls the student editing window.
*/

package windowRunners;

import database.FileInputOutput;
import database.Student;
import windows.AdminEditWindow;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

public class AdminEditRunner
{
    private AdminEditWindow aew; // Associated AdminEditWindow
    private Student student;     // Associated student
    
    /**
        Constructor.
    */
    public AdminEditRunner(Student student)
    {
        this.student = student;
        aew = new AdminEditWindow(this, student);
        aew.setVisible(true);
    }
    
    /**
        clickedSave updates the student with new information.
    */
    public void clickedSave(String name, String password, String courseData)
    {
        // Create a new Student and StringTokenizers to read courseData.
        Student updateStudent = new Student(student.getStudentID(), password, name);
        StringTokenizer outerST = new StringTokenizer(courseData, "\n");
        StringTokenizer innerST;
        String courseName;
        
        // Read an entire line of course data.
        try
        {
            // Read specific information in the course data line.
            while(outerST.hasMoreTokens())
            {
                innerST = new StringTokenizer(outerST.nextToken(), " ");
                courseName = innerST.nextToken();
                updateStudent.enrollCourse(courseName);
                while(innerST.hasMoreTokens())
                    updateStudent.addGrade(courseName, Double.parseDouble(innerST.nextToken()));
            }
        }
        
        // End the process if courseData is in the wrong format.
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Course data is in the wrong format.", "Edit Student", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Replace the current student with the new student and return to the students window.
        try
        {
            FileInputOutput.replaceStudentFile(updateStudent);
        }
        catch (FileNotFoundException e) {}
        
        aew.dispose();
        AdminStudentRunner asr = new AdminStudentRunner(updateStudent);
    }
    
    /**
        clickedDelete deletes the student file.
    */
    public void clickedDelete()
    {
        // Display a window to confirm the delete.
        int doDelete = JOptionPane.showConfirmDialog(null, "Delete student " + student.getStudentID() + "?", "Delete Student", JOptionPane.WARNING_MESSAGE);
        if(doDelete == JOptionPane.YES_OPTION)
        {
            // Delete the student and return to the students window.
            try
            {
                FileInputOutput.deleteStudentFile(student);
            }
            catch (FileNotFoundException e) {}
            aew.dispose();
            AdminRunner ar = new AdminRunner();
        }
    }
    
    /**
        clickedCancel returns to the administrator's student window.
     */
    public void clickedCancel()
    {
        aew.dispose();
        AdminStudentRunner asr = new AdminStudentRunner(student);
    }
}