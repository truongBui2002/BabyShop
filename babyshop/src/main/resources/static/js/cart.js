var cartCheckboxes = document.querySelectorAll('.cart-checkbox');
var labelCheckbox = document.querySelectorAll('.stardust-checkbox');
var checkboxes = document.querySelectorAll('.stardust-checkbox__input');

var firstCheckbox = labelCheckbox[0];
var lastCheckbox = labelCheckbox[labelCheckbox.length - 1];



var pTotalAmount = document.querySelector('.p-total-amount');
let intTotalAmount = 0;
let countClickLabel = 0;

const productQuantitY = document.querySelectorAll(".product-cart-input-quantity");
productQuantitY.forEach((p) => {
	var productLefT = p.querySelector(".product-left");
	var btnIncreaseP = p.querySelector(".input-quantity-increase");
	var inQuantitY = p.querySelector(".btn-quantity-input");
	if (productLefT !== null || btnIncreaseP !== null) {
		if (productLefT.ariaValueMax === inQuantitY.value) {
			btnIncreaseP.disabled = true;
		}
	}
	//console.log(productLefT.ariaValueMax);
	//console.log(inQuantitY.value);
});





checkboxes.forEach((e) => {
	e.addEventListener('click', (a) => {
		var targetE = a.target;
		var parentChecboxes = targetE.closest('label');
		//console.log(parentChecboxes);

		if (parentChecboxes === firstCheckbox || parentChecboxes === lastCheckbox) {
			if (!firstCheckbox.classList.contains('stardust-checkbox--checked')) {
				countClickLabel = labelCheckbox.length - 2;
				for (var i = 0; i < labelCheckbox.length; i++) {
					labelCheckbox[i].classList.add('stardust-checkbox--checked');
				}
				intTotalAmount = 0;
				for (var i = 1; i < labelCheckbox.length - 1; i++) {
					var cartInfors = labelCheckbox[i].closest('.product-added_cart__info');
					var productTotalAmout = cartInfors.querySelector('.product-cart_total_amount');
					var totalAmount = productTotalAmout.textContent.replace('₫', '').replace('.', '');
					intTotalAmount += parseInt(totalAmount);
					//console.log(intTotalAmount);
					pTotalAmount.textContent = '₫' + intTotalAmount.toString().toLocaleString("vi-VN");
				}
			} else {
				countClickLabel = 0;
				for (var i = 0; i < labelCheckbox.length; i++) {
					labelCheckbox[i].classList.remove('stardust-checkbox--checked');

				}
				intTotalAmount = 0;
				pTotalAmount.textContent = '₫0';
				// for (var i = 1; i < labelCheckbox.length - 1; i++) {
				//     var cartInfors = labelCheckbox[i].closest('.product-added_cart__info');
				//     var productTotalAmout = cartInfors.querySelector('.product-cart_total_amount');
				//     var totalAmount = productTotalAmout.textContent.replace('₫', '').replace('.', '');
				//     intTotalAmount -= parseInt(totalAmount);
				//     console.log(intTotalAmount);
				//     pTotalAmount.textContent = '₫' + intTotalAmount.toString().toLocaleString("vi-VN");
				// }
			}
		} else {
			if (!parentChecboxes.classList.contains('stardust-checkbox--checked')) {
				parentChecboxes.classList.add('stardust-checkbox--checked');
				countClickLabel++;

			} else {
				parentChecboxes.classList.remove('stardust-checkbox--checked');
				firstCheckbox.classList.remove('stardust-checkbox--checked');
				lastCheckbox.classList.remove('stardust-checkbox--checked');
				countClickLabel--;

			}
			if (countClickLabel == labelCheckbox.length - 2 && !firstCheckbox.classList.contains('stardust-checkbox--checked') && !lastCheckbox.classList.contains('stardust-checkbox--checked')) {
				for (var i = 0; i < labelCheckbox.length; i++) {
					labelCheckbox[i].classList.add('stardust-checkbox--checked');
				}
			}

			var cartInfo = e.closest('.product-added_cart__info');
			var productTotalAmout = cartInfo.querySelector('.product-cart_total_amount');
			var totalAmount = productTotalAmout.textContent.replace('₫', '').replace('.', '');

			if (parentChecboxes.classList.contains('stardust-checkbox--checked')) {
				intTotalAmount += parseInt(totalAmount);
				pTotalAmount.textContent = '₫' + intTotalAmount.toString().toLocaleString("vi-VN");
				//console.log('checked', intTotalAmount);
			} else {
				intTotalAmount -= parseInt(totalAmount);
				pTotalAmount.textContent = '₫' + intTotalAmount.toString().toLocaleString("vi-VN");
				//console.log('un-checked', intTotalAmount);
			}

		}


	})
});

// valid input


// quantity
var btnQuantityDecreases = document.querySelectorAll('.input-quantity-decrease');
var btnQuantityIncreases = document.querySelectorAll('.input-quantity-increase');


