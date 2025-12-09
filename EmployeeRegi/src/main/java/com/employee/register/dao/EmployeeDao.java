package com.employee.register.dao;

import com.employee.register.bean.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private String jdbcURL = "jdbc:mysql://localhost:3306/EmployeeRegistrationDB";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_EMP_SQL =
            "INSERT INTO employee (firstname, lastname, username, password, contactno, address) VALUES (?, ?, ?, ?, ?, ?)";

    public EmployeeDao() {}

    protected Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean save(Employee emp) {
        boolean rowInserted = false;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_EMP_SQL)) {
            ps.setString(1, emp.getFirstname());
            ps.setString(2, emp.getLastname());
            ps.setString(3, emp.getUsername());
            ps.setString(4, emp.getPassword());
            ps.setString(5, emp.getContactno());
            ps.setString(6, emp.getAddress());
            rowInserted = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowInserted;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM employee");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("address"),
                        rs.getString("contactno")
                );
                list.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean delete(int id) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM employee WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Employee getEmployeeById(int id) {
        Employee emp = null;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM employee WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                emp = new Employee(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("address"),
                        rs.getString("contactno")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }
}
