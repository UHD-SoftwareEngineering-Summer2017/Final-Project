#pragma once
#include <string>
using namespace std;

class Course
{
private:
	int courseID;
	string name;
	double grade;

public:
	Course(int courseID = 0, string name = "", double grade = 0.0)
	{
		setCourseID(courseID);
		setName(name);
		setGrade(grade);
	}
	void setCourseID(int courseID)
	{
		this->courseID = courseID;
	}
	void setName(string name)
	{
		this->name = name;
	}
	void setGrade(double grade)
	{
		this->grade = grade;
	}
	int getCourseID() const
	{
		return courseID;
	}
	string getName() const
	{
		return name;
	}
	int getGrade() const
	{
		return grade;
	}
};