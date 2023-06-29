var textFieldInput = document.getElementById('text_field_input');
var arletInput = document.querySelector('.arlet-input');
var textBoxSend = document.querySelector('.textbox-send');





textBoxSend.addEventListener('click', textFieldCheck);

function textFieldCheck() {
	var textFieldInput = document.getElementById('text_field_input');
	var otpValue = document.getElementById('otpValue');
	var btnForgot = document.getElementById('btn-forgot');
	
	let valueTextField = textFieldInput.value.toString();
	
	if(valueTextField.length == 0) {
		arletInput.innerHTML = 'Phone number is not null!';
		arletInput.classList.add('active');
		otpValue.setAttribute('disabled', 'true');
		btnForgot.classList.add('rs-pass');
		
	}else if(valueTextField.length <= 9) {
		arletInput.innerHTML = 'Phone number is not valid!';
		arletInput.classList.add('active');
		otpValue.setAttribute('disabled', 'true');
		btnForgot.classList.add('rs-pass');
	}else {
		arletInput.innerHTML = 'Check your phone for the OTP Code';
		arletInput.classList.remove('active');
		arletInput.classList.add('valid');
		otpValue.removeAttribute('disabled');
		btnForgot.classList.remove('rs-pass');
	}
}