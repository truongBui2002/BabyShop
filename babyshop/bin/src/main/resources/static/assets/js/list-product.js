function generateSizesArray() {
	let count = 50;
	let sizes = ["One Size"];

	while (true) {
		if (count > 158) break;
		let size = count + " cm";
		sizes.push(size);
		count += 6;
	}

	count = 18;
	while (true) {
		if (count > 36) break;
		let size = count + " EU";
		sizes.push(size);
		count++;
	}

	return sizes;
}

// Call the function to generate the sizes array
const sizes = generateSizesArray();


function geneInputCheckbox() {

	const checkboxSize = document.querySelector(".checkbox-size");

	for (var i = 0; i < sizes.length; i++) {
		const size = sizes[i];
		const lbS = document.createElement("label");
		const cbS = document.createElement("input");

		cbS.type = "checkbox";
		cbS.name = "checkbox-size";
		cbS.value = size;
		cbS.id = size;

		lbS.appendChild(cbS);
		lbS.appendChild(document.createTextNode(size));
		checkboxSize.appendChild(lbS);

	}

}

geneInputCheckbox();


//load age
function loadAge() {
	let ages = ["0-3 months", "3-6 months", "6-9 months", "9-12 months"];
	let count = 1;

	while (true) {
		if (count > 18) break;
		let age = count + " year";
		ages.push(age);
		count++;
	}

	return ages;
}

const agesArray = loadAge();
function selectAge() {
	const selectAges = document.querySelector(".checkbox-size1");

	const selectE = document.createElement("select");

	selectE.classList.add("form-select");
	selectE.setAttribute("aria-label", "Default select example");

	selectE.name = "age";

	agesArray.forEach((age, index) => {
		const option = document.createElement("option");
		var ageValid = age.trim();
		option.value = ageValid;
		option.text = ageValid;


		if (index === 0) {
			option.setAttribute("selected", "selected")
			option.textContent = "Choose Age"
		}
		selectE.appendChild(option);
	});
	selectAges.appendChild(selectE);
}
selectAge();


//load color
function loadColors() {
	let colors = ["Beige", "Black", "Blue", "Brown", "Cream", "Green", "Grey", "Navy", "Orange", "Pink", "Purple", "Red", "Yellow"];
	return colors;
}

// Call the function to load colors into an array
const colorsArray = loadColors();
function selectColors() {
	const selectAges = document.querySelector(".checkbox-size3");

	const selectE = document.createElement("select");

	selectE.classList.add("form-select");
	selectE.setAttribute("aria-label", "Default select example");

	selectE.name = "color";


	colorsArray.forEach((color, index) => {
		const option = document.createElement("option");
		var colorValid = color.trim();
		option.value = colorValid;
		option.text = colorValid;
		selectE.appendChild(option);
		if (index === 0) {
			option.setAttribute("selected", "selected")
			option.textContent = "Choose Color"
		}
	});
	selectAges.appendChild(selectE);
}
selectColors();


//load gender
function loadGenders() {
	let genders = ["Boys", "Girls", "Unisex"];
	return genders;
}


const gendersArray = loadGenders();

function selectGenders() {
	const selectAges = document.querySelector(".checkbox-size2");

	const selectE = document.createElement("select");

	selectE.classList.add("form-select");
	selectE.setAttribute("aria-label", "Default select example");

	selectE.name = "gender";

	gendersArray.forEach((gender, index) => {
		const option = document.createElement("option");
		var genderVaid = gender.trim();
		option.value = genderVaid;
		option.text = genderVaid;

		if (index === 0) {
			option.setAttribute("selected", "selected")
			option.textContent = "Choose Gender"
		}
		selectE.appendChild(option);
	});
	selectAges.appendChild(selectE);
}
selectGenders();


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


var list_product = document.querySelectorAll('.list-product');
list_product.forEach((product) => {
	var productId = product.dataset.productid;
	var btn_change = product.querySelector('.btn-change');
	btn_change.addEventListener('click', (e) => {
		e.preventDefault();
		statusChange(productId).then(result => {
			var status = product.querySelector('.view-status');
			status.innerHTML = result;
		})
	})


})

