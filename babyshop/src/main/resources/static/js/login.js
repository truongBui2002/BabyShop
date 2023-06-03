
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

const recaptchaVerifier = new firebase.auth.RecaptchaVerifier('recaptcha-container', {
	size: 'invisible' // Ẩn reCAPTCHA
});

function phoneAuth() {
	var phoneNumber = document.getElementById('phoneNumber').value;
	var capcha = document.getElementById('recaptcha-container');
	console.log(phoneNumber);
	var otpField = document.getElementsByClassName('field_otp')[0];
	otpField.style.display = 'block';
	
	var passField = document.getElementsByClassName('field_pass');
	var otp = document.getElementById('otpValue');
	firebase.auth().signInWithPhoneNumber(phoneNumber, recaptchaVerifier)
		.then(function(confirmationResult) {
			otp.oninput = () => {
				var inputValue = otp.value;
				if (inputValue.length === 6) {
					confirmationResult.confirm(inputValue)
						.then((result) => {
							console.log(result);
							result.user.getIdToken().then((token) => {
								console.log('TOKEN: ' + token);
							});
							passField[0].style.display = 'block';
							passField[1].style.display = 'block';
						})
						.catch((err) => {
							otp.value = "";
							alert('Bạn đã nhập sai OTP, xin vui lòng gửi lại mã xác thực');
						});
				}
			};

		}).catch(function(error) {
			alert(error.message);
		});
	otpField.style.display = 'none';
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
