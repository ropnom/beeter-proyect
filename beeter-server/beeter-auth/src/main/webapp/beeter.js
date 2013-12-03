var API_BASE_URL = "http://localhost:8000/beeter-api";
 
//Don't execute any code until the DOM is ready!
$(document).ready(function(){

	// Validate
	// http://bassistance.de/jquery-plugins/jquery-plugin-validation/
	// http://docs.jquery.com/Plugins/Validation/
	// http://docs.jquery.com/Plugins/Validation/validate#toptions

		$('#datos').validate({
	    rules: {
	      id: {
	        minlength: 1,
	        required: true
	      },
	      autor: {
	    	minlength: 1,
	        required: true,	       
	      },
	      subjet: {
	      	minlength: 2,
	        required: true
	      },
	      contenido: {
	        minlength: 2,
	        required: true
	      },
	      num: {
		        minlength: 1,
		        required: true
		      },
	    },
			highlight: function(element) {
				$(element).closest('.control-group').removeClass('success').addClass('error');
			},
			success: function(element) {
				element
				.text('OK!').addClass('valid')
				.closest('.control-group').removeClass('error').addClass('success');
			}
	  });

}); // end document.ready

$("#button_get_sting").click(function(e){
	e.preventDefault();
	var id = $('#id').val();
	getSting(id);
});
$("#button_list_sting").click(function(e){
	e.preventDefault();	
	var num = $('#num').val();
	var offset = $('#offset').val();
	getListSting(num,offset);
});
$("#button_update_sting").click(function(e){
	e.preventDefault();
	var id = $('#id').val();
	var subjet = $('#get_subjet').val();
	var autor = $('#get_autor').val();
	var contenido = $('#get_contenido').val();
	
	var sting ='{"content": ' + '"'+contenido+'"';
	sting += ', "subject": ' + '"' + subjet + '"' + ',' + '"username":' +'"' +autor+'"' + "}";
	
	updateSting(id, sting);
});
 
$("#button_delete_sting").click(function(e){
	e.preventDefault();
	var id = $('#id').val();
	deleteSting(id);
});
 
$("#button_post_sting").click(function(e){
	e.preventDefault();
	var subjet= $('#subjet').val();
	var author= $('#autor').val();
	var content= $('#contenido').val();
	
	var sting ='{"content": ' + '"'+content+'"';
	sting += ', "subject": ' + '"' + subjet + '"' + ',' + '"username":' +'"' +author+'"' + "}";
	$("#jsontext").html(sting);
	createSting(sting);
});
 
 
function getSting(stingid) {
	var url = API_BASE_URL + "/stings/"+stingid;
	//var url = "http://localhost:8000/better-api/stings/85";
 
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,	
		username : 'alicia',
		password : 'alicia',
		dataType : 'json',
		headers : {
			"Accept" : "application/vnd.beeter.api.sting+json",
		},		
	})
	.done(function (data, status, jqxhr) {
		$("#resultado").html("Sting transmitido: OK <p>Autor: "+ data.author+ "<p> Subjet:" +data.subject +"<p> Contenido:" +data.content );
		var sting = $.parseJSON(jqxhr.responseText);
		console.log(sting);
	})
    .fail(function (jqXHR, textStatus) {
		console.log(textStatus);
		$("#resultado").html("Data: FAIL <p>" +  textStatus );
	});
 
}
 
function updateSting(stingid, sting) {
	var url = API_BASE_URL + "/stings/"+stingid;
	//var url = "http://localhost:8000/better-api/stings/85";
 
	
	$.ajax({
		url : url,
		type : 'PUT',
		crossDomain : true,	
		username : 'alicia',
		password : 'alicia',
		dataType : 'json',
		headers : {
			"Accept" : "application/vnd.beeter.api.sting+json",
			"Content-Type" : "application/vnd.beeter.api.sting+json",
		},	
		data: sting,
	})
	.done(function (data, status, jqxhr) {
		$("#resultado").html("Data: OK <p>Autor: "+ data.author+ "<p> Contenido:" +data.content);
		var sting = $.parseJSON(jqxhr.responseText);
		console.log(sting);
	})
    .fail(function (jqXHR, textStatus) {
		console.log(textStatus);
		$("#resultado").html("Data: FAIL <p>" +  textStatus );
	});
 
}

function deleteSting(stingid) {
	var url = API_BASE_URL + '/stings/'+stingid;
 
	$.ajax({
		url : url,
		type : 'DELETE',
		crossDomain : true,
		username : 'alicia',
		password : 'alicia',
		dataType : 'json',
		headers : {
			"Accept" : "application/vnd.beeter.api.sting+json",
		},
		
	})
    .done(function (data, status, jqxhr) {
		console.log(status);
		$("#resultado").text("Delete: OK ");
	})
    .fail(function (jqXHR, textStatus) {
		console.log(textStatus);
		$("#resultado").text("Delete: FAIL <p>"+textStatus );
	});
		
}
 
function createSting(sting) {
	var url = API_BASE_URL + '/stings';
 
	$.ajax({
       
		url : url,
		type : 'POST',
		crossDomain : true,
		data : sting,
		dataType : 'json',
		username : 'alicia',
		password : 'alicia',
		headers : {
			"Accept" : "application/vnd.beeter.api.sting+json",
			"Content-Type" : "application/vnd.beeter.api.sting+json",
		},
	})
	.done(function (data, status, jqxhr) {
		console.log(status);
		$("#resultado").html("Sting transmitido: OK <p>Autor: "+ data.author+ "<p> Subjet:" +data.subjet +"<p> Contenido:" +data.content +"<p> IDsting:"+ data.stingid);
		var sting = $.parseJSON(jqxhr.responseText);
		console.log(sting);
	})
    .fail(function (jqXHR, textStatus) {
		console.log(textStatus);
		$("#resultado").html("Data: FAIL <p>" +  textStatus );
	});
}

function getListSting(num , offset) {
	var url = API_BASE_URL + "/stings/search?pattern=1&offset="+offset+"&length="+num;

 
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,	
		username : 'alicia',
		password : 'alicia',
		dataType : 'json',
		headers : {
			"Accept" : "application/vnd.beeter.api.sting.collection+json",
		},		
	})
	.done(function (data, status, jqxhr) {
		$("#resultado").html("OK:" + jqxhr.responseText);
		var sting = $.parseJSON(jqxhr.responseText);
		console.log(sting);
	})
    .fail(function (jqXHR, textStatus) {
		console.log(textStatus);
		$("#resultado").html("Data: FAIL <p>" +  jqxhr.responseText);
	});
 
}