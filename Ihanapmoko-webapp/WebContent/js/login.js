function checkPasswordMatch() {
	var password = $("input[name='regPassword']").val();
	var confirmPassword = $("input[name='regConfirmPassword']").val();

	if (password != confirmPassword)
		$("input[name='regConfirmPassword']").addClass("error");
	else
		$("input[name='regConfirmPassword']").removeClass("error");
}

function validateForm(){
	var valid = true;
	var regConfirmPassword = $("input[name='regEmail']");
	var firstName = $("input[name='regFirstName']");
	var middleName = $("input[name='regMiddleName']");
	var lastName = $("input[name='regLastName']");
	var contactNumber = $("input[name='regContactNumber']");
	var password = $("input[name='regPassword']");
	var passwordConfirm = $("input[name='regConfirmPassword']");
	
	if(regConfirmPassword.val()==""){
		valid = false;
		regConfirmPassword.addClass("error");
	} else{
		regConfirmPassword.removeClass("error");
	}
	
	if(firstName.val()==""){
		valid = false;
		firstName.addClass("error");
	} else{
		firstName.removeClass("error");
	}
	
	if(middleName.val()==""){
		valid = false;
		middleName.addClass("error");
	} else{
		middleName.removeClass("error");
	}
	
	if(lastName.val()==""){
		valid = false;
		lastName.addClass("error");
	} else{
		lastName.removeClass("error");
	}
	
	if(contactNumber.val()==""){
		valid = false;
		contactNumber.addClass("error");
	} else{
		contactNumber.removeClass("error");
	}
	
	if(password.val()==""){
		valid = false;
		password.addClass("error");
	} else{
		password.removeClass("error");
	}
	
	if(passwordConfirm.val()==""){
		valid = false;
		passwordConfirm.addClass("error");
	} else{
		passwordConfirm.removeClass("error");
	}
	
	return valid;
	
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
	
	$("#login-form").submit(function(event) {
		var password = $("input[name='password']");
		var username = $("input[name='username']");
		if(password.val()==""){
			password.addClass("error");
		}else{
			password.removeClass("error");
		}
		
		if(username.val()==""){
			username.addClass("error");
		}else{
			username.removeClass("error");
		}
		if (password.val() !== "" && username.val()!== "") {
			
			return
		}
		alert("Missing input!")
		event.preventDefault();

	});

});
