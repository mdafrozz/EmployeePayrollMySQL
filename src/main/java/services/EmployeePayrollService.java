package services;

import common.Constants;
import common.Util;
import dbconnection.PayrollDBService;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollService {
    static PayrollDBService payrollDbService;
    Connection connection;
    List<Employee> empList = new ArrayList<Employee>();
    Constants constants;

    public EmployeePayrollService() {
        constants = new Constants();
        payrollDbService = PayrollDBService.init();
        connection = payrollDbService.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* FETCH EMPLOYEE DATA FROM DATABASE */
    public void getAllEmployeesData() {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(constants.FETCH_EMP_DATA);

            while(rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setGender(rs.getString("gender").charAt(0));
                employee.setPhoneno(rs.getString("phoneno"));
                employee.setAddress(rs.getString("address"));
                employee.setStartDate(rs.getDate("startDate"));
                empList.add(employee);
            }

            empList.forEach(e -> {
                System.out.println("ID: " + e.getId());
                System.out.println("Name: " + e.getName());
                System.out.println("Gender: " + e.getGender());
                System.out.println("Phone No: " + e.getPhoneno());
                System.out.println("Address: " + e.getAddress());
                System.out.println("Joining Date: " + e.getStartDate());
                System.out.println("<--------------------------------------------------->");
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* UPDATE SALARY */
    public void updateSalary(Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement(constants.EMP_UPDATE_SALARY);

            double basic_pay = 3000000;
            double deduction = Util.formatDoubleValue(basic_pay* 0.1);
            double taxable_pay = Util.formatDoubleValue(basic_pay - deduction);
            double income_tax = Util.formatDoubleValue(taxable_pay * 0.2);
            double net_pay = Util.formatDoubleValue(taxable_pay - income_tax);

            ps.setDouble(1, basic_pay);
            ps.setDouble(2, deduction);
            ps.setDouble(3, taxable_pay);
            ps.setDouble(4, income_tax);
            ps.setDouble(5, net_pay);
            ps.setInt(6, 1);

            int status = ps.executeUpdate();

            if(status > 0) {
                System.out.println("Salary is updated with " + basic_pay + " successfully.");
            }else {
                System.out.println("There is some error");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}