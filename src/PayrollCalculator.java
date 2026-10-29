/**
 * Contains all salary calculation logic.
 * Formula: Net Salary = Basic Salary + Allowance - Deduction
 */
public class PayrollCalculator {

    public static final int ALLOWANCE_PERCENT = 20;
    public static final int DEDUCTION_PERCENT = 10;

    private static final double PERCENT_DIVISOR = 100.0;

    public double calculateAllowance(double basicSalary) {
        return basicSalary * ALLOWANCE_PERCENT / PERCENT_DIVISOR;
    }

    public double calculateDeduction(double basicSalary) {
        return basicSalary * DEDUCTION_PERCENT / PERCENT_DIVISOR;
    }

    public double calculateNetSalary(double basicSalary, double allowance, double deduction) {
        return basicSalary + allowance - deduction;
    }

    /** Runs all three calculations for an employee and saves the results in it. */
    public void processPayroll(Employee employee) {
        double basic = employee.getBasicSalary();
        double allowance = calculateAllowance(basic);
        double deduction = calculateDeduction(basic);
        double netSalary = calculateNetSalary(basic, allowance, deduction);
        employee.setPayrollDetails(allowance, deduction, netSalary);
    }
}
