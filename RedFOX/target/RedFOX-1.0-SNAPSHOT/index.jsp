<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="style.css">
    <title>RedFox Movies Rating Web Application</title>
</head>
<body>

    <div class="container" id="container">
        <div class="form-container sign-up">
            <form action="validation"   >
                <h1>Login</h1>
                <p>${loginMessage}</p>
                <input type="text"
                       name="email" placeholder="Email" required>
                <input type="text"
                       name="password" placeholder="Password" required>
                <input type="hidden" name="action" value="login">
                <button type="submit">Login</button>
            </form>
        </div>
        <div class="form-container sign-up">
            <form action="validation">
                <h1>Registration</h1>
                <p>${registrationMessage}</p>
                <input type="text"
                       name="email" placeholder="Email" required>
                <input type="text"
                       name="password" placeholder="Password" required>
                <input type="text"
                       name="password" placeholder="Password" required>
                <input type="text"
                       name="Fname" placeholder="First Name">
                <input type="text"
                       name="Lname" placeholder="Last Name">
                <input type="number"
                       name="Contact" placeholder="Email">
                <label for="gender">Gender:</label>
                <select id="gender" name="gender">
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="other">Other</option>
                </select>
                <input type="checkbox" value="" id="registerCheck" checked aria-describedby="registerCheckHelp"/>
                <label>
                    I have read and I agree to the terms
                </label>

                <input type="hidden" name="action" value="join">
                <button type="submit">Sign Up</button>
            </form>
            <div class="toggle-container">
                <div class="toggle">
                    <div class="toggle-panel toggle-left">
                        <h1>Welcome Back!</h1>
                        <p>Enter your personal details to use all of site features</p>
                        <button class="hidden" id="login">Login</button>
                    </div>
                    <div class="toggle-panel toggle-right">
                        <h1>Hello, Friend!</h1>
                        <p>Register with your personal details to use all of site features</p>
                        <button class="hidden" id="register">Sign Up</button>
                    </div>
                </div>
            </div>
        </div>
    </div>






<script src="scrip.js"></script>
</body>
</html>