$(document).ready(function(){
						
						$("#name").focusout(function() {
						
							if ($('#name').val() == "") {
							$('#spanName').text("Enter User Name");
							} else{
							var name = $('#name').val();
							var valid = /^[a-zA-Z]+$/;
								if (!valid.test(name)) {
									$('#spanName').text("Invalid Name");
									
								} else {
							$('#spanName').text("");
							
						}
						}
					});
	
	$("#contact").focusout(function(){
		if ($("#contact").val() == "")
		{
			$('#spanContact').text("Enter Contact");
		}else{
			var contactLength = $('#contact').val().length;
			var contact = $('#contact').val();
			
							if (!$.isNumeric(contact)) {
								$('#spanContact').text("Enter Numbers Only");
							} else {
								if (contactLength == 10) {
									$('#spanContact').text("");
								} else {
									$('#spanContact').text("Contact digit is more than 10");

								}
							}
		}
	});
	
	$("#email").focusout(function(){
		if ($('#email').val() == "")
		{
			$('#spanEmail').text("Enter Email");
		}else{
			var emailValid = $('#email').val();
			var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			if (!filter.test(emailValid)) {
				$('#spanEmail').text("Enter Valid email");
			} else {
				$('#spanEmail').text("");
			}
		}
	});
	
	$("#username1").focusout(function(){
		if ($('#username1').val() == "")
		{
			$('#spanUser').text("Enter User UserName");
		}else{
			$('#spanUser').text("");
		}
	});
	
	$("#password1").focusout(function(){
		if ($('#password1').val() == "")
		{
			$('#spanPassword').text("Enter Password");
		}else{
			var passwordValid = $('#password1').val();
			var regex = /^[a-zA-Z0-9]+$/;
			if (passwordValid.length<10 && passwordValid.length>4){
				if (regex.test(passwordValid)) {
					$('#spanPassword').text("Not AlphaNumeric");
				}
				else
				$('#spanPassword').text("")

			}else {
				$('#spanPassword').text("Not Valid length");
			}

		}
	});
	
	
	//=============================keyUp=======================================================================
	
	$('#password1').keyup(function(){
		
				var passwordValid = $('#password1').val();
				var regex = /^[a-zA-Z0-9]+$/;
				if (passwordValid.length<10 && passwordValid.length>4){
					if (regex.test(passwordValid)) {
						$('#spanPassword').text("Not AlphaNumeric");
					}
					else
					$('#spanPassword').text("")

				}else {
					$('#spanPassword').text("Not Valid length");
				}

			});
	
	$('#email').keyup(function() {
				var emailValid = $('#email').val();
				var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
				if (!filter.test(emailValid)) {
					$('#spanEmail').text("Enter Valid email");
				} else {
					$('#spanEmail').text("");
				}

			});
	
	
	$('#contact').keyup(function() {
		var contactLength = $('#contact').val().length;
		var contact = $('#contact').val();
		
						if (!$.isNumeric(contact)) {
							$('#spanContact').text("Enter Numbers Only");
						} else {
							if (contactLength == 10) {
								$('#spanContact').text("");
							} else {
								$('#spanContact').text("Contact digit is more than 10");

							}
						}
					

				});
	$("#name").keyup(function(){
		var name= $('#name').val();
		var valid=/^[a-zA-Z]+$/;
		if (!valid.test(name)) {
			$('#spanName').text("Invalid Name");
		} else {
			$('#spanName').text("");
		}
	});
	
	
	
});