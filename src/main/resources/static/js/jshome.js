


//search weather
$(".search-weather").click(function() {
	var nameSearch = $document.getElementById("nameSearch").value;
	$.ajax({
		url : "/home-weather/search-citya",
		type : "GET",
		data : {
			name : nameSearch
		},
		success : function(value) {
			return value;
		}
	})
})

//xoa weather
$(".xoa-weather").click(function() {
	var self = $(this);
	var id = $(this).closest("tr").attr("data-id");
	$.ajax({
		url : "/home-weather/deleteWeather",
		type : "GET",
		data : {
			id : id
		},
		success : function(value) {
			self.closest("tr").remove();
			Swal.fire('Done !', 'You click OK to continue !', 'success')
		}
	})
})

//xu ly show

function showlist(e) {

	var name = e.getAttribute("attr-name");
	$("#"+name+"showmoretd").remove();

	$
			.ajax({
				url : "/home-weather/show-more",
				type : "GET",
				data : {
					name : name
				},
				success : function(value) {
					var trHTML = '';
					var showLessHTML = '<tr id="'+name+'showless"><td colspan="4"><p onclick="hidelist(this)" attr-name="'+name+'" class="showmorestyle">Show Less</p></td></tr>';
					$
							.each(
									value,
									function(i, item) {
										trHTML += '<tr class="hideshowless'+item.nameCity+'"><td><img src="http://openweathermap.org/img/wn/'
												+ item.icon
												+ '.png"  width="50" height="50"></td><td><b><a href="#" >'
												+ item.nameCity
												+ '</a></b><p><span >'
												+ item.date
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

		function hidelist(e) {
			var name = e.getAttribute("attr-name");
			$(".hideshowless"+name).remove();
			$("#"+name+"showless").remove();
			var tdHTML = '<td colspan="4" id="'+name+'showmoretd"><p onclick="showlist(this)" attr-name="'+name+'" class="showmorestyle">Show More</p></td>';
			$("#"+name+"showmore").append(tdHTML);
			
		}

/* function showlist() {
	var showLessArray = Array.prototype.slice.call(document
			.getElementsByClassName("hideshowless"));
	showLessArray.forEach(itemShowLess);
	function itemShowLess(item) {
		item.style.display = "table-row";
	}
	document.getElementById("showless").style.display = "table-row";
}

function hidelist() {
	var showLessArray = Array.prototype.slice.call(document
			.getElementsByClassName("hideshowless"));
	showLessArray.forEach(itemShowLess);
	function itemShowLess(item) {
		item.style.display = "none";
	}
	document.getElementById("showless").style.display = "none";
} */