var inP = document.querySelectorAll('.btn-quantity-input');

inP.forEach((e) => {
	if (e.value === '1') {
		var inQ = e.closest('.input-quantity');
		inQ.querySelector('.input-quantity-decrease').disabled = true;
	}
})




btnQuantityDecreases.forEach((btnQuantityDecrease) => {


	btnQuantityDecrease.addEventListener('click', () => {

		var inputQuality = btnQuantityDecrease.closest('.input-quantity');

		var btnInputQuantity = inputQuality.querySelector('.btn-quantity-input');
		var parentInputQuality = btnQuantityDecrease.closest('.product-added_cart__info');
		var labChecked = parentInputQuality.querySelector('.stardust-checkbox');

		var totalAmountDes = parentInputQuality.querySelector('.product-cart_total_amount');


		inputQuality.style.opacity = 0.5;
		var value = parseInt(btnInputQuantity.value);

		let valueAmountDes = parseInt(totalAmountDes.textContent.replace('₫', '').replace('.', ''));
		let oneVAD = valueAmountDes / value;
		valueAmountDes -= oneVAD;
		//console.log('oneVAD', oneVAD);
		if (value > 1) {
			value -= 1;
			btnInputQuantity.value = value.toString();
			inputQuality.querySelector('.input-quantity-increase').disabled = false;
			totalAmountDes.textContent = '₫' + valueAmountDes.toString().toLocaleString("vi-VN");

		}
		//console.log('value', value);
		if (labChecked.classList.contains('stardust-checkbox--checked') && value >= 1) {
			intTotalAmount -= oneVAD;
			//console.log('decrease', intTotalAmount);
			pTotalAmount.textContent = '₫' + intTotalAmount.toString().toLocaleString("vi-VN");
		}
		if (value === 1) {
			inputQuality.querySelector('.input-quantity-decrease').disabled = true;

		}
		setTimeout(() => {
			inputQuality.style.opacity = 1;
		}, 100);

	});
});


btnQuantityIncreases.forEach((btnQuantityIncrease) => {

	btnQuantityIncrease.addEventListener('click', () => {

		var inputQuality = btnQuantityIncrease.closest('.input-quantity');
		var parentInputQuality = btnQuantityIncrease.closest('.product-added_cart__info');

		var productleft = parentInputQuality.querySelector('.product-left');
		var btnInputQuantity = inputQuality.querySelector('.btn-quantity-input');
		var labChecked = parentInputQuality.querySelector('.stardust-checkbox');

		var totalAmountDes = parentInputQuality.querySelector('.product-cart_total_amount');
		inputQuality.style.opacity = 0.5;
		var value = parseInt(btnInputQuantity.value); // number product

		//console.log(value);
		let valueAmountDes = parseInt(totalAmountDes.textContent.replace('₫', '').replace('.', ''));
		//console.log('1', valueAmountDes); // first price
		let oneVAD = valueAmountDes / value;
		valueAmountDes += oneVAD;

		//console.log('2', valueAmountDes); // after click increase price

		if (value >= 1) {
			value += 1;
			btnInputQuantity.value = value.toString();
			inputQuality.querySelector('.input-quantity-decrease').disabled = false;
			totalAmountDes.textContent = '₫' + valueAmountDes.toString().toLocaleString("vi-VN");
		}
		if (productleft !== null) {
			//console.log(productleft.ariaValueMax);
			if (btnInputQuantity.value === productleft.ariaValueMax) {
				btnQuantityIncrease.disabled = true;
			}
		}

		if (labChecked.classList.contains('stardust-checkbox--checked')) {
			intTotalAmount += oneVAD;
			pTotalAmount.textContent = '₫' + intTotalAmount.toString().toLocaleString("vi-VN");
		}
		setTimeout(() => {
			inputQuality.style.opacity = 1;
		}, 100);

	});
});

var inputQ = document.querySelectorAll('.btn-quantity-input');

