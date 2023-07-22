// generic
var a = document.querySelectorAll('.FEE-3D');;
a.forEach((e) => {
    e.addEventListener('click', () => {
        a.forEach((c) => {
            c.classList.remove('tH0d6d');
        })
        e.classList.add('tH0d6d');
    });
});