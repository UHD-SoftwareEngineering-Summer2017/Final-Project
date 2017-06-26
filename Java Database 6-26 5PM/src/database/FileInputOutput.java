package database;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class FileInputOutput
{
    public static boolean studentIDExists(int studentID)
    {
        try
        {
            FileInputStream check = new FileInputStream("Students/" + studentID + ".txt");
            return true;
        }
        catch(FileNotFoundException e)
        {
            return false;
        }
    }
    public static boolean adminIDExists(int administratorID)
    {
        try
        {
            FileInputStream check = new FileInputStream("Administrators/" + administratorID + ".txt");
            return true;
        }
        catch(FileNotFoundException e)
        {
            return false;
        }
    }
    public static boolean rightStudentPassword(int studentID, String password)
    {
        try
        {
            Scanner fInput = new Scanner(new FileInputStream("Students/" + studentID + ".txt"));
            return fInput.nextLine().equals(password);
        }
        catch(FileNotFoundException e)
        {
            return false;
        }
    }
    public static boolean rightAdminPassword(int administratorID, String password)
    {
        try
        {
            Scanner fInput = new Scanner(new FileInputStream("Administrators/" + administratorID + ".txt"));
            return fInput.nextLine().equals(password);
        }
        catch(FileNotFoundException e)
        {
            return false;
        }
    }
    
    public static Student getStudentFromFile(int username) throws FileNotFoundException
    {
        Scanner fInput = new Scanner(new FileInputStream("Students/" + username + ".txt"));
        Student student = new Student(username, fInput.nextLine(), fInput.nextLine());
        
        StringTokenizer st;
        String courseName;
        while (fInput.hasNextLine())
        {
            st = new StringTokenizer(fInput.nextLine(), "$");
            courseName = st.nextToken();
            student.enrollCourse(courseName);
            while (st.hasMoreTokens())
                student.addGrade(courseName, Double.parseDouble(st.nextToken()));
        }
        
        fInput.close();
        return student;
    }
    public static Student[] getStudentsFromFolder()
    {
        File[] studentFilesTemp = new File("Students").listFiles();
        ArrayList<File> studentFiles = new ArrayList();
        for(int index = 0; index < studentFilesTemp.length; index++)
        {
            if(studentFilesTemp[index].getName().equals(".DS_Store"))
                continue;
            studentFiles.add(studentFilesTemp[index]);
        }
        
        Student[] students = new Student[studentFiles.size()];
        StringTokenizer st;
        try
        {
            for(int index = 0; index < students.length; index++)
            {
                st = new StringTokenizer(studentFiles.get(index).getName(), ".");
                students[index] = getStudentFromFile(Integer.parseInt(st.nextToken()));
            }
        }
        catch (FileNotFoundException e) {}
        
        return students;
    }
    
    public static void createNewStudentFile(Student student) throws Exception
    {
        if(studentIDExists(student.getStudentID()))
            throw new Exception("ID " + student.getStudentID() + " already exists.");
        
        File studentFile = new File("Students/" + student.getStudentID() + ".txt");
        Course[] courses = student.getCourses();
        double[] grades;
        try
        {
            PrintWriter fOutput = new PrintWriter(studentFile);
            fOutput.println(student.getPassword());
            fOutput.print(student.getName());
            for(int courseIndex = 0; courseIndex < courses.length; courseIndex++)
            {
                fOutput.println();
                fOutput.print(courses[courseIndex].getName());
                grades = courses[courseIndex].getGrades();
                for(int gradeIndex = 0; gradeIndex < grades.length; gradeIndex++)
                    fOutput.print("$" + grades[gradeIndex]);
            }
            fOutput.close();
        }
        catch(FileNotFoundException e) {}
    }
    public static void replaceStudentFile(Student student) throws FileNotFoundException
    {
        if(!studentIDExists(student.getStudentID()))
            throw new FileNotFoundException("ID " + student.getStudentID() + " does not exist.");
        
        File studentFile = new File("Students/" + student.getStudentID() + ".txt");
        Course[] courses = student.getCourses();
        double[] grades;
        try
        {
            PrintWriter fOutput = new PrintWriter(studentFile);
            fOutput.println(student.getPassword());
            fOutput.print(student.getName());
            for(int courseIndex = 0; courseIndex < courses.length; courseIndex++)
            {
                fOutput.println();
                fOutput.print(courses[courseIndex].getName());
                grades = courses[courseIndex].getGrades();
                for(int gradeIndex = 0; gradeIndex < grades.length; gradeIndex++)
                    fOutput.print("$" + grades[gradeIndex]);
            }
            fOutput.close();
        }
        catch(FileNotFoundException e) {}
    }
    public static void deleteStudentFile(Student student) throws FileNotFoundException
    {
        if(!studentIDExists(student.getStudentID()))
            throw new FileNotFoundException("ID " + student.getStudentID() + " already does not exist.");
        
        new File("Students/" + student.getStudentID() + ".txt").delete();
    }
}