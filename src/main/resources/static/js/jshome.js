	/**
	 * Process Show more
	 * @param e
	 * @returns
	 */
	function showlist(e) {
	
		var name = e.getAttribute("attr-name");
		$("#" + name + "showmoretd").remove();
		
				$.ajax({
					url : "/home-weather/show-more",
					type : "GET",
					data : {
						name : name
					},
					success : function(value) {
						var trHTML = '';
						var showLessHTML = '<tr id="'
								+ name
								+ 'showless"><td colspan="4"><p onclick="hidelist(this)" attr-name="'
								+ name
								+ '" class="showmorestyle">Show Less</p></td></tr>';
						
								$.each(
										value,
										function(i, item) {
											trHTML += '<tr class="hideshowless'
													+ item.nameCity
													+ '"><td><img src="http://openweathermap.org/img/wn/'
													+ item.icon
													+ '.png"  width="50" height="50"></td><td><b><a href="#" >'
													+ item.nameCity
													+ '</a></b><p><span >'
													+ convertDay(item.date)
													+ '</span></p></td><td><span class="badge badge-info" >'
													+ item.temp
													+ '°С</span><b><i>'
													+ ' '
													+ item.description
													+ '</i></b><p >'
													+ item.wind
													+ ' m/s. '
													+ item.humidity
													+ ' %, '
													+ item.pressure
													+ ' hpa</p></td><td><a class="btn btn-danger" href="/home-weather/deleteWeather?id='
													+ item.weatherId
													+ '" >Delete</a></td></tr>';
										});
	
						$('.' + name).append(trHTML).append(showLessHTML);
			}
		})
	}
	
	function convertDay(day){
		
		var mydate = new Date(day)
		var year = mydate.getYear()
		if (year < 1000)
			year += 1900
			var day = mydate.getDay()
			var month = mydate.getMonth()
			var daym = mydate.getDate()
			if (daym < 10)
				daym = "0" + daym
				var montharray = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

		var fomatDay = " " + montharray[month] + " " + daym + ", " + year + "";
		
		return fomatDay;
	}
	
	/**
	 * Process Show Less
	 * @param e
	 * @returns
	 */
	function hidelist(e) {
		var name = e.getAttribute("attr-name");
		$(".hideshowless" + name).remove();
		$("#" + name + "showless").remove();
		var tdHTML = '<td colspan="4" id="' + name
				+ 'showmoretd"><p onclick="showlist(this)" attr-name="' + name
				+ '" class="showmorestyle">Show More</p></td>';
		$("#" + name + "showmore").append(tdHTML);
	
	}
