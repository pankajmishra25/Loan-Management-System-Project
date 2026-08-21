/*Method to validate old password*/
function validatePassword() {
    let newPass = document.querySelector("[name='newPassword']").value;
    let confirmPass = document.querySelector("[name='confirmPassword']").value;

    if (newPass !== confirmPass) {
        alert("Passwords do not match!");
        return false;
    }
    return true;
}