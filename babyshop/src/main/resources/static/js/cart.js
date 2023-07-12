var cartCheckboxes = document.querySelectorAll('.cart-checkbox');
var labelCheckbox = document.querySelectorAll('.stardust-checkbox');
var checkboxes = document.querySelectorAll('.stardust-checkbox__input');

var firstCheckbox = labelCheckbox[0];
var lastCheckbox = labelCheckbox[labelCheckbox.length - 1];
console.log(labelCheckbox.length);


var pTotalAmount = document.querySelector('.p-total-amount');
let intTotalAmount = 0;
let countClickLabel = 0;

checkboxes.forEach((e) => {
    e.addEventListener('click', (a) => {
        var targetE = a.target;
        var parentChecboxes = targetE.closest('label');

        if (parentChecboxes === firstCheckbox || parentChecboxes === lastCheckbox) {
            if (!firstCheckbox.classList.contains('stardust-checkbox--checked')) {
				countClickLabel = labelCheckbox.length - 2;
                for (var i = 0; i < labelCheckbox.length; i++) {
                    labelCheckbox[i].classList.add('stardust-checkbox--checked');
					

                }
                for (var i = 1; i < labelCheckbox.length - 1; i++) {
                    var cartInfors = labelCheckbox[i].closest('.product-added_cart__info');
                    var productTotalAmout = cartInfors.querySelector('.product-cart_total_amount');
                    var totalAmount = productTotalAmout.textContent.replace('₫', '').replace('.', '');
                    intTotalAmount += parseInt(totalAmount);
                    pTotalAmount.textContent = '₫' + intTotalAmount.toString().toLocaleString("vi-VN");
                }
            } else {
                countClickLabel = 0;
                for (var i = 0; i < labelCheckbox.length; i++) {
                    labelCheckbox[i].classList.remove('stardust-checkbox--checked');

                }
                for (var i = 1; i < labelCheckbox.length - 1; i++) {
                    var cartInfors = labelCheckbox[i].closest('.product-added_cart__info');
                    var productTotalAmout = cartInfors.querySelector('.product-cart_total_amount');
                    var totalAmount = productTotalAmout.textContent.replace('₫', '').replace('.', '');
                    intTotalAmount -= parseInt(totalAmount);
                    pTotalAmount.textContent = '₫' + intTotalAmount.toString().toLocaleString("vi-VN");
                }
            }
        } else {
            if (!parentChecboxes.classList.contains('stardust-checkbox--checked')) {
                parentChecboxes.classList.add('stardust-checkbox--checked');
                countClickLabel++;
                console.log(countClickLabel);
            } else {
                parentChecboxes.classList.remove('stardust-checkbox--checked');
                firstCheckbox.classList.remove('stardust-checkbox--checked');
                lastCheckbox.classList.remove('stardust-checkbox--checked');
                countClickLabel--;
                console.log(countClickLabel);

            }
            if (countClickLabel == labelCheckbox.length - 2 && !firstCheckbox.classList.contains('stardust-checkbox--checked') && !lastCheckbox.classList.contains('stardust-checkbox--checked')) {
                for (var i = 0; i < labelCheckbox.length; i++) {
                    labelCheckbox[i].classList.add('stardust-checkbox--checked');
                }
            }

            var cartInfo = e.closest('.product-added_cart__info');
            var productTotalAmout = cartInfo.querySelector('.product-cart_total_amount');
            var totalAmount = productTotalAmout.textContent.replace('₫', '').replace('.', '');
            console.log(e.checked);
            if (parentChecboxes.classList.contains('stardust-checkbox--checked')) {
                intTotalAmount += parseInt(totalAmount);
                pTotalAmount.textContent = '₫' + intTotalAmount.toString().toLocaleString("vi-VN");
            } else {
                intTotalAmount -= parseInt(totalAmount);
                pTotalAmount.textContent = '₫' + intTotalAmount.toString().toLocaleString("vi-VN");
            }

        }


    })
});

