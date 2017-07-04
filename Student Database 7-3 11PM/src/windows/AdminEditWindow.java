/**
    AdminEditWindow displays the window for editing student data.
*/

package windows;

import database.Course;
import database.Student;
import windowRunners.AdminEditRunner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminEditWindow extends JFrame implements ActionListener
{
    public static final int WIDTH = 350;  // Width
    public static final int HEIGHT = 395; // Height
    private AdminEditRunner aer;          // Associated AdminEditRunner
    private Student student;              // Associated student
    
    // Labels and text fields for entering student information.
    private JPanel editPanel;
    private JLabel nameLabel;
    private JTextField nameBox;
    private JLabel passwordLabel;
    private JTextField passwordBox;
    private JTextArea courseLabel;
    private JTextArea courseBox;
    
    // Buttons for actions.
    private JPanel buttonPanel;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton cancelButton;
    
    /**
        Constructor.
    */
    public AdminEditWindow(AdminEditRunner aer, Student student)
    {
        // Setup basic window information.
        super("Edit Student " + student.getStudentID());
        this.aer = aer;
        this.student = student;
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Add labels and text fields for basic student information.
        editPanel = new JPanel(new GridLayout(6, 1));
        nameLabel = new JLabel("Name");
        nameBox = new JTextField(student.getName());
        passwordLabel = new JLabel("Password");
        passwordBox = new JTextField(student.getPassword());
        editPanel.add(nameLabel);
        editPanel.add(nameBox);
        editPanel.add(passwordLabel);
        editPanel.add(passwordBox);
        
        // Setup the label and text field for course information.
        setupCourseLabels();
        add(editPanel, BorderLayout.CENTER);
        
        // Add buttons for actions.
        buttonPanel = new JPanel(new FlowLayout());
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        buttonPanel.add(saveButton);
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        buttonPanel.add(deleteButton);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
        setupCourseLabels initializes and adds the text field for course information.
    */
    private void setupCourseLabels()
    {
        // Add the instruction label.
        courseLabel = new JTextArea("Courses and grades format:"
                                + "\nNameOfCourseCourse 99 86.7 89"
                                + "\nNameOfCourseCourse 87 92.5 100 85.3");
        courseLabel.setEditable(false);
        editPanel.add(courseLabel);
        
        // Create arrays and a StringBuilder to join together course information.
        StringBuilder sb = new StringBuilder();
        Course[] courseList = student.getCourses();
        double[] gradeList;
        
        // Cycle through each course.
        for(int courseIndex = 0; courseIndex < courseList.length; courseIndex++)
        {
            // Add the course name and each grade.
            sb.append(courseList[courseIndex].getName());
            gradeList = courseList[courseIndex].getGrades();
            for(int gradeIndex = 0; gradeIndex < gradeList.length; gradeIndex++)
                sb.append(" " + gradeList[gradeIndex]);
            
            if(courseIndex <= courseList.length - 1)
                sb.append("\n");
        }
        
        // Add the course text field with a scroll pane.
        courseBox = new JTextArea(sb.toString());
        JScrollPane scrollBar = new JScrollPane(courseBox);
        scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editPanel.add(scrollBar);
    }
    
    /**
        actionPerformed chooses an AdminEditRunner action depending on the button clicked.
    */
    public void actionPerformed(ActionEvent e)
    {
        String action = e.getActionCommand();
        if(action.equals("Save"))
            aer.clickedSave(nameBox.getText(), passwordBox.getText(), courseBox.getText());
        if(action.equals("Delete"))
            aer.clickedDelete();
        if(action.equals("Cancel"))
            aer.clickedCancel();
    }
}