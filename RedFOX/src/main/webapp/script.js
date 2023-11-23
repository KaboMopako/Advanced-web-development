
const passwordConfirmInput = document.getElementById('passwordConfirm');
const registrationForm = document.querySelector('.form-container.sign-up form');
const checkbox = document.getElementById('registerCheck');
const signupButton = document.getElementById('signupButton');
const errorMessage = document.getElementById('errorMessage');


document.addEventListener('DOMContentLoaded', function() {
    var signUpForm = document.querySelector('.form-container.sign-up');
    var loginForm = document.querySelector('.form-container.sign-in');
    var toggleLoginBtn = document.getElementById('toggleLogin');
    var toggleSignupBtn = document.getElementById('toggleSignup');

    signUpForm.classList.add('active');
    toggleLoginBtn.classList.add('active');
    toggleSignupBtn.style.display = 'none'; // Hide the Sign Up toggle button initially

    toggleLoginBtn.addEventListener('click', function() {
        signUpForm.classList.remove('active');
        loginForm.classList.add('active');
        toggleSignupBtn.style.display = 'inline-block';
        this.style.display = 'none';
        toggleSignupBtn.classList.remove('active');
        this.classList.add('active');
    });

    toggleSignupBtn.addEventListener('click', function() {
        loginForm.classList.remove('active');
        signUpForm.classList.add('active');
        toggleLoginBtn.style.display = 'inline-block';
        this.style.display = 'none';
        toggleLoginBtn.classList.remove('active');
        this.classList.add('active');
    });
});


checkbox.addEventListener('change', () => {
    signupButton.disabled = !checkbox.checked;
    updateErrorMessage(); // Call the function to update the error message
});

registrationForm.addEventListener('submit', (event) => {
    const passwordInput = registrationForm.querySelector('input[name="password"]');
    const password = passwordInput.value;
    const passwordConfirm = passwordConfirmInput.value;

    if (password !== passwordConfirm) {
        event.preventDefault(); // Prevent form submission
        setErrorMessage("Passwords do not match. Please re-enter your password.");
    }
});

function setErrorMessage(message) {
    errorMessage.textContent = message;
}

function updateErrorMessage() {
    errorMessage.textContent = ""; // Clear the error message
}

