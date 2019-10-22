
	$().ready(function() {
		/**
		 * check user name in DB
		 * 
		 * @param userName
		 * @returns
		 *//*
		$('.userName').on('keyup', function() {
			//Check UserName existed when user type input
			if (this.value.length > 0) {
				var userName = this.value;
				//Check in DB system
				$.ajax({
					url : "/checkUserName",
					type : "POST",
					data : {
						userName : userName
					},
					success : function(value) {
						// If result = true, UserName exsts 
						if(value == "true"){
							// Message when UserName exists
							$("#kqCheckName").text("");
							// Disable input in form sign up
							var inputs=document.getElementsByClassName("disinput");
							for(i=0;i<inputs.length;i++){
								inputs[i].disabled=false;
							}
						// If result = false
						}else{
							$("#kqCheckName").text("User name not exists in system !")
							var inputs=document.getElementsByClassName("disinput");
							for(i=0;i<inputs.length;i++){
								inputs[i].disabled=true;
							}
						}
					}
				})
		}else{
			$("#kqCheckName").text("");
		}
	});
	*/
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
	});
