$(document).ready(function() {
	'use strict';

	let c, currentScrollTop = 0, navbar = $('.layout-topbar');

	$(window).scroll(function() {
		currentScrollTop = $(window).scrollTop();

		if (c < currentScrollTop) {
			navbar.addClass("scrollUp");
		} else if (c > currentScrollTop) {
			navbar.removeClass("scrollUp");
		}
		c = currentScrollTop;
	});

});
