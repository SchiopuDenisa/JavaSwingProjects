package project.gui;

import project.entities.ComplexTask;
import project.entities.SimpleTask;
import project.entities.Task;
import project.logic.TaskManagement;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TaskForm extends JFrame {
    private TaskManagement taskManagement;
    private JTable allTasksTable;
    private JTable selectedTaskTable;
    private JTextField taskNameField;
    private JTextField startHourField;
    private JTextField endHourField;
    private AllTasksTableModel allTasksTableModel;
    private SelectedTaskTableModel selectedTaskTableModel;

    public TaskForm(TaskManagement taskManagement) {
        this.taskManagement = taskManagement;
        setTitle("Task Management");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setupTables();
        setupInputPanel();
        refreshAllTasksTable();
    }

    private void setupTables() {
        allTasksTableModel = new AllTasksTableModel();
        allTasksTable = new JTable(allTasksTableModel);

        allTasksTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && allTasksTable.getSelectedRow() != -1) {
                handleTaskSelection();
            }
        });

        selectedTaskTableModel = new SelectedTaskTableModel();
        selectedTaskTable = new JTable(selectedTaskTableModel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                createPanelWithTitle("All Tasks (Ctrl+Click to select multiple)", allTasksTable),
                createPanelWithTitle("Selected Task Details / Sub-tasks", selectedTaskTable));
        splitPane.setResizeWeight(0.6);
        add(splitPane, BorderLayout.CENTER);
    }

    private JPanel createPanelWithTitle(String title, JTable table) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(title), BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private void setupInputPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.add(new JLabel("Task Name:"));
        taskNameField = new JTextField();
        formPanel.add(taskNameField);
        formPanel.add(new JLabel("Start Hour (Simple only):"));
        startHourField = new JTextField();
        formPanel.add(startHourField);
        formPanel.add(new JLabel("End Hour (Simple only):"));
        endHourField = new JTextField();
        formPanel.add(endHourField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnAddSimple = new JButton("Add Simple");
        btnAddSimple.addActionListener(e -> addSimpleTask());
        JButton btnAddComplex = new JButton("Create Complex from Selected");
        btnAddComplex.addActionListener(e -> createComplexTask());
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> updateTask());
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> deleteTask());

        buttonPanel.add(btnAddSimple);
        buttonPanel.add(btnAddComplex);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);

        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void handleTaskSelection() {
        int[] selectedRows = allTasksTable.getSelectedRows();
        if (selectedRows.length == 1) {
            Task selectedTask = allTasksTableModel.getTaskAt(selectedRows[0]);
            taskNameField.setText(selectedTask.getName());

            if (selectedTask instanceof SimpleTask) {
                SimpleTask st = (SimpleTask) selectedTask;
                startHourField.setText(String.valueOf(st.getStartHour()));
                endHourField.setText(String.valueOf(st.getEndHour()));

                selectedTaskTableModel.setTask(selectedTask);
            } else if (selectedTask instanceof ComplexTask) {
                startHourField.setText("");
                endHourField.setText("");
                selectedTaskTableModel.setSubTasks(((ComplexTask) selectedTask).getSubTasks());
            }
        }
    }

    private void addSimpleTask() {
        try {
            taskManagement.addSimpleTask(taskNameField.getText(),
                    Integer.parseInt(startHourField.getText()), Integer.parseInt(endHourField.getText()));
            refreshAllTasksTable();
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid hours.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createComplexTask() {
        int[] selectedRows = allTasksTable.getSelectedRows();
        String name = taskNameField.getText().trim();

        if (selectedRows.length > 0 && !name.isEmpty()) {
            List<Integer> subtaskIds = new ArrayList<>();
            for (int row : selectedRows) {
                subtaskIds.add(allTasksTableModel.getTaskAt(row).getTaskId());
            }
            taskManagement.createMultiComplexTask(name, subtaskIds);
            refreshAllTasksTable();
            clearFields();
            JOptionPane.showMessageDialog(this, "Complex Task Created!");
        } else {
            JOptionPane.showMessageDialog(this, "Select tasks from the table and enter a name.");
        }
    }

    private void updateTask() {
        int selectedRow = allTasksTable.getSelectedRow();
        if (selectedRow >= 0) {
            int taskId = allTasksTableModel.getTaskAt(selectedRow).getTaskId();
            try {
                int start = startHourField.getText().isEmpty() ? 0 : Integer.parseInt(startHourField.getText());
                int end = endHourField.getText().isEmpty() ? 0 : Integer.parseInt(endHourField.getText());
                taskManagement.updateTask(taskId, taskNameField.getText(), start, end);
                refreshAllTasksTable();
                clearFields();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid hours format.");
            }
        }
    }

    private void deleteTask() {
        int selectedRow = allTasksTable.getSelectedRow();
        if (selectedRow >= 0) {
            int taskId = allTasksTableModel.getTaskAt(selectedRow).getTaskId();
            taskManagement.deleteTask(taskId);
            refreshAllTasksTable();
            clearFields();
        }
    }

    private void refreshAllTasksTable() {
        allTasksTableModel.setTasks(taskManagement.getAllTasks());
    }

    private void clearFields() {
        taskNameField.setText("");
        startHourField.setText("");
        endHourField.setText("");
    }

    private class AllTasksTableModel extends AbstractTableModel {
        private String[] columnNames = {"ID", "Name", "Type", "Status"};
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
                case 2 -> (t instanceof SimpleTask) ? "Simple" : "Complex";
                case 3 -> t.getStatus();
                default -> null;
            };
        }
    }

    private class SelectedTaskTableModel extends AbstractTableModel {
        private final String[] columnNames = {"ID", "Name", "Est. Duration"};
        private List<Task> tasks = new ArrayList<>();

        public void setTask(Task task) {
            this.tasks.clear();
            if (task != null) {
                this.tasks.add(task);
            }
            fireTableDataChanged();
        }

        public void setSubTasks(List<Task> subTasks) {
            this.tasks.clear();
            if (subTasks != null) {
                this.tasks.addAll(subTasks);
            }
            fireTableDataChanged();
        }

        @Override public int getRowCount() { return tasks.size(); }
        @Override public int getColumnCount() { return columnNames.length; }
        @Override public String getColumnName(int col) { return columnNames[col]; }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Task t = tasks.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> t.getTaskId();
                case 1 -> t.getName();
                case 2 -> t.estimateDuration();
                default -> null;
            };
        }
    }
}