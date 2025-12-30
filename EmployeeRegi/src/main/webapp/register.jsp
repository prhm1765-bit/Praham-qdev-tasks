<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.employee.register.bean.Employee" %>

<jsp:include page="/WEB-INF/header.jsp" />

<%
    // Mode & data
    Employee emp = (Employee) request.getAttribute("employee");
    Boolean isEditObj = (Boolean) request.getAttribute("isEdit");
    boolean isEdit = (isEditObj != null && isEditObj);

    // Per-field server-side errors (either as a Map "errors" or individual attrs)
    java.util.Map<String, String> errors = (java.util.Map<String, String>) request.getAttribute("errors");
    String errFirstname = errors != null ? errors.get("firstname") : (String) request.getAttribute("errFirstname");
    String errLastname  = errors != null ? errors.get("lastname")  : (String) request.getAttribute("errLastname");
    String errUsername  = errors != null ? errors.get("username")  : (String) request.getAttribute("errUsername");
    String errPassword  = errors != null ? errors.get("password")  : (String) request.getAttribute("errPassword");
    String errAddress   = errors != null ? errors.get("address")   : (String) request.getAttribute("errAddress");
    String errContact   = errors != null ? errors.get("contact")   : (String) request.getAttribute("errContact");

    // Prefill values (prefer request attribute 'employee' then forwarded parameters)
    String p_firstname = emp != null ? emp.getFirstname() : (request.getParameter("firstname") != null ? request.getParameter("firstname") : "");
    String p_lastname  = emp != null ? emp.getLastname()  : (request.getParameter("lastname")  != null ? request.getParameter("lastname")  : "");
    String p_username  = emp != null ? emp.getUsername()  : (request.getParameter("username")  != null ? request.getParameter("username")  : "");
    // Password: for registration keep previously typed value if servlet forwarded with validation errors
    String p_password  = request.getParameter("password") != null ? request.getParameter("password") : "";
    String p_address   = emp != null ? emp.getAddress()   : (request.getParameter("address")   != null ? request.getParameter("address")   : "");
    String p_contact   = emp != null ? emp.getContactno() : (request.getParameter("contact")   != null ? request.getParameter("contact")   : "");
%>

