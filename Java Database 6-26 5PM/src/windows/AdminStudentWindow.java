package windows;

import windowRunners.AdminStudentRunner;
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

public class AdminStudentWindow extends JFrame implements ActionListener
{
    public static final int MINIMUM_WIDTH = 300;
    public static final int MINIMUM_HEIGHT = 70;
    public static final int ADD_WIDTH_FACTOR = 80;
    public static final int ADD_HEIGHT_FACTOR = 17;
    private AdminStudentRunner asr;
    private Student student;
    
    private JPanel infoPanel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    
    private JPanel coursePanel;
    private JLabel[][] courseLabels;
    
    private JPanel buttonPanel;
    private JButton gpaButton;
    private JButton editButton;
    private JButton doneButton;
    
    public AdminStudentWindow(AdminStudentRunner asr, Student student)
    {
        super(student.getName());
        this.asr = asr;
        this.student = student;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        infoPanel = new JPanel(new GridLayout(1, 2));
        usernameLabel = new JLabel("ID: " + student.getStudentID());
        infoPanel.add(usernameLabel);
        passwordLabel = new JLabel("Password: " + student.getPassword());
        infoPanel.add(passwordLabel);
        add(infoPanel, BorderLayout.NORTH);
        
        setupCourseData();
        
        buttonPanel = new JPanel(new FlowLayout());
        gpaButton = new JButton("GPA");
        gpaButton.addActionListener(this);
        buttonPanel.add(gpaButton);
        editButton = new JButton("Edit");
        editButton.addActionListener(this);
        buttonPanel.add(editButton);
        doneButton = new JButton("Done");
        doneButton.addActionListener(this);
        buttonPanel.add(doneButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void setupCourseData()
    {
        String[][] courseData = student.getCourseData();
        if(courseData.length == 0)
        {
            setSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
            return;
        }
        
        coursePanel = new JPanel(new GridLayout(courseData.length, courseData[0].length, -1, -1));
        courseLabels = new JLabel[courseData.length][courseData[0].length];
        for (int i = 0; i < courseData.length; i++)
        {
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
        
        add(coursePanel, BorderLayout.CENTER);
        setSize(MINIMUM_WIDTH + courseData[0].length*ADD_WIDTH_FACTOR, 75 + courseData.length*ADD_HEIGHT_FACTOR);
        setLocationRelativeTo(null);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String action = e.getActionCommand();
        if(action.equals("GPA"))
            asr.clickedGPA();
        else if(action.equals("Edit"))
            asr.clickedEdit();
        else if(action.equals("Done"))
            asr.clickedDone();
    }
}