function statusChange(productId) {
	return new Promise((resolve, reject) => {
		var xhr = new XMLHttpRequest();
		xhr.open("PUT", "/manager/staff/list-product/change-status");
		xhr.onreadystatechange = function () {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				resolve(xhr.response);

			}
		};
		xhr.onerror = function () {
			reject(xhr.statusText);// xảy ra nếu lỗi
		};
		xhr.send(productId);
	});
}

//edit product

const modalB1 = document.querySelector(".modal-body1");
const modalB = document.querySelector(".modal-body");
modalB1.style.display = "none";
modalB1.style.display = "block";

const e = document.querySelectorAll('.cc');
if (e !== null) {
	e.forEach((f) => {
		f.addEventListener("click", function (p) {
			modalB.style.display = "block";
			modalB1.style.display = "none";
			p.preventDefault();
			var b = f.closest("tr");

			var tdI = b.querySelectorAll("td");
			tdI.forEach((td) => {

			});


			var modalBody = document.querySelector(".modal-body");
			var id = modalBody.querySelector("#hidden-id");

			var typeText = modalBody.querySelectorAll("input[type=text]");
			var typeNum = modalBody.querySelectorAll("input[type=number]");
			var typeCheck = modalBody.querySelectorAll("input[type=checkbox]");
			var opSelected = modalBody.querySelectorAll("select option[selected]");

			var select = modalBody.querySelectorAll("select");


			var idcac = b.dataset.productid;
			id.setAttribute("value", idcac);


			typeText[0].setAttribute("value", tdI[0].textContent.trim()); // name product
			typeText[1].setAttribute("value", tdI[2].textContent.trim()); // description
			typeText[2].setAttribute("value", tdI[3].textContent.trim()); // specifications.


			typeNum[0].setAttribute("value", parseInt(tdI[1].textContent.trim()));

			var discount = tdI[6].textContent.trim();
			var newD = discount.replace("%", "").trim();
			typeNum[1].setAttribute("value", parseInt(newD));

			opSelected[0].textContent = tdI[5].textContent.trim();
			opSelected[1].textContent = tdI[4].textContent.trim();

			select[1].selectedIndex = 0;
			select[2].selectedIndex = 0;

			//product infor (4,5,6)
			opSelected[2].textContent = tdI[7].textContent.trim();
			opSelected[3].textContent = tdI[8].textContent.trim();
			opSelected[4].textContent = tdI[9].textContent.trim();
			typeCheck.forEach((j) => {
				j.checked = false;
			})

			document.getElementById("imageForm").removeEventListener("submit", function (event) {
				event.preventDefault();
				console.log("ACascascasc");
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
	})
}

const x = document.querySelectorAll(".xx");
if (x !== null) {
	x.forEach((r) => {
		r.addEventListener("click", function (p) {
			modalB1.style.display = "block";
			modalB.style.display = "none";


			p.preventDefault();

			var b = r.closest("tr");

			var inputSize = b.querySelectorAll("input[type=text]");
			var modalBody1 = document.querySelector(".modal-body1");
			var sizeForm = modalBody1.querySelector(".size1");
			var sizeID = modalBody1.querySelector("#hidden-id1");



			inputSize.forEach((i) => {

			})
			var idcac = b.dataset.productid;
			sizeID.setAttribute("value", idcac);

			for (var i = 0; i < inputSize.length; i++) {
				const nLabel = document.createElement("label");
				const nInput = document.createElement("input");
				const cbRemove = document.createElement("input");



				nLabel.textContent = inputSize[i].value;
				nInput.type = "number";
				nInput.name = "size";
				nInput.setAttribute("value", parseInt(inputSize[i].ariaLabel));

				cbRemove.type = "checkbox";
				cbRemove.name = "variant";
				cbRemove.setAttribute("value", parseInt(inputSize[i].id));

				nLabel.appendChild(nInput);
				sizeForm.appendChild(nLabel);
				sizeForm.appendChild(cbRemove);
			}

		})
	})
}

//cl
const cl = document.querySelectorAll(".cl");

function closeModal() {

	var modalBody1 = document.querySelector(".modal-body1");
	var sizeForm = modalBody1.querySelector(".size1");
	sizeForm.innerHTML = "";
	console.log("aaa");
	var selectedImagesDiv = document.getElementById('imageContainer');
	selectedImagesDiv.innerHTML = '';
}
cl.forEach((k) => {
	k.addEventListener("click", closeModal);
})



//reset form 
var resetF = document.querySelector(".resetF");
if (resetF !== null) {
	resetF.addEventListener("click", function () {
		modalB.style.display = "block";
		modalB1.style.display = "none";
		var modalBody = document.querySelector(".modal-body");
		var id = modalBody.querySelector("#hidden-id");
		var typeText = modalBody.querySelectorAll("input[type=text]");
		var typeNum = modalBody.querySelectorAll("input[type=number]");

		var typeCheck = modalBody.querySelectorAll("input[type=checkbox]");
		var opSelected = modalBody.querySelectorAll("select option[selected]");
		var select = modalBody.querySelectorAll("select");

		var inFormControl = document.querySelectorAll("input.form-control");


		id.removeAttribute("value");

		typeText[0].removeAttribute("value"); // name product
		typeText[1].removeAttribute("value"); // description
		typeText[2].removeAttribute("value"); // specifications.


		typeNum[0].removeAttribute("value");
		typeNum[1].removeAttribute("value");

		opSelected[1].removeAttribute("value");
		opSelected[2].removeAttribute("value");



		select[0].options[0].textContent = "Sub Category";
		select[1].options[0].textContent = "Brand";
		select[2].options[0].textContent = "0-3 months";
		select[3].options[0].textContent = "Boys";
		select[4].options[0].textContent = "Color";
		for (var i = 0; i < 5; i++) {

			select[i].selectedIndex = 0;
			if (select[i].selectedIndex === 0) {
				var btnSuccess = document.querySelector("button.btn-success");
				inFormControl.forEach((i) => {
					var iV = i.value.trim();
					if (iV === "") {
						btnSuccess.setAttribute("disabled", "true");
					} else {
						btnSuccess.removeAttribute("disabled");
					}
				})
			} else {
				btnSuccess.removeAttribute("disabled");
			}
		}
		typeCheck.forEach((j) => {
			j.checked = false;
		})

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

			if (select[0].options[select[0].selectedIndex].value === "Sub Category" || select[1].options[select[1].selectedIndex].value === "Brand") {
				messageElement.textContent = `select brand and sub category`;
			} else {

				event.currentTarget.submit();

			}
		});


	})
}


var inputTs = document.querySelectorAll(".form-control");
if (inputTs !== null) {

	inputTs.forEach((inputT) => {

		inputT.addEventListener("blur", function () {
			var btnSuccess = document.querySelector("button.btn-success");
			var inputTValue = inputT.value.trim();


			if (inputTValue === "") {
				btnSuccess.setAttribute("disabled", "true");
			} else {
				inputTs.forEach((i) => {
					var iV = i.value.trim();
					if (iV === "") {
						btnSuccess.setAttribute("disabled", "true");
					} else {
						btnSuccess.removeAttribute("disabled");
					}
				})
			}

		})
	})
}
var selects = document.querySelectorAll("select.form-select");
if (selects !== null) {

	inputTs.forEach((select) => {

		select.addEventListener("click", function () {
			var btnSuccess = document.querySelector("button.btn-success");
			var selectValue = select.selectedIndex;

			if (selectValue === 0) {
				btnSuccess.setAttribute("disabled", "true");

			} else {
				btnSuccess.removeAttribute("disabled");
			}
		})
	})
}







