package project.logic;


import project.daccess.Data_management;
import project.entities.ComplexTask;
import project.entities.Employee;
import project.entities.SimpleTask;
import project.entities.Task;


import java.io.*;
import java.util.*;

public class TaskManagement implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Map<Employee, List<Task>> employeeTasks;
    private final List<Task> tasks;

    private static final String EMPLOYEE_FILE = "employee_data.ser";
    private static final String TASK_FILE = "tasks_data.ser";

    public TaskManagement() {
        this.employeeTasks = loadEmployeeData();
        this.tasks = loadTasksData();
    }

    private Map<Employee, List<Task>> loadEmployeeData() {
        TaskManagement deserializedData = Data_management.deserialize(EMPLOYEE_FILE);
        if (deserializedData != null) {
            return deserializedData.employeeTasks;
        }
        return new HashMap<>();
    }

    public List<Task> loadTasksData() {
        List<Task> tasks = Data_management.deserialize(TASK_FILE);
        return tasks != null ? tasks : new ArrayList<>();
    }

    public int generateNextTaskId() {
        int nextId = 1;
        for (Task task : tasks) {
            if (task.getTaskId() >= nextId) {
                nextId = task.getTaskId() + 1;
            }
        }
        return nextId;
    }

    public int generateNextEmployeeId() {
        int nextId = 1;
        for (Employee employee : employeeTasks.keySet()) {
            if (employee.getEmpId() >= nextId) {
                nextId = employee.getEmpId() + 1;
            }
        }
        return nextId;
    }

    private void saveEmployeeData() {
        Data_management.serialize(this, EMPLOYEE_FILE);
    }

    private void saveTasksData() {
        Data_management.serialize(tasks, TASK_FILE);
    }


    public void addEmployee(String name) {
        Employee newEmployee = new Employee(generateNextEmployeeId(), name);
        employeeTasks.put(newEmployee, new ArrayList<>());
        saveEmployeeData();
    }

    public void updateEmployee(int empId, String newName) {
        for (Employee employee : employeeTasks.keySet()) {
            if (employee.getEmpId() == empId) {
                employee.setName(newName);
                saveEmployeeData();
                break;
            }
        }
    }

    public void deleteEmployee(int empId) {
        Employee toRemove = null;
        for (Employee employee : employeeTasks.keySet()) {
            if (employee.getEmpId() == empId) {
                toRemove = employee;
                break;
            }
        }
        if (toRemove != null) {
            employeeTasks.remove(toRemove);
            saveEmployeeData();
        }
    }


    private Task getTaskById(int task_id) {
        for (Task task : tasks) {
            if (task.getTaskId() == task_id) {
                return task;
            }
        }
        return null;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : employeeTasks.keySet()) {
            employees.add(employee);
        }
        return employees;
    }

    public List<Task> getTasksForEmployee(int idEmployee) {
        for (Map.Entry<Employee, List<Task>> entry : employeeTasks.entrySet()) {
            if (entry.getKey().getEmpId() == idEmployee) {
                return entry.getValue();
            }
        }
        return new ArrayList<>();
    }


    public void addSimpleTask(String name, int start, int end) {
        SimpleTask simpleTask = new SimpleTask(generateNextTaskId(), name, start, end);
        tasks.add(simpleTask);
        saveTasksData();
    }

    public void createMultiComplexTask(String name, List<Integer> subtaskIds) {
        ComplexTask complexTask = new ComplexTask(generateNextTaskId(), name);
        for (Integer id : subtaskIds) {
            Task subTask = getTaskById(id);
            if (subTask != null) {
                complexTask.addTask(subTask);
            }
        }
        tasks.add(complexTask);
        saveTasksData();
    }

    public void updateTask(int taskId, String newName, int startHour, int endHour) {
        Task task = getTaskById(taskId);
        if (task != null) {
            task.setName(newName);
            if (task instanceof SimpleTask) {
                ((SimpleTask) task).setStartHour(startHour);
                ((SimpleTask) task).setEndHour(endHour);
            }
            saveTasksData();
        }
    }

    public void deleteTask(int taskId) {
        Task toRemove = getTaskById(taskId);
        if (toRemove != null) {
            tasks.remove(toRemove);

            for (Task t : tasks) {
                if (t instanceof ComplexTask) {
                    ((ComplexTask) t).deleteTask(toRemove);
                }
            }
            for (List<Task> assignedTasks : employeeTasks.values()) {
                assignedTasks.removeIf(t -> t.getTaskId() == taskId);
            }
            saveTasksData();
            saveEmployeeData();
        }
    }


    public void modifyTaskStatus(int idEmployee, int idTask) {
        String newStatus = null;
        for (Map.Entry<Employee, List<Task>> entry : employeeTasks.entrySet()) {
            if (entry.getKey().getEmpId() == idEmployee) {
                for (Task task : entry.getValue()) {
                    if (task.getTaskId() == idTask) {
                        newStatus = "Completed".equals(task.getStatus()) ? "Uncompleted" : "Completed";
                        task.setStatus(newStatus);
                        break;
                    }
                }
            }
        }
        if (newStatus != null) {
            for (Task task : tasks) {
                if (task.getTaskId() == idTask) {
                    task.setStatus(newStatus);
                    break;
                }
            }
            saveEmployeeData();
            saveTasksData();
        }
    }

    public void assignTaskToEmployee(int idEmployee, Task task) {
        for (Employee employee : employeeTasks.keySet()) {
            if (employee.getEmpId() == idEmployee) {
                employeeTasks.get(employee).add(task);
                saveEmployeeData();
                break;
            }
        }
    }
}

