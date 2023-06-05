

var inputKey = document.getElementById('phoneNumber');
var passField = document.getElementsByClassName('field_pass');
var otp = document.getElementById('otpValue');
var registerField = document.getElementsByClassName('field_register')[0];
async function sendOtp() {
	var phoneOrMail = inputKey.value;
	if (isPhoneNumber(phoneOrMail)) {
		var url = "/login/checkexits";
		var value = await sendata(url, phoneOrMail);
		console.log(value);
		if (value !=='exists') {
			var phone = phoneOrMail.replace(/^0/, "+84");
			phoneAuth(phone);
		} else {
			document.getElementById("phoneOrMaiExists").style.display = 'block';
		}

	} else if (isGmail(phoneOrMail)) {
		const url = "/login/sendcode/email"
		const email = phoneOrMail;
		
		var value = await sendata(url, email);
		console.log(value);
		if (value !=='exists') {
			inputKey.readonly = true;
			otpField.style.display = 'block';
			passField[0].style.display = 'block';
			registerField.style.display = 'block';
		} else {
			document.getElementById("phoneOrMaiExists").style.display = 'block';
		}
	} else {
		document.getElementById('syntax_error').style.display = 'block';
	}


}
function isPhoneNumber(input) {
	const phoneRegex = /^0\d{9,10}$/;
	return phoneRegex.test(input);
}
function isGmail(email) {
	const gmailRegex = /^[a-zA-Z0-9._%+-]+@gmail\.com$/;
	return gmailRegex.test(email);
}
// fetch là hàm bất đồng bộ nên cần phải thêm await
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
	appId: "1:776466389576:web:ef677af869e4e739ff567d",
	measurementId: "G-4VH3R0S17N"
};
firebase.initializeApp(firebaseConfig);

var otpField = document.getElementsByClassName('field_otp')[0];

function phoneAuth(phoneNumber) {
	const recaptchaVerifier = new firebase.auth.RecaptchaVerifier('recaptcha-container', {
		size: 'invisible' // Ẩn reCAPTCHA
	});
	var capcha = document.getElementById('recaptcha-container');
	console.log(phoneNumber);
	otpField.style.display = 'block';



	firebase.auth().signInWithPhoneNumber(phoneNumber, recaptchaVerifier)
		.then(function (confirmationResult) {
			otp.oninput = () => {
				var inputValue = otp.value;
				inputKey.readonly = true;
				if (inputValue.length === 6) {
					confirmationResult.confirm(inputValue)
						.then((result) => {
							console.log(result);
							result.user.getIdToken().then((token) => {
								console.log('TOKEN: ' + token);
							});
							passField[0].style.display = 'block';
							// passField[1].style.display = 'block';
							otpField.style.display = 'none';
							registerField.style.display = 'block';
						})
						.catch((err) => {
				 			alert('Bạn đã nhập sai OTP, xin vui lòng gửi lại mã xác thực');
						});
				}
			};
		}).catch(function (error) {
			alert(error.message);
		});

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
