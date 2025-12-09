<style>
    body {
        margin: 0;
        font-family: Arial, sans-serif;
        background: #f5f5f5;
    }
    .navbar {
        background: #2c3e50;
        padding: 15px 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .navbar .logo {
        color: white;
        font-size: 20px;
        font-weight: bold;
        letter-spacing: 1px;
    }
    .navbar ul {
        list-style: none;
        margin: 0;
        padding: 0;
        display: flex;
        gap: 20px;
    }
    .navbar ul li a {
        color: #ecf0f1;
        text-decoration: none;
        font-size: 16px;
    }
    .navbar ul li a:hover {
        color: #1abc9c;
    }
</style>

<div class="navbar">
    <div class="logo">QDev Employee</div>

    <ul>
        <li><a href="<%= request.getContextPath() %>/index.jsp">Home</a></li>
        <li><a href="<%= request.getContextPath() %>/register.jsp">Register Employee</a></li>
        <li><a href="<%= request.getContextPath() %>/EmployeeServlet/list">Employee List</a></li>
    </ul>

</div>
