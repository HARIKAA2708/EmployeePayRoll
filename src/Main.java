import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Console menu for the Employee Payroll Management System.
 * Handles input and output only; calculations are done by PayrollCalculator.
 */
public class Main {

    private static final String LINE = "----------------------------------------";
    private static final String DOUBLE_LINE = "========================================";

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Employee> employees = new ArrayList<>();
    private static final PayrollCalculator calculator = new PayrollCalculator();

    public static void main(String[] args) {
        try {
            runMenu();
        } catch (NoSuchElementException e) {
            System.out.println("\nInput ended. Exiting program.");
        }
    }

    private static void runMenu() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> calculatePayroll();
                case 3 -> displayAllPayroll();
                case 4 -> {
                    System.out.println("\nThank you for using the Employee Payroll Management System!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please enter a number from 1 to 4.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n" + DOUBLE_LINE);
        System.out.println("     EMPLOYEE PAYROLL MANAGEMENT SYSTEM");
        System.out.println(DOUBLE_LINE);
        System.out.println("\n1. Add Employee");
        System.out.println("2. Calculate Payroll");
        System.out.println("3. Display Employee Payroll");
        System.out.println("4. Exit\n");
    }

    // ---------- Menu actions ----------

    private static void addEmployee() {
        int id = readEmployeeId();
        String name = readName();
        double salary = readSalary();

        employees.add(new Employee(id, name, salary));
        System.out.println("Employee added successfully.");
    }

    private static void calculatePayroll() {
        if (employees.isEmpty()) {
            System.out.println("No employees found. Please add an employee first.");
            return;
        }
        for (Employee employee : employees) {
            calculator.processPayroll(employee);
        }
        System.out.println("Payroll calculated for " + employees.size() + " employee(s).");
    }

    private static void displayAllPayroll() {
        if (employees.isEmpty()) {
            System.out.println("No employees found. Please add an employee first.");
            return;
        }
        for (Employee employee : employees) {
            if (employee.isPayrollCalculated()) {
                printPayroll(employee);
            } else {
                System.out.println("\nPayroll not calculated yet for Employee ID "
                        + employee.getEmployeeId() + " (" + employee.getEmployeeName()
                        + "). Choose option 2 first.");
            }
        }
    }

    private static void printPayroll(Employee employee) {
        System.out.println("\n" + LINE);
        System.out.println("           PAYROLL DETAILS");
        System.out.println(LINE);
        System.out.printf("Employee ID     : %d%n", employee.getEmployeeId());
        System.out.printf("Employee Name   : %s%n", employee.getEmployeeName());
        System.out.printf("Basic Salary    : %s%n", formatMoney(employee.getBasicSalary()));
        System.out.printf("Allowance (%d%%) : %s%n", PayrollCalculator.ALLOWANCE_PERCENT,
                formatMoney(employee.getAllowance()));
        System.out.printf("Deduction (%d%%) : %s%n", PayrollCalculator.DEDUCTION_PERCENT,
                formatMoney(employee.getDeduction()));
        System.out.printf("Net Salary      : %s%n", formatMoney(employee.getNetSalary()));
        System.out.println(LINE);
    }

    private static String formatMoney(double amount) {
        return String.format("₹%,.2f", amount);
    }

    // ---------- Input helpers (each one repeats until the input is valid) ----------

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static int readEmployeeId() {
        while (true) {
            int id = readInt("Enter Employee ID: ");
            if (id <= 0) {
                System.out.println("Employee ID must be greater than 0.");
            } else if (idExists(id)) {
                System.out.println("An employee with this ID already exists.");
            } else {
                return id;
            }
        }
    }

    private static String readName() {
        while (true) {
            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                return name;
            }
            System.out.println("Name cannot be empty.");
        }
    }

    private static double readSalary() {
        while (true) {
            System.out.print("Enter Basic Salary: ");
            String input = scanner.nextLine().trim();
            try {
                double salary = Double.parseDouble(input);
                if (Double.isFinite(salary) && salary > 0) {
                    return salary;
                }
                System.out.println("Salary must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (for example 20000).");
            }
        }
    }

    private static boolean idExists(int id) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == id) {
                return true;
            }
        }
        return false;
    }
}
