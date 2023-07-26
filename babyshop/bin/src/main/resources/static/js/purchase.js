
var targetElement = document.getElementById("popup");
var popAddress = document.querySelector('.pop_up_address');
var GrPVC = popAddress.querySelector('.G-rPVC');
GrPVC.style.display = "none";
targetElement.style.display = "none";

var popupAddress = document.querySelector('.popup-address');

var btnCancel = document.querySelector('.btn-cancel');
var btnConfirm = document.querySelector('.btn-confirm');


const userName = document.querySelector('.address-user-name');
const userAddress = document.querySelector('.address__user');

if (popupAddress !== null) {
	popupAddress.addEventListener('click', () => {
		if (GrPVC.style.display === "none") {
			GrPVC.style.display = "block";
			targetElement.style.display = "block";
			var overlay = document.createElement('div');
			overlay.classList.add('overlay__popup');
			popAddress.appendChild(overlay);
			popAddress.classList.add('overlay__popup')
		}
	});
}

btnCancel.addEventListener('click', () => {
	popAddress.removeChild(popAddress.children[1]);
	GrPVC.style.display = "none";
	targetElement.style.display = "none";
	popAddress.classList.remove('overlay__popup');
})
btnConfirm.addEventListener('click', () => {
	const addIn = document.querySelector("input:checked");
	if (addIn !== null) {
		const boxAdd = addIn.closest('.box__address');
		const firstA = boxAdd.querySelectorAll('.fristA');
		const secondA = boxAdd.querySelectorAll('.secondA');

		var userNameT = "";

		firstA.forEach((e) => {
			userNameT += e.textContent + " ";
		})
		userName.textContent = userNameT.toString();
		userNameT = " ";
		secondA.forEach((e) => {
			userNameT += e.textContent + " ";
		})

		userAddress.innerHTML = userNameT.toString();
	}
	popAddress.removeChild(popAddress.children[1]);
	GrPVC.style.display = "none";
	targetElement.style.display = "none";
	popAddress.classList.remove('overlay__popup');
});


//Đặt hàng
function getParams() {
	var param = [];
	var products = document.querySelectorAll('.list-purchase');
	products.forEach((product) => {
		var cartItemId = product.querySelector('input[type="hidden"]').id;
		param.push(cartItemId);
	})
	var cartItemsId = "";
	for (var i = 0; i < param.length; i++) {
		if (i < param.length - 1) {
			cartItemsId += "cartItemId=" + param[i] + "&";
		} else {
			cartItemsId += "cartItemId=" + param[i];
		}
	}
	return cartItemsId;
}
var address = document.querySelector('.address-user');
var btn_order = document.querySelector('.TTXpRG');
btn_order.addEventListener('click', () => {
	if (address === null) {
		location.href = '/user/viewprofile/address';
	} else {
		var param = getParams();
		var locationId = document.querySelector('input[type="radio"]:checked').id;
		location.href = '/user/purchase/order?locationId='+locationId+"&"+param;
		console.log(param);
		console.log(locationId);
	}
})
