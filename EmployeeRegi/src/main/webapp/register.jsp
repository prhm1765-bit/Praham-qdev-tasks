<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/header.jsp" />

<h2 style="text-align:center; margin-top:20px;">Employee Registration</h2>

<div style="width: 450px; margin: auto; padding: 20px; border: 1px solid #ccc; border-radius: 8px;">
    <form action="<%= request.getContextPath() %>/EmployeeServlet/register" method="post">

    <div style="display:flex; justify-content:space-between; margin-bottom:15px;">
            <label style="width:40%;">First Name:</label>
            <input type="text" name="firstname" required style="width:55%; padding:6px;">
        </div>

        <div style="display:flex; justify-content:space-between; margin-bottom:15px;">
            <label style="width:40%;">Last Name:</label>
            <input type="text" name="lastname" required style="width:55%; padding:6px;">
        </div>

        <div style="display:flex; justify-content:space-between; margin-bottom:15px;">
            <label style="width:40%;">Username:</label>
            <input type="text" name="username" required style="width:55%; padding:6px;">
        </div>

        <div style="display:flex; justify-content:space-between; margin-bottom:15px;">
            <label style="width:40%;">Password:</label>
            <input type="password" name="password" required style="width:55%; padding:6px;">
        </div>

        <div style="display:flex; justify-content:space-between; margin-bottom:15px;">
            <label style="width:40%;">Address:</label>
            <input type="text" name="address" required style="width:55%; padding:6px;">
        </div>

        <div style="display:flex; justify-content:space-between; margin-bottom:15px;">
            <label style="width:40%;">Contact No:</label>
            <input type="text" name="contact" required style="width:55%; padding:6px;">
        </div>

        <button type="submit"
                style="width:100%; padding:10px; background:#007bff; color:#fff; border:none; border-radius:5px;">
            Register Employee
        </button>
    </form>
</div>

<jsp:include page="/WEB-INF/footer.jsp" />
