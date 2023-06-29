var desClick = document.getElementById('des-click-btn');
var desInfo = document.getElementById('des-info');

function hiddenDescription() {
    if (desInfo.style.display === 'none') {
        desInfo.style.display = 'block';
    }
    else {
        desInfo.style.display = 'none';
    }
}
desClick.addEventListener('click', hiddenDescription);

var detailsClick = document.getElementById('details-click-btn');
var detailsInfo = document.getElementById('details-info');

function hiddenDetails() {
    if (detailsInfo.style.display === 'none') {
        detailsInfo.style.display = 'block';
    }
    else {
        detailsInfo.style.display = 'none';
    }
}

detailsClick.addEventListener('click', hiddenDetails);


var shipClick = document.getElementById('shipping-click-btn');
var shipInfo = document.getElementById('shipping-info');

function hiddenShipping() {
    if (shipInfo.style.display === 'none') {
        shipInfo.style.display = 'block';
    }
    else {
        shipInfo.style.display = 'none';
    }
}
shipClick.addEventListener('click', hiddenShipping);


var productRatingOverview = document.querySelector('div.product-rating-overview__filters');
var productRatings = productRatingOverview.querySelectorAll('.product-rating-overview__filter');


productRatings.forEach((productRating) => {
    productRating.addEventListener('click', toggleRating);

    function toggleRating() {
        var ratingActive = document.querySelector('div.product-rating-overview__filter.product-rating-overview__filter--active')
        ratingActive.classList.remove('product-rating-overview__filter--active');
        productRating.classList.add('product-rating-overview__filter--active');
    }
});



