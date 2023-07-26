var list_order_details = document.querySelectorAll('.order-detatils');
list_order_details.forEach((order_details)=>{
	var orderDetailsId = order_details.dataset.oddid;
	var statusShip = order_details.querySelector('.btn-ship');
	var odStatus = order_details.querySelector('.od-status');
	console.log(orderDetailsId);
	statusShip.addEventListener('click', (e)=>{
		e.preventDefault();
		shipStatus(orderDetailsId).then(result=>{
			if(result=='SHIP'){
				odStatus.innerHTML = result;
			}
			console.log(orderDetailsId);
		})
	})
	var statusComplete = order_details.querySelector('.btn-complete');
	statusComplete.addEventListener('click', (e)=>{
		e.preventDefault();
		completeStatus(orderDetailsId).then(result=>{
			if(result=='COMPLETED'){
				odStatus.innerHTML = result;
			}
			console.log(orderDetailsId);
		})
	})
	var statusCancel = order_details.querySelector('.btn-cancel');
	statusCancel.addEventListener('click', (e)=>{
		e.preventDefault();
		cancelStatus(orderDetailsId).then(result=>{
			if(result=='CANCELLED'){
				odStatus.innerHTML = result;
			}
			console.log(orderDetailsId);
		})
	})
})

function shipStatus(oddId){
	return new Promise((resolve, reject) => {
		var xhr = new XMLHttpRequest();
		xhr.open("PUT", "/manager/staff/order-list/change-status/ship");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				resolve(xhr.response);
				//console.log(xhr.response);
			}
		};
		xhr.onerror = function() {
			reject(xhr.statusText);// xảy ra nếu lỗi
		};
		xhr.send(oddId);
	});
}

function completeStatus(oddId){
	return new Promise((resolve, reject) => {
		var xhr = new XMLHttpRequest();
		xhr.open("PUT", "/manager/staff/order-list/change-status/complete");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				resolve(xhr.response);
				//console.log(xhr.response);
			}
		};
		xhr.onerror = function() {
			reject(xhr.statusText);// xảy ra nếu lỗi
		};
		xhr.send(oddId);
	});
}

function cancelStatus(oddId){
	return new Promise((resolve, reject) => {
		var xhr = new XMLHttpRequest();
		xhr.open("PUT", "/manager/staff/order-list/change-status/cancel");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				resolve(xhr.response);
				//console.log(xhr.response);
			}
		};
		xhr.onerror = function() {
			reject(xhr.statusText);// xảy ra nếu lỗi
		};
		xhr.send(oddId);
	});
}