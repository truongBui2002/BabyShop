

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
	apiKey: "AIzaSyDbLvnQAtTUqLAYLE3F2x0du-ua_xAD4ko",
	authDomain: "sendotp-d3598.firebaseapp.com",
	projectId: "sendotp-d3598",
	storageBucket: "sendotp-d3598.appspot.com",
	messagingSenderId: "776466389576",
	appId: "1:776466389576:web:06c2232c88fdde7eff567d",
	measurementId: "G-HXTVZD75PM"
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
	if (value === "exists" && validatePhoneNumber(phone)) {
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
			sendTokenConfirm(result)
			logOutUserFirebase();
			return true;
		})
		.catch(function(error) {
			return false;
		});
}

function sendTokenConfirm(result) {
	console.log(result.user)
	result.user.getIdToken().then((token) => {
		console.log(token);
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "/login/phone/token");
		xhr.send(token);
	});
}

var popUpPhone = document.querySelector('.popup-register-phone');
validationFormRegister(popUpPhone);

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


		enableIn(otpValue);
		let countOTP = 3;

		otpValue.addEventListener('input', function() {
			if (otpValue.value.length === 6 && confirmationResult) {
				confirmOTP(otpValue.value)
					.then(function(success) {
						if (success) {
							// truong hop thanh conng
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
								otpValue.value = "";
								disabledIn(otpValue);
								showAlert('Gửi lại mã đi thằng ngu', 'warning', 'alertContainer');
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

	} account.addEventListener('input', () => {

		checkExistedPhone(account).then(function(result) {
			if (result) {

				otpBtn.addEventListener('click', handleClickPhone);
				account.classList.add('valid-form')
				account.classList.remove('invalid-form')
			} else {
				otpBtn.removeEventListener('click', handleClickPhone);
				account.classList.add('invalid-form')
				account.classList.remove('valid-form')
				if (account.value.length === 10) {
					showAlert('Phone number is invalid', 'warning', 'alertContainer');
				}
			}
		});
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