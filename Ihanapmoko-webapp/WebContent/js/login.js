function checkPasswordMatch() {
	var password = $("input[name='regPassword']").val();
	var confirmPassword = $("input[name='regConfirmPassword']").val();

	if (password != confirmPassword)
		$("input[name='regConfirmPassword']").addClass("error");
	else
		$("input[name='regConfirmPassword']").removeClass("error");
}

$(document).ready(function() {
	$("input[name='regConfirmPassword']").keyup(checkPasswordMatch);

	$("#registration-form").submit(function(event) {
		var password = $("input[name='regPassword']").val();
		var confirmPassword = $("input[name='regConfirmPassword']").val();
		if (password === confirmPassword) {
			return

		}
		alert("Password doesnt match")
		event.preventDefault();

	});

});
