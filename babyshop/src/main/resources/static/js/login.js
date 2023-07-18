
async function sendata(url, data) {
	var value;
	await fetch(url, {
		method: 'POST',
		body: JSON.stringify(data),
		headers: {
			'Content-Type': 'application/json'
		}
	}).then(res => {
		return res.text();
	}).then(text => {
		value = text;
	});
	return value;
}

const firebaseConfig = {
	apiKey: "AIzaSyBk8Rd-TIwUuwUoxjtcEqpiz043pcZVikE",
	authDomain: "localhost",
	projectId: "otp-project-9efb7",
	storageBucket: "otp-project-9efb7.appspot.com",
	messagingSenderId: "139847367306",
	appId: "1:139847367306:web:a216a0847311f2fb974b73",
	measurementId: "G-78Z0MX4QC4"
};
firebase.initializeApp(firebaseConfig);
//send code by Phone
function sendOTPPhone(e, otpValue) {
	var accountType = e.value;
	console.log(otpValue);
	var phoneNumBer = accountType.replace(/^0/, "+84");
	phoneAuth(phoneNumBer, otpValue, e);
}
// 1 function check existed in database
async function checkExistedPhone(e) {
	var url = "/login/checkexits";
	var phone = e.value;
	var value = await sendata(url, phone);
	if (value === "not exists" && validatePhoneNumber(phone)) {
		return true;
	} else {
		return false;
	}
}

function phoneAuth(phoneNumber, otpValue, acc) {

	const recaptchaVerifier = new firebase.auth.RecaptchaVerifier('recaptcha-container', {
		size: 'invisible'
	});
	firebase.auth().signInWithPhoneNumber(phoneNumber, recaptchaVerifier)
		.then(function(result) {
			confirmationResult = result;
			console.log("Gửi mã OTP thành công");
			showAlert('Check your phone to get OTP Code!', 'success', 'alertContainer');
			logOutUserFirebase;
		})
		.catch(function(error) {
			console.log("Gửi mã OTP thất bại:", error);
			showAlert("Send OTP Code failed! Try again", "warning", "alertContainer")
			disabledIn(otpValue);
			enableIn(acc);
			logOutUserFirebase

		});
}
function confirmOTP(otpValue) {
	return confirmationResult.confirm(otpValue)
		.then(function(result) {
			return true;
		})
		.catch(function(error) {
			return false;
		});
}

// send OTP in email


async function checkExistsEmail(e) {
	const url = "/login/email/exists";
	var email = e.value;
	var value = await sendata(url, email);
	if (value === "not exists" && validateGmail(email)) {
		return true;
	} else {
		return false;
	}
}
async function sendOTPEmail(email) {
	var url = "/login/sendcode/email";
	var data = email.value;
	var value = await sendata(url, data);
	console.log(value);
	return value;
}
async function checkOTPEmail(email, code) {
	var url = "/login/authen/email";
	var data = [email, code];
	console.log(JSON.stringify(data));
	var value = await sendata(url, data);

	if (validateOTP(code) && value === "true") {
		return true;
	} else {
		return false;
	}

}

function showTab(event, tabNumber) {
	event.preventDefault();
	var tabPanels = document.querySelectorAll('.tabs__panel');
	tabPanels.forEach(function(panel) {
		panel.style.display = 'none';
	});

	var tabLinks = document.querySelectorAll('.tabs__tab');
	tabLinks.forEach(function(link) {
		link.classList.remove('is-selected');
	});


	var selectedTabPanel = document.getElementById(tabNumber);
	selectedTabPanel.classList.remove('hidden-tab');
	selectedTabPanel.style.display = 'block';


	var selectedTabLink = document.getElementById(tabNumber + '-tab');
	selectedTabLink.classList.add('is-selected');
	if (tabNumber == 1) {
		var selectedTabLink1 = document.getElementById('1-tab');
		var selectedTabLink2 = document.getElementById('2-tab');
		selectedTabLink1.style.backgroundColor = '#fff';
		selectedTabLink2.style.backgroundColor = '#f7efef';
	}
	if (tabNumber == 2) {
		var selectedTabLink1 = document.getElementById('1-tab');
		var selectedTabLink2 = document.getElementById('2-tab');
		selectedTabLink2.style.backgroundColor = '#fff';
		selectedTabLink1.style.backgroundColor = '#f7efef';
	}
}


