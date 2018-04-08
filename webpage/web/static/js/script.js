$(document).ready(() => {
	ACCEPT = 3
	DECLINE = 0

	set_state = (event, state) => {
		source = $(event.target).closest('.modal-content')
		index = $(source).children('modal-header').find('.id')
		comment = $(source).find('#comment')
		// Add deletion of the row
		return {'id': $(index).hmtl(), 'approved_level': state, 'comment': comment.val()}
	}

	console.log("hello webkit")
	$("#submit").click((e) => {
	  console.log( "Handler for submit called." );
	  $.post('127.0.0.1:5000/api/post/markers/gov_feedback', set_state(e, ACCEPT));
	});

	$("#decline").click((e) => {
	  console.log( "Handler for decline called." );
	  $.post('127.0.0.1:5000/api/post/markers/gov_feedback', set_state(e, DECLINE));
	});	
});