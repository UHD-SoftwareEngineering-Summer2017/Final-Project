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
    public static final int WIDTH = 300;
    public static final int MINIMUM_HEIGHT = 70;
    public static final int ADD_HEIGHT_FACTOR = 21;
    private AdminRunner ar;
    private Student[] students;
    
    private JLabel viewStudentLabel;
    private JPanel studentPanel;
    private JButton[][] studentButtons;
    private JPanel buttonPanel;
    private JButton addButton;
    private JButton doneButton;
    
    public AdminWindow(AdminRunner ar, Student[] students)
    {
        super("Administrator Directory");
        this.ar = ar;
        this.students = students;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        viewStudentLabel = new JLabel("Click student to view details.");
        add(viewStudentLabel, BorderLayout.NORTH);
        
        setupStudentData();
        
        buttonPanel = new JPanel(new GridLayout(1, 2));
        addButton = new JButton("Add Student");
        addButton.addActionListener(this);
        buttonPanel.add(addButton);
        doneButton = new JButton("Done");
        doneButton.addActionListener(this);
        buttonPanel.add(doneButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void setupStudentData()
    {
        studentPanel = new JPanel(new GridLayout(students.length, 2, -1, -1));
        studentButtons = new JButton[students.length][2];
        
        for (int index = 0; index < students.length; index++)
        {
            studentButtons[index][0] = new JButton(String.valueOf(students[index].getStudentID()));
            studentButtons[index][0].addActionListener(this);
            studentButtons[index][0].setActionCommand(String.valueOf(index));
            studentPanel.add(studentButtons[index][0]);
            
            studentButtons[index][1] = new JButton(students[index].getName());
            studentButtons[index][1].addActionListener(this);
            studentButtons[index][1].setActionCommand(String.valueOf(index));
            studentPanel.add(studentButtons[index][1]);
        }
        
        add(studentPanel, BorderLayout.CENTER);
        setSize(WIDTH, MINIMUM_HEIGHT + students.length*ADD_HEIGHT_FACTOR);
        setLocationRelativeTo(null);
    }
    
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