inputQ.forEach((input) => {
	input.addEventListener('input', function(e) {
		var inputQuality = input.closest('.input-quantity');
		var parentInputQuality = input.closest('.product-added_cart__info');
		var productleft = parentInputQuality.querySelector('.product-left');

		var value = e.target.value;


		var sanitizedValue = value.replace(/[^0-9]/g, '');
		let maxVL = parseInt(sanitizedValue);


		inputQuality.querySelector('.input-quantity-decrease').disabled = false;
		if (sanitizedValue === '' || sanitizedValue === '0') { // th nhap 0 hoac 1
			sanitizedValue = '1';
			inputQuality.querySelector('.input-quantity-decrease').disabled = true;


		}
		if (productleft !== null) {
			if (maxVL >= productleft.ariaValueMax) {
				sanitizedValue = productleft.ariaValueMax.toString();
				inputQuality.querySelector('.input-quantity-increase').disabled = true;
				inputQuality.querySelector('.input-quantity-decrease').disabled = false;
			}
		}

		e.target.value = sanitizedValue;

	});
	input.addEventListener('blur', function(e) {
		var parentInputQuality = input.closest('.product-added_cart__info');
		var totalAmountDes = parentInputQuality.querySelector('.product-cart_total_amount');


		var oneVTA = parseInt(totalAmountDes.ariaLabel);
		var valueAmountDes = oneVTA * e.target.value;
		totalAmountDes.textContent = '₫' + valueAmountDes.toString().toLocaleString("vi-VN");

		intTotalAmount = 0;
		for (var i = 1; i < labelCheckbox.length - 1; i++) {
			if (labelCheckbox[i].classList.contains('stardust-checkbox--checked')) {
				var cartInfors = labelCheckbox[i].closest('.product-added_cart__info');
				var productTotalAmout = cartInfors.querySelector('.product-cart_total_amount');
				var totalAmount = productTotalAmout.textContent.replace('₫', '').replace('.', '');
				intTotalAmount += parseInt(totalAmount);
				//console.log(intTotalAmount);
				pTotalAmount.textContent = '₫' + intTotalAmount.toString().toLocaleString("vi-VN");
			}
		}

	});

})



var btnPurchase = document.querySelector('.btn--purchase');

btnPurchase.addEventListener('click', () => {
	var checkedL = document.querySelectorAll('.stardust-checkbox--checked');

	if (checkedL.length <= 0) {
		showPopup("Please choose a product!!!");
		var solidOK = document.querySelector('.btn-solid--primary');
		solidOK.addEventListener('click', function() {
			var popUR = document.querySelector('.pop__up');
			popUR.classList.remove('pop__up-show');
			while (popUR.firstChild) {
				popUR.removeChild(popUR.firstChild);
			}
		});
	} else {
		var param =  getParam();
		console.log(param);
		//gọi hàm chuyển hướng ở đây
		location.href = "/user/purchase?" + param;
	}
})

function getParam() {
	var param = [];
	var productsView = document.querySelectorAll('.product-added-cart');
	productsView.forEach((product) => {
		var variantId = product.querySelector('input[type="hidden"]').id;
		var check = product.querySelector('.stardust-checkbox--checked');
		if (check !== null) {
				param.push(variantId);
			}
	});
	var stringParam ="";
	for(var i = 0; i < param.length; i++){
		if(i<param.length-1){
			stringParam += "cartItemId=" + param[i] + "&";
		}else{
			stringParam += "cartItemId=" + param[i];
		}
	}
	return stringParam;
}

function showPopup(message) {
	var targetElement = document.getElementById("popup");

	var childP = targetElement.getElementsByClassName("pop__up")[0];
	childP.classList.add('pop__up-show');

	var overlayPopup = document.createElement('div');
	var popContainer = document.createElement('div');

	overlayPopup.classList.add('popup__overlay');
	popContainer.classList.add('popup__container');
	childP.appendChild(overlayPopup);
	childP.appendChild(popContainer);

	var alertP = document.createElement('div');
	alertP.classList.add('alert-popup')
	alertP.classList.add('card')
	popContainer.appendChild(alertP);

	var pM = document.createElement('div');
	pM.classList.add('popup__message');
	pM.textContent = message;
	alertP.appendChild(pM);

	var pB = document.createElement('div');
	pB.classList.add('popup-btn');
	alertP.appendChild(pB);

	var btnP = document.createElement('button');
	btnP.classList.add("btn-solid");
	btnP.classList.add("btn-solid--primary");
	btnP.textContent = 'OK';
	pB.appendChild(btnP);
}

// Tăng giảm, xóa số lượng sản phẩm trong cart

function updateQuantity(variantId, subOrAdd) {
	var data = [variantId, subOrAdd];
	var jsonData = JSON.stringify(data);
	var xhr = new XMLHttpRequest();
	xhr.open("PUT", "/user/cart/update");
	xhr.send(jsonData);
}

var productsView = document.querySelectorAll('.product-added-cart');
productsView.forEach((product) => {
	var quantityInput = product.querySelector('.btn-quantity-input');
	var cartItemId = product.querySelector('input[type="hidden"]').id;
	var add = product.querySelector('.input-quantity-increase');
	add.addEventListener('click', () => {
		updateQuantity(cartItemId, quantityInput.value);
	});
	var sub = product.querySelector('.input-quantity-decrease');
	sub.addEventListener('click', () => {
		updateQuantity(cartItemId, quantityInput.value);
	});
	var del = product.querySelector('.remove-item-from-cart');
	del.addEventListener('click', () => {
		delProduct(cartItemId);
	});
	quantityInput.addEventListener('blur', () => {
		updateQuantity(cartItemId, quantityInput.value);
	});
	//console.log(add);
	//console.log(sub);
	//console.log(del);
	//console.log(variantId);
})

function delProduct(variantId) {
	var xhr = new XMLHttpRequest();
	xhr.open("PUT", "/user/cart/delete");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
			location.reload();
		}
	}
	xhr.send(variantId);
}







