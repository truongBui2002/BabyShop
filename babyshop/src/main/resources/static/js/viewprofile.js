const genderInputs = document.querySelectorAll('input[type="radio"]');
genderInputs.forEach(input => {
	input.addEventListener('change', function() {
		genderInputs.forEach(input => {
			input.parentElement.classList.remove('selected');
		});

		if (this.checked) {
			this.parentElement.classList.add('selected');
		}
	});
});

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

fileInput.addEventListener('change', function(event) {
	const file = event.target.files[0];
	console.log(file);
	// Check if file size is within the limit (1MB)
	if (file && file.size <= 1024 * 1024) {
		const reader = new FileReader();

		reader.onload = function(event) {
			//avatarPreview.style.backgroundImage = url(${event.target.result});
			avatarPreview.src = reader.result;
		};

		reader.readAsDataURL(file);
	} else {
		alert('Please select a file smaller than 1MB.');
		fileInput.value = ''; // Reset file input
	}
});

avatarPreview.addEventListener('click', function() {
	fileInput.click();
});
const selectButton = document.querySelector('.btn_avatar');
selectButton.addEventListener('click', function() {
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

// update dob 
var d = document.getElementById("d");
var m = document.getElementById("m");
var y = document.getElementById("y");

var day = document.getElementById("day");
var month = document.getElementById("month");
var year = document.getElementById("year");


var btnC = document.querySelector(".btn-dob");
btnC.addEventListener("click", () => {
	d.textContent = day.value;
	m.textContent = month.value;
	y.textContent = year.value;
})

//set up value value select
var selectedDay = document.getElementById("d").innerHTML;
var selectedMonth = document.getElementById("m").innerHTML;
var selectedYear = document.getElementById("y").innerHTML;

day.selectedIndex = selectedDay - 1;
month.selectedIndex = selectedMonth - 1;
year.selectedIndex = year.options[0].textContent - selectedYear;

// gửi thêm data khi submit form
var profileForm = document.querySelector(".profile-info");
profileForm.addEventListener("submit", function(e) {
	e.preventDefault(); 

	const form = e.target;
	const day = document.querySelector('#day').value;
	const month = document.querySelector('#month').value;
	const year = document.querySelector('#year').value;

	const addDay = document.createElement("input");
	addDay.type = "hidden";
	addDay.name = "day";
	addDay.value = day;
	form.appendChild(addDay);

	const addMonth = document.createElement("input");
	addMonth.type = "hidden";
	addMonth.name = "month";
	addMonth.value = month;
	form.appendChild(addMonth);

	const addYear = document.createElement("input");
	addYear.type = "hidden";
	addYear.name = "year";
	addYear.value = year;
	form.appendChild(addYear);
	
	form.submit();
});


