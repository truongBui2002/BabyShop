
document.addEventListener('DOMContentLoaded', function () {
	const imageInput = document.getElementById('imageInput');
	const imageContainer = document.getElementById('imageContainer');

	imageInput.addEventListener('change', function () {
		imageContainer.innerHTML = ''; // Clear existing images

		for (const file of imageInput.files) {
			const reader = new FileReader();
			const imgElement = document.createElement('img');

			reader.addEventListener('load', function () {
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
		f.addEventListener("click", function (p) {
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

			document.getElementById("imageForm").removeEventListener("submit", function (event) {
				event.preventDefault();
				console.log("cacacacacac");
				const imageInput = document.getElementById("imageInput");
				const messageElement = document.getElementById("message");
	
	
				if (imageInput.files.length === 0) {
					messageElement.textContent = "Please select an image.";
					return;
				}
	
	
				const file = imageInput.files[0];
				const fileType = file.type;
				const allowedTypes = ["image/jpeg", "image/png", "image/gif"];
	
				if (!allowedTypes.includes(fileType)) {
					messageElement.textContent = "Only JPEG, PNG, and GIF images are allowed.";
					return;
				}
	
				const maxSizeMB = 5;
				const maxSizeBytes = maxSizeMB * 1024 * 1024;
	
				if (file.size > maxSizeBytes) {
					messageElement.textContent = `File size should be less than ${maxSizeMB}MB.`;
					return;
				}
	
				// If all validation passes, you can proceed to upload the image here.
				// For demonstration purposes, we're just showing a success message.
				messageElement.textContent = "Image added successfully!";
				
			});
		})
	})
}

//reset form 
var resetF = document.querySelector(".resetF");
if (resetF !== null) {
	resetF.addEventListener("click", function () {
		var modalBody = document.querySelector(".modal-body");
		var id = modalBody.querySelector("#hidden-id");
		var typeText = modalBody.querySelectorAll("input[type=text]");
		id.removeAttribute("value");

		typeText[0].removeAttribute("value"); // name product
		typeText[1].removeAttribute("value"); // description

		document.getElementById("imageForm").addEventListener("submit", function (event) {
			event.preventDefault();
			const imageInput = document.getElementById("imageInput");
			const messageElement = document.getElementById("message");


			if (imageInput.files.length === 0) {
				messageElement.textContent = "Please select an image.";
				return;
			}


			const file = imageInput.files[0];
			const fileType = file.type;
			const allowedTypes = ["image/jpeg", "image/png", "image/gif"];

			if (!allowedTypes.includes(fileType)) {
				messageElement.textContent = "Only JPEG, PNG, and GIF images are allowed.";
				return;
			}

			const maxSizeMB = 5;
			const maxSizeBytes = maxSizeMB * 1024 * 1024;

			if (file.size > maxSizeBytes) {
				messageElement.textContent = `File size should be less than ${maxSizeMB}MB.`;
				return;
			}

			// If all validation passes, you can proceed to upload the image here.
			// For demonstration purposes, we're just showing a success message.
			messageElement.textContent = "Image added successfully!";
			event.currentTarget.submit();

		});
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
		xhr.onreadystatechange = function () {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				resolve(xhr.response);
				//console.log(xhr.response);
			}
		};
		xhr.onerror = function () {
			reject(xhr.statusText);// xảy ra nếu lỗi
		};
		xhr.send(brandId);
	});
}

var bName = document.getElementById("bName");
bName.addEventListener("input", function () {
	var bNameV = bName.value.trim();
	var btnSuccess = document.querySelector(".btn-success");
	if(bNameV === "") {
		btnSuccess.setAttribute("disabled", "true");
		
	}
	else {
		btnSuccess.removeAttribute("disabled");
	}
	
});