/* valid form - login*/
const userInputted = document.getElementById("email-login");
const passInputted = document.getElementById("password-login");

const btnLogin = document.getElementById("btn-login1");

function checkField() {
	if (userInputted.value !== '' && passInputted.value !== '') {
		btnLogin.disabled = false;
		btnLogin.style.cursor = 'pointer';
	} else {
		btnLogin.disabled = true;
	}
}

userInputted.addEventListener('input', checkField);
passInputted.addEventListener('input', checkField);


//new register form
var phoneTab = document.querySelector('.popup-login-tab-login');
var emailTab = document.querySelector('.popup-login-tab-register');

var popUpPhone = document.querySelector('.popup-register-phone');
var popUpEmail = document.querySelector('.popup-register-email');

validationFormRegister(popUpPhone);

function switchToEmailSection() {
	phoneTab.classList.remove("active");
	emailTab.classList.add("active");
	popUpPhone.style.display = "none";
	popUpEmail.style.display = "block";
	validationFormRegister(popUpEmail);
}
function switchToPhoneSection() {
	phoneTab.classList.add("active");
	emailTab.classList.remove("active");
	popUpPhone.style.display = "block";
	popUpEmail.style.display = "none";
	validationFormRegister(popUpPhone);
}
phoneTab.addEventListener("click", switchToPhoneSection);
emailTab.addEventListener("click", switchToEmailSection);


function validationFormRegister(e) {
	var account = e.querySelector('.js-account');
	var otpBtn = e.querySelector('.js-otp');
	var otpValue = e.querySelector('.js-otpValue');
	var rgtPass = e.querySelector('.js-pass');
	var rgtRePass = e.querySelector('.js-repass');
	var rgtBtn = e.querySelector('.js-register');
	var rgtHiddenInput = e.querySelector(".rgt-hidden");

	if (emptyIn(otpValue) && emptyIn(rgtPass) && emptyIn(rgtRePass)) {
		disabledIn(otpValue);
		disabledIn(rgtPass);
		disabledIn(rgtRePass);
		disabledIn(rgtBtn);
	}

	//valid function
	function handleClickPhone() {
		disabledIn(account);
		sendOTPPhone(account, otpValue);

		console.log("logOutUserFirebase");

		enableIn(otpValue);
		let countOTP = 3;

		otpValue.addEventListener('input', function() {
			if (otpValue.value.length === 6 && confirmationResult) {
				confirmOTP(otpValue.value)
					.then(function(success) {
						if (success) {
							
							enableIn(rgtPass);
							enableIn(rgtRePass);
							disabledIn(otpValue);
							rgtHiddenInput.value =  account.value;
							otpValue.classList.add('valid-form');
							if (!(rgtPass.disabled && rgtRePass.disabled)) {
								rgtRePass.addEventListener('blur', function() {
									if (validateRePassword(rgtPass.value, rgtRePass.value)) {
										console.log('true pass');
										enableIn(rgtBtn);
									} else {
										showAlert('Password is not match, try again!', 'warning', 'alertContainer');
										disabledIn(rgtBtn);
									}
								});
								rgtPass.addEventListener('input', function() {
									if (validateRePassword(rgtPass.value, rgtRePass.value)) {
										console.log('true pass');
										enableIn(rgtBtn);
									} else {

										disabledIn(rgtBtn);
									}
								});
							}
						} else {
							countOTP--;
							showAlert('OTP verification failed, you have 3 attempts', 'warning', 'alertContainer');
							if (countOTP === 0) {
								enableIn(account);
							}
						}
					})
					.catch(function(error) {
						showAlert('OTP Error', 'warning', 'alertContainer');
						enableIn(account);
						disabledIn(otpValue);
					});
			}
		});

	}
	function handleClickEmail() {
		// disabledIn(account);
		//send OTP email
		sendOTPEmail(account);
		showAlert('Check your email to get OTP Code!', 'success', 'alertContainer');
		enableIn(otpValue);
		let countOTP = 3;
		otpValue.addEventListener('input', function() {
			if (otpValue.value.length === 6) {
				checkOTPEmail(account.value, otpValue.value)
					.then(function(result) {
						console.log(result);
						if (result) {

							enableIn(rgtPass);
							enableIn(rgtRePass);
							otpValue.classList.add('valid-form')
							disabledIn(otpValue);
							rgtHiddenInput.value =  account.value;
							if (!(rgtPass.disabled && rgtRePass.disabled)) {
								rgtRePass.addEventListener('blur', function() {
									if (validateRePassword(rgtPass.value, rgtRePass.value)) {
										console.log('true pass');
										enableIn(rgtBtn);
									} else {
										showAlert('Password is not match, try again!', 'warning', 'alertContainer');
										disabledIn(rgtBtn);
									}
								});
								rgtPass.addEventListener('input', function() {
									if (validateRePassword(rgtPass.value, rgtRePass.value)) {
										console.log('true pass');
										enableIn(rgtBtn);
									} else {

										disabledIn(rgtBtn);
									}
								});
							}
						} else {
							if (otpValue.value.length === 6) {
								showAlert('OTP verification failed, you have 3 attempts', 'warning', 'alertContainer');
								if (countOTP === 0) {
									enableIn(account);
								}
							}
						}
					})
					.catch(function(error) {
						console.log("Lỗi xác thực OTP:", error);
					});
			}

		});

	}
	account.addEventListener('input', () => {
		if (account.name === 'rgt-phone') {
			checkExistedPhone(account).then(function(result) {
				if (result) {

					otpBtn.addEventListener('click', handleClickPhone);
					account.classList.add('valid-form')
					account.classList.remove('invalid-form')
				} else {

					otpBtn.removeEventListener('click', handleClickPhone);
					account.classList.add('invalid-form')
					account.classList.remove('valid-form')
					//if (account.value.length === 10) {
					//showAlert('Phone number is invalid or already exists!!!', 'warning', 'alertContainer');
					//}
				}
			});

		}
		//email
		else if (account.name === 'rgt-email') {
			checkExistsEmail(account).then(function(result) {
				if (result) {
					otpBtn.addEventListener('click', handleClickEmail);
					account.classList.add('valid-form')
					account.classList.remove('invalid-form')
				} else {
					otpBtn.removeEventListener('click', handleClickEmail);
					account.classList.add('invalid-form')
					account.classList.remove('valid-form')
				}
			})

		}
	});
};



