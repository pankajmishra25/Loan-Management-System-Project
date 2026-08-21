/*Function to Confirm paymnet*/
function confirmPayment() {
    let amount = document.getElementById("amount").value;

    if (confirm("Are you sure you want to pay ₹" + amount + " ?")) {
        document.getElementById("payBtn").disabled = true;
        document.getElementById("payBtn").innerText = "Processing...";
        return true;
    }
    return false;
}
