
var list_produt = document.querySelectorAll('.product-details');

list_produt.forEach((product) => {
	var fbId = product.dataset.feedbackid;
	var change_status = product.querySelector('.change-status');
	change_status.addEventListener('click', (e)=>{
		e.preventDefault();
		chagneStatus(fbId).then(result=>{
			var status = product.querySelector('.feedback-status')
			status.innerHTML = result;
		})
	})
})


function chagneStatus(feedbackId) {
	return new Promise((resolve, reject) => {
		var xhr = new XMLHttpRequest();
		xhr.open("PUT", "/manager/staff/feedback-management/change-status");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				resolve(xhr.response);
				//console.log(xhr.response);
			}
		};
		xhr.onerror = function() {
			reject(xhr.statusText);// xảy ra nếu lỗi
		};
		xhr.send(feedbackId);
	});
}