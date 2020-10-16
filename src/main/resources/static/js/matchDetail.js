// jQuery
function reloadPage () {
	window.location.reload();
}

$('button[name="joinBt"]').click(function (event) {
	event.preventDefault();
	const data = $('form').serialize();
	$.post('/match/join', data, reloadPage);
});

$('button[name="leaveBt"]').click(function (event) {
	event.preventDefault();
	const data = $('form').serialize();
	$.post('/match/leave', data, reloadPage);
});
