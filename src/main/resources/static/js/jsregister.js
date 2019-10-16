		/**
		 * check user name in DB
		 * @param userName
		 * @returns
		 */
		function checkUserName(userName){
			
			$.ajax({
				url : "/checkUserName",
				type : "POST",
				data : {
					userName : userName
				},
				success : function(value) {
					if(value == "true"){
						$("#kqCheckName").text("User name exists !");
						var inputs=document.getElementsByClassName("disinput");
						for(i=0;i<inputs.length;i++){
						    inputs[i].disabled=true;
						}  
					
					}else{
					 	$("#kqCheckName").text("")
					 	var inputs=document.getElementsByClassName("disinput");
						for(i=0;i<inputs.length;i++){
						    inputs[i].disabled=false;
						}  
					}
				}
			})
		}
		
		
		/**
		 * check email in DB
		 * @param email
		 * @returns
		 */
		function checkEmail(email){
			
			$.ajax({
				url : "/checkEmail",
				type : "POST",
				data : {
					email : email
				},
				success : function(value) {
					
					if(value == "true"){
						
						$("#kqCheckMail").text("Email exists !");
						var inputs=document.getElementsByClassName("dinput");
						for(i=0;i<inputs.length;i++){
						    inputs[i].disabled=true;
						}  
						
					}else{
						
						$("#kqCheckMail").text("")
						var inputs=document.getElementsByClassName("dinput");
						for(i=0;i<inputs.length;i++){
						    inputs[i].disabled=false;
						}  
						
					}
				}
			})
		}