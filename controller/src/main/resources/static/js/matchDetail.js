// jQuery
function reloadPage () {
	window.location.reload();
}

$('button[name="confirmJoinBt"]').click(function (event) {
	event.preventDefault();

	const data = $('form').serialize();
	$.post('/match/join', data, reloadPage);
});

$('button[name="confirmLeaveBt"]').click(function (event) {
	event.preventDefault();
	const data = $('form').serialize();
	$.post('/match/leave', data, reloadPage);
});

$('button[name="confirmCancelRequestBt"]').click(function (event) {
	event.preventDefault();
	const data = $('form').serialize();
	$.post('/match/cancel/request', data, reloadPage);
});
