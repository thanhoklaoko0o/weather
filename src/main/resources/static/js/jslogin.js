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
			var email =  $('#txt_email').val();
			$.ajax({
				url : "/forgot-password",
				type : "POST",
				data : {
					email : email
				},

				success : function(value) {
					if(value == true){
						Swal.fire('Done !', 'You click OK to continue !', 'success')
					}
				},
				error : function() {
					
				}
			})
		})
	});
