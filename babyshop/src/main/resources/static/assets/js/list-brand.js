
document.addEventListener('DOMContentLoaded', function() {
	const imageInput = document.getElementById('imageInput');
	const imageContainer = document.getElementById('imageContainer');

	imageInput.addEventListener('change', function() {
		imageContainer.innerHTML = ''; // Clear existing images

		for (const file of imageInput.files) {
			const reader = new FileReader();
			const imgElement = document.createElement('img');

			reader.addEventListener('load', function() {
				imgElement.setAttribute('src', reader.result);
			});

			reader.readAsDataURL(file);

			imgElement.style.width = '80px';
			imgElement.style.height = '80px';
			imageContainer.appendChild(imgElement);
		}
	});
});



const e = document.querySelectorAll('.cc');
if (e !== null) {
	e.forEach((f) => {
		f.addEventListener("click", function(p) {
			p.preventDefault();
			var b = f.closest("tr");

			var tdI = b.querySelectorAll("td"); //5


			var modalBody = document.querySelector(".modal-body");
			var id = modalBody.querySelector("#hidden-id");

			var typeText = modalBody.querySelectorAll("input[type=text]");

			var idcac = b.dataset.brandid;
			id.setAttribute("value", idcac);
			console.log(idcac);



			typeText[0].setAttribute("value", tdI[0].textContent.trim()); // name brand
			typeText[1].setAttribute("value", tdI[2].textContent.trim()); // description


		})
	})
}

//reset form 
var resetF = document.querySelector(".resetF");
if (resetF !== null) {
	resetF.addEventListener("click", function() {
		var modalBody = document.querySelector(".modal-body");
		var id = modalBody.querySelector("#hidden-id");
		var typeText = modalBody.querySelectorAll("input[type=text]");
		id.removeAttribute("value");

		typeText[0].removeAttribute("value"); // name product
		typeText[1].removeAttribute("value"); // description

	})
}


// change status
var list_brand = document.querySelectorAll('.list-brand');
list_brand.forEach((brand) => {
	var brandId = brand.dataset.brandid;
	var btn_change = brand.querySelector('.btn-change');
	btn_change.addEventListener('click', (e) => {
		e.preventDefault();
		statusChange(brandId).then(result => {
			var status = brand.querySelector('.view-status');
			status.innerHTML = result;
		})
	})
})

function statusChange(brandId) {
	return new Promise((resolve, reject) => {
		var xhr = new XMLHttpRequest();
		xhr.open("PUT", "/manager/staff/list-brand/change-status");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				resolve(xhr.response);
				//console.log(xhr.response);
			}
		};
		xhr.onerror = function() {
			reject(xhr.statusText);// xảy ra nếu lỗi
		};
		xhr.send(brandId);
	});
}