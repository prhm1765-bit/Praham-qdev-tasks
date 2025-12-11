<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.employee.register.bean.Employee" %>

<jsp:include page="/WEB-INF/header.jsp" />

<%
    Employee emp = (Employee) request.getAttribute("employee");
    Boolean isEditObj = (Boolean) request.getAttribute("isEdit");
    boolean isEdit = (isEditObj != null && isEditObj);

    java.util.Map<String, String> errors = (java.util.Map<String, String>) request.getAttribute("errors");
    String errFirstname = errors != null ? errors.get("firstname") : (String) request.getAttribute("errFirstname");
    String errLastname  = errors != null ? errors.get("lastname")  : (String) request.getAttribute("errLastname");
    String errUsername  = errors != null ? errors.get("username")  : (String) request.getAttribute("errUsername");
    String errPassword  = errors != null ? errors.get("password")  : (String) request.getAttribute("errPassword");
    String errAddress   = errors != null ? errors.get("address")   : (String) request.getAttribute("errAddress");
    String errContact   = errors != null ? errors.get("contact")   : (String) request.getAttribute("errContact");

    String p_firstname = emp != null ? emp.getFirstname() : (request.getParameter("firstname") != null ? request.getParameter("firstname") : "");
    String p_lastname  = emp != null ? emp.getLastname()  : (request.getParameter("lastname")  != null ? request.getParameter("lastname")  : "");
    String p_username  = emp != null ? emp.getUsername()  : (request.getParameter("username")  != null ? request.getParameter("username")  : "");
    String p_address   = emp != null ? emp.getAddress()   : (request.getParameter("address")   != null ? request.getParameter("address")   : "");
    String p_contact   = emp != null ? emp.getContactno() : (request.getParameter("contact")   != null ? request.getParameter("contact")   : "");
%>

