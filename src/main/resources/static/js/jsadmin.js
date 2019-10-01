
	/**
	 * 
	 * @returns page admin + delete user by id
	 */
	$(".xoa-user").click(function(){
		 swal({
			 
             title: 'Comfirm!',
             text: "Are you sure that you want to perform this action?",
             showCancelButton: true,
             confirmButtonColor: '#3085d6',
             cancelButtonColor: '#d33',
             confirmButtonText: 'Yes, delete it',
             cancelButtonText: 'No, cancel'
            	 
         }).then((result) => { 
        	 
        	if (result.value) {
        		
        		var self = $(this);
        		var id = $(this).closest("tr").attr("data-id");
        		
        		$.ajax({
        			
        			url		:"/home-admin/delete",
        			type	:"GET",
        			data	:{
        					id:id,
        			},
        			    
        			    success: function(value){
        			    	
        					self.closest("tr").remove();
        					
        				}
        			})   
        	}
         })
	   })

		/**
		 * Change status user (Active -> Unactive) and (Unactive -> Active)
		 * 
		 * @returns
		 */
	    $('.activeUser').change(function() {
	    	// event tu uncheck -> check
	        if($(this).is(":checked")) {
	        	
	      	  swal({
	              title: 'Comfirm!',
	              text: "Are you sure that you want to perform this action?",
	              showCancelButton: true,
	              confirmButtonColor: '#3085d6',
	              cancelButtonColor: '#d33',
	              confirmButtonText: 'Yes, change it',
	              cancelButtonText: 'No, cancel'
	            	  
	          }).then((result) => {
	        	  
	              if (result.value) {
	            	  var self = $(this);
	            	  var id   = $(this).closest("tr").attr("data-id");
	            	  
	            	  $.ajax({
	            		  
	          			url  :"/home-admin/editActiveUser",
	          		    type :"GET",
	          		    data :{
	          		    		id:id,
	          		    },
	          		})   
	              }else{
	            	  
	            	  $(this).prop('checked',false)
	            	  
	              }
	          })
	        	
	        }else{
	        	
	        	swal({
		              title: 'Comfirm!',
		              text: "Are you sure that you want to perform this action?",
		              showCancelButton: true,
		              confirmButtonColor: '#3085d6',
		              cancelButtonColor: '#d33',
		              confirmButtonText: 'Yes, change it',
		              cancelButtonText: 'No, cancel'
		            	  
		          }).then((result) => {
		        	  
		              if (result.value) {
		            	  var self = $(this);
		            	  var id = $(this).closest("tr").attr("data-id");
		            	  $.ajax({
		          			url:"/home-admin/editActiveUser",
		          		    type:"GET",
		          		    data:{
		          		    	id:id,
		          		    },
		          		})   
		            	  
		              }else{
		            	  
		            	  $(this).prop('checked',true)
		              }
		              	
		          }) 
	        }
	             
	        	 
	    });
	
	/**
	 * Process change role USER
	 * @returns
	 */
	
	  $('select').on('change', function() {
		  
		  var id 	   = $(this).closest("tr").attr("data-id");
		  var selected = $(this).children("option:selected").val();
		  if(selected == 1){
			  var roleName = "ADMIN";
		  }else{
			  var roleName = "USER";
		  }
		  $.ajax({
    		  
    			url  :"/home-admin/change-role",
    		    type :"GET",
    		    data :{
    		    		id		 : id,
    		    		roleName : roleName
    		    },
    		    success : function(value) {
    				
    				Swal.fire('Done !', 'You click OK to continue !', 'success')
    			}
    		})   
		  
	  })
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*$(document).ready(function() {
		
		
		 $('.roleUser').change(function() {
				// event tu uncheck -> check
			        if($(this).is(":checked")) {
			        	
			        	  swal({
				              title: 'Comfirm!',
				              text: "Are you sure that you want to perform this action?",
				              showCancelButton: true,
				              confirmButtonColor: '#3085d6',
				              cancelButtonColor: '#d33',
				              confirmButtonText: 'Yes, change it',
				              cancelButtonText: 'No, cancel'
				            	  
				          }).then((result) => {
				        	  
				              if (result.value) {
				            	  var id = $(this).closest("tr").attr("data-id");
				            	  var va =  $(this).val();
				            	  
						        	if(va == 1){
						        		var roleName = "ADMIN";
						        		
						        	}else{
						        		var roleName= "USER";
						        	}
				            	  
				            	  $.ajax({
				            		  
				          			url  :"http://localhost:8080/home-admin/change-role",
				          		    type :"GET",
				          		    data :{
				          		    		id		 : id,
				          		    		roleName : roleName
				          		    },
				          		})   
				              }else{
				            	  
				            	  $(this).prop('checked',false)
				            	  
				              }
				          })
			        	
			        	
			        	
			        }else{
			        	
			        	swal({
				              title: 'Comfirm!',
				              text: "Are you sure that you want to perform this action?",
				              showCancelButton: true,
				              confirmButtonColor: '#3085d6',
				              cancelButtonColor: '#d33',
				              confirmButtonText: 'Yes, change it',
				              cancelButtonText: 'No, cancel'
				            	  
				          }).then((result) => {
				        	  
				              if (result.value) {
				            	 
				            	  var id = $(this).closest("tr").attr("data-id");
				            	  var va =  $(this).val();
				            	  
						        	if(va == 1){
						        		var roleName = "ADMIN";
						        		
						        	}else{
						        		var roleName= "USER";
						        	}
				            	  
				            	  $.ajax({
				          			url:"http://localhost:8080/home-admin/change-role",
				          		    type:"GET",
				          		    data:{
				          		    	
				          		    	id		 : id,
				          		    	roleName : roleName
				          		    	
				          		    },
				          		})   
				            	  
				              }else{
				            	  
				            	  $(this).prop('checked',true)
				              }
				              	
				          }) 
			        	
			        }
			       
			   })
			   
	});
		*/


			