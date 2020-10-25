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

$('button[name="confirmAcceptJoinRequestBt"]').click(function (event) {
	event.preventDefault();
	const modal = $('form')
	const email = modal.find('.modal-body #requestUserEmail').val();
	let data = modal.serialize();
	$.post('/match/participant/accept?email='+email, data, reloadPage);
});

$('#acceptJoinRequestModal').on('show.bs.modal', function (event) {
	const button = $(event.relatedTarget) // Button that triggered the modal
	const objectName = button.data('object-name')
	console.log("acceptJoinRequestModal show-up: " + objectName);
	// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
	const modal = $(this)
	modal.find('.modal-body #requestUserEmail').val(objectName);
});
