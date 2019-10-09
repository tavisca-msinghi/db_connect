package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dbconnect {

    private static final String url = "jdbc:mysql://localhost/GCE?autoReconnect=true&useSSL=false";
    private static final String user = "root";
    private static final String password = "root";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    List<Employee>employees = null;

        public List<Employee> getEmployees() throws ClassNotFoundException {
            employees = new ArrayList<Employee>();
            Class.forName("com.mysql.jdbc.Driver");
            try {
                con=DriverManager.getConnection(url, user, password);
                stmt=con.createStatement();
                rs=stmt.executeQuery("SELECT * from Employee");
                while (rs.next()){
                    Employee employee = new Employee(rs.getInt("PersonID"));
                    employee.setEmpId(rs.getString("EmpNo"));
                    employee.setName(rs.getString("Name"));
                    employee.setSalary(rs.getString("Salary"));
                    employee.setDept(rs.getString("Dept"));

                    System.out.println(rs);
                    employees.add(employee);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return employees;
        }
}