// valid input


// quantity
var btnQuantityDecreases = document.querySelectorAll('.input-quantity-decrease');
var btnQuantityIncreases = document.querySelectorAll('.input-quantity-increase');


console.log(btnQuantityDecreases);
console.log(btnQuantityIncreases);

btnQuantityDecreases.forEach((btnQuantityDecrease) => {

    btnQuantityDecrease.addEventListener('click', () => {

        var inputQuality = btnQuantityDecrease.closest('.input-quantity');
        var btnInputQuantity = inputQuality.querySelector('.btn-quantity-input');

        var parentInputQuality = btnQuantityDecrease.closest('.product-added_cart__info');
        var totalAmountDes = parentInputQuality.querySelector('.product-cart_total_amount');


        inputQuality.style.opacity = 0.5;
        var value = parseInt(btnInputQuantity.value);

        let valueAmountDes = parseInt(totalAmountDes.textContent.replace('₫', '').replace('.', ''));
        let oneVAD = valueAmountDes / value;
        valueAmountDes -= oneVAD;

        console.log(oneVAD);
        if (value > 1) {
            value -= 1;
            btnInputQuantity.value = value.toString();
            inputQuality.querySelector('.input-quantity-increase').disabled = false;
            totalAmountDes.textContent = '₫' + valueAmountDes.toString().toLocaleString("vi-VN");
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

        var totalAmountDes = parentInputQuality.querySelector('.product-cart_total_amount');
        inputQuality.style.opacity = 0.5;
        var value = parseInt(btnInputQuantity.value); // number product

        console.log(value);
        let valueAmountDes = parseInt(totalAmountDes.textContent.replace('₫', '').replace('.', ''));
        console.log('1', valueAmountDes); // first price
        let oneVAD = valueAmountDes / value;
        valueAmountDes += oneVAD;
        console.log('2', valueAmountDes); // after click increase price

        if (value >= 1) {
            value += 1;
            btnInputQuantity.value = value.toString();
            inputQuality.querySelector('.input-quantity-decrease').disabled = false;
            totalAmountDes.textContent = '₫' + valueAmountDes.toString().toLocaleString("vi-VN");
        }
        if (productleft !== null) {
            console.log(productleft.ariaValueMax);
            if (btnInputQuantity.value === productleft.ariaValueMax) {
                btnQuantityIncrease.disabled = true;
            }
        }
        setTimeout(() => {
            inputQuality.style.opacity = 1;
        }, 100);

    });
});

var inputQ = document.querySelectorAll('.btn-quantity-input');

inputQ.forEach((input) => {
    input.addEventListener('input', function (e) {
        var inputQuality = input.closest('.input-quantity');
        var parentInputQuality = input.closest('.product-added_cart__info');
        var productleft = parentInputQuality.querySelector('.product-left');

        var value = e.target.value;

        var sanitizedValue = value.replace(/[^0-9]/g, '');
        let maxVL = parseInt(sanitizedValue);


        if (sanitizedValue === '' || sanitizedValue === '0') { // th nhap 0 hoac 1
            sanitizedValue = '1';

        }
        if (productleft !== null) {
            if (maxVL > productleft.ariaValueMax) {
                sanitizedValue = productleft.ariaValueMax.toString();
                inputQuality.querySelector('.input-quantity-increase').disabled = true;
            }
        }

        e.target.value = sanitizedValue;

    });
    input.addEventListener('blur', function (e) {
        console.log(e.target.value);
        var parentInputQuality = input.closest('.product-added_cart__info');
        var totalAmountDes = parentInputQuality.querySelector('.product-cart_total_amount');

        var oneVTA = parseInt(totalAmountDes.ariaLabel);
        var valueAmountDes = oneVTA * e.target.value;
        totalAmountDes.textContent = '₫' + valueAmountDes.toString().toLocaleString("vi-VN");

    });

})
