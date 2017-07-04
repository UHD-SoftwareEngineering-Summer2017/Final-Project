/**
    The Course class holds information about a student's course.
*/

package database;

import java.util.ArrayList;

public class Course
{
    private String name;              // Course name
    private ArrayList<Double> grades; // List of grades
    
    /**
        Normal constructor.
    */
    public Course(String name)
    {
        setName(name);
        grades = new ArrayList();
    }
    
    /**
        Copy Constructor.
    */
    public Course(Course course)
    {
        setName(course.getName());
        grades = new ArrayList();
        for (int index = 0; index < course.getNumGrades(); index++)
            grades.add(course.grades.get(index));
    }
    
    /**
        name Setter.
    */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
        name Getter.
    */
    public String getName()
    {
        return name;
    }
    
    /**
        addGrade adds a grade to the course.
    */
    public void addGrade(double grade)
    {
        grades.add(grade);
    }
    
    /**
        deleteGrade deletes a grade from the course.
    */
    public void deleteGrade(double grade)
    {
        // Find and delete the grade.
        for (int index = 0; index < grades.size(); index++)
        {
            if (Math.abs(grades.get(index) - grade) <= 0.0001)
            {
                grades.remove(index);
                return;
            }
        }
        
        // Throw an exception if the grade already does not exist.
        throw new RuntimeException("Course already does not have grade.");
    }
    
    /**
        getNumGrades returns the number of grades.
    */
    public int getNumGrades()
    {
        return grades.size();
    }
    
    /**
        getGrades returns an array of the grades.
    */
    public double[] getGrades()
    {
        double[] gradeList = new double[grades.size()];
        for (int index = 0; index < grades.size(); index++)
            gradeList[index] = grades.get(index);
        return gradeList;
    }
}