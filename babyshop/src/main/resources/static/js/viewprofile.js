//option checkmark gender
var maleOption = document.getElementById('male');
var femaleOption = document.getElementById('female');

//femaleOption.setAttribute("checked", "true");

const femaleFunc = (e) => {
    femaleOption.setAttribute("checked", "true");
    e.preventDefault();
    if (maleOption.hasAttribute("checked")) {
        maleOption.removeAttribute('checked');
    }

}

const maleFunc = (e) => {
    e.preventDefault();
    maleOption.setAttribute("checked", "true");
    if (femaleOption.hasAttribute("checked")) {
        femaleOption.removeAttribute("checked");
    }
}
femaleOption.addEventListener('click', femaleFunc);
maleOption.addEventListener('click', maleFunc);

// // process disable input
// const email = document.getElementById('email');
// const phoneNumber = document.getElementById('phoneNumber');

// const spanEmail = document.getElementById("email_span");
// const spanPhone = document.getElementById("phoneNumber_span");

// const clickEmailSpan = () => {
//     if(email.hasAttribute('disabled')) {
//         email.removeAttribute('disabled');
//         email.style.background = 'none'; 
//     }
// }
// const clickPhoneSpan = () => {
//     if(phoneNumber.hasAttribute('disabled')) {
//         phoneNumber.removeAttribute('disabled');
//         phoneNumber.style.background = 'none';
//     }
// }
// spanEmail.addEventListener('click', clickEmailSpan);
// spanPhone.addEventListener('click', clickPhoneSpan);

// constraints phone number with OTP

//dob
var dayDropdown = document.getElementById("day");
for (var i = 1; i <= 31; i++) {
    var option = document.createElement("option");
    option.text = i;
    dayDropdown.add(option);
}

// Populate the month dropdown
var monthDropdown = document.getElementById("month");
var months = [
    "1", "2", "3", "4",
    "5", "6", "7", "8",
    "9", "10", "11", "12"
];
for (var j = 0; j < months.length; j++) {
    var option = document.createElement("option");
    option.text = months[j];
    monthDropdown.add(option);
}

// Populate the year dropdown
var yearDropdown = document.getElementById("year");
var currentYear = new Date().getFullYear();
for (var k = currentYear; k >= 1900; k--) {
    var option = document.createElement("option");
    option.text = k;
    yearDropdown.add(option);
}


//avatar
const fileInput = document.querySelector('.type__image');
const avatarPreview = document.querySelector('.image__link');

fileInput.addEventListener('change', function (event) {
    const file = event.target.files[0];
	console.log(file);
    // Check if file size is within the limit (1MB)
    if (file && file.size <= 1024 * 1024) {
        const reader = new FileReader();

        reader.onload = function (event) {
            //avatarPreview.style.backgroundImage = url(${event.target.result});
            avatarPreview.src = reader.result;
        };

        reader.readAsDataURL(file);
    } else {
        alert('Please select a file smaller than 1MB.');
        fileInput.value = ''; // Reset file input
    }
});

avatarPreview.addEventListener('click', function () {
    fileInput.click();
});
const selectButton = document.querySelector('.btn_avatar');
selectButton.addEventListener('click', function () {
    fileInput.click();
});

/* end avatar*/

var a = document.querySelectorAll('.FEE-3D');
console.log(a);
a.forEach((e) => {
    e.addEventListener('click', () => {
        a.forEach((c) => {
            c.classList.remove('tH0d6d');
        })
        e.classList.add('tH0d6d');
    });
});