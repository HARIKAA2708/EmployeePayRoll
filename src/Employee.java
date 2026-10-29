/**
 * Represents one employee and their payroll details.
 * Fields are private (encapsulation); they are accessed through getters.
 */
public class Employee {

    private final int employeeId;
    private final String employeeName;
    private final double basicSalary;

    private double allowance;
    private double deduction;
    private double netSalary;
    private boolean payrollCalculated;

    public Employee(int employeeId, String employeeName, double basicSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.basicSalary = basicSalary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public double getAllowance() {
        return allowance;
    }

    public double getDeduction() {
        return deduction;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public boolean isPayrollCalculated() {
        return payrollCalculated;
    }

    /** Stores the calculated payroll values and marks payroll as done. */
    public void setPayrollDetails(double allowance, double deduction, double netSalary) {
        this.allowance = allowance;
        this.deduction = deduction;
        this.netSalary = netSalary;
        this.payrollCalculated = true;
    }
}
