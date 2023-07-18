
var btnDs = document.querySelectorAll(".make-default");
var btnRs = document.querySelectorAll(".btn-r");
if(btnDs !== null & btnRs !== null) {
	btnDs.forEach( (e) => {
		if(e.disabled) {
			var addB = e.closest(".address-b");
			var btnR = addB.querySelector(".btn-r");
			btnR.style.display = "none";
		}
		e.addEventListener("click", () => {
			var addB = e.closest(".address-b");
			var btnR = addB.querySelector(".btn-r");
			btnDs.forEach( (k) => {
				k.removeAttribute("disabled");
				
			})
			btnRs.forEach( (k) => {
				k.style.display = "block";
				
			})
			if(e.disabled === false) {
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
    }
});

btnCancel.addEventListener('click', () => {
    popAddress.removeChild(popAddress.children[1]);
    GrPVC.style.display = "none";
    targetElement.style.display = "none";
    popAddress.classList.remove('overlay__popup');
})
btnConfirm.addEventListener('click', () => {

    GrPVC.style.display = "none";
    targetElement.style.display = "none";
    popAddress.classList.remove('overlay__popup');
});

var submit = document.querySelector('.btn-confirm');
submit.addEventListener('click', ()=>{
	document.querySelector('.form-address').submit();
})