<style>
    .form-container { width: 520px; margin: 22px auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 8px; background:#fff; box-shadow:0 1px 4px rgba(0,0,0,0.03); }
    .field { margin-bottom:14px; }
    .field label { display:block; font-weight:600; margin-bottom:6px; color:#333; }
    .field input, .field textarea { width:100%; padding:10px; border-radius:6px; border:1px solid #ccc; font-size:14px; box-sizing: border-box; }
    .field textarea { min-height:80px; resize:vertical; }
    .field-error { color:#c02; font-size:0.9em; margin-top:6px; display:none; }
    .field-error.server { display:block; } /* used for server-side errors that should be visible on load */
    .submit-btn { width:100%; padding:12px; background:#007bff; color:#fff; border:none; border-radius:6px; font-weight:600; cursor:pointer; }
    .submit-btn:disabled { opacity:0.6; cursor:not-allowed; }
</style>

<h2 style="text-align:center; margin-top:6px; margin-bottom:10px;">
    <%= isEdit ? "Edit Employee" : "Register Employee" %>
</h2>

<div class="form-container">
    <!-- action: /EmployeeServlet for insert ; /EmployeeServlet/update for update -->
    <form id="empForm" action="<%= request.getContextPath() %>/EmployeeServlet<%= isEdit ? "/update" : "" %>" method="post" novalidate>
        <% if (isEdit) { %>
        <input type="hidden" name="id" value="<%= emp.getId() %>" />
        <% } %>

        <!-- FIRST NAME -->
        <div class="field">
            <label for="firstname">First Name</label>
            <input id="firstname" name="firstname" type="text" maxlength="50" autocomplete="given-name"
                   value="<%= p_firstname %>">
            <div id="errFirstnameClient" class="field-error"></div>
            <% if (errFirstname != null) { %>
            <div class="field-error server"><%= errFirstname %></div>
            <% } %>
        </div>

        <!-- LAST NAME -->
        <div class="field">
            <label for="lastname">Last Name</label>
            <input id="lastname" name="lastname" type="text" maxlength="50" autocomplete="family-name"
                   value="<%= p_lastname %>">
            <div id="errLastnameClient" class="field-error"></div>
            <% if (errLastname != null) { %>
            <div class="field-error server"><%= errLastname %></div>
            <% } %>
        </div>

        <!-- USERNAME -->
        <div class="field">
            <label for="username">Username</label>
            <input id="username" name="username" type="text" maxlength="50" autocomplete="username"
                   value="<%= p_username %>">
            <div id="errUsernameClient" class="field-error"></div>
            <% if (errUsername != null) { %>
            <div class="field-error server"><%= errUsername %></div>
            <% } %>
        </div>

        <!-- PASSWORD (only show for registration) -->
        <% if (!isEdit) { %>
        <div class="field">
            <label for="password">Password</label>
            <input id="password" name="password" type="password" maxlength="255" autocomplete="new-password"
                   value="<%= p_password %>">
            <div id="errPasswordClient" class="field-error"></div>
            <% if (errPassword != null) { %>
            <div class="field-error server"><%= errPassword %></div>
            <% } %>
            <div style="margin-top:6px; font-size:0.86em; color:#666;">
                Password must be at least 6 characters, include at least one letter and one number. (Cannot equal username.)
            </div>
        </div>
        <% } %>

        <!-- ADDRESS (textarea) -->
        <div class="field">
            <label for="address">Address</label>
            <textarea id="address" name="address" maxlength="500"><%= p_address %></textarea>
            <div id="errAddressClient" class="field-error"></div>
            <% if (errAddress != null) { %>
            <div class="field-error server"><%= errAddress %></div>
            <% } %>
        </div>

        <!-- CONTACT -->
        <div class="field">
            <label for="contact">Contact No</label>
            <input id="contact" name="contact" type="text" inputmode="numeric" maxlength="10" value="<%= p_contact %>">
            <div id="errContactClient" class="field-error"></div>
            <% if (errContact != null) { %>
            <div class="field-error server"><%= errContact %></div>
            <% } %>
        </div>

        <div style="margin-top:6px;">
            <button id="submitBtn" type="submit" class="submit-btn"><%= isEdit ? "Update Employee" : "Register Employee" %></button>
        </div>
    </form>
</div>

<script>
    // --- helpers ---
    function showErr(id, msg) {
        const el = document.getElementById(id);
        if (!el) return;
        if (msg) { el.style.display = 'block'; el.textContent = msg; }
        else { el.style.display = 'none'; el.textContent = ''; }
    }

    function sanitizeLettersOnly(val) { return val.replace(/[^A-Za-z]/g, ''); }
    function sanitizeDigitsOnly(val) { return val.replace(/\D/g, ''); }

    // --- DOM nodes ---
    const form = document.getElementById('empForm');
    const firstname = document.getElementById('firstname');
    const lastname  = document.getElementById('lastname');
    const username  = document.getElementById('username');
    const password  = document.getElementById('password'); // may be null in edit
    const address   = document.getElementById('address');
    const contact   = document.getElementById('contact');

    // If field not present bail out safely
    function onInputName(e) {
        const cleaned = sanitizeLettersOnly(e.target.value);
        if (cleaned !== e.target.value) e.target.value = cleaned;
        // live validation: must be at least 2 letters
        if (cleaned.length < 2) showErr(e.target.id === 'firstname' ? 'errFirstnameClient' : 'errLastnameClient','Minimum 2 letters required.');
        else showErr(e.target.id === 'firstname' ? 'errFirstnameClient' : 'errLastnameClient','');
    }

    if (firstname) {
        firstname.addEventListener('input', onInputName);
        firstname.addEventListener('blur', function(){ if (firstname.value.trim().length < 2) showErr('errFirstnameClient','Minimum 2 letters required.'); else showErr('errFirstnameClient',''); });
    }
    if (lastname) {
        lastname.addEventListener('input', onInputName);
        lastname.addEventListener('blur', function(){ if (lastname.value.trim().length < 2) showErr('errLastnameClient','Minimum 2 letters required.'); else showErr('errLastnameClient',''); });
    }

    // Username: disallow spaces and show quick message if empty
    // if (username) {
    //     username.addEventListener('input', function(e){
    //         const cleaned = e.target.value.replace(/\s/g,'');
    //         if (cleaned !== e.target.value) e.target.value = cleaned;
    //         if (!cleaned) showErr('errUsernameClient','Username is required.');
    //         else showErr('errUsernameClient','');
    //     });
    //     username.addEventListener('blur', function(){ if (!username.value.trim()) showErr('errUsernameClient','Username is required.'); else showErr('errUsernameClient',''); });
    // }

    if (username) {
        username.addEventListener('input', function(e){

            // 🔥 Hide server-side username error if user starts typing
            const serverErr = document.querySelector('#username + .field-error.server');
            if (serverErr) serverErr.style.display = 'none';

            // remove spaces
            const cleaned = e.target.value.replace(/\s/g,'');
            if (cleaned !== e.target.value) e.target.value = cleaned;

            // client validation
            if (!cleaned) {
                showErr('errUsernameClient','Username is required.');
            } else {
                showErr('errUsernameClient','');
            }
        });

        username.addEventListener('blur', function() {
            if (!username.value.trim()) {
                showErr('errUsernameClient','Username is required.');
            } else {
                showErr('errUsernameClient','');
            }
        });
    }


    // Contact: digits only, limit 10, dynamic error
    if (contact) {
        contact.addEventListener('input', function(e){
            let cleaned = sanitizeDigitsOnly(e.target.value).slice(0,10);
            if (cleaned !== e.target.value) e.target.value = cleaned;
            if (cleaned.length !== 10) showErr('errContactClient','Contact must be exactly 10 digits.');
            else showErr('errContactClient','');
        });
        contact.addEventListener('blur', function(){ if (!/^\d{10}$/.test(contact.value)) showErr('errContactClient','Contact must be exactly 10 digits.'); else showErr('errContactClient',''); });
    }

    // Password: only in registration mode; dynamic checks
    if (password) {
        password.addEventListener('input', function(){
            const v = password.value;
            const hasMin = v.length >= 6;
            const hasLetter = /[A-Za-z]/.test(v);
            const hasDigit = /\d/.test(v);
            if (!hasMin) showErr('errPasswordClient', 'Password must be at least 6 characters.');
            else if (!hasLetter || !hasDigit) showErr('errPasswordClient', 'Password must include letters and numbers.');
            else if (username && v === username.value) showErr('errPasswordClient','Password cannot equal username.');
            else showErr('errPasswordClient','');
        });
        password.addEventListener('blur', function(){
            const v = password.value;
            if (!v) showErr('errPasswordClient','Password is required.');
        });
    }

    // Address simple live check
    if (address) {
        address.addEventListener('input', function(){ if (address.value.trim()) showErr('errAddressClient',''); });
        address.addEventListener('blur', function(){ if (!address.value.trim()) showErr('errAddressClient','Address is required.'); });
    }

    // Final submit check (client-side)
    form.addEventListener('submit', function(e){
        let hasError = false;

        // Firstname
        if (!firstname.value || firstname.value.trim().length < 2) { showErr('errFirstnameClient','Minimum 2 letters required.'); firstname.focus(); hasError = true; }

        // Lastname
        if (!lastname.value || lastname.value.trim().length < 2) { if(!hasError){ lastname.focus(); } showErr('errLastnameClient','Minimum 2 letters required.'); hasError = true; }

        // Username
        if (!username.value || !username.value.trim()) { if(!hasError) username.focus(); showErr('errUsernameClient','Username is required.'); hasError = true; }

        // Password (only present on page)
        if (password) {
            const v = password.value;
            if (!v || v.length < 6 || !/[A-Za-z]/.test(v) || !/\d/.test(v)) {
                if (!hasError) password.focus();
                showErr('errPasswordClient','Password min 6 chars & include letters and numbers.');
                hasError = true;
            } else if (username && v === username.value) {
                if (!hasError) password.focus();
                showErr('errPasswordClient','Password cannot equal username.');
                hasError = true;
            }
        }

        // Contact
        if (!/^\d{10}$/.test(contact.value)) {
            if (!hasError) contact.focus();
            showErr('errContactClient','Contact must be exactly 10 digits.');
            hasError = true;
        }

        // Address
        if (!address.value.trim()) {
            if (!hasError) address.focus();
            showErr('errAddressClient','Address is required.');
            hasError = true;
        }

        if (hasError) e.preventDefault();
    });

    // On load: if server provided per-field errors, show them under the field (server takes precedence)
    (function showServerErrors() {
        <% if (errFirstname != null) { %> showErr('errFirstnameClient', '<%= errFirstname.replace("'", "\\'") %>'); <% } %>
        <% if (errLastname  != null) { %> showErr('errLastnameClient', '<%= errLastname.replace("'", "\\'") %>');   <% } %>
<%--        <% if (errUsername  != null) { %> showErr('errUsernameClient', '<%= errUsername.replace("'", "\\'") %>');   <% } %>--%>
        <% if (errPassword  != null) { %> showErr('errPasswordClient', '<%= errPassword.replace("'", "\\'") %>');   <% } %>
        <% if (errAddress   != null) { %> showErr('errAddressClient', '<%= errAddress.replace("'", "\\'") %>');     <% } %>
        <% if (errContact   != null) { %> showErr('errContactClient', '<%= errContact.replace("'", "\\'") %>');     <% } %>
    })();
</script>

<jsp:include page="/WEB-INF/footer.jsp" />
