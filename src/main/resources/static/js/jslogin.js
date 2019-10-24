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
					"userName" : {
						required : "UserName is required .",
					},
					
					"username" : {
						required : "Password is required .",
						minlength : "Please enter at least 8 characters .",
						maxlength : "Please enter up to 32 characters ."
					}
			}
		});

		//Reset password
		$(".btn-block").click(function(){
			var form =  $('#txt_email').val();
			$.ajax({
				url : "/forgot-password",
				type : "POST",
				data : {
					form : form
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