<style>
    .form-container { width: 480px; margin: 20px auto; padding: 18px; border: 1px solid #ccc; border-radius: 8px; background:#fff;}
    .field { margin-bottom:12px; }
    .field label { display:block; font-weight:600; margin-bottom:4px; }
    .field input { width:100%; padding:8px; border-radius:4px; border:1px solid #bbb; }
    .field-error { color:#b22; font-size:0.9em; margin-top:6px; }
    .submit-btn { width:100%; padding:10px; background:#007bff; color:#fff; border:none; border-radius:5px; cursor:pointer; }
</style>

<h2 style="text-align:center; margin-top:10px;"><%= isEdit ? "Edit Employee" : "Register Employee" %></h2>

<div class="form-container">
    <form id="empForm" action="<%= request.getContextPath() %>/EmployeeServlet<%= isEdit ? "/update" : "" %>" method="post" novalidate>
        <% if (isEdit) { %><input type="hidden" name="id" value="<%= emp.getId() %>"><% } %>

        <div class="field">
            <label for="firstname">First Name</label>
            <input id="firstname" name="firstname" type="text" maxlength="30" autocomplete="given-name" value="<%= p_firstname %>">
            <div id="errFirstnameClient" class="field-error" style="display:none;"></div>
            <% if (errFirstname != null) { %><div class="field-error"><%= errFirstname %></div><% } %>
        </div>

        <div class="field">
            <label for="lastname">Last Name</label>
            <input id="lastname" name="lastname" type="text" maxlength="30" autocomplete="family-name" value="<%= p_lastname %>">
            <div id="errLastnameClient" class="field-error" style="display:none;"></div>
            <% if (errLastname != null) { %><div class="field-error"><%= errLastname %></div><% } %>
        </div>

        <div class="field">
            <label for="username">Username</label>
            <input id="username" name="username" type="text" maxlength="40" autocomplete="username" value="<%= p_username %>">
            <div id="errUsernameClient" class="field-error" style="display:none;"></div>
            <% if (errUsername != null) { %><div class="field-error"><%= errUsername %></div><% } %>
        </div>

        <% if (!isEdit) { %>
        <div class="field">
            <label for="password">Password</label>
            <input id="password" name="password" type="password" autocomplete="new-password" maxlength="60">
            <div id="errPasswordClient" class="field-error" style="display:none;"></div>
            <% if (errPassword != null) { %><div class="field-error"><%= errPassword %></div><% } %>
        </div>
        <% } %>

        <div class="field">
            <label for="address">Address</label>
            <input id="address" name="address" type="text" maxlength="200" value="<%= p_address %>">
            <div id="errAddressClient" class="field-error" style="display:none;"></div>
            <% if (errAddress != null) { %><div class="field-error"><%= errAddress %></div><% } %>
        </div>

        <div class="field">
            <label for="contact">Contact No</label>
            <input id="contact" name="contact" type="text" inputmode="numeric" maxlength="10" value="<%= p_contact %>">
            <div id="errContactClient" class="field-error" style="display:none;"></div>
            <% if (errContact != null) { %><div class="field-error"><%= errContact %></div><% } %>
        </div>

        <button id="submitBtn" type="submit" class="submit-btn"><%= isEdit ? "Update Employee" : "Register Employee" %></button>
    </form>
</div>

<script>
    function setErr(id, msg) {
        const el = document.getElementById(id);
        if (!el) return;
        if (msg) { el.style.display = 'block'; el.textContent = msg; }
        else { el.style.display = 'none'; el.textContent = ''; }
    }

    const firstname = document.getElementById('firstname');
    const lastname  = document.getElementById('lastname');
    const username  = document.getElementById('username');
    const password  = document.getElementById('password'); // may be null on edit
    const address   = document.getElementById('address');
    const contact   = document.getElementById('contact');
    const form      = document.getElementById('empForm');

    function sanitizeNameInput(e) {
        const cleaned = e.target.value.replace(/[^A-Za-z]/g, '');
        if (cleaned !== e.target.value) e.target.value = cleaned;
    }
    firstname && firstname.addEventListener('input', sanitizeNameInput);
    lastname && lastname.addEventListener('input', sanitizeNameInput);

    contact.addEventListener('input', function(e){
        let cleaned = e.target.value.replace(/\D/g, '').slice(0,10);
        if (cleaned !== e.target.value) e.target.value = cleaned;
    });

    function nameBlurHandler(el, errId) {
        if (!el) return;
        el.addEventListener('blur', function() {
            const v = el.value.trim();
            if (!v) setErr(errId, 'This field is required.');
            else if (v.length < 2) setErr(errId, 'Minimum 2 letters required.');
            else setErr(errId, '');
        });
    }
    nameBlurHandler(firstname, 'errFirstnameClient');
    nameBlurHandler(lastname, 'errLastnameClient');

    username && username.addEventListener('blur', function() {
        if (!username.value.trim()) setErr('errUsernameClient', 'Username required.');
        else setErr('errUsernameClient', '');
    });

    if (password) {
        password.addEventListener('blur', function() {
            const v = password.value;
            if (!v) setErr('errPasswordClient', 'Password required.');
            else if (v.length < 6) setErr('errPasswordClient', 'Min 6 characters.');
            else if (!/[A-Za-z]/.test(v) || !/\d/.test(v)) setErr('errPasswordClient', 'Include at least one letter and one number.');
            else if (username && v === username.value) setErr('errPasswordClient', 'Password cannot be same as username.');
            else setErr('errPasswordClient', '');
        });
    }

    address && address.addEventListener('blur', function(){ if (!address.value.trim()) setErr('errAddressClient','Address required'); else setErr('errAddressClient',''); });

    contact && contact.addEventListener('blur', function() {
        const v = contact.value;
        if (!v) setErr('errContactClient', 'Contact required.');
        else if (!/^\d{10}$/.test(v)) setErr('errContactClient', 'Contact must be exactly 10 digits.');
        else setErr('errContactClient', '');
    });

    form.addEventListener('submit', function(e) {
        let hasError = false;

        if (!firstname.value || firstname.value.trim().length < 2) { setErr('errFirstnameClient','Min 2 letters required'); firstname.focus(); hasError = true; }
        if (!lastname.value || lastname.value.trim().length < 2) { setErr('errLastnameClient','Min 2 letters required'); if(!hasError) lastname.focus(); hasError = true; }
        if (!username.value.trim()) { setErr('errUsernameClient','Username required'); if(!hasError) username.focus(); hasError = true; }

        if (password) {
            const v = password.value;
            if (!v || v.length < 6 || !/[A-Za-z]/.test(v) || !/\d/.test(v)) {
                setErr('errPasswordClient','Password min 6 chars and include letters & numbers');
                if(!hasError) password.focus();
                hasError = true;
            }
            if (v && username.value && v === username.value) {
                setErr('errPasswordClient','Password cannot be same as username');
                if(!hasError) password.focus();
                hasError = true;
            }
        }

        if (!/^\d{10}$/.test(contact.value)) { setErr('errContactClient','Contact must be exactly 10 digits'); if(!hasError) contact.focus(); hasError = true; }
        if (!address.value.trim()) { setErr('errAddressClient','Address required'); if(!hasError) address.focus(); hasError = true; }

        if (hasError) e.preventDefault();
    });

    (function showServerErrors(){
        <% if (errFirstname != null) { %> setErr('errFirstnameClient','<%= errFirstname.replace("'", "\\'") %>'); <% } %>
        <% if (errLastname  != null) { %> setErr('errLastnameClient','<%= errLastname.replace("'", "\\'") %>');   <% } %>
        <% if (errUsername  != null) { %> setErr('errUsernameClient','<%= errUsername.replace("'", "\\'") %>');   <% } %>
        <% if (errPassword  != null) { %> setErr('errPasswordClient','<%= errPassword.replace("'", "\\'") %>');   <% } %>
        <% if (errAddress   != null) { %> setErr('errAddressClient','<%= errAddress.replace("'", "\\'") %>');     <% } %>
        <% if (errContact   != null) { %> setErr('errContactClient','<%= errContact.replace("'", "\\'") %>');     <% } %>
    })();
</script>

<jsp:include page="/WEB-INF/footer.jsp" />
