
// product listing 
var listProductSliders = document.querySelectorAll('.list-product-slider');
//console.log(listProductSliders);

listProductSliders.forEach((listProductSlider) => {



  const sliderItems = listProductSlider.querySelector('.slider___items1');

  const firstItem = sliderItems.querySelectorAll('.slider-items-list1')[0];

  const arrowProductLists = listProductSlider.querySelectorAll('button.scroll_carousel');
  arrowProductLists[0].setAttribute('disabled', 'true');

  let firstItemWidth = firstItem.clientWidth + 12;
  let scrollWidth = sliderItems.scrollWidth - sliderItems.clientWidth;
  arrowProductLists.forEach(
    icon => {
      icon.addEventListener('click', () => {
        //console.log(icon);
        //console.log('sliderItems.scrollLeft', sliderItems.scrollLeft);
        sliderItems.scrollLeft += icon.classList.contains('scroll_carousel_left') ? -firstItemWidth * 2 : firstItemWidth * 2;
        setTimeout(() => showHideIcons(), 30);
      });
    }
  );

  const showHideIcons = () => {
    if (sliderItems.scrollLeft === 0) {
      arrowProductLists[0].setAttribute('disabled', 'true');
    } else {
      arrowProductLists[0].removeAttribute('disabled');
    }
    if (sliderItems.scrollLeft === scrollWidth) {
      arrowProductLists[1].setAttribute('disabled', 'true');
    } else {
      arrowProductLists[1].removeAttribute('disabled');
    }

  }
});


/* image in list product */
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
/* categories load image */
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
var boxImgs = document.querySelectorAll('.box-img');
//console.log('boxImgs', boxImgs);
boxImgs.forEach((e) => {
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


const upTop = document.getElementById('up-top');
upTop.addEventListener('click', () => {
  document.body.scrollTop = 0;
  document.documentElement.scrollTop = 0;
});

window.onscroll = function () { scrollFunction() };

function scrollFunction() {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    upTop.style.opacity = "1";

  } else {
    upTop.style.opacity = "0";
  }
}

/* fill heart */
var addWishLists = document.querySelectorAll('.add-wishlist');
addWishLists.forEach( (addWishList) => {
	
	var heartLight = addWishList.querySelector('.heart-light');
	var heartTomato = addWishList.querySelector('.heart-tomato');
	
	//Lấy ra productId theo input hidden
	var productId = addWishList.querySelector('input[type="hidden"]').value;
//	console.log('productId: ' + productId)
	
	addWishList.addEventListener('click', (e) => {
		addOrRemoveFavorite(productId);
	
		if(heartLight.classList.contains('fill-neutral-light') && heartTomato.classList.contains('fill-neutral-microwave')) {
			heartLight.classList.add('fill-tomato-dark');
			heartLight.classList.remove('fill-neutral-light');
			heartTomato.classList.add('fill-tomato-dark');
			heartTomato.classList.remove('fill-neutral-microwave');
			addWishList.ariaLabel = 'add from wishlist';
			
		}else {
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

function addOrRemoveFavorite(productId){
	//console.log("Đã bấm vào favorite: " + productId );
	//Thêm request ở đây
	var xhr = new XMLHttpRequest();
	xhr.open("PUT", "/product/favorite/" + productId);
	xhr.send();
}
