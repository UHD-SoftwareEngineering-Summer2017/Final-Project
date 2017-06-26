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
    public static final int WIDTH = 350;
    public static final int HEIGHT = 395;
    private AdminEditRunner aer;
    private Student student;
    
    private JPanel editPanel;
    private JLabel nameLabel;
    private JTextField nameBox;
    private JLabel passwordLabel;
    private JTextField passwordBox;
    private JTextArea courseLabel;
    private JTextArea courseBox;
    
    private JPanel buttonPanel;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton cancelButton;
    
    public AdminEditWindow(AdminEditRunner aer, Student student)
    {
        super("Edit Student " + student.getStudentID());
        this.aer = aer;
        this.student = student;
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        editPanel = new JPanel(new GridLayout(6, 1));
        nameLabel = new JLabel("Name");
        nameBox = new JTextField(student.getName());
        passwordLabel = new JLabel("Password");
        passwordBox = new JTextField(student.getPassword());
        editPanel.add(nameLabel);
        editPanel.add(nameBox);
        editPanel.add(passwordLabel);
        editPanel.add(passwordBox);
        
        setupCourseLabels();
        add(editPanel, BorderLayout.CENTER);
        
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
    private void setupCourseLabels()
    {
        courseLabel = new JTextArea("Courses and grades format:"
                                + "\nCourse1 99 86.7 89"
                                + "\nCourse2 87 92.5 100 85.3");
        courseLabel.setEditable(false);
        editPanel.add(courseLabel);
        
        StringBuilder sb = new StringBuilder();
        Course[] courseList = student.getCourses();
        double[] gradeList;
        
        for(int courseIndex = 0; courseIndex < courseList.length; courseIndex++)
        {
            sb.append(courseList[courseIndex].getName());
            gradeList = courseList[courseIndex].getGrades();
            for(int gradeIndex = 0; gradeIndex < gradeList.length; gradeIndex++)
                sb.append(" " + gradeList[gradeIndex]);
            
            if(courseIndex <= courseList.length - 1)
                sb.append("\n");
        }
        
        courseBox = new JTextArea(sb.toString());
        JScrollPane scrollBar = new JScrollPane(courseBox);
        scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editPanel.add(scrollBar);
    }
    
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