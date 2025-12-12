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

        //------------------------------------------------------------save an employee in db
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

        //-------------------------------------------------------------Get All Employees
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

        //--------------------------------------------------------------Delete
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

        //---------------------------------------------------------------Get Employee By Id
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

        //----------------------------------------------------------update Employee
        public boolean updateEmployee(Employee emp) {

            String sql = "UPDATE employee SET firstname=?, lastname=?, username=?, password=?, contactno=?, address=? WHERE id=?";

            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, emp.getFirstname());
                ps.setString(2, emp.getLastname());
                ps.setString(3, emp.getUsername());
                ps.setString(4, emp.getPassword());
                ps.setString(5, emp.getContactno());
                ps.setString(6, emp.getAddress());
                ps.setInt(7, emp.getId());

                return ps.executeUpdate() > 0;

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean isUsernameExists(String username) {
            String sql = "SELECT COUNT(*) FROM employee WHERE username = ?";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getInt(1) > 0;  // true if count > 0
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        public boolean isUsernameExistsForUpdate(String username, int id) {
            String sql = "SELECT COUNT(*) FROM employee WHERE username = ? AND id <> ?";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, username);
                ps.setInt(2, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

    }
