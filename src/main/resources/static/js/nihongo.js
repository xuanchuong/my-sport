$(document).ready(function() {
	'use strict';

	var c, currentScrollTop = 0, navbar = $('.layout-topbar');

	$(window).scroll(function() {
		var a = $(window).scrollTop();
		var b = navbar.height();

		currentScrollTop = a;

		if (c < currentScrollTop) {
			navbar.addClass("scrollUp");
		} else if (c > currentScrollTop) {
			navbar.removeClass("scrollUp");
		}
		c = currentScrollTop;
	});

});