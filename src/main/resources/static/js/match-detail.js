$(document).on("click", ".paticipant-link", function() {
	var playerId = $(this).data('id');
	$(".modal-body #playerId").val(playerId);
});