package common;

public class Constants {
    /* MySql Connection configuration */
    public static final String JDBC_STR = "jdbc:mysql://localhost:3306/payroll_service";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";
    /* SQL QUERIES */
    public final String FETCH_EMP_DATA = "select * from employee_payroll";
}
