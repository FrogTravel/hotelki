$(document).ready(() => {

	console.log("hello webkit")
	$( "#submit" ).click(() => {
	  console.log( "Handler for submit called." );
	});

	$( "#decline" ).click(() => {
	  console.log( "Handler for decline called." );
	});
});