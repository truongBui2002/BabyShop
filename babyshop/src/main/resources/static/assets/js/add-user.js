
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

			imgElement.style.width = '100px';
			imgElement.style.height = '100px';
			imgElement.classList.add("profile-pic");
			imgElement.classList.add("rounded");
			imageContainer.appendChild(imgElement);
		}
	});
});

var form_add_user = document.querySelector('#form-add-user');
var form_image = document.querySelector('#form-image');

form_add_user.addEventListener("submit", function(e) {
	e.preventDefault();
	const form = e.target;
	var valueRole = form_image.querySelector('.selected-role').value;
	var inputFile = form_image.querySelector('#imageInput');
	var inf = inputFile.cloneNode(true);
	inf.style.display = 'none';

	const role = document.createElement("input");
	role.type = "hidden";
	role.name = "role";
	role.value = valueRole;

	form_add_user.appendChild(role);
	form_add_user.appendChild(inf);
	form.submit();
})
