
var btnDs = document.querySelectorAll(".make-default");
var btnRs = document.querySelectorAll(".btn-r");
if (btnDs !== null & btnRs !== null) {
	btnDs.forEach((e) => {
		if (e.disabled) {
			var addB = e.closest(".address-b");
			var btnR = addB.querySelector(".btn-r");
			btnR.style.display = "none";
		}
		e.addEventListener("click", () => {
			var addB = e.closest(".address-b");
			var btnR = addB.querySelector(".btn-r");
			btnDs.forEach((k) => {
				k.removeAttribute("disabled");

			})
			btnRs.forEach((k) => {
				k.style.display = "block";

			})
			if (e.disabled === false) {
				e.setAttribute("disabled", "true");
				btnR.style.display = "none";
			}
		})
	})

}



var targetElement = document.getElementById("popup");
var popAddress = document.querySelector('.pop_up_address');
var GrPVC = popAddress.querySelector('.G-rPVC1');
GrPVC.style.display = "none";
targetElement.style.display = "none";

var popupAddress = document.querySelector('.popup-address');

var btnCancel = document.querySelector('.btn-cancel');
var btnConfirm = document.querySelector('.btn-confirm');


popupAddress.addEventListener('click', () => {
	if (GrPVC.style.display === "none") {
		GrPVC.style.display = "block";
		targetElement.style.display = "block";
		var overlay = document.createElement('div');
		overlay.classList.add('overlay__popup');
		popAddress.appendChild(overlay);
		popAddress.classList.add('overlay__popup')
		btnConfirm.setAttribute("disabled", "true");
	}
});

btnCancel.addEventListener('click', () => {
	popAddress.removeChild(popAddress.children[1]);
	GrPVC.style.display = "none";
	targetElement.style.display = "none";
	popAddress.classList.remove('overlay__popup');
	btnConfirm.removeAttribute("disabled");
})
btnConfirm.addEventListener('click', () => {

	GrPVC.style.display = "none";
	targetElement.style.display = "none";
	popAddress.classList.remove('overlay__popup');
});

var submit = document.querySelector('.btn-confirm');
submit.addEventListener('click', () => {
	document.querySelector('.form-address').submit();
})

var locations = document.querySelectorAll('.RnMqRZ');
locations.forEach((loca, index) => {
	var makeDefault = loca.querySelector('.make-default');
	var locationId = loca.dataset.location;
	makeDefault.addEventListener('click', () => {
		setMakeDefault(locationId)
		console.log(locationId);
	})
	var removeLocation = loca.querySelector('.btn-r');
	removeLocation.addEventListener('click', () => {
		removeAdress(locationId).then(result => {
			location.reload();
		});
		console.log("remove");
	})
})

function setMakeDefault(locationId) {
	var xhr = new XMLHttpRequest();
	xhr.open("PUT", "/user/viewprofile/address/address-make-default");
	xhr.send(locationId);
}
function removeAdress(locationId) {
	return new Promise((resolve, reject) => {
		var xhr = new XMLHttpRequest();
		xhr.open("PUT", "/user/viewprofile/address/address-remove");
		xhr.onreadystatechange = function () {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				resolve(xhr.response);
			}
		};
		xhr.onerror = function () {
			reject(xhr.statusText);// xảy ra nếu lỗi
		};
		xhr.send(locationId);
	});
}



var nameF = document.getElementById("name");

var phoneF = document.getElementById("phone-number");

var addF = document.getElementById("address");

nameF.addEventListener("blur", function () {
	var nameFV = nameF.value.trim();
	var namePattern = /^[A-Za-z]+(\s[A-Za-z]+)+$/;
	if (nameFV === "" || !namePattern.test(nameFV)) {
		btnConfirm.setAttribute("disabled", "true");
	} else {
		var formCTR = document.querySelectorAll(".form-control");
		formCTR.forEach((r) => {
			var rV = r.value.trim();
			if (rV === "") {
				btnConfirm.setAttribute("disabled", "true");
			} else {
				btnConfirm.removeAttribute("disabled");
			}

		})
	}
})


phoneF.addEventListener("blur", function () {
	var nameFV = addF.value.trim();
	
	if (nameFV === "" || !validatePhoneNumber(nameFV)) {
		btnConfirm.setAttribute("disabled", "true");
	} else {
		var formCTR = document.querySelectorAll(".form-control");
		formCTR.forEach((r) => {
			var rV = r.value.trim();
			if (rV === "") {
				btnConfirm.setAttribute("disabled", "true");
			} else {
				btnConfirm.removeAttribute("disabled");
			}

		})
	}
})

addF.addEventListener("blur", function () {
	var nameFV = addF.value.trim();
	if (nameFV === "") {
		btnConfirm.setAttribute("disabled", "true");
	} else {
		var formCTR = document.querySelectorAll(".form-control");
		formCTR.forEach((r) => {
			var rV = r.value.trim();
			if (rV === "") {
				btnConfirm.setAttribute("disabled", "true");
			} else {
				btnConfirm.removeAttribute("disabled");
			}

		})
	}
})

function validatePhoneNumber(phoneNumber) {
	var pattern = /^0\d{9}$/;
	var cleanPhoneNumber = phoneNumber.replace(/\D/g, '');

	if (cleanPhoneNumber.match(pattern)) {
		return true;
	} else {
		return false;
	}
}