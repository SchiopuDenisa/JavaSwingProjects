package project.gui;

import project.entities.Employee;
import project.entities.Task;
import project.logic.TaskManagement;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AssignForm extends JFrame {
    private TaskManagement taskManagement;
    private JComboBox<EmployeeWrapper> employeeComboBox;
    private JTable allTasksTable;
    private JTable assignedTasksTable;
    private TaskTableModel allTasksModel;
    private TaskTableModel assignedTasksModel;

    public AssignForm(TaskManagement taskManagement) {
        this.taskManagement = taskManagement;
        setTitle("Assign Tasks & Manage Status");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        setupTopPanel();
        setupTables();
        setupBottomPanel();

        refreshAllTasksTable();
        refreshAssignedTasksTable();
    }


    private void setupTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Select Employee: "));

        employeeComboBox = new JComboBox<>();
        for (Employee emp : taskManagement.getAllEmployees()) {
            employeeComboBox.addItem(new EmployeeWrapper(emp));
        }

        employeeComboBox.addActionListener(e -> refreshAssignedTasksTable());
        topPanel.add(employeeComboBox);
        add(topPanel, BorderLayout.NORTH);
    }

    private void setupTables() {
        allTasksModel = new TaskTableModel();
        allTasksTable = new JTable(allTasksModel);
        allTasksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        assignedTasksModel = new TaskTableModel();
        assignedTasksTable = new JTable(assignedTasksModel);
        assignedTasksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                createPanelWithTitle("All Available Tasks", allTasksTable),
                createPanelWithTitle("Tasks Assigned to Employee", assignedTasksTable));
        splitPane.setResizeWeight(0.5);
        add(splitPane, BorderLayout.CENTER);
    }

    private JPanel createPanelWithTitle(String title, JTable table) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(title), BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private void setupBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton btnAssign = new JButton("Assign Selected Task ->");
        btnAssign.addActionListener(e -> assignTask());

        JButton btnToggleStatus = new JButton("Toggle Status (Assigned Task)");
        btnToggleStatus.addActionListener(e -> toggleStatus());

        bottomPanel.add(btnAssign);
        bottomPanel.add(btnToggleStatus);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void assignTask() {
        EmployeeWrapper selectedEmp = (EmployeeWrapper) employeeComboBox.getSelectedItem();
        int selectedTaskRow = allTasksTable.getSelectedRow();

        if (selectedEmp != null && selectedTaskRow >= 0) {
            Task taskToAssign = allTasksModel.getTaskAt(selectedTaskRow);
            taskManagement.assignTaskToEmployee(selectedEmp.employee.getEmpId(), taskToAssign);
            refreshAssignedTasksTable();
        } else {
            JOptionPane.showMessageDialog(this, "Select an employee and a task from the left table.");
        }
    }

    private void toggleStatus() {
        EmployeeWrapper selectedEmp = (EmployeeWrapper) employeeComboBox.getSelectedItem();
        int selectedAssignedRow = assignedTasksTable.getSelectedRow();

        if (selectedEmp != null && selectedAssignedRow >= 0) {
            int taskId = assignedTasksModel.getTaskAt(selectedAssignedRow).getTaskId();
            taskManagement.modifyTaskStatus(selectedEmp.employee.getEmpId(), taskId);

            refreshAssignedTasksTable();
            refreshAllTasksTable();
        } else {
            JOptionPane.showMessageDialog(this, "Select a task from the right table to toggle.");
        }
    }

    private void refreshAllTasksTable() {
        allTasksModel.setTasks(taskManagement.getAllTasks());
    }

    private void refreshAssignedTasksTable() {
        EmployeeWrapper selectedEmp = (EmployeeWrapper) employeeComboBox.getSelectedItem();
        if (selectedEmp != null) {
            List<Task> tasks = taskManagement.getTasksForEmployee(selectedEmp.employee.getEmpId());
            assignedTasksModel.setTasks(tasks);
        } else {
            assignedTasksModel.setTasks(new ArrayList<>());
        }
    }

    private class EmployeeWrapper {
        Employee employee;
        public EmployeeWrapper(Employee employee) { this.employee = employee; }
        @Override public String toString() { return employee.getEmpId() + " - " + employee.getName(); }
    }

    private class TaskTableModel extends AbstractTableModel {
        private String[] columnNames = {"ID", "Name", "Status"};
        private List<Task> tasks = new ArrayList<>();

        public void setTasks(List<Task> tasks) {
            this.tasks = tasks;
            fireTableDataChanged();
        }

        public Task getTaskAt(int row) { return tasks.get(row); }
        @Override public int getRowCount() { return tasks.size(); }
        @Override public int getColumnCount() { return columnNames.length; }
        @Override public String getColumnName(int col) { return columnNames[col]; }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Task t = tasks.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> t.getTaskId();
                case 1 -> t.getName();
                case 2 -> t.getStatus();
                default -> null;
            };
        }
    }
}