/**
    AdminWindow displays the window for viewing all the students.
*/

package windows;

import windowRunners.AdminRunner;
import database.Student;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminWindow extends JFrame implements ActionListener
{
    public static final int WIDTH = 300;            // Width
    public static final int MINIMUM_HEIGHT = 70;    // Minimum height
    public static final int ADD_HEIGHT_FACTOR = 21; // Height to add per student
    private AdminRunner ar;                         // Associated AdminRunner
    private Student[] students;                     // List of students
    
    // Labels and buttons for the students.
    private JLabel viewStudentLabel;
    private JPanel studentPanel;
    private JButton[][] studentButtons;
    private JPanel buttonPanel;
    
    // Buttons for actions.
    private JButton addButton;
    private JButton doneButton;
    
    /**
        Constructor.
    */
    public AdminWindow(AdminRunner ar, Student[] students)
    {
        // Setup basic window information.
        super("Administrator Directory");
        this.ar = ar;
        this.students = students;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Setup the buttons for students.
        setupStudentData();
        
        // Add buttons for actions.
        buttonPanel = new JPanel(new GridLayout(1, 2));
        addButton = new JButton("Add Student");
        addButton.addActionListener(this);
        buttonPanel.add(addButton);
        doneButton = new JButton("Done");
        doneButton.addActionListener(this);
        buttonPanel.add(doneButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
        setupStudentData initializes and adds buttons that hold student data.
    */
    private void setupStudentData()
    {
        // Add the instruction label.
        viewStudentLabel = new JLabel("Click student to view details.");
        add(viewStudentLabel, BorderLayout.NORTH);
        
        // Create an array to hold the buttons.
        studentPanel = new JPanel(new GridLayout(students.length, 2, -1, -1));
        studentButtons = new JButton[students.length][2];
        
        // Cycle through the list of students.
        for (int index = 0; index < students.length; index++)
        {
            // Add the student ID.
            studentButtons[index][0] = new JButton(String.valueOf(students[index].getStudentID()));
            studentButtons[index][0].addActionListener(this);
            studentButtons[index][0].setActionCommand(String.valueOf(index));
            studentPanel.add(studentButtons[index][0]);
            
            // Add the student name.
            studentButtons[index][1] = new JButton(students[index].getName());
            studentButtons[index][1].addActionListener(this);
            studentButtons[index][1].setActionCommand(String.valueOf(index));
            studentPanel.add(studentButtons[index][1]);
        }
        
        // Add the panel and set the window size.
        add(studentPanel, BorderLayout.CENTER);
        setSize(WIDTH, MINIMUM_HEIGHT + students.length*ADD_HEIGHT_FACTOR);
        setLocationRelativeTo(null);
    }
    
    /**
        actionPerformed chooses an AdminRunner action depending on the button clicked.
    */
    public void actionPerformed(ActionEvent e)
    {
        String action = e.getActionCommand();
        if(action.equals("Add Student"))
            ar.clickedAdd();
        else if(action.equals("Done"))
            ar.clickedDone();
        else
            ar.clickedStudent(Integer.parseInt(action));
    }
}