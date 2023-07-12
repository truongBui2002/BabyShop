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
function sendOTPPhone(e) {
	var accountType = e.value;
	var phoneNumBer = accountType.replace(/^0/, "+84");
	phoneAuth(phoneNumBer);
}
// 1 function check existed in database
async function checkExistedPhone(e) {
	var url = "/login/checkexits";
	var phone = e.value;
	var value = await sendata(url, phone);
	return value === "not exists" ? true : false;


}

function phoneAuth(phoneNumber) {

	const recaptchaVerifier = new firebase.auth.RecaptchaVerifier('recaptcha-container', {
		size: 'invisible'
	});
	firebase.auth().signInWithPhoneNumber(phoneNumber, recaptchaVerifier)
		.then(function (result) {
			confirmationResult = result;
			console.log("Gửi mã OTP thành công");
		})
		.catch(function (error) {
			console.log("Gửi mã OTP thất bại:", error);
		});

}
function confirmOTP(otpValue) { 
	return confirmationResult.confirm(otpValue)
		.then(function (result) {
			return true;
		})
		.catch(function (error) {
			return false;
		});
}

// send OTP in email
async function sendOTPEmail(e) {
	const url = "/login/sendcode/email";
	var email = e.value;
	var value = await sendata(url, email);
	console.log(value);
}


function showTab(event, tabNumber) {
	event.preventDefault();
	var tabPanels = document.querySelectorAll('.tabs__panel');
	tabPanels.forEach(function (panel) {
		panel.style.display = 'none';
	});

	var tabLinks = document.querySelectorAll('.tabs__tab');
	tabLinks.forEach(function (link) {
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


	if (emptyIn(otpValue) && emptyIn(rgtPass) && emptyIn(rgtRePass)) {
		disabledIn(otpValue);
		disabledIn(rgtPass);
		disabledIn(rgtRePass);
		disabledIn(rgtBtn);
	}
	account.addEventListener('input', () => {
		if (account.name === 'rgt-phone') {
			if (validatePhoneNumber(account.value)) {
				checkExistedPhone(account).then(function (result) {
					console.log(result);
					if (result) {
						otpBtn.addEventListener('click', function handleClick() {
							
							disabledIn(account);
							sendOTPPhone(account);

							enableIn(otpValue);
							let countOTP = 3;
							otpValue.addEventListener('input', function () {
								console.log(confirmationResult);
								if (otpValue.value.length === 6 && confirmationResult) {
									confirmOTP(otpValue.value)
										.then(function (success) {
											if (success) {
												console.log("Xác thực OTP thành công");
												enableIn(rgtPass);
												enableIn(rgtRePass);
												disabledIn(otpValue);
												if (!(rgtPass.disabled && rgtRePass.disabled)) {
													rgtRePass.addEventListener('input', function () {
														if (validateRePassword(rgtPass.value, rgtRePass.value)) {
															console.log('true pass');
															enableIn(rgtBtn);
														} else {
															console.log('error pass & re-pass');
															disabledIn(rgtBtn);
														}
													});
													rgtPass.addEventListener('input', function () {
														if (validateRePassword(rgtPass.value, rgtRePass.value)) {
															console.log('true pass');
															enableIn(rgtBtn);
														} else {
															console.log('error pass & re-pass');
															disabledIn(rgtBtn);
														}
													});
												}
											} else {
												countOTP--;
												console.log("Xác thực OTP thất bại", countOTP);
												if (countOTP === 0) {
													location.reload();
												}
											}
										})
										.catch(function (error) {
											console.log("Lỗi xác thực OTP:", error);
										});
								}
							});
							otpBtn.removeEventListener('click', handleClick);
						});
						console.log('valid phone');
					}
				});




			} else {
				// k duoc thi in ra loi o day
				console.log('in-valid phone');
				otpBtn.removeEventListener('click', sendOTPPhone);
			}
		}
		//email
		else if (account.name === 'rgt-email') {
			if (validateGmail(account.value)) {
				otpBtn.addEventListener('click', function () {
					disabledIn(account);
					sendOTPEmail(account);

					enableIn(otpValue);
					otpValue.addEventListener('input', function () {

						if (validateOTP(otpValue.value)) {
							enableIn(rgtPass);
							enableIn(rgtRePass);
							disabledIn(otpValue);
							if (!(rgtPass.disabled && rgtRePass.disabled)) {
								rgtRePass.addEventListener('input', function () {
									if (validateRePassword(rgtPass.value, rgtRePass.value)) {
										console.log('true pass');
										enableIn(rgtBtn);
									} else {
										console.log('error pass & re-pass');
										disabledIn(rgtBtn);
									}
								});
								rgtPass.addEventListener('input', function () {
									if (validateRePassword(rgtPass.value, rgtRePass.value)) {
										console.log('true pass');
										enableIn(rgtBtn);
									} else {
										console.log('error pass & re-pass');
										disabledIn(rgtBtn);
									}
								});
							}
						} else {
							console.log('error otp value');
						}

					});
				});
				console.log('valid email');


			} else {
				// k duoc thi in ra loi o day
				console.log('in-valid email');
				otpBtn.removeEventListener('click', sendOTPEmail);
			}
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
		console.log(phoneNumber);
		return true;
	} else {
		return false;
	}
}
function validateGmail(email) {
	var gmailPattern = /^[a-zA-Z0-9._%+-]+@gmail\.com$/;


	if (email.match(gmailPattern)) {
		return true;
	} else {
		return false;
	}
}

function clearInputField(inputElement) {
	inputElement.value = "";
}

function validateRePassword(password, rePassword) {
	var passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$/;

	var isMatch = password === rePassword;
	var isValid = rePassword.match(passwordPattern);

	return isMatch && isValid;
}

//authen mail
//trả về true nếu đúng, false nếu sai
async function checkOtpEmail(email, code){
	var url = "/login/authen/email";
	var data = {email, code};
	var value = await sendata(url, data);
	return value;
}



