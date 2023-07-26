
//sort
var sortBtn = document.getElementById('sortBtn');
var sortBody = document.getElementById('sort');
var subCateName = document.querySelector('.sort__option:checked ~ .sub-categories__name1');


sortBtn.innerHTML = subCateName.textContent;


sortBody.style.display = 'none';

//outside
document.addEventListener('click', function (event) {
	var target = event.target;
	if (!sortBtn.contains(target)) {
		sortBody.style.display = 'none';

	}
});
//inside
sortBtn.addEventListener('click', function () {
	console.log(sortBody.style.display === 'none');
	if (sortBody.style.display === 'none') {
		sortBody.style.display = 'block';
	} else {
		sortBody.style.display = 'none';
	}
});

//filter

var filterBtn = document.getElementById('filter-btn');
var filterHidden = document.getElementById('filter-hidden');
filterHidden.style.display = 'none';

filterBtn.addEventListener('click', function () {
	if (filterHidden.style.display === 'none') {
		filterHidden.style.display = 'block';
		console.log(filterHidden.style.display);
	} else {
		filterHidden.style.display = 'none';
	}
});

function closeF() {
	filterHidden.style.display = 'none';
}

var tabsList = document.querySelector('tabs__list');

var tabsTab = document.querySelectorAll('.tabs__tab');
var tabPanels = document.querySelectorAll('.tabs__panel');

tabsTab[0].setAttribute('aria-selected', 'true');
tabPanels[0].removeAttribute('aria-hidden');


tabsTab.forEach(function (tab) {
	tab.addEventListener('click', function () {
		console.log('tab', tab);
		tabsTab.forEach((e) => {
			e.removeAttribute('araria-selected');
		});
		tabPanels.forEach((e) => {
			e.setAttribute('aria-hidden', 'true');
			e.classList.remove('is-selected');
		});
		if (tabPanels.length === tabsTab.length) {
			for (var i = 0; i < tabPanels.length; i++) {
				if (tab.getAttribute('aria-controls') === tabPanels[i].getAttribute('id')) {
					tab.setAttribute('aria-selected', 'true');
					tabPanels[i].removeAttribute('aria-hidden');
					tabPanels[i].classList.add('is-selected');
				}
			}
		}
	});
});

//send form data

var form = document.getElementById('form-search-product')
var inputs = form.querySelectorAll("input");

inputs.forEach(function (input) {
	input.addEventListener("click", update);
});


var productName = document.getElementById('search').value;
function update() {
	var xhr = new XMLHttpRequest();
	var para = getListInputChecked();
	//var numberPage = document.querySelector('.number-of-pages');
	//numberPage.innerHTML = 'Page 1 ' + 'of ' + pageMax;
	//console.log(pageMax);
	//pageMax.setAttribute('data-maxpage')

	xhr.open("POST", "/search/product/filter?" + para, true);
	xhr.onreadystatechange = function () {

		if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
			document.getElementById("search-product").innerHTML = xhr.responseText;
			//console.log(xhr.responseText);
		}

		var imageBlocks = document.querySelectorAll('.image_block');
		imageBlocks.forEach((e) => {
			const img = e.querySelector('img');
			function loaded() {
				e.classList.add('loaded');
			}
			if (img.complete) {
				loaded();
			} else {
				img.addEventListener('load', loaded);
			}
		});
		setTimeout(wishListInSearch, 60);
		var subCateName = document.querySelector('.sort__option:checked ~ .sub-categories__name1');
		sortBtn.innerHTML = subCateName.textContent;


		history.replaceState(null, null, '/search/product?page=1' + '&productName=' + productName + '&' + para)
		nextPage(para, 1);

	};

	xhr.send();
}

function getListInputChecked() {
	var inputCheckboxes = document.querySelectorAll("input:checked");
	var para = "";

	for (var i = 0; i < inputCheckboxes.length; i++) {
		if (i < inputCheckboxes.length - 1) {
			para += inputCheckboxes[i].name + "=" + inputCheckboxes[i].value + "&";
		} else {
			para += inputCheckboxes[i].name + "=" + inputCheckboxes[i].value;
		}
	}
	return para;
}


function wishListInSearch() {

	var addWishLists = document.querySelectorAll('.add-wishlist');
	addWishLists.forEach((addWishList) => {

		var heartLight = addWishList.querySelector('.heart-light');
		var heartTomato = addWishList.querySelector('.heart-tomato');

		//Lấy ra productId theo input hidden
		var productId = addWishList.querySelector('input[type="hidden"]').value;


		addWishList.addEventListener('click', (e) => {
			addOrRemoveFavorite(productId);

			if (heartLight.classList.contains('fill-neutral-light') && heartTomato.classList.contains('fill-neutral-microwave')) {
				heartLight.classList.add('fill-tomato-dark');
				heartLight.classList.remove('fill-neutral-light');
				heartTomato.classList.add('fill-tomato-dark');
				heartTomato.classList.remove('fill-neutral-microwave');
				addWishList.ariaLabel = 'add from wishlist';

			} else {
				heartLight.classList.add('fill-neutral-light');
				heartTomato.classList.add('fill-neutral-microwave');
				addWishList.ariaLabel = 'remove from wishlist';
			}
		});
	});
	var ariaWishList = document.querySelectorAll('[aria-label="remove from wishlist"]');

	function addedWishlist() {
		var ariaWishList = document.querySelectorAll('[aria-label]');
	}

	// click heart

	function addOrRemoveFavorite(productId) {
		var xhr = new XMLHttpRequest();
		xhr.open("PUT", "/product/favorite/" + productId);
		xhr.send();
	}
}

// next page
var param = getListInputChecked();
var pageLink = document.getElementsByClassName("pagi-btn");
var pageDataElement = document.getElementById("page-data");

var pageNum = pageDataElement.dataset.page;

var pageDataMax = document.getElementById("page-dataMax");
var pageMax = pageDataMax.dataset.maxpage;
nextPage(param, pageNum);
function nextPage(para, pageNum) {
	pageLink[0].setAttribute('href', '/search/product?page=' + (parseInt(pageNum) - 1) + '&productName=' + productName + '&' + para);
	pageLink[1].setAttribute('href', '/search/product?page=' + (parseInt(pageNum) + 1) + '&productName=' + productName + '&' + para);
}

//back/foward thì load lại trang 
/*window.addEventListener('popstate', function(event) {
  location.reload();
});*/
console.log(pageLink[1]);
if (pageLink[1] !== null) {
	pageLink[1].addEventListener("click", function () {
	
	});
}

var numOfPages = document.querySelectorAll(".number-of-pages > span");
if (numOfPages !== null) {
	var num1 = parseInt(numOfPages[0].textContent);
	var num2 = parseInt(numOfPages[1].textContent);

	if (num1 > num2) {
		num1 = num2;
		numOfPages[0].textContent = num1;
	}

}


