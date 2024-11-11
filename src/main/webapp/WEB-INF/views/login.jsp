<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="cheader.jsp"></jsp:include>
<div class="main">
    <div class="content">
        <div class="login_panel" style="width: 400px;">
            
            
            <!-- Display success message above the form -->
            <% if (session.getAttribute("successMessage") != null) { %>
                <div class="alert alert-success">
                    <%= session.getAttribute("successMessage") %>
                </div>
                <% session.removeAttribute("successMessage"); %>
            <% } %>
 
            <!-- Display error message above the form -->
            <% if (session.getAttribute("errorMessage") != null) { %>
                <div class="alert alert-danger">
                    <%= session.getAttribute("errorMessage") %>
                </div>
                <% session.removeAttribute("errorMessage"); %>
            <% } %>
            <h3>Existing Customers</h3>
            <p>Sign in with the form below.</p>
 
            <form method="post" autocomplete="off" action="/login">
                <input name="userid" type="text" placeholder="User ID" required>
                <input name="pwd" type="password" placeholder="Password" required>
                <div class="buttons"><div><button class="grey">Sign In</button></div></div>
            </form>
        </div>
        
        <div class="register_account" style="width:1000px;">
            <img src="images/download.png" class="img-thumbnail float-right">
            <h3>Register New Account</h3>
            <form autocomplete="off" action="/register/" method="post">
                <table>
                    <tbody>
                        <tr>
                            <td>
                                <div>
                                    <input name="fname" required type="text" placeholder="First Name">
                                    <input name="lname" required type="text" placeholder="Last Name">
                                    <input name="userid" required type="text" placeholder="User ID">
                                    <input name="dob" required type="date">
                                    <select name="gender" required>
                                        <option>Male</option>
                                        <option>Female</option>
                                    </select>
                                    <input type="email" required name="email" placeholder="Email Id">
                                    <input type="password" required name="pwd" placeholder="Password">
                                    <input type="password" required name="cpwd" placeholder="Repeat Password">
                                </div>
                            </td>                            
                        </tr>
                    </tbody>
                </table>
                <div class="buttons"><div><button class="grey">Create Account</button></div></div>
                <p class="terms">By clicking 'Create Account' you agree to the <a href="#">Terms &amp; Conditions</a>.</p>
                <div class="clear"></div>
            </form>
        </div>  
        <div class="clear"></div>
    </div>
</div>
<jsp:include page="cfooter.jsp"></jsp:include>
 