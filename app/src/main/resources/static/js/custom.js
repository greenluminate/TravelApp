window.onload = setActiveNavItem();

//window.onhashchange=changeMenu();

function setActiveNavItem() {
    let navName = window.location.href.split('/').at(-1).split('?')[0];
    let navItem = document.querySelector(`[href="/${navName}"]`);
    navItem.className += " active"
}