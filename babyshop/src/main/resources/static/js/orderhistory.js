
var tabs = document.querySelectorAll('.OFl2GI'); //trả về list thẻ a
tabs.forEach((tab, index) => {
	tab.addEventListener('click', (e) => {
		e.preventDefault();
		tabs.forEach((tb) => {
			if (tb.classList.contains("gAImis")) {
				tb.classList.remove("gAImis");
			}
		});
		tab.classList.add("gAImis");
		//console.log(index);
		var content = document.querySelector('.order-history-load');
		switch (index) {
			case 0:
				var url = "/user/viewprofile/order/orderhistory/all";
				getData(url).then(result => {
					content.innerHTML = result;
					cancelItem();
					confirmReceive();
					sendFeedback();
				});
				break;
			case 1:
				var url = "/user/viewprofile/order/orderhistory/wait";
				getData(url).then(result => {
					content.innerHTML = result;
					cancelItem();
					confirmReceive();
				});
				break;
			case 2:
				var url = "/user/viewprofile/order/orderhistory/shipping";
				getData(url).then(result => {
					content.innerHTML = result;
					cancelItem();
					confirmReceive();
				});
				break;
			case 3:
				var url = "/user/viewprofile/order/orderhistory/completed";
				getData(url).then(result => {
					content.innerHTML = result;
					cancelItem();
					confirmReceive();
					sendFeedback();
				});
				break;
			case 4:
				var url = "/user/viewprofile/order/orderhistory/cancelled";
				getData(url).then(result => {
					content.innerHTML = result;
					cancelItem();
					confirmReceive();
				});
				break;
		}


	});
});

function getData(url) {
	return new Promise((resolve, reject) => {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", url);
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				resolve(xhr.response);// 
				//console.log(xhr.response);
			}
		};
		xhr.onerror = function() {
			reject(xhr.statusText);// xảy ra nếu lỗi
		};
		xhr.send();
	});
}


//rate star
const ratingStars = [...document.getElementsByClassName("rating__star")];

function executeRating(stars) {
	const starClassActive = "rating__star fas fa-star";
	const starClassInactive = "rating__star far fa-star";
	const starsLength = stars.length;
	let i;
	stars.map((star) => {
		star.onclick = () => {
			i = stars.indexOf(star);

			if (star.className === starClassInactive) {
				for (i; i >= 0; --i) stars[i].className = starClassActive;
			} else {
				for (i; i < starsLength; ++i) stars[i].className = starClassInactive;
			}
		};
	});
}
executeRating(ratingStars);

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

// send feedback
sendFeedback();
function sendFeedback() {
	var list_product = document.querySelectorAll('.hiXKxx');
	list_product.forEach((product) => {
		var openFeeback = product.querySelector('.add-feedback');
		var oddid = product.dataset.oddid;
		//console.log(oddid);
		if (openFeeback !== null) {
			openFeeback.addEventListener('click', () => {
				var fbForm = document.querySelector('#form-feedback');
				var inputOddId = fbForm.querySelector('#odDetailsId');
				inputOddId.value = oddid;
				//console.log(inputOddId)
				//console.log("ONPEN FEEDBACK")
			});
		}
	})
}
//hủy đơn
cancelItem();
confirmReceive();
function cancelItem() {
	var btn_cancel = document.querySelectorAll('.btn-cancel-product');
	btn_cancel.forEach((btn) => {
		btn.addEventListener('click', () => {
			var odDetailsId = btn.dataset.oddid;
			cancel(odDetailsId).then(result => {
				if (result === 'CANCELLED') {
					location.reload();

				}
				console.log(result);
			})

		})
	})
}
// nhận được hàng 
function confirmReceive() {
	var btn_receive = document.querySelectorAll('.btn-receive');
	btn_receive.forEach((btn) => {
		btn.addEventListener('click', () => {
			var odDetailsId = btn.dataset.oddid;
			receive(odDetailsId).then(result => {
				if (result === 'COMPLETED') {
					location.reload();
				}
				console.log(result);
			})
		})
	})
}

function cancel(oddid) {
	return new Promise((resolve, reject) => {
		var xhr = new XMLHttpRequest();
		xhr.open("PUT", "/user/viewprofile/order/orderhistory/cancel");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				resolve(xhr.response);// 
				//console.log(xhr.response);
			}
		};
		xhr.onerror = function() {
			reject(xhr.statusText);// xảy ra nếu lỗi
		};
		xhr.send(oddid);
	});
}

function receive(oddid) {
	return new Promise((resolve, reject) => {
		var xhr = new XMLHttpRequest();
		xhr.open("PUT", "/user/viewprofile/order/orderhistory/confirm-recive");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				resolve(xhr.response);// 
				//console.log(xhr.response);
			}
		};
		xhr.onerror = function() {
			reject(xhr.statusText);// xảy ra nếu lỗi
		};
		xhr.send(oddid);
	});
}
