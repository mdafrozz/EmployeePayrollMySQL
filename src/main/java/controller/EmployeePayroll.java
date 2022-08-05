package controller;

import dbconnection.PayrollDBService;

public class EmployeePayroll {
    public static void main(String[] args) {
        System.out.println("************** Welcome to Employee Payroll Service (MySQL) **************");

        PayrollDBService payrollDbService = new PayrollDBService();
        System.out.println("Please wait Database is connecting......");
        payrollDbService.getConnection();

    }
}
