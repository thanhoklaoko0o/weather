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
						//$("#kqCheckName").text("User name exists !");
						var inputs=document.getElementsByClassName("disinput");
						for(i=0;i<inputs.length;i++){
						    inputs[i].disabled=false;
						}  
					
					}else{
					 	$("#kqCheckName").text("")
					 	var inputs=document.getElementsByClassName("disinput");
						for(i=0;i<inputs.length;i++){
						    inputs[i].disabled=true;
						}  
					}
				}
			})
		}
