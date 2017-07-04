/**
    StudentWindow displays the window for a student to view their information.
*/

package windows;

import windowRunners.StudentRunner;
import database.Student;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class StudentWindow extends JFrame implements ActionListener
{
    public static final int MINIMUM_WIDTH = 300;    // Minimum width
    public static final int MINIMUM_HEIGHT = 70;    // Minimum height
    public static final int ADD_WIDTH_FACTOR = 80;  // Width to add per grade
    public static final int ADD_HEIGHT_FACTOR = 17; // Height to add per course
    private StudentRunner sr;                       // Associated StudentRunner
    private Student student;                        // Associated student
    
    // Labels for the username and password.
    private JPanel infoPanel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    
    // Labels for course data.
    private JPanel coursePanel;
    private JLabel[][] courseLabels;
    
    // Buttons for actions.
    private JPanel buttonPanel;
    private JButton gpaButton;
    private JButton doneButton;
    
    /**
        Constructor.
    */
    public StudentWindow(StudentRunner sr, Student student)
    {
        // Setup basic window information.
        super(student.getName());
        this.sr = sr;
        this.student = student;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Add labels for the username and password.
        infoPanel = new JPanel(new GridLayout(1, 2));
        usernameLabel = new JLabel("ID: " + student.getStudentID());
        infoPanel.add(usernameLabel);
        passwordLabel = new JLabel("Password: " + student.getPassword());
        infoPanel.add(passwordLabel);
        add(infoPanel, BorderLayout.NORTH);
        
        // Setup the course labels.
        setupCourseData();
        
        // Add buttons for actions.
        buttonPanel = new JPanel(new FlowLayout());
        gpaButton = new JButton("GPA");
        gpaButton.addActionListener(this);
        buttonPanel.add(gpaButton);
        doneButton = new JButton("Done");
        doneButton.addActionListener(this);
        buttonPanel.add(doneButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
        setupCourseData initializes and adds the course labels.
    */
    private void setupCourseData()
    {
        // Create an array to hold course information as Strings.
        // If the array has no values, return an empty array.
        String[][] courseData = student.getCourseData();
        if(courseData.length == 0)
        {
            setSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
            setLocationRelativeTo(null);
            return;
        }
        
        // Create an array to hold the labels.
        coursePanel = new JPanel(new GridLayout(courseData.length, courseData[0].length, -1, -1));
        courseLabels = new JLabel[courseData.length][courseData[0].length];
        
        // Cycle through each course.
        for (int i = 0; i < courseData.length; i++)
        {
            // Cycle through the grades. If -1.0 is detected as a grade, create a blank label.
            for (int j = 0; j < courseData[i].length; j++)
            {
                if(!courseData[i][j].equals("-1.0"))
                    courseLabels[i][j] = new JLabel(courseData[i][j]);
                else
                    courseLabels[i][j] = new JLabel();
                courseLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                coursePanel.add(courseLabels[i][j]);
            }
        }
        
        // Add the panel and set the window size.
        add(coursePanel, BorderLayout.CENTER);
        setSize(MINIMUM_WIDTH + courseData[0].length*ADD_WIDTH_FACTOR, 75 + courseData.length*ADD_HEIGHT_FACTOR);
        setLocationRelativeTo(null);
    }
    
    /**
        actionPerformed chooses a StudentRunner action depending on the button clicked.
    */
    public void actionPerformed(ActionEvent e)
    {
        String action = e.getActionCommand();
        if(action.equals("Done"))
            sr.clickedDone();
        else if(action.equals("GPA"))
            sr.clickedGPA();
    }
}