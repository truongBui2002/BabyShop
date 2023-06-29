
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

//submit form
function submit(){
	document.getElementsByTagName('form').submit();
}




