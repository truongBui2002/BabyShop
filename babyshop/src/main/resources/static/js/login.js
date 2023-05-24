function showTab(event, tabNumber) {
    event.preventDefault();
    var tabPanels = document.querySelectorAll('.tabs__panel');
    tabPanels.forEach(function (panel) {
        panel.style.display = 'none';
    });

    var tabLinks = document.querySelectorAll('.tabs__tab');
    tabLinks.forEach(function (link) {
        link.classList.remove('is-selected');
    });


    var selectedTabPanel = document.getElementById(tabNumber);
    selectedTabPanel.classList.remove('hidden-tab');
    selectedTabPanel.style.display = 'block';


    var selectedTabLink = document.getElementById(tabNumber + '-tab');
    selectedTabLink.classList.add('is-selected');
    if (tabNumber == 1) {
        var selectedTabLink1 = document.getElementById('1-tab');
        var selectedTabLink2 = document.getElementById('2-tab');
        selectedTabLink1.style.backgroundColor = '#fff';
        selectedTabLink2.style.backgroundColor = '#f7efef';
    }
    if (tabNumber == 2) {
        var selectedTabLink1 = document.getElementById('1-tab');
        var selectedTabLink2 = document.getElementById('2-tab');
        selectedTabLink2.style.backgroundColor = '#fff';
        selectedTabLink1.style.backgroundColor = '#f7efef';
    }
}




