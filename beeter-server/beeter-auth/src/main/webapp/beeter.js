
//definimos variables a utilizar
var API_BASE_URL = "http://localhost:8000/beeter-api";
var user = 'alicia';
var pass  ='alicia';
var auten = 'alicia:aliacia';
 
 
//evento de accion sobre el boton
$("#button_get_sting").click(function(e){
	e.preventDefault();
	getSting(46);
});
 
//evento de accion sobre el boton
$("#button_delete_sting").click(function(e){
	e.preventDefault();
	deleteSting(45);
});

//evento de accion sobre el boton
$("#button_post_sting").click(function(e){
	e.preventDefault();
	var sting ='{"content": "Muchos años después, frente al pelotón de fusilamiento...", "subject": "Cien Años de Soledad", "username": "alicia"}';
	createSting(sting);
});
 
 //funcion obtener un sting realiza la peticion json a beeter-api
function getSting(stingid) {
	var url = "http://127.0.0.1:8000/beeter-api/stings/41";
 
	//creamos el json Accept que interoga a beterapi y envia la apeticion
	$.ajax({
		url : url,
		type : 'GET',
		username : 'alicia',
		password : 'alicia',
		crossDomain : true,		
	})
	//?si la respuesta es buena
	.done(function (data, status, jqxhr) {
		//lee la respuesta  la introduce a uan variable y la pega en consola del navegador
		var sting = $.parseJSON(jqxhr.responseText);
		console.log(sting);
	})
	//si la respuesta es negativa pega el error en la consola del navegador
    .fail(function (jqXHR, textStatus) {
		console.log(textStatus);
	});
 
}
 
function deleteSting(stingid) {
	var url = API_BASE_URL + '/stings/'+stingid;
 
	$.ajax({
		url : url,
		type : 'DELETE',
		crossDomain : true,
		username : user,
		password : pass,
	})
    .done(function (data, status, jqxhr) {
		console.log(status);
	})
    .fail(function (jqXHR, textStatus) {
		console.log(textStatus);
	});
		
}
 
 
function createSting(sting) {
	var url = API_BASE_URL + '/stings';
 
	$.ajax({
        dataType: "jsonp",
        url: url,
		// url : url,
		type : 'POST',
		crossDomain : true,
		data : sting,
		//dataType : 'json',
		username : user,
		password : pass,
	})
	.done(function (data, status, jqxhr) {
		console.log(status);
	})
    .fail(function (jqXHR, textStatus) {
		console.log(textStatus);
	});
}