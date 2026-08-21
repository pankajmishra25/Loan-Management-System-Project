/* Function to switch Customer/Admin tabs */
function selectRole(role, btn) {

    let roleField = document.getElementById("role");
    roleField.value = role;

    // Remove active class from all tabs
    let tabs = document.querySelectorAll(".role-tab");

    tabs.forEach(function(tab) {
        tab.classList.remove("active");
    });

    // Add active class to clicked tab
    btn.classList.add("active");

    // Show / Hide Register link
    let registerBox = document.getElementById("registerBox");

    if (registerBox) {
        if (role === "admin") {
            registerBox.style.display = "none";
        } else {
            registerBox.style.display = "block";
        }
    }

    // Clear role error when tab selected
    document.getElementById("roleError").innerHTML = "";
}


/* Function to validate user login */
function validateLogin() {

    let emailField = document.getElementById("email");
    let passwordField = document.getElementById("password");
    let roleField = document.getElementById("role");

    let email = emailField.value.trim();
    let password = passwordField.value.trim();
    let role = roleField.value;

    let valid = true;

    // Clear previous errors
    document.getElementById("emailError").innerHTML = "";
    document.getElementById("passError").innerHTML = "";
    document.getElementById("roleError").innerHTML = "";

    // Email / Username validation
    if (email === "") {
        document.getElementById("emailError").innerHTML =
            "❌ Email/Username is required";

        emailField.focus();
        valid = false;
    }

    // Password validation
    if (password === "") {
        document.getElementById("passError").innerHTML =
            "❌ Password is required";

        if (valid) passwordField.focus();

        valid = false;

    } else if (password.length < 6) {

        document.getElementById("passError").innerHTML =
            "❌ Minimum 6 characters required";

        if (valid) passwordField.focus();

        valid = false;
    }

    // Role validation
    if (role === "") {

        document.getElementById("roleError").innerHTML =
            "❌ Please select a role";

        if (valid) roleField.focus();

        valid = false;
    }

    // Email format validation only for customer
    let emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;

    if (role === "customer" && email !== "" &&
        !emailPattern.test(email)) {

        document.getElementById("emailError").innerHTML =
            "❌ Enter valid email";

        if (valid) emailField.focus();

        valid = false;
    }

    return valid;
}