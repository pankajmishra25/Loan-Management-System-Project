/*Function to show loan details*/
function showLoanDetails() {
    let select = document.getElementById("loanType");
    let option = select.options[select.selectedIndex];

    if (option.value === "") {
        document.getElementById("loanDetails").style.display = "none";
        return;
    }

    document.getElementById("rate").innerText = option.getAttribute("data-rate");
    document.getElementById("maxAmount").innerText = option.getAttribute("data-maxAmount");
    document.getElementById("maxDuration").innerText = option.getAttribute("data-maxDuration");

    document.getElementById("loanDetails").style.display = "block";
}

/*Function to update loan purpose*/
function updatePurpose() {
    let option = document.getElementById("loanType").selectedOptions[0];
    let type = (option.getAttribute("data-type") || "").toLowerCase();

    let map = {
        home: ["Buy house", "Construction", "Renovation"],
        car: ["Buy vehicle", "Used vehicle"],
        personal: ["Medical", "Travel", "Wedding"],
        education: ["Studies", "Fees"],
        business: ["Startup", "Expansion"]
    };

    let list = [];
    for (let k in map) {
        if (type.includes(k)) list = map[k];
    }

    let p = document.getElementById("purpose");
    p.innerHTML = "<option value=''>--Select--</option>";
    list.forEach(x => {
        let o = document.createElement("option");
        o.value = x;
        o.text = x;
        p.appendChild(o);
    });
}

/*Function to validate loan*/
function validateLoan() {

    let opt = document.getElementById("loanType").selectedOptions[0];

    if (!opt || opt.value === "") {
        alert("Please select loan type");
        return false;
    }

    let amt = parseFloat(document.querySelector("[name='amount']").value);
    let dur = parseInt(document.querySelector("[name='duration']").value);

    if (isNaN(amt) || amt <= 0) {
        alert("Enter valid amount");
        return false;
    }

    if (isNaN(dur) || dur <= 0) {
        alert("Enter valid duration");
        return false;
    }

    let maxAmount = parseFloat(opt.getAttribute("data-maxAmount"));
    let maxDuration = parseInt(opt.getAttribute("data-maxDuration"));

    if (amt > maxAmount) {
        alert("Amount exceeds maximum allowed");
        return false;
    }

    if (dur > maxDuration) {
        alert("Duration exceeds maximum allowed");
        return false;
    }

    // FINAL CONFIRMATION
    return confirm("Are you sure you want to submit this loan application?");
}