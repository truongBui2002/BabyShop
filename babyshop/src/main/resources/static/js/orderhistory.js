
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
				});
				break;
			case 1:
				var url = "/user/viewprofile/order/orderhistory/wait";
				getData(url).then(result => {
					content.innerHTML = result;
				});
				break;
			case 2:
				var url = "/user/viewprofile/order/orderhistory/shipping";
				getData(url).then(result => {
					content.innerHTML = result;
				});
				break;
			case 3:
				var url = "/user/viewprofile/order/orderhistory/completed";
				getData(url).then(result => {
					content.innerHTML = result;
				});
				break;
			case 4:
				var url = "/user/viewprofile/order/orderhistory/cancelled";
				getData(url).then(result => {
					content.innerHTML = result;
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





