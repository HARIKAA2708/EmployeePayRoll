# Employee Payroll Management System

## Introduction
A simple console-based Java mini project that calculates employee payroll. The user enters an employee's ID, name and basic salary; the program calculates allowance, deduction and net salary and displays the payroll details. Multiple employees can be stored during one run.

## Features
- Add multiple employees (stored in an `ArrayList<Employee>`)
- Automatic allowance, deduction and net salary calculation
- Formatted payroll display for every employee
- Input validation with retry (no crashes on wrong input)
- Simple menu-driven interface

## Technologies Used
- Java 17+ (core Java only)
- `Scanner` for console input
- `ArrayList` from the Java Collections Framework
- No external libraries or frameworks

## OOP Concepts Used
- **Class and Object** – `Employee` objects are created for each employee
- **Encapsulation** – private fields in `Employee`, accessed through public getters
- **Constructor** – `Employee(int, String, double)` initialises a new employee
- **Methods** – each task is a separate method
- **Access modifiers** – `private`, `public`
- **Separation of responsibilities** – data (`Employee`), logic (`PayrollCalculator`), user interface (`Main`)

## Project Structure
```
EmployeePayrollSystem/
├── src/
│   ├── Main.java
│   ├── Employee.java
│   └── PayrollCalculator.java
├── web/
│   ├── index.html
│   ├── style.css
│   └── script.js
├── README.md
└── screenshots/
```

## Salary Calculation
```
Allowance  = 20% of Basic Salary
Deduction  = 10% of Basic Salary
Net Salary = Basic Salary + Allowance - Deduction
```
Example: Basic ₹20,000 → Allowance ₹4,000, Deduction ₹2,000, Net ₹22,000.

## How to Run
```
cd EmployeePayrollSystem
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```
On Windows, if the ₹ symbol looks garbled, run `chcp 65001` first, or use `java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -cp out Main`.

### Web Frontend (optional)
Open `web/index.html` in any browser. It is a standalone visual version (HTML, CSS, JavaScript) that uses the same payroll logic. It is not connected to the Java program.

## Sample Output
```
========================================
     EMPLOYEE PAYROLL MANAGEMENT SYSTEM
========================================

1. Add Employee
2. Calculate Payroll
3. Display Employee Payroll
4. Exit

Enter your choice: 3

----------------------------------------
           PAYROLL DETAILS
----------------------------------------
Employee ID     : 101
Employee Name   : Ananya
Basic Salary    : ₹20,000.00
Allowance (20%) : ₹4,000.00
Deduction (10%) : ₹2,000.00
Net Salary      : ₹22,000.00
----------------------------------------
```

## Future Enhancements
- GUI using Java Swing or JavaFX
- MySQL database for permanent storage
- Employee search
- Update / delete employee
- Payslip generation
- Login system