//validation method
function emptyIn(e) {
	return e.value === "";
}

function disabledIn(e) {
	if (e !== null) {
		e.disabled = true;
	}
}

function enableIn(e) {
	if (e !== null) {
		e.disabled = false;
		e.focus();
	}
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
function validateGmail(email) {
	var gmailPattern = /^[a-zA-Z0-9._%+-]+@gmail.com$/;


	if (email.match(gmailPattern)) {
		return true;
	} else {
		return false;
	}
}

function clearInputField(inputElement) {
	inputElement.value = "";
}

function validateOTP(otp) {
	var pattern = /\d{6}$/;
	var cleanotp = otp.replace(/\D/g, '');

	if (cleanotp.match(pattern)) {
		return true;
	} else {
		return false;
	}
}

function validateRePassword(password, rePassword) {
	var passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$/;

	var isMatch = password === rePassword;
	var isValid = rePassword.match(passwordPattern);

	return isMatch && isValid;
}



// var mArlet = arlet.querySelector('.alert-primary');
// console.log(mArlet);
function showAlert(message, type, targetElementId) {
	var targetElement = document.getElementById(targetElementId);


	var alertElement = document.createElement('div');
	alertElement.classList.add('alert');
	alertElement.classList.add('alert-' + type);
	alertElement.textContent = message;

	targetElement.appendChild(alertElement);


	new bootstrap.Alert(alertElement);
	setTimeout(function() {
		alertElement.classList.add('show');
	}, 10);
	setTimeout(function() {
		alertElement.classList.remove('show');
		setTimeout(function() {
			alertElement.remove();
		}, 300);
	}, 1000);
}


var rgtP = document.getElementById('rgt-phone');
rgtP.addEventListener('input', (e) => {
	var value = e.target.value;

	var sanitizedValue = value.replace(/[^0-9]/g, '');
	e.target.value = sanitizedValue;
})


function logOutUserFirebase() {
	console.log('Logout Btn Call')
	firebase.auth().signOut().then(() => {
		console.log("Logged Out");
	}).catch(e => {
		console.log(e)
	})
}



