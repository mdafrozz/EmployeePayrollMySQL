package controller;

import dbconnection.PayrollDBService;
import services.EmployeePayrollService;

import java.util.Scanner;

public class EmployeePayroll {
    static Scanner sc;
    PayrollDBService payrollDbService;

    public static void main(String[] args) {
        System.out.println("************** Welcome to Employee Payroll Service (MySQL) **************");

        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        System.out.println("<---------------- Fetch Employee Data ---------------->");
        employeePayrollService.getAllEmployeesData();
        System.out.println("<---------------- Update Salary with 3000000 ---------------->");
        PayrollDBService payrollDbService = new PayrollDBService();
        employeePayrollService.updateSalary(payrollDbService.getConnection());
        System.out.println("<---------------- Get data with date range ---------------->");
        employeePayrollService.getEmpDataWithDateRange("2022-06-10", "2022-06-25");
        System.out.println("<---------------- Get salary by gender ---------------->");
        employeePayrollService.getSalaryByGender();

    }

}
