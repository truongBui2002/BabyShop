var brandLetters = document.getElementById("brands-letter");

console.log(brandLetters);
for (var i = 65; i <= 90; i++) {
    var chr = String.fromCharCode(i).toUpperCase();
    var link = document.createElement("a");
    link.text = chr;
    // link.setAttribute("href", "group" + chr);
    link.setAttribute("href", "#");
    link.classList.add("brands-letter_link");
    brandLetters.appendChild(link);

}
const brandLetterLinks = document.querySelectorAll(".brands-letter_link");
console.log(brandLetterLinks);

brandLetterLinks.forEach((brandLetterLink) => {
    brandLetterLink.addEventListener('click', (e) => {
        e.classList.add("is-selected");
    })
});