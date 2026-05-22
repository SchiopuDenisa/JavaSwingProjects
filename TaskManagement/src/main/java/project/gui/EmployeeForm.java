package project.gui;

import project.entities.Employee;
import project.logic.TaskManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EmployeeForm extends JFrame {
    private TaskManagement taskManagement;
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private JTextField employeeNameField;

    public EmployeeForm(TaskManagement taskManagement) {
        this.taskManagement = taskManagement;

        setTitle("Employee Management");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"ID", "Name"}, 0);
        employeeTable = new JTable(tableModel);
        add(new JScrollPane(employeeTable), BorderLayout.CENTER);

        employeeTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow >= 0) {
                employeeNameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
            }
        });

        setupInputPanel();
        refreshEmployeeTable();
    }

    private void setupInputPanel() {
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Employee Name:"));
        employeeNameField = new JTextField(15);
        inputPanel.add(employeeNameField);

        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(e -> addNewEmployee());
        inputPanel.add(addBtn);

        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener(e -> updateEmployee());
        inputPanel.add(updateBtn);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(e -> deleteEmployee());
        inputPanel.add(deleteBtn);

        add(inputPanel, BorderLayout.SOUTH);
    }

    private void refreshEmployeeTable() {
        List<Employee> employees = taskManagement.getAllEmployees();
        tableModel.setRowCount(0);
        for (Employee employee : employees) {
            tableModel.addRow(new Object[]{employee.getEmpId(), employee.getName()});
        }
    }

    private void addNewEmployee() {
        String name = employeeNameField.getText().trim();
        if (!name.isEmpty()) {
            taskManagement.addEmployee(name);
            refreshEmployeeTable();
            employeeNameField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid name.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        String newName = employeeNameField.getText().trim();

        if (selectedRow >= 0 && !newName.isEmpty()) {
            int empId = (int) tableModel.getValueAt(selectedRow, 0);
            taskManagement.updateEmployee(empId, newName);
            refreshEmployeeTable();
            employeeNameField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Select an employee and enter a valid name.");
        }
    }

    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            int empId = (int) tableModel.getValueAt(selectedRow, 0);
            taskManagement.deleteEmployee(empId);
            refreshEmployeeTable();
            employeeNameField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Select an employee to delete.");
        }
    }
}