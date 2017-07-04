/*
    The Student Database program holds a small database of students in textfiles.
    User students can login and view their student and course information. User
    administrators can login and view every student's information, as well as
    add students, edit information, and delete students.

    Specific details while using the program:
    Usernames, or ID numbers, can only be digits 0-9 and must start with a digit 1-9.
    Course names cannot have spaces. Example: use "NameOfCourse" instead of "Name Of Course."
    The Students folder cannot have any files other than student files.

    The Main class starts the program with the login window.
*/

package database;

import windowRunners.LoginRunner;

public class Main
{
    public static void main(String[] args)
    {
        LoginRunner lr = new LoginRunner();
    }
}