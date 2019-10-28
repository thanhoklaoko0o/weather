$().ready(function() {
	//Validate Form
	$("#form-login").validate({
		rules: {
			"username" : {
				required: true
			},
			
			"password" : {
				required : true,
				minlength : 8,
				maxlength : 32
			}
		},
		 messages : {
				"username" : {
					required : "UserName is required .",
				},
				
				"password" : {
					required : "Password is required .",
					minlength : "Please enter at least 8 characters .",
					maxlength : "Please enter up to 32 characters ."
				}
		}
	});

	//Reset password
	$(".btn-reset").click(function(){
		$("#kqSucessful").text("");
		$("#kqFailed").text("");
		var email =  $('#txt_email').val();
		$("#loading").show();
		console.log(email);
		$.ajax({
			url : "/forgot-password",
			type : "POST",
			data : {
				email : email
			},
			
			success : function(value) {
				if(value == true){
					$("#kqSucessful").text("Access your email to change password .");
					$("#loading").hide();
				}else{
					$("#kqFailed").text("Send email failed . Please check agains !");
					$("#loading").hide();
				}
			},
			error : function() {
				$("#kqFailed").text("System error . Please check agains .");
				$("#loading").hide();
			}
		})
	})

	// Validate form reset password
	$("#form-reset").validate({
		rules: {
			"password" : {
				required : true,
				minlength : 8,
				maxlength : 32
			},
			
			"confirmPassword" : {
				equalTo: "#password"
			}
		},
		 messages : {
				"password" : {
					required : "Password is required .",
					minlength : "Please enter at least 8 characters .",
					maxlength : "Please enter up to 32 characters ."
				},
				
				"confirmPassword" : {
					equalTo: "The two passwords must be the same ."
				}
		}
	});
});
