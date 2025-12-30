<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.employee.register.bean.Employee" %>

<%
    List<Employee> employees = (List<Employee>) request.getAttribute("employees");
%>

<html>
<head>
    <title>Employee List</title>
    <style>
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #555;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        a {
            padding: 4px 8px;
            border-radius: 4px;
            text-decoration: none;
            color: white;
        }
        .edit { background: #007bff; }
        .delete { background: #dc3545; }
    </style>
</head>
<body>

<jsp:include page="/WEB-INF/header.jsp" />

<h2 style="text-align:center;">Employee List</h2>

<table>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Username</th>
        <th>Address</th>
        <th>Contact</th>
        <th>Actions</th>
    </tr>

    <%
        if (employees != null) {
            for (Employee emp : employees) {
    %>
    <tr>
        <td><%= emp.getId() %></td>
        <td><%= emp.getFirstname() %></td>
        <td><%= emp.getLastname() %></td>
        <td><%= emp.getUsername() %></td>
        <td><%= emp.getAddress() %></td>
        <td><%= emp.getContactno() %></td>

        <td>
            <a class="edit" href="<%= request.getContextPath() %>/EmployeeServlet/edit?id=<%= emp.getId() %>">Edit</a>
            <a class="delete" href="<%= request.getContextPath() %>/EmployeeServlet/delete?id=<%= emp.getId() %>">Delete</a>
        </td>

    </tr>
    <%
            }
        }
    %>

</table>

<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>
