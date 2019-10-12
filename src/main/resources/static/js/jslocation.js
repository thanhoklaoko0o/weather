
$( document ).ready(function() {
		
		  var x = document.getElementById("demo");
		  if (navigator.geolocation) {
			  
		     navigator.geolocation.getCurrentPosition(showPosition);
		     } else { 
		     x.innerHTML = "Geolocation is not supported by this browser.";
		     }
		    
		  function showPosition(position) {
			  
			  var lat =  position.coords.latitude;
			  var lon =  position.coords.longitude;
			
			  $.ajax({
      			
      			url		:"/home-weather/forecast-current",
      			type	:"GET",
      			data	:{
      					lat : lat,
      					lon : lon
      			},
      			    
      			    success: function(value){
      			    	var weHTML = '<div class="widget__temperature"><div  class="weather-widget"> <h2 class="weather-widget__city-name">Your Current Location </h2>'
      				      			+' <h3 class="weather-widget__temperature"><img class="weather-widget__img" src="https://openweathermap.org/img/wn/'
      				      			+value.image+'.png"  width="50" height="50">'+value.temp+' °C </h3><b><p class="weather-widget__main">'
      				      			+value.cloudiness+'</p></b>'
      			          			+'<table class="weather-widget__items">'
      			            		+'<tbody>'
      			               		+'<tr class="weather-widget__item">'
      			               		+'<td>Wind</td>'
      			               		+'<td id="weather-widget-wind">'+value.wind+'  m/s</td>'
      			               		+'</tr>'
      			              		+'<tr class="weather-widget__item">'
      			              		+'<td>Cloudiness</td>'
      			                  	+'<td id="weather-widget-cloudiness">'+value.cloudiness+'</td>'
      			                  	+'</tr>'
      			                  	+'<tr class="weather-widget__item">'
      			                  	+'<td>Pressure</td>'
      			                  	+'<td>'+value.pressure+' hpa</td>'
      			                  	+'</tr>'
      			                  	+'<tr class="weather-widget__item">'
      			                  	+'<td>Humidity</td><td>'+value.humidity+' %</td>'
      			                  	+'</tr>'
      			                  	+'<tr class="weather-widget__item">'
      			                	+'<td>Sunrise</td>'
      			                	+'<td>'+value.sunrise+'</td>'
      			                	+'</tr>'
      								+'<tr class="weather-widget__item">'
      			                	+'<td>Sunset</td>'
      			                  	+'<td>'+value.sunset+'</td>'
      			                 	+'</tr>'
      			                	+'<tr class="weather-widget__item">'
      								+'<td>Geo coords</td>'
      			                	+'<td>'+value.geocoords+' </td></tr></tbody></table>'
      			                 	+'</div>'
      			                  	+'<p class="text-muted widget__text-description">The weather forecast is displayed in accordance with your local time. Please pay attention to it when you will watch the weather in another time zone.</p>'
      			     				+'</div>'
      			     
      			     				$('.col-sm-4').append(weHTML);
      				}
      			})   
		}
	});