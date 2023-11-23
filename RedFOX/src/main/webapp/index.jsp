<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<link rel="stylesheet" href="style2.css">
<title>RedFox Movies Rating Web Application</title>
<style>
body {
	max-width: 100%;
}
.header{
	width:100%;
	background-color: #333;
}

.footer {
	width: 100%;
	background-color: #333;
	color: white;
	text-align: center;
}

.main {
	width: 100%;
	margin: 0 auto; /* Center the container */
}
/* Styling for the cookie prompt */
.cookie-prompt { 
	display: none;/* Initially hidden */
	position: fixed;
	bottom: 20px;
	right: 20px;
	width: 300px;
	background-color: #f7f7f7;
	border: 1px solid #ddd;
	border-radius: 5px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	padding: 15px;
	animation: slideIn 0.5s forwards;
}
/* Form container styling */
.form-container {
	padding: 30px; /* Increased padding for space inside the form */
	margin: 30px auto 50px; /* Adjusted margins */
	display: none;
	
	border-radius: 8px;
	background-color: #fff;
	max-width: 400px;
	/* Center horizontally and create space from the top */
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	border-left: 5px solid #e50914; /* Nice left border */
	transition: margin-left 0.3s ease;
	/* Smooth transition for margin change */
}

.form-container.active {
	display: block;
}

/* Input fields */
.form-container input[type="text"], .form-container input[type="password"],
	.form-container input[type="number"], .form-container select {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 3px;
	font-size: 16px;
}

/* Buttons */
.form-container button {
	width: 100%;
	padding: 12px 20px;
	border: none;
	border-radius: 3px;
	background-color: #e50914;
	color: #fff;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.form-container button:hover {
	background-color: #ff0f24;
}

/* Toggle buttons */
.toggle-container {
	text-align: center;
	margin-top: 3px;
}

.toggle-container button {
	padding: 5px 15px;
	margin: 5px;
	border: none;
	border-radius: 3px;
	cursor: pointer;
	transition: background-color 0.3s ease;
	font-size: 16px;
}

.toggle-container button.active {
	background-color: #fff;
	color: #333;
	border: 1px solid #333;
}
/* Buttons */
.form-container button {
	width: calc(100% - 40px); /* Adjust width with padding */
	padding: 12px 20px;
	border: none;
	border-radius: 3px;
	background-color: #e50914;
	color: #fff;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s ease;
	box-sizing: border-box; /* Includes padding in width calculation */
}

.form-container button:hover {
	background-color: #ff0f24;
	border-color: #e50914;
}

/* Animation keyframes */
@keyframes slideIn {
  from { transform: translateY(100%); }
  to { transform: translateY(0); }
}
}
/* Welcome message styling */
</style>
</head>

<body>

	<%!private String getRememberedEmail(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("remembered_email")) {
					return cookie.getValue();
				}
			}
		}
		return ""; // Return empty string if remembered_email cookie is not found
	}%>
	<div id="cookiePrompt" class="cookie-prompt">
		<p>
			This website uses cookies to ensure you get the best experience. By
			using our website, you agree to our <a href="#">Privacy Policy</a>
			regarding cookies.
		</p>
		<button onclick="acceptCookies()">Accept</button>
	</div>
	<div class="header">
		<div class="welcome-message">
			<h2>Welcome to RedFox Agency!</h2>
			<p>Please sign in or sign up to access our services.</p>
		</div>
	</div>
	<div class="main">
		<div class="form-container sign-up active">
			<form action="validation" method="Post">
				<h1>Registration</h1>
				<p class="error-message">${registrationMessage}</p>

				<!-- Retrieve remembered email from cookie and prefill the email input -->
				<input type="text" name="email" placeholder="Email" required
					value="<%=getRememberedEmail(request)%>"> <input
					type="password" name="password" placeholder="Password" required>
				<input type="password" name="passwordConfirm" id="passwordConfirm"
					placeholder="Re-enter Password" required>
				<div class="error-message" id="errorMessage"></div>
				<input type="text" name="Fname" placeholder="First Name"> <input
					type="text" name="Lname" placeholder="Last Name"> <input
					type="number" name="Contact" placeholder="Contact">

				<div class="gender-select">
					<label for="gender">Gender:</label> <select id="gender"
						name="gender">
						<option value="male">Male</option>
						<option value="female">Female</option>
						<option value="other">Other</option>
					</select>
				</div>
				<div class="preferredGenre">
					<label for="preferredGenre">Preferred Genre:</label> <select
						id="prefGen" name="prefGen">
						<option value="action">Action</option>
						<option value="animation">Animation</option>
						<option value="drama">Drama</option>
						<!-- Add more genre options as needed -->
					</select>
				</div>
				<input type="checkbox" value="" id="registerCheck"> <label
					for="registerCheck" class="left-checkbox-label">I have read
					and I agree to the terms</label> <input type="hidden" name="action"
					value="join"> <input type="checkbox" name="rememberMe"
					value="true" id="rememberMe"> <label for="rememberMe">Remember
					Me</label>
				<button type="submit" id="signupButton" disabled>Sign Up</button>

			</form>
		</div>

		<div class="form-container sign-in">
			<form action="validation" method="post">
				<h1>Login</h1>
				<p class="error-message">${loginMessage}</p>
				<!-- Retrieve remembered email from cookie and prefill the email input -->
				<input type="text" name="email" placeholder="Email" required
					value="<%=getRememberedEmail(request)%>"> <input
					type="password" name="password" placeholder="Password" required>
				<input type="hidden" name="action" value="login"> <input
					type="checkbox" name="rememberMe" value="true" id="rememberMe">
				<label for="rememberMe">Remember Me</label>
				<button type="submit">Login</button>
			</form>
		</div>
		<div class="toggle-container">
			<button id="toggleLogin" class="active">Sign In</button>
			<button id="toggleSignup">Sign Up</button>
		</div>
	</div>
	<div class="footer"></div>




	<script>
		//Function to show the cookie prompt
		function showCookiePrompt() {
		    var cookiePrompt = document.getElementById('cookiePrompt');
		    cookiePrompt.style.display = 'block';
		}
		
		// Function to accept cookies
		function acceptCookies() {
		    document.cookie = 'cookies_accepted=true; expires=Fri, 31 Dec 9999 23:59:59 GMT; path=/';
		    var cookiePrompt = document.getElementById('cookiePrompt');
		    cookiePrompt.style.display = 'none';
		}
		
		// Check if cookies have been accepted; if not, display the prompt
		window.onload = function() {
		    var cookiesAccepted = document.cookie.split(';').some((item) => item.trim().startsWith('cookies_accepted='));
		    if (!cookiesAccepted) {
		        showCookiePrompt();
		    }
		};

	</script>
	<script src="script.js"></script>
</body>
</html>
