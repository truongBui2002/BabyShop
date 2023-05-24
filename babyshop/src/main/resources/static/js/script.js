

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
