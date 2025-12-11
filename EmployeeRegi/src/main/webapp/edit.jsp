<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.employee.register.bean.Employee" %>

<jsp:include page="/WEB-INF/header.jsp" />

<%
    Employee emp = (Employee) request.getAttribute("employee");
    java.util.Map<String,String> errors = (java.util.Map<String,String>) request.getAttribute("errors");
    String errFirstname = errors != null ? errors.get("firstname") : (String) request.getAttribute("errFirstname");
    String errLastname  = errors != null ? errors.get("lastname")  : (String) request.getAttribute("errLastname");
    String errUsername  = errors != null ? errors.get("username")  : (String) request.getAttribute("errUsername");
    String errAddress   = errors != null ? errors.get("address")   : (String) request.getAttribute("errAddress");
    String errContact   = errors != null ? errors.get("contact")   : (String) request.getAttribute("errContact");
%>

<style>
    .form-container{ width:480px; margin:20px auto; padding:18px; border:1px solid #ccc; border-radius:8px; background:#fff;}
    .field{ margin-bottom:12px;}
    .field label{ display:block; font-weight:600; margin-bottom:4px; }
    .field input{ width:100%; padding:8px; border-radius:4px; border:1px solid #bbb; }
    .field-error{ color:#b22; font-size:0.9em; margin-top:6px; }
    .btn{ width:100%; padding:10px; background:#007bff; color:#fff; border:none; border-radius:5px; cursor:pointer; }
</style>

<h2 style="text-align:center; margin-top:10px;">Edit Employee</h2>

<div class="form-container">
    <form id="editForm" action="<%= request.getContextPath() %>/EmployeeServlet/update" method="post" novalidate>
        <input type="hidden" name="id" value="<%= emp.getId() %>">

        <div class="field">
            <label for="firstname">First Name</label>
            <input id="firstname" name="firstname" type="text" maxlength="30" value="<%= emp.getFirstname() %>">
            <div id="errFirstnameClient" class="field-error" style="display:none;"></div>
            <% if (errFirstname != null) { %><div class="field-error"><%= errFirstname %></div><% } %>
        </div>

        <div class="field">
            <label for="lastname">Last Name</label>
            <input id="lastname" name="lastname" type="text" maxlength="30" value="<%= emp.getLastname() %>">
            <div id="errLastnameClient" class="field-error" style="display:none;"></div>
            <% if (errLastname != null) { %><div class="field-error"><%= errLastname %></div><% } %>
        </div>

        <div class="field">
            <label for="username">Username</label>
            <input id="username" name="username" type="text" maxlength="40" value="<%= emp.getUsername() %>">
            <div id="errUsernameClient" class="field-error" style="display:none;"></div>
            <% if (errUsername != null) { %><div class="field-error"><%= errUsername %></div><% } %>
        </div>

        <div class="field">
            <label for="address">Address</label>
            <input id="address" name="address" type="text" maxlength="200" value="<%= emp.getAddress() %>">
            <div id="errAddressClient" class="field-error" style="display:none;"></div>
            <% if (errAddress != null) { %><div class="field-error"><%= errAddress %></div><% } %>
        </div>

        <div class="field">
            <label for="contact">Contact No</label>
            <input id="contact" name="contact" type="text" maxlength="10" inputmode="numeric" value="<%= emp.getContactno() %>">
            <div id="errContactClient" class="field-error" style="display:none;"></div>
            <% if (errContact != null) { %><div class="field-error"><%= errContact %></div><% } %>
        </div>

        <button type="submit" class="btn">Update Employee</button>
    </form>
</div>

<script>
    function setErr(id,msg){ const el=document.getElementById(id); if(!el) return; if(msg){ el.style.display='block'; el.textContent=msg; } else { el.style.display='none'; el.textContent=''; } }

    const firstname = document.getElementById('firstname');
    const lastname  = document.getElementById('lastname');
    const username  = document.getElementById('username');
    const address   = document.getElementById('address');
    const contact   = document.getElementById('contact');
    const form      = document.getElementById('editForm');

    function sanitizeName(e){ const cleaned = e.target.value.replace(/[^A-Za-z]/g,''); if(cleaned !== e.target.value) e.target.value = cleaned; }
    firstname.addEventListener('input', sanitizeName);
    lastname.addEventListener('input', sanitizeName);

    contact.addEventListener('input', function(e){ const cleaned = e.target.value.replace(/\D/g,'').slice(0,10); if(cleaned !== e.target.value) e.target.value = cleaned; });

    firstname.addEventListener('blur', ()=> { if(!firstname.value || firstname.value.trim().length < 2) setErr('errFirstnameClient','Min 2 letters required'); else setErr('errFirstnameClient',''); });
    lastname.addEventListener('blur', ()=> { if(!lastname.value || lastname.value.trim().length < 2) setErr('errLastnameClient','Min 2 letters required'); else setErr('errLastnameClient',''); });
    username.addEventListener('blur', ()=> { if(!username.value.trim()) setErr('errUsernameClient','Username required'); else setErr('errUsernameClient',''); });
    address.addEventListener('blur', ()=> { if(!address.value.trim()) setErr('errAddressClient','Address required'); else setErr('errAddressClient',''); });
    contact.addEventListener('blur', ()=> { if(!/^\d{10}$/.test(contact.value)) setErr('errContactClient','Contact must be exactly 10 digits'); else setErr('errContactClient',''); });

    form.addEventListener('submit', function(e){
        let err=false;
        if(!firstname.value || firstname.value.trim().length < 2){ setErr('errFirstnameClient','Min 2 letters required'); if(!err) firstname.focus(); err=true; }
        if(!lastname.value || lastname.value.trim().length < 2){ setErr('errLastnameClient','Min 2 letters required'); if(!err) lastname.focus(); err=true; }
        if(!username.value.trim()){ setErr('errUsernameClient','Username required'); if(!err) username.focus(); err=true; }
        if(!address.value.trim()){ setErr('errAddressClient','Address required'); if(!err) address.focus(); err=true; }
        if(!/^\d{10}$/.test(contact.value)){ setErr('errContactClient','Contact must be exactly 10 digits'); if(!err) contact.focus(); err=true; }
        if(err) e.preventDefault();
    });
</script>

<jsp:include page="/WEB-INF/footer.jsp" />
