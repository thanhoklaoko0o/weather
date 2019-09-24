	
	$(".xoa-user").click(function(){

		const swalWithBootstrapButtons = Swal.mixin({
	customClass: {

	cancelButton: 'btn btn-danger',
	confirmButton: 'btn btn-success'
	},
	buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
	title: 'Are you sure that you want to perform this action?',
	type: 'warning',
	showCancelButton: true,
	cancelButtonText: 'No, cancel!',
	confirmButtonText: 'Yes, delete it!',
	reverseButtons: false
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
	swalWithBootstrapButtons.fire(
 	 'Deleted!',
 	 'User has been deleted.',
  	'success'
	)
	} else if (

	result.dismiss === Swal.DismissReason.cancel
	) {
	swalWithBootstrapButtons.fire(
  	'Cancelled',
  	'Your action is safe :)',
 	 'error'
	)
	}
		})
			})