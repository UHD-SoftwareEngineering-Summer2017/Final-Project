#pragma once
#include <string>
#include <list>
#include "Course.h"
using namespace std;

class Student
{
private:
	string name;
	int studentID;
	string password;
	list<Course> courses;

public:
	Student(string name = "", int studentID = 0, string password = "")
	{
		setName(name);
		setStudentID(studentID);
		setPassword(password);
	}
	void setName(string name)
	{
		this->name = name;
	}
	void setStudentID(int studentID)
	{
		this->studentID = studentID;
	}
	void setPassword(string password)
	{
		this->password = password;
	}
	string getName() const
	{
		return name;
	}
	int getStudentID() const
	{
		return studentID;
	}
	string getPassword() const
	{
		return password;
	}

	bool isEnrolled(int courseID) const
	{
		list<Course>::const_iterator itC;
		for (itC = courses.begin(); itC != courses.end(); itC++)
		{
			if (itC->getCourseID() == courseID)
				return true;
		}
		return false;
	}
	void enrollCourse(int courseID, string courseName = "", double grade = 0.0)
	{
		if (isEnrolled(courseID))
			throw new runtime_error("Student already enrolled in course.");
		courses.push_back(Course(courseID, courseName, grade));
	}
	void dropCourse(int courseID)
	{
		if (!isEnrolled(courseID))
			throw new runtime_error("Student already not enrolled in course.");
		courses.remove(courseID);
	}
	void setGrade(int courseID, double grade)
	{
		list<Course>::iterator itC;
		for (itC = courses.begin(); itC != courses.end(); itC++)
		{
			if (itC->getCourseID() == courseID)
			{
				itC->setGrade(grade);
				return;
			}
		}

		throw new runtime_error("Student not enrolled in course.");
	}
	double getGrade(int courseID) const
	{
		list<Course>::const_iterator itC;
		for (itC = courses.begin(); itC != courses.end(); itC++)
		{
			if (itC->getCourseID() == courseID)
				return itC->getGrade();
		}

		throw new runtime_error("Student not enrolled in course.");
	}
	
	// Note: not sure how to find GPA: this currently just returns the average
	double calculateGPA() const
	{
		list<Course>::const_iterator itC;
		double totalGrade = 0.0;

		for (itC = courses.begin(); itC != courses.end(); itC++)
			totalGrade += itC->getGrade();

		// Not sure on this step
		return totalGrade / courses.size();
	}
};