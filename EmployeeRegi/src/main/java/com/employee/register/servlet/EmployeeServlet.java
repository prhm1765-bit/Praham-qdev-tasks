package com.employee.register.servlet;

import com.employee.register.bean.Employee;
import com.employee.register.dao.EmployeeDao;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/EmployeeServlet/*")
public class EmployeeServlet extends HttpServlet {

	private EmployeeDao employeeDAO;

	@Override
	public void init() {
		employeeDAO = new EmployeeDao();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		if (action == null) action = "/";
		switch (action) {
			case "/edit":
				showEditForm(request, response);
				break;
			case "/delete":
				deleteEmployee(request, response);
				break;
			default:
				listEmployees(request, response);
				break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		if (action == null) action = "/";
		if ("/update".equals(action)) {
			updateEmployee(request, response);
		} else {
			insertEmployee(request, response);
		}
	}

	private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Employee> list = employeeDAO.getAllEmployees();
		request.setAttribute("employees", list);
		request.getRequestDispatcher("/list.jsp").forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		try {
			int id = Integer.parseInt(idStr);
			Employee existing = employeeDAO.getEmployeeById(id);
			if (existing == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Employee not found");
				return;
			}
			request.setAttribute("employee", existing);
			request.setAttribute("isEdit", Boolean.TRUE);
			// forward to same register.jsp (edit mode)
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		} catch (NumberFormatException ex) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid id");
		}
	}

	private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// read raw params
		String firstname = request.getParameter("firstname");
		String lastname  = request.getParameter("lastname");
		String username  = request.getParameter("username");
		String password  = request.getParameter("password");
		String address   = request.getParameter("address");
		String contact   = request.getParameter("contact");
		Map<String,String> errors = new HashMap<>();

		// server-side validation (trimmed)
		firstname = safeTrim(firstname);
		lastname  = safeTrim(lastname);
		username  = safeTrim(username);
		password  = safeTrim(password);
		address   = safeTrim(address);
		contact   = safeTrim(contact);

		// Required checks
		if (firstname.isEmpty()) errors.put("firstname", "First name is required.");
		if (lastname.isEmpty())  errors.put("lastname",  "Last name is required.");
		if (username.isEmpty())  errors.put("username",  "Username is required.");
		if (password.isEmpty())  errors.put("password",  "Password is required.");
		if (address.isEmpty())   errors.put("address",   "Address is required.");
		if (contact.isEmpty())   errors.put("contact",   "Contact is required.");

		// If any required missing -> forward
		if (!errors.isEmpty()) {
			forwardWithErrorsRegistration(request, response, errors, firstname, lastname, username, address, contact);
			return;
		}

		// Name rules
		if (!firstname.matches("^[A-Za-z]{2,}$")) errors.put("firstname", "First name: only letters, min 2.");
		if (!lastname.matches("^[A-Za-z]{2,}$"))  errors.put("lastname",  "Last name: only letters, min 2.");

		// Contact rules
		if (!contact.matches("\\d{10}")) errors.put("contact", "Contact must be exactly 10 digits.");

		// Password rules
		if (password.length() < 6 || !password.matches(".*[A-Za-z].*") || !password.matches(".*\\d.*"))
			errors.put("password", "Password must be min 6 chars and include letters and numbers.");

		if (password.equals(username)) errors.put("password", "Password cannot be same as username.");

		// username uniqueness
		if (employeeDAO.isUsernameExists(username)) errors.put("username", "Username already exists.");

		if (!errors.isEmpty()) {
			forwardWithErrorsRegistration(request, response, errors, firstname, lastname, username, address, contact);
			return;
		}

		// hash password and save
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		Employee emp = new Employee();
		emp.setFirstname(firstname);
		emp.setLastname(lastname);
		emp.setUsername(username);
		emp.setPassword(hashed);
		emp.setAddress(address);
		emp.setContactno(contact);

		boolean saved = employeeDAO.save(emp);
		if (!saved) {
			errors.put("general", "Failed to register employee. Please try again.");
			forwardWithErrorsRegistration(request, response, errors, firstname, lastname, username, address, contact);
			return;
		}

		// Success
		response.sendRedirect(request.getContextPath() + "/EmployeeServlet/");
	}

	private void forwardWithErrorsRegistration(HttpServletRequest request, HttpServletResponse response,
											   Map<String,String> errors,
											   String firstname, String lastname, String username,
											   String address, String contact)
			throws ServletException, IOException {
		request.setAttribute("errors", errors);
		request.setAttribute("isEdit", Boolean.FALSE);
		Employee prefill = new Employee();
		prefill.setFirstname(firstname);
		prefill.setLastname(lastname);
		prefill.setUsername(username);
		prefill.setAddress(address);
		prefill.setContactno(contact);
		request.setAttribute("employee", prefill);
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idStr = request.getParameter("id");
		String firstname = request.getParameter("firstname");
		String lastname  = request.getParameter("lastname");
		String username  = request.getParameter("username");
		String newPassword = request.getParameter("password"); // may be null / empty
		String address   = request.getParameter("address");
		String contact   = request.getParameter("contact");

		Map<String,String> errors = new HashMap<>();

		// parse id
		int id;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid id");
			return;
		}

		Employee existing = employeeDAO.getEmployeeById(id);
		if (existing == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Employee not found");
			return;
		}

		// trim input
		firstname = safeTrim(firstname);
		lastname  = safeTrim(lastname);
		username  = safeTrim(username);
		newPassword = newPassword == null ? "" : newPassword.trim();
		address   = safeTrim(address);
		contact   = safeTrim(contact);

		// required checks (password optional)
		if (firstname.isEmpty()) errors.put("firstname", "First name is required.");
		if (lastname.isEmpty())  errors.put("lastname",  "Last name is required.");
		if (username.isEmpty())  errors.put("username",  "Username is required.");
		if (address.isEmpty())   errors.put("address",   "Address is required.");
		if (contact.isEmpty())   errors.put("contact",   "Contact is required.");

		if (!errors.isEmpty()) {
			forwardWithErrorsEdit(request, response, errors, id, firstname, lastname, username, address, contact);
			return;
		}

		// name checks
		if (!firstname.matches("^[A-Za-z]{2,}$")) errors.put("firstname", "First name: only letters, min 2.");
		if (!lastname.matches("^[A-Za-z]{2,}$"))  errors.put("lastname",  "Last name: only letters, min 2.");

		// contact
		if (!contact.matches("\\d{10}")) errors.put("contact", "Contact must be exactly 10 digits.");

		// handle new password if provided
		String finalHash = existing.getPassword();
		if (!newPassword.isEmpty()) {
			if (newPassword.length() < 6 || !newPassword.matches(".*[A-Za-z].*") || !newPassword.matches(".*\\d.*"))
				errors.put("password", "Password must be min 6 chars and include letters and numbers.");

			if (newPassword.equals(username)) errors.put("password", "Password cannot be same as username.");

			// cannot equal old (BCrypt check)
			if (BCrypt.checkpw(newPassword, existing.getPassword()))
				errors.put("password", "New password cannot be the same as old password.");

			if (!errors.containsKey("password")) {
				finalHash = BCrypt.hashpw(newPassword, BCrypt.gensalt());
			}
		}

		// username uniqueness (exclude this id)
		if (employeeDAO.isUsernameExistsForUpdate(username, id)) errors.put("username", "Username already exists.");

		if (!errors.isEmpty()) {
			forwardWithErrorsEdit(request, response, errors, id, firstname, lastname, username, address, contact);
			return;
		}

		// Build updated employee and save
		Employee updated = new Employee();
		updated.setId(id);
		updated.setFirstname(firstname);
		updated.setLastname(lastname);
		updated.setUsername(username);
		updated.setPassword(finalHash);
		updated.setAddress(address);
		updated.setContactno(contact);

		boolean ok = employeeDAO.updateEmployee(updated);
		if (!ok) {
			errors.put("general", "Failed to update employee.");
			forwardWithErrorsEdit(request, response, errors, id, firstname, lastname, username, address, contact);
			return;
		}

		response.sendRedirect(request.getContextPath() + "/EmployeeServlet/");
	}

	private void forwardWithErrorsEdit(HttpServletRequest request, HttpServletResponse response,
									   Map<String,String> errors,
									   int id, String firstname, String lastname, String username,
									   String address, String contact)
			throws ServletException, IOException {
		request.setAttribute("errors", errors);
		request.setAttribute("isEdit", Boolean.TRUE);
		Employee prefill = new Employee();
		prefill.setId(id);
		prefill.setFirstname(firstname);
		prefill.setLastname(lastname);
		prefill.setUsername(username);
		prefill.setAddress(address);
		prefill.setContactno(contact);
		request.setAttribute("employee", prefill);
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			employeeDAO.delete(id);
			response.sendRedirect(request.getContextPath() + "/EmployeeServlet/");
		} catch (NumberFormatException ex) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid id");
		}
	}

	private String safeTrim(String s) {
		return s == null ? "" : s.trim();
	}

}
