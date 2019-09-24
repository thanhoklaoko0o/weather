	
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
        				url:"http://localhost:8080/home-admin/delete",
        			    type:"GET",
        			    data:{
        			    	id:id,
        			    	
        			    },
        				success: function(value){
        					self.closest("tr").remove();
        				}
        			})   
        		
        		}
         })

		})
			