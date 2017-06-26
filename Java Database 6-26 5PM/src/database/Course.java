package database;

import java.util.ArrayList;

public class Course
{
    private String name;
    private ArrayList<Double> grades;
    
    public Course(String name)
    {
        setName(name);
        grades = new ArrayList();
    }
    public Course(Course course)
    {
        setName(course.getName());
        grades = new ArrayList();
        for (int index = 0; index < course.getNumGrades(); index++)
            grades.add(course.grades.get(index));
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    
    public void addGrade(double grade)
    {
        grades.add(grade);
    }
    public void deleteGrade(double grade)
    {
        for (int index = 0; index < grades.size(); index++)
        {
            if (Math.abs(grades.get(index) - grade) <= 0.0001)
            {
                grades.remove(index);
                return;
            }
        }
        
        throw new RuntimeException("Course already does not have grade.");
    }
    public int getNumGrades()
    {
        return grades.size();
    }
    public double[] getGrades()
    {
        double[] gradeList = new double[grades.size()];
        for (int index = 0; index < grades.size(); index++)
            gradeList[index] = grades.get(index);
        return gradeList;
    }
}