// image 
var feedbacks = document.querySelectorAll('.feedback-product_comment');
console.log('feedbacks', feedbacks);
feedbacks.forEach((feedback) => {
    var imageWrappers = feedback.querySelectorAll('.rating-media-list__image-wrapper');
    var imageZoomed = feedback.querySelector('.rating-media-list__zoomed-image');

    var listImages = feedback.querySelector('.rating-media-list-image-carousel');
    var ratingListImage = feedback.querySelector('.rating-media-list-image-carousel__item-list');

    var arrowPrev = feedback.querySelector('.rating-media-list-carousel-arrow--prev');
    var arrowNext = feedback.querySelector('.rating-media-list-carousel-arrow--next');

    var marginLeftRatingImage;
    let marginLeftRating = ratingListImage.style.width;

    listImages.style.transition = "all 0ms ease 0s";
    listImages.style.width = '0px';

    imageWrappers.forEach(function (imageWrapper) {
        imageWrapper.addEventListener('click', function () {
            imageWrappers.forEach(function (img1) {
                if (img1 !== imageWrapper) {
                    img1.classList.remove('rating-media-list__image-wrapper--active');
                    img1.classList.add('rating-media-list__image-wrapper--inactive');
                }
            });
            if (imageWrapper.classList.contains('rating-media-list__image-wrapper--active')) {
                imageWrapper.classList.remove('rating-media-list__image-wrapper--active');
                imageWrapper.classList.add('rating-media-list__image-wrapper--inactive');
            } else {
                imageWrapper.classList.add('rating-media-list__image-wrapper--active');
                imageWrapper.classList.remove('rating-media-list__image-wrapper--inactive');
            }
            //active zoom in property
            if (imageWrapper.classList.contains('rating-media-list__image-wrapper--active')) {
                imageZoomed.classList.add('rating-media-list__zoomed-image--active');
            } else {
                imageZoomed.classList.remove('rating-media-list__zoomed-image--active');
            }
        });
    });

    var imgSpecZoomed = feedback.querySelectorAll('.rating-media-list-image-carousel__item');

    imageWrappers.forEach(function (img) {
        img.addEventListener('click', zoomEv);
    });
    function zoomEv() {
        if (imageWrappers.length === imgSpecZoomed.length) {
            for (var i = 0; i < imageWrappers.length; i++) {
                if (imageWrappers[i].classList.contains('rating-media-list__image-wrapper--active') && imgSpecZoomed[i].classList.contains('rating-media-list-image-carousel__item')) {

                    listImages.style.width = imgSpecZoomed[i].clientWidth + 'px';
                    ratingListImage.style.marginLeft = -(imgSpecZoomed[i].clientWidth) * i + 'px';
                    marginLeftRatingImage = -(imgSpecZoomed[i].clientWidth) * i;
                }
            }

        }

    }
    imageWrappers.forEach(function (e) {
        e.addEventListener('click', resetRatingMediaList);
    });

    var firstImageWrapper = imageWrappers[0];
    var lastImageWrapper = imageWrappers[imageWrappers.length - 1];


    firstImageWrapper.addEventListener('click', hiddenPrev);
    function hiddenPrev() {
        if (arrowPrev.style.visibility === 'visible') {
            arrowPrev.style.visibility = 'hidden';
            arrowNext.style.visibility = 'visible';
        }
    }
    lastImageWrapper.addEventListener('click', hiddenNext);
    function hiddenNext() {
        if (arrowNext.style.visibility === 'visible') {
            arrowNext.style.visibility = 'hidden';
            arrowPrev.style.visibility = 'visible';
        }
    }


    function resetRatingMediaList() {
        if (!imageZoomed.classList.contains('rating-media-list__zoomed-image--active')) {
            marginLeftRatingImage = 0;
            ratingListImage.style.marginLeft = marginLeftRatingImage + 'px';
        }
        for (var i = 1; i < imageWrappers.length - 1; i++) {
            arrowPrev.style.visibility = 'visible';
            arrowNext.style.visibility = 'visible';
        }
    }


    // make left and right click for zoomed image
    arrowPrev.addEventListener('click', arrowPrevClick);
    arrowPrev.addEventListener('click', firstImgZoomed);

    function arrowPrevClick() {
        arrowNext.style.visibility = 'visible';
        for (var i = 0; i < imageWrappers.length; i++) {
            if (imageWrappers[i].classList.contains('rating-media-list__image-wrapper--active')) {

                imageWrappers[i].classList.remove('rating-media-list__image-wrapper--active');
                imageWrappers[i].classList.add('rating-media-list__image-wrapper--inactive');
                imageWrappers[i - 1].classList.add('rating-media-list__image-wrapper--active');
                imageWrappers[i - 1].classList.remove('rating-media-list__image-wrapper--inactive');
                break;
            }
        }
        if (imageWrappers.length === imgSpecZoomed.length) {
            for (var i = 0; i < imageWrappers.length; i++) {
                if (imageWrappers[i].classList.contains('rating-media-list__image-wrapper--active') && imgSpecZoomed[i].classList.contains('rating-media-list-image-carousel__item')) {


                    listImages.style.width = imgSpecZoomed[i].clientWidth + 'px';
                    marginLeftRatingImage = marginLeftRatingImage + imgSpecZoomed[i].clientWidth;
                    ratingListImage.style.marginLeft = marginLeftRatingImage + 'px';
                }
            }
        }
    }
    function firstImgZoomed() {
        if (firstImageWrapper.classList.contains('rating-media-list__image-wrapper--active')) {
            arrowNext.style.visibility = 'visible';
            arrowPrev.style.visibility = 'hidden';
        }
    }

    //next
    arrowNext.addEventListener('click', arrowNextClick);
    arrowNext.addEventListener('click', arrowNextSmallImg);
    arrowNext.addEventListener('click', lastImgZoomed);

    function arrowNextClick() {
        arrowPrev.style.visibility = 'visible';
        if (imageWrappers.length === imgSpecZoomed.length) {
            for (var i = 0; i < imageWrappers.length; i++) {
                if (imageWrappers[i].classList.contains('rating-media-list__image-wrapper--active') && imgSpecZoomed[i].classList.contains('rating-media-list-image-carousel__item')) {
                    // console.log('Index:', i);
                    // console.log('Element 1:', imageWrappers[i]);
                    // console.log('Element 2:', imgSpecZoomed[i].clientWidth);

                    listImages.style.width = imgSpecZoomed[i].clientWidth + 'px';
                    marginLeftRatingImage = marginLeftRatingImage - imgSpecZoomed[i].clientWidth;
                    ratingListImage.style.marginLeft = marginLeftRatingImage + 'px';
                }
            }
        }
    }
    function arrowNextSmallImg() {
        for (var i = 0; i < imageWrappers.length; i++) {
            if (imageWrappers[i].classList.contains('rating-media-list__image-wrapper--active')) {
                imageWrappers[i].classList.remove('rating-media-list__image-wrapper--active');
                imageWrappers[i].classList.add('rating-media-list__image-wrapper--inactive');
                imageWrappers[i + 1].classList.add('rating-media-list__image-wrapper--active');
                imageWrappers[i + 1].classList.remove('rating-media-list__image-wrapper--inactive');
                break;
            }
        }
    }
    function lastImgZoomed() {
        if (lastImageWrapper.classList.contains('rating-media-list__image-wrapper--active')) {
            arrowNext.style.visibility = 'hidden';
            arrowPrev.style.visibility = 'visible';
        }
    }


    var carouselArrowMouseIn = feedback.querySelector(".rating-media-list-image-carousel__item-list-wrapper");
    var carouselArrowMouseIns = feedback.querySelectorAll('.rating-media-list-carousel-arrow');
    var carouselArrows = feedback.querySelectorAll(".rating-media-list-carousel-arrow");

    carouselArrowMouseIns.forEach(function (e) {
        e.addEventListener("mousemove", function () {
            carouselArrows.forEach(function (carouselArrow) {
                carouselArrow.classList.remove('rating-media-list-carousel-arrow--hint');
            });
        });
    });
    carouselArrowMouseIns.forEach(function (e) {
        e.addEventListener("mouseout", function () {
            carouselArrows.forEach(function (carouselArrow) {
                carouselArrow.classList.add('rating-media-list-carousel-arrow--hint');
            });
        });
    });

    carouselArrowMouseIn.addEventListener("mousemove", function () {

        carouselArrows.forEach(function (carouselArrow) {
            carouselArrow.classList.remove('rating-media-list-carousel-arrow--hint');
        });
    });

    carouselArrowMouseIn.addEventListener("mouseout", function () {
        carouselArrows.forEach(function (carouselArrow) {
            carouselArrow.classList.add('rating-media-list-carousel-arrow--hint');
        });
    });

    //button like
    var likeBtn = feedback.querySelector('.product-rating__like-button');
    var likeValue = feedback.querySelector('.product-rating__like-count');

    if (localStorage.getItem('likeValue')) {
        likeValue.textContent = localStorage.getItem('likeValue');
    }
    likeBtn.addEventListener('click', checkLike);

    function checkLike() {
        var newCount;
        if (likeBtn.classList.contains('product-rating__like-button--liked')) {
            likeBtn.classList.remove('product-rating__like-button--liked');
            var currentCount = parseInt(likeValue.textContent);
            newCount = currentCount - 1;
            likeValue.textContent = newCount.toString();
        } else {
            likeBtn.classList.add('product-rating__like-button--liked');
            var currentCount = parseInt(likeValue.textContent);
            newCount = currentCount + 1;
            likeValue.textContent = newCount.toString();
        }
        localStorage.setItem('likeValue', newCount.toString());
    }

    var dropdown = feedback.querySelector('.stardust-dropdown');
    var dropdownBody = feedback.querySelector('.stardust-dropdown__item-body');
    dropdownBody.style.display = 'none';
    dropdownBody.style.opacity = '0';

    //outside
    document.addEventListener('click', function (event) {
        var target = event.target;
        if (!dropdown.contains(target)) {
            dropdownBody.style.display = 'none';
            dropdownBody.style.opacity = '0';
        }
    });
    //inside
    dropdown.addEventListener('click', function () {
        console.log(dropdownBody.style.display === 'none');
        if (dropdownBody.style.display === 'none') {
            dropdownBody.style.display = 'block';
            dropdownBody.style.opacity = '1';
        } else {
            dropdownBody.style.display = 'none';
            dropdownBody.style.opacity = '0';
        }
    });
});

// product listing 
var listProductSliders = document.querySelectorAll('.list-product-slider');
console.log(listProductSliders);

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
                console.log(icon);
                console.log('sliderItems.scrollLeft', sliderItems.scrollLeft);
                sliderItems.scrollLeft += icon.classList.contains('scroll_carousel_left') ? -firstItemWidth*2 : firstItemWidth*2;
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

// const showHideRightIcons = () => {
//     if(arrowProductLists[1].scrollLeft)
// }




