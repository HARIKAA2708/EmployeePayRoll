// Same logic as the Java version: Employee, PayrollCalculator, and the UI (this file's functions).

const ALLOWANCE_PERCENT = 20;
const DEDUCTION_PERCENT = 10;

class Employee {
  constructor(id, name, basicSalary) {
    this.id = id;
    this.name = name;
    this.basicSalary = basicSalary;
    this.allowance = 0;
    this.deduction = 0;
    this.netSalary = 0;
    this.calculated = false;
  }
}

class PayrollCalculator {
  calculateAllowance(basic) { return (basic * ALLOWANCE_PERCENT) / 100; }
  calculateDeduction(basic) { return (basic * DEDUCTION_PERCENT) / 100; }
  calculateNetSalary(basic, allowance, deduction) { return basic + allowance - deduction; }

  processPayroll(employee) {
    employee.allowance = this.calculateAllowance(employee.basicSalary);
    employee.deduction = this.calculateDeduction(employee.basicSalary);
    employee.netSalary = this.calculateNetSalary(
      employee.basicSalary, employee.allowance, employee.deduction);
    employee.calculated = true;
  }
}

const employees = [];
const calculator = new PayrollCalculator();
const formatMoney = (n) => "₹" + n.toLocaleString("en-IN", { minimumFractionDigits: 2, maximumFractionDigits: 2 });
const $ = (id) => document.getElementById(id);

function showToast(message) {
  const toast = $("toast");
  toast.textContent = message;
  toast.classList.add("show");
  setTimeout(() => toast.classList.remove("show"), 2200);
}

function setError(inputId, errorId, message) {
  $(errorId).textContent = message;
  $(inputId).classList.toggle("invalid", message !== "");
  return message === "";
}

function validateForm() {
  const idText = $("empId").value.trim();
  const id = Number(idText);
  const name = $("empName").value.trim();
  const salary = Number($("empSalary").value);

  let idMsg = "";
  if (idText === "" || !Number.isInteger(id) || id <= 0) idMsg = "ID must be a whole number greater than 0.";
  else if (employees.some((e) => e.id === id)) idMsg = "This ID already exists.";

  const nameMsg = name === "" ? "Name cannot be empty." : "";
  const salaryMsg = $("empSalary").value.trim() === "" || !Number.isFinite(salary) || salary <= 0
    ? "Salary must be greater than 0." : "";

  const idOk = setError("empId", "idError", idMsg);
  const nameOk = setError("empName", "nameError", nameMsg);
  const salaryOk = setError("empSalary", "salaryError", salaryMsg);
  return idOk && nameOk && salaryOk ? { id, name, salary } : null;
}

function addEmployee() {
  const data = validateForm();
  if (!data) return;
  employees.push(new Employee(data.id, data.name, data.salary));
  ["empId", "empName", "empSalary"].forEach((id) => ($(id).value = ""));
  render();
  showToast("Employee added successfully.");
}

function calculatePayroll() {
  if (employees.length === 0) return showToast("Please add an employee first.");
  employees.forEach((e) => calculator.processPayroll(e));
  render();
  showToast(`Payroll calculated for ${employees.length} employee(s).`);
}

function makeCell(text, className) {
  const td = document.createElement("td");
  td.textContent = text;
  if (className) td.className = className;
  return td;
}

function render() {
  const body = $("tableBody");
  body.innerHTML = "";

  if (employees.length === 0) {
    const row = document.createElement("tr");
    const cell = makeCell("No employees yet. Add one above.", "empty");
    cell.colSpan = 7;
    row.appendChild(cell);
    body.appendChild(row);
  }

  employees.forEach((e) => {
    const row = document.createElement("tr");
    row.append(
      makeCell(e.id),
      makeCell(e.name),
      makeCell(formatMoney(e.basicSalary)),
      makeCell(e.calculated ? formatMoney(e.allowance) : "-"),
      makeCell(e.calculated ? formatMoney(e.deduction) : "-"),
      makeCell(e.calculated ? formatMoney(e.netSalary) : "-", "net")
    );
    const status = document.createElement("td");
    const badge = document.createElement("span");
    badge.className = "badge " + (e.calculated ? "done" : "pending");
    badge.textContent = e.calculated ? "Calculated" : "Pending";
    status.appendChild(badge);
    row.appendChild(status);
    body.appendChild(row);
  });

  $("statCount").textContent = employees.length;
  $("statBasic").textContent = formatMoney(employees.reduce((sum, e) => sum + e.basicSalary, 0));
  $("statNet").textContent = formatMoney(employees.reduce((sum, e) => sum + e.netSalary, 0));
}

$("addBtn").addEventListener("click", addEmployee);
$("calcBtn").addEventListener("click", calculatePayroll);
document.querySelectorAll("input").forEach((input) =>
  input.addEventListener("keydown", (ev) => { if (ev.key === "Enter") addEmployee(); }));

render();
