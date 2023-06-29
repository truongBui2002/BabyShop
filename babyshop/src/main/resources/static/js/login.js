

var inputKey = document.getElementById('phoneNumber');
var passField = document.getElementsByClassName('field_pass');
var otp = document.getElementById('otpValue');
var registerField = document.getElementsByClassName('field_register')[0];
var phoneIsExists = document.getElementById('phoneOrMaiExists');
var sendOTP = document.getElementsByClassName('sendOTP')[0];
async function sendOtp() {
	if (phoneIsExists.style.display == 'block') {
		console.log('Phone is EXISTS');
		phoneIsExists.style.display = 'none';
	}
	var phoneStr = inputKey.value;
	if (isPhoneNumber(phoneStr)) {
		var url = "/login/checkexits";
		var value = await sendata(url, phoneStr);
		console.log(value);
		if (value !== 'exists') {
			var phone = phoneStr.replace(/^0/, "+84");
			//vô hiệu hóa sự kiện bấm vào thẻ span
			sendOTP.style.pointerEvents = 'none';
			inputKey.readOnly = true;
			phoneAuth(phone);
			//firebase.auth().signOut();
		} else {
			phoneIsExists.style.display = 'block';
		}
	} else {
		/*document.getElementById('syntax_error').style.display = 'block';*/
		
		//Check valid phone number in here
	}

}
function isPhoneNumber(input) {
	const phoneRegex = /^0\d{9,10}$/;
	return phoneRegex.test(input);
}
// fetch là hàm bất đồng bộ nên cần phải thêm await
async function sendata(url, data) {
	var value;
	await fetch(url, {
		method: 'POST',
		body: data,
		headers: {
			'Content-Type': 'text/plain'
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
	appId: "1:776466389576:web:ef677af869e4e739ff567d",
	measurementId: "G-4VH3R0S17N"
};
firebase.initializeApp(firebaseConfig);

var otpField = document.getElementsByClassName('field_otp')[0];
var manyError = document.getElementById('manyError');
function phoneAuth(phoneNumber) {
	const recaptchaVerifier = new firebase.auth.RecaptchaVerifier('recaptcha-container', {
		size: 'invisible' // Ẩn reCAPTCHA
	});
	var capcha = document.getElementById('recaptcha-container');
	otpField.style.display = 'block';



	firebase.auth().signInWithPhoneNumber(phoneNumber, recaptchaVerifier)
		.then(function(confirmationResult) {
			var count = 0; //đến số lần nhập otp
			var opt_err = document.getElementById('opt_err');
			otp.oninput = () => {
				var inputValue = otp.value;
				opt_err.style.display = 'none';
				if (inputValue.length === 6 && count <= 2) {
					count++;
					console.log("COUNT :" + count);
					confirmationResult.confirm(inputValue)
						.then((result) => {
							
							passField[0].style.display = 'block';
							otpField.style.display = 'none';
							registerField.style.display = 'block';
						})
						.catch((err) => {
							opt_err.style.display = 'block';
						});
				} else if (count >= 2) {
					document.getElementById('manyError').style.display = 'block';
				}
			};
		}).catch(function(error) {
			alert(error.message);
		});

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
/* valid form*/
const userInputted = document.getElementById("email-login");
console.log(userInputted);
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

