
var list_user = document.querySelectorAll('.user-details');

list_user.forEach((user) => {
	var uId = user.dataset.userid;
	var change_status = user.querySelector('.change-status');
	change_status.addEventListener('click', (e)=>{
		e.preventDefault();
		chagneStatus(uId).then(result=>{
			var status = user.querySelector('.user-status')
			status.innerHTML = result;
		})
	})
})


function chagneStatus(userId) {
	return new Promise((resolve, reject) => {
		var xhr = new XMLHttpRequest();
		xhr.open("PUT", "/manager/admin/list-user/change-status");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				resolve(xhr.response);
				//console.log(xhr.response);
			}
		};
		xhr.onerror = function() {
			reject(xhr.statusText);// xảy ra nếu lỗi
		};
		xhr.send(userId);
	});
}