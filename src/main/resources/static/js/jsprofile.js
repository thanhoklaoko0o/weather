$().ready(function() {
	//Validate Form
	$("#form-profile").validate({
		rules: {
			"firstName" : {
				required : true
			},
			"lastName" : {
				required : true
			},
			"email" : {
				required : true,
				email : true
			},
			"encrytedPassword" : {
				required : true,
				minlength : 8,
				maxlength : 32
			},
			"confirmPassword" : {
				equalTo: "#password",
				minlength : 8
			}
		},
		 messages : {
			"firstName" : {
				required : "FirstName is required ."
			},
			"lastName" : {
				required : "LastName is required ."
			},
			"email" : {
				required : "Email is required ."
			},
			"encrytedPassword" : {
				required : "Password is required .",
				minlength : "Please enter at least 8 characters .",
				maxlength : "Please enter up to 32 characters ."
			},
			"confirmPassword" : {
				equalTo: "The two passwords must be the same .",
				minlength : "Please enter at least 8 characters .",
			}
		}
	});
});