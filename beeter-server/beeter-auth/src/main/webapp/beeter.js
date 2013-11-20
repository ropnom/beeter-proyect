var API_BASE_URL = "http://localhost:8000/beeter-api";

 

$("#button_get_sting").click(function(e){
	e.preventDefault();
	var id = $('#id').val();
	getSting(id);
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
		$("#resultado").html("Sting transmitido: OK <p>Autor: "+ data.author+ "<p> Contenido:" +data.content +"<p> IDsting:"+ data.stingid);
		var sting = $.parseJSON(jqxhr.responseText);
		console.log(sting);
	})
    .fail(function (jqXHR, textStatus) {
		console.log(textStatus);
		$("#resultado").html("Data: FAIL <p>" +  textStatus );
	});
}