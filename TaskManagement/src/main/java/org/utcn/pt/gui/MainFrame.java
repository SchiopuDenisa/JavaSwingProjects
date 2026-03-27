package org.utcn.pt.gui;

import org.utcn.pt.logic.TaskManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().createAndShowGUI());
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Task Management");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel navigationPanel = createNavigationPanel();
        frame.getContentPane().add(navigationPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createNavigationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalStrut(30));

        JPanel employeesPanel = new JPanel(new BorderLayout());
        JButton employeesButton = new JButton("Employees");
        employeesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openEmployeeForm();
            }
        });
        JLabel employeesLabel = new JLabel("Manage Employees", JLabel.CENTER);
        employeesPanel.add(employeesButton, BorderLayout.CENTER);
        employeesPanel.add(employeesLabel, BorderLayout.NORTH);

        panel.add(employeesPanel);
        panel.add(Box.createVerticalStrut(20));

        JPanel tasksPanel = new JPanel(new BorderLayout());
        JButton tasksButton = new JButton("Tasks");
        tasksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openTaskForm();
            }
        });
        JLabel tasksLabel = new JLabel("Manage Tasks", JLabel.CENTER);
        tasksPanel.add(tasksButton, BorderLayout.CENTER);
        tasksPanel.add(tasksLabel, BorderLayout.NORTH);

        panel.add(tasksPanel);
        panel.add(Box.createVerticalStrut(20));

        JPanel assignPanel = new JPanel(new BorderLayout());
        JButton assignButton = new JButton("Assign");
        assignButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAssignForm();
            }
        });
        JLabel assignLabel = new JLabel("Assign Tasks", JLabel.CENTER);
        assignPanel.add(assignButton, BorderLayout.CENTER);
        assignPanel.add(assignLabel, BorderLayout.NORTH);

        panel.add(assignPanel);
        panel.add(Box.createVerticalStrut(30));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel;
    }

    private void openEmployeeForm() {
        TaskManagement taskManagement = new TaskManagement();
        EmployeeForm employeeForm = new EmployeeForm(taskManagement);
        employeeForm.setVisible(true);
    }

    private void openTaskForm() {
        TaskManagement taskManagement = new TaskManagement();
        TaskForm taskForm = new TaskForm(taskManagement);
        taskForm.setVisible(true);
    }

    private void openAssignForm() {
        TaskManagement taskManagement = new TaskManagement();
        AssignForm assignForm = new AssignForm(taskManagement);
        assignForm.setVisible(true);
    }
}
