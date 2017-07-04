/**
    The Student class holds account and course information about a student.
*/

package database;

import java.util.ArrayList;

public class Student
{
    private int studentID;             // Student ID# or username
    private String password;           // Student password
    private String name;               // Student name
    private ArrayList<Course> courses; // List of student's courses
    
    /**
        Normal constructor.
    */
    public Student(int studentID, String password, String name)
    {
        setStudentID(studentID);
        setPassword(password);
        setName(name);
        courses = new ArrayList();
    }
    
    /**
        Copy constructor.
    */
    public Student(Student student)
    {
        setStudentID(student.studentID);
        setPassword(student.getPassword());
        setName(student.getName());
        
        courses = new ArrayList();
        for (int index = 0; index < student.getNumCourses(); index++)
            courses.add(student.courses.get(index));
    }
    
    /**
        studentID Setter.
    */
    public void setStudentID(int studentID)
    {
        this.studentID = studentID;
    }
    
    /**
        password Setter.
    */
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    /**
        name Setter.
    */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
        studentID Getter.
    */
    public int getStudentID()
    {
        return studentID;
    }
    
    /**
        password Getter.
    */
    public String getPassword()
    {
        return password;
    }
    
    /**
        name Getter.
    */
    public String getName()
    {
        return name;
    }
    
    /**
        courseIndex returns the index that a course is stored in, or -1 if the
        course does not exist.
    */
    public int courseIndex(String courseName)
    {
        for (int index = 0; index < courses.size(); index++)
        {
            if (courses.get(index).getName().equals(courseName))
                return index;
        }
        return -1;
    }
    
    /**
        enrollCourse adds a course to the student.
    */
    public void enrollCourse(String courseName)
    {
        // Do not proceed if the student is already enrolled in the course.
        if (courseIndex(courseName) != -1)
            throw new RuntimeException("Student already enrolled in course.");
        
        // Add the course.
        courses.add(new Course(courseName));
    }
    
    /**
        dropCourse removes a course from the student.
    */
    public void dropCourse(String courseName)
    {
        // Do not proceed if the student is already not enrolled in the course.
        int index = courseIndex(courseName);
        if(index == -1)
            throw new RuntimeException("Student already not enrolled in course.");
        
        // Remove the course.
        courses.remove(index);
    }
    
    /**
        getNumCourses returns the number of courses that the student is in.
    */
    public int getNumCourses()
    {
        return courses.size();
    }
    
    /**
        getCourses returns copies of all the student's courses.
    */
    public Course[] getCourses()
    {
        Course[] courseList = new Course[courses.size()];
        for(int index = 0; index < courseList.length; index++)
            courseList[index] = new Course(courses.get(index));
        return courseList;
    }
    
    /**
        getCourseData returns a special array that lists the student's course information.
        The first row lists the column information, the first column lists the course names,
        and the other rows and columns list the courses' grades.
    */
    public String[][] getCourseData()
    {
        // If the student has no courses, return an empty array.
        if(courses.isEmpty())
            return new String[0][0];
        
        // Initialize the array.
        String[][] courseArray = new String[courses.size() + 1][mostGradesForCourses() + 1];
        courseArray[0][0] = "Course";
        for (int examNum = 1; examNum < courseArray[0].length; examNum++)
            courseArray[0][examNum] = "Exam " + examNum;
        
        // Add course grades.
        double[] gradeList;
        for (int courseNum = 1; courseNum < courseArray.length; courseNum++)
        {
            courseArray[courseNum][0] = courses.get(courseNum - 1).getName();
            gradeList = courses.get(courseNum - 1).getGrades();
            for (int examNum = 1; examNum < gradeList.length + 1; examNum++)
                courseArray[courseNum][examNum] = String.valueOf(gradeList[examNum - 1]);
            
            // Write "-1.0" for empty grades.
            for (int nullExamNum = gradeList.length + 1; nullExamNum < courseArray[courseNum].length; nullExamNum++)
                courseArray[courseNum][nullExamNum] = "-1.0";
        }
        
        return courseArray;
    }
    
    /**
        addGrade adds a grade to one of the student's courses.
    */
    public void addGrade(String courseName, double grade)
    {
        // Do not proceed if the course does not exist.
        int index = courseIndex(courseName);
        if (index == -1)
            throw new RuntimeException("Student not enrolled in course.");
        
        // Add the grade.
        courses.get(index).addGrade(grade);
    }
    
    /**
        removeGrade removes a grade from one of the student's courses.
    */
    public void deleteGrade(String courseName, double grade)
    {
        // Do not proceed if the course does not exist.
        int index = courseIndex(courseName);
        if (index == -1)
            throw new RuntimeException("Student not enrolled in course.");
        
        // Remove the grade.
        courses.get(index).deleteGrade(grade);
    }
    
    /**
        mostGradesForCourses returns the number of grades from the course with the most grades.
    */
    public int mostGradesForCourses()
    {
        int mostCourseGrades = 0;
        
        // Search through the courses and find the one with the most grades.
        int oneCourseGrades;
        for(int index = 0; index < courses.size(); index++)
        {
            oneCourseGrades = courses.get(index).getNumGrades();
            if (oneCourseGrades > mostCourseGrades)
                mostCourseGrades = oneCourseGrades;
        }
        
        return mostCourseGrades;
    }
    
    /**
        calculateGPA returns the student's GPA.
    */
    public double calculateGPA()
    {
        double gradeTotal = 0.0;
        int numGrades = 0;
        double averageGrade;
        double[] gradeList;
        
        // Add together all the grades and number of courses.
        for(int index = 0; index < courses.size(); index++)
        {
            gradeList = courses.get(index).getGrades();
            for (double grade : gradeList)
                gradeTotal += grade;
            numGrades += gradeList.length;
        }
        
        // Divide the grades by the number of courses to find the average.
        if(numGrades == 0)
            return 0.0;
        return gradeTotal / numGrades;
    }
}