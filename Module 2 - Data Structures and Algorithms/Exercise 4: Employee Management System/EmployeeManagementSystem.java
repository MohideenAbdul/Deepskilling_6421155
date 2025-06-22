import java.util.Scanner;

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager(100);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Employee\n2. Search Employee\n3. Traverse Employees\n4. Delete Employee\n5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.print("Enter Employee ID: ");
                String id = scanner.nextLine();
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Position: ");
                String position = scanner.nextLine();
                System.out.print("Enter Salary: ");
                double salary = scanner.nextDouble();
                scanner.nextLine();
                manager.addEmployee(new Employee(id, name, position, salary));
            } else if (option == 2) {
                System.out.print("Enter Employee ID to search: ");
                String id = scanner.nextLine();
                Employee emp = manager.searchEmployee(id);
                if (emp != null)
                    System.out.println("Found: " + emp.getEmployeeId() + ", " + emp.getName() + ", " + emp.getPosition() + ", " + emp.getSalary());
                else
                    System.out.println("Employee not found.");
            } else if (option == 3) {
                manager.traverseEmployees();
            } else if (option == 4) {
                System.out.print("Enter Employee ID to delete: ");
                String id = scanner.nextLine();
                boolean deleted = manager.deleteEmployee(id);
                if (deleted)
                    System.out.println("Employee deleted.");
                else
                    System.out.println("Employee not found.");
            } else if (option == 5) {
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
        // scanner.close(); // Not closed to avoid closing System.in
        System.out.println("Exiting Employee Management System.");
    }
}

class Employee {
    private String employeeId;
    private String name;
    private String position;
    private double salary;

    public Employee(String employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public double getSalary() { return salary; }
}

class EmployeeManager {
    private Employee[] employees;
    private int size;

    public EmployeeManager(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

    public void addEmployee(Employee emp) {
        if (size < employees.length) {
            employees[size++] = emp;
            System.out.println("Employee added.");
        } else {
            System.out.println("Employee array is full.");
        }
    }

    public Employee searchEmployee(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                return employees[i];
            }
        }
        return null;
    }

    public void traverseEmployees() {
        if (size == 0) {
            System.out.println("No employees to display.");
            return;
        }
        for (int i = 0; i < size; i++) {
            Employee e = employees[i];
            System.out.println(e.getEmployeeId() + ", " + e.getName() + ", " + e.getPosition() + ", " + e.getSalary());
        }
    }

    public boolean deleteEmployee(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }
}
