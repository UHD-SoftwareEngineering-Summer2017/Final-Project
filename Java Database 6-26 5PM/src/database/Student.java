package database;

import java.util.ArrayList;

public class Student
{
    private int studentID;
    private String password;
    private String name;
    private ArrayList<Course> courses;
    
    public Student(int studentID, String password, String name)
    {
        setStudentID(studentID);
        setPassword(password);
        setName(name);
        courses = new ArrayList();
    }
    public Student(Student student)
    {
        setStudentID(student.studentID);
        setPassword(student.getPassword());
        setName(student.getName());
        
        courses = new ArrayList();
        for (int index = 0; index < student.getNumCourses(); index++)
            courses.add(student.courses.get(index));
    }
    public void setStudentID(int studentID)
    {
        this.studentID = studentID;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getStudentID()
    {
        return studentID;
    }
    public String getPassword()
    {
        return password;
    }
    public String getName()
    {
        return name;
    }
    
    public int courseIndex(String courseName)
    {
        for (int index = 0; index < courses.size(); index++)
        {
            if (courses.get(index).getName().equals(courseName))
                return index;
        }
        return -1;
    }
    public void enrollCourse(String courseName)
    {
        if (courseIndex(courseName) != -1)
            throw new RuntimeException("Student already enrolled in course.");
        courses.add(new Course(courseName));
    }
    public void dropCourse(String courseName)
    {
        int index = courseIndex(courseName);
        if(index == -1)
            throw new RuntimeException("Student already not enrolled in course.");
        courses.remove(index);
    }
    public int getNumCourses()
    {
        return courses.size();
    }
    public Course[] getCourses()
    {
        Course[] courseList = new Course[courses.size()];
        for(int index = 0; index < courseList.length; index++)
            courseList[index] = new Course(courses.get(index));
        return courseList;
    }
    public String[][] getCourseData()
    {
        if(courses.isEmpty())
            return new String[0][0];
        
        String[][] courseArray = new String[courses.size() + 1][mostGradesForCourses() + 1];
        courseArray[0][0] = "Course";
        for (int examNum = 1; examNum < courseArray[0].length; examNum++)
            courseArray[0][examNum] = "Exam " + examNum;
        
        double[] gradeList;
        for (int courseNum = 1; courseNum < courseArray.length; courseNum++)
        {
            courseArray[courseNum][0] = courses.get(courseNum - 1).getName();
            gradeList = courses.get(courseNum - 1).getGrades();
            for (int examNum = 1; examNum < gradeList.length + 1; examNum++)
                courseArray[courseNum][examNum] = String.valueOf(gradeList[examNum - 1]);
            
            for (int nullExamNum = gradeList.length + 1; nullExamNum < courseArray[courseNum].length; nullExamNum++)
                courseArray[courseNum][nullExamNum] = "-1.0";
        }
        
        return courseArray;
    }
    
    public void addGrade(String courseName, double grade)
    {
        int index = courseIndex(courseName);
        if (index == -1)
            throw new RuntimeException("Student not enrolled in course.");
        courses.get(index).addGrade(grade);
    }
    public void deleteGrade(String courseName, double grade)
    {
        int index = courseIndex(courseName);
        if (index == -1)
            throw new RuntimeException("Student not enrolled in course.");
        courses.get(index).deleteGrade(grade);
    }
    public int mostGradesForCourses()
    {
        int mostCourseGrades = 0;
        
        int oneCourseGrades;
        for(int index = 0; index < courses.size(); index++)
        {
            oneCourseGrades = courses.get(index).getNumGrades();
            if (oneCourseGrades > mostCourseGrades)
                mostCourseGrades = oneCourseGrades;
        }
        
        return mostCourseGrades;
    }
    
    
    
    
    
    
    // Currently only Average
    public double calculateGPA()
    {
        double gradeTotal = 0.0;
        int numGrades = 0;
        double averageGrade;
        double[] gradeList;
        
        for(int index = 0; index < courses.size(); index++)
        {
            gradeList = courses.get(index).getGrades();
            for (double grade : gradeList)
                gradeTotal += grade;
            numGrades += gradeList.length;
        }
        
        averageGrade = gradeTotal / numGrades;
        return averageGrade;
    }
}