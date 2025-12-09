package com.employee.register.servlet;

import com.employee.register.bean.Employee;
import com.employee.register.dao.EmployeeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/EmployeeServlet/*")
public class EmployeeServlet extends HttpServlet {

    private EmployeeDao employeeDao;

    @Override
    public void init() {
        employeeDao = new EmployeeDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String contact = request.getParameter("contact");
        String address = request.getParameter("address");

        Employee emp = new Employee();
        emp.setFirstname(firstname);
        emp.setLastname(lastname);
        emp.setUsername(username);
        emp.setPassword(password);
        emp.setContactno(contact);
        emp.setAddress(address);

        boolean result = employeeDao.save(emp);

        if (result) {
            response.sendRedirect(request.getContextPath() + "/EmployeeServlet/list");
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo(); // Use getPathInfo() instead of getServletPath()

        if (action == null) {
            action = "/list"; // default
        }

        try {
            switch (action) {
                case "/list":
                    listEmployees(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/delete":
                    deleteEmployee(request, response);
                    break;
                default:
                    response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Employee> employees = employeeDao.getAllEmployees();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        employeeDao.delete(id);
        response.sendRedirect(request.getContextPath() + "/EmployeeServlet/list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee emp = employeeDao.getEmployeeById(id);
        request.setAttribute("employee", emp);
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }
}

