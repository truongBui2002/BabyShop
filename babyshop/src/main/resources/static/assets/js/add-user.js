
document.addEventListener('DOMContentLoaded', function () {
	const imageInput = document.getElementById('imageInput');
	const imageContainer = document.getElementById('imageContainer');

	imageInput.addEventListener('change', function () {
		imageContainer.innerHTML = ''; // Clear existing images

		for (const file of imageInput.files) {
			const reader = new FileReader();
			const imgElement = document.createElement('img');

			reader.addEventListener('load', function () {
				imgElement.setAttribute('src', reader.result);
			});

			reader.readAsDataURL(file);

			imgElement.style.width = '100px';
			imgElement.style.height = '100px';
			imgElement.classList.add("profile-pic");
			imgElement.classList.add("rounded");
			imageContainer.appendChild(imgElement);
		}
	});
});

var form_add_user = document.querySelector('#form-add-user');
var form_image = document.querySelector('#form-image');

form_add_user.addEventListener("submit", function (e) {
	e.preventDefault();
	const form = e.target;
	var valueRole = form_image.querySelector('.selected-role').value;
	var inputFile = form_image.querySelector('#imageInput');
	var inf = inputFile.cloneNode(true);
	inf.style.display = 'none';

	const role = document.createElement("input");
	role.type = "hidden";
	role.name = "role";
	role.value = valueRole;

	form_add_user.appendChild(role);
	form_add_user.appendChild(inf);
	form.submit();
})

var firstNameInput = document.getElementById('fname');

function showError(errorMessage, parentN) {
	var errorDiv = document.createElement('div');
	errorDiv.textContent = errorMessage;
	errorDiv.style.color = 'red';
	errorDiv.setAttribute('id', 'error-message');

	var form = parentN.parentNode;
	form.insertBefore(errorDiv, parentN.nextSibling);
}

function removeError(err) {
	var pE = err.closest(".form-group");

	var errorDiv = pE.querySelectorAll('#error-message');
	console.log(errorDiv);
	if (errorDiv !== null) {
		errorDiv.forEach((c) => {
			pE.removeChild(c);
		})
	}
}
function validateFirstName() {
	var firstName = firstNameInput.value.trim();

	if (firstName === '') {
		removeError(firstNameInput);
		showError('Please enter your name.', firstNameInput);
		return false;
	}

	var namePattern = /^[A-Za-z]+(\s[A-Za-z]+)+$/;
	if (!namePattern.test(firstName)) {
		removeError(firstNameInput);
		showError('Please enter a valid name.', firstNameInput);
		return false;
	}

	removeError(firstNameInput);

	return true;
}
firstNameInput.addEventListener("blur", validateFirstName);

//phone number 
var pNum = document.getElementById("mobno");
console.log(pNum)
function validatePhone() {
	var phoneNum = pNum.value.trim();
	if (phoneNum === '') {
		removeError(pNum);
		showError('Please enter your phone number.', pNum);
		return false;
	}

	if (!validatePhoneNumber(phoneNum)) {
		removeError(pNum);
		showError('Please enter a valid phone name.', pNum);
		return false;
	}
	removeError(pNum);
	return true;
}

function validatePhoneNumber(phoneNumber) {
	var pattern = /^0\d{9}$/;
	var cleanPhoneNumber = phoneNumber.replace(/\D/g, '');

	if (cleanPhoneNumber.match(pattern)) {
		return true;
	} else {
		return false;
	}
}
pNum.addEventListener('blur', validatePhone);

//date
function getCurrentDate() {
	const today = new Date();
	const year = today.getFullYear();
	const month = String(today.getMonth() + 1).padStart(2, '0');
	const day = String(today.getDate()).padStart(2, '0');
	return `${year}-${month}-${day}`;
}

document.getElementById('add1').value = getCurrentDate();


//email
function validateGmail(email) {
	var gmailPattern = /^[a-zA-Z0-9._%+-]+@gmail.com$/;


	if (email.match(gmailPattern)) {
		return true;
	} else {
		return false;
	}
}
var eM = document.getElementById("email");
function validateEmail() {
	var eMail = eM.value.trim();
	if (eMail === '') {
		removeError(eM);
		showError('Please enter your email.', eM);
		return false;
	}

	if (!validateGmail(eMail)) {
		removeError(eM);
		showError('Please enter a valid email.', eM);
		return false;
	}
	removeError(eM);
	return true;
}

eM.addEventListener("blur", validateEmail);



document.getElementById('pass').addEventListener('blur', validatePassword);
document.getElementById('rpass').addEventListener('input', validatePassword);

function validatePassword() {
  var passInput = document.getElementById('pass');
  var rpassInput = document.getElementById('rpass');
  var errorMessage = document.getElementById('error-p');

  var password = passInput.value;
  var repeatPassword = rpassInput.value;

  if (password !== '' && repeatPassword !== '') {

    if (password !== repeatPassword) {
      errorMessage.textContent = 'Passwords do not match.';
      return false;
    }
  }


  errorMessage.textContent = '';
  return true;
}
