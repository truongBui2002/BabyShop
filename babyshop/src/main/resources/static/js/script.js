function startSlideshow() {
  const slides = document.querySelectorAll('.slides a');
  let currentSlide = 0;

  setInterval(() => {
    slides[currentSlide].className = '';
    currentSlide = (currentSlide + 1) % slides.length;
    slides[currentSlide].className = 'active';
  }, 4000);
}

window.addEventListener('load', startSlideshow);

//dragging items
const carousels = document.querySelectorAll(".slider___items")

console.log(carousels);
carousels.forEach((carousel) => {
  const firstImg = carousel.querySelectorAll("img")[0];
  const arrowIcons = document.querySelectorAll(".slider_nav button");
  const sliderChildren = document.querySelectorAll(".slider-items-list");
  sliderChildren.forEach((sliderChild) => {
    sliderChild.addEventListener("click", (e) => {
      e.preventDefault();
    });
  });

  let isDragStart = false, isDragging = false, prevPageX, prevScrollLeft, positionDiff;

  const showHideIcons = () => {
    // showing and hiding prev/next icon according to carousel scroll left value
    let scrollWidth = carousel.scrollWidth - carousel.clientWidth; 
    arrowIcons[0].style.display = carousel.scrollLeft == 0 ? "none" : "block";
    arrowIcons[1].style.display = carousel.scrollLeft >= scrollWidth -1 ? "none" : "block";
  }

  arrowIcons.forEach(icon => {
    icon.addEventListener("click", () => {
      let firstImgWidth = firstImg.clientWidth + 14;  
      carousel.scrollLeft += icon.className == "left" ? -firstImgWidth : firstImgWidth;
      setTimeout(showHideIcons(), 60); // calling showHideIcons after 60ms
    });
  });


  const dragStart = (e) => {
    // updatating global variables value on mouse down event
    isDragStart = true;
    prevPageX = e.pageX || e.touches[0].pageX;
    prevScrollLeft = carousel.scrollLeft;
  }

  const dragging = (e) => {
    // scrolling images/carousel to left according to mouse pointer
    if (!isDragStart) return;
    e.preventDefault();
    isDragging = true;
    carousel.classList.add("dragging");
    positionDiff = (e.pageX || e.touches[0].pageX) - prevPageX;
    carousel.scrollLeft = prevScrollLeft - positionDiff;
    showHideIcons();
  }

  const dragStop = () => {
    isDragStart = false;
    carousel.classList.remove("dragging");

    if (!isDragging) return;
    isDragging = false;
    // autoSlide();
  }

  carousel.addEventListener("mousedown", dragStart); // push
  carousel.addEventListener("touchstart", dragStart);

  document.addEventListener("mousemove", dragging);
  carousel.addEventListener("touchmove", dragging);

  document.addEventListener("mouseup", dragStop); //drop
  carousel.addEventListener("touchend", dragStop);
});

