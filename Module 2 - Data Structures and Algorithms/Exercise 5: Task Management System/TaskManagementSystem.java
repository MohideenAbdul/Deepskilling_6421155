import java.util.Scanner;

public class TaskManagementSystem {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Task\n2. Search Task\n3. Traverse Tasks\n4. Delete Task\n5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.print("Enter Task ID: ");
                String taskId = scanner.nextLine();
                System.out.print("Enter Task Name: ");
                String taskName = scanner.nextLine();
                System.out.print("Enter Status: ");
                String status = scanner.nextLine();
                taskList.addTask(new Task(taskId, taskName, status));
            } else if (option == 2) {
                System.out.print("Enter Task ID to search: ");
                String taskId = scanner.nextLine();
                Task task = taskList.searchTask(taskId);
                if (task != null)
                    System.out.println("Found: " + task.getTaskId() + ", " + task.getTaskName() + ", " + task.getStatus());
                else
                    System.out.println("Task not found.");
            } else if (option == 3) {
                taskList.traverseTasks();
            } else if (option == 4) {
                System.out.print("Enter Task ID to delete: ");
                String taskId = scanner.nextLine();
                boolean deleted = taskList.deleteTask(taskId);
                if (deleted)
                    System.out.println("Task deleted.");
                else
                    System.out.println("Task not found.");
            } else if (option == 5) {
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
        // scanner.close(); // Not closed to avoid closing System.in
        System.out.println("Exiting Task Management System.");
    }
}

class Task {
    private String taskId;
    private String taskName;
    private String status;

    public Task(String taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public String getTaskId() { return taskId; }
    public String getTaskName() { return taskName; }
    public String getStatus() { return status; }
}

class TaskNode {
    Task task;
    TaskNode next;

    public TaskNode(Task task) {
        this.task = task;
        this.next = null;
    }
}

class TaskLinkedList {
    private TaskNode head;

    public void addTask(Task task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Task added.");
    }

    public Task searchTask(String taskId) {
        TaskNode current = head;
        while (current != null) {
            if (current.task.getTaskId().equals(taskId)) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    public void traverseTasks() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }
        TaskNode current = head;
        while (current != null) {
            Task t = current.task;
            System.out.println(t.getTaskId() + ", " + t.getTaskName() + ", " + t.getStatus());
            current = current.next;
        }
    }

    public boolean deleteTask(String taskId) {
        if (head == null) return false;
        if (head.task.getTaskId().equals(taskId)) {
            head = head.next;
            return true;
        }
        TaskNode current = head;
        while (current.next != null) {
            if (current.next.task.getTaskId().equals(taskId)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
