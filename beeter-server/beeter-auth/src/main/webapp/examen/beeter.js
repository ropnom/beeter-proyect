var API_BASE_URL = "https://api.github.com/repos/ropnom/";
var user;
var pass;


$("#button_login").click(function(e) {
	e.preventDefault();
	user = $('#username').val();
	pass = $('#pass').val();
	$("#login").html("Logueado: "+user );
});
$("#button_get_sting").click(function(e) {
	e.preventDefault();
	var repo = $('#repo').val();
	getgit(repo);
});

$("#button_list_sting").click(function(e) {
	e.preventDefault();
	
	getlist();
});

$("#button_update_sting").click(
		function(e) {
			e.preventDefault();
			var name = $('#repo').val();
			var descripcion = $('#descripcion').val();
						
			var string = '{"name": "'+name+'", "description": "'+descripcion;
			string += '"}';			
			updategit(name, string);
		});

$("#button_delete_sting").click(function(e) {
	e.preventDefault();
	var repo = $('#repo').val();
	deletegit(repo);
});

$("#button_post_sting").click(
		function(e) {
			e.preventDefault();
			var name = $('#repo').val();
			var descripcion = $('#descripcion').val();
			//var content = $('#contenido').val();

			/*var sting = '{"content": ' + '"' + content + '"';
			sting += ', "subject": ' + '"' + subjet + '"' + ',' + '"username":'
					+ '"' + author + '"' + "}";*/
			
			var string = '{"name": "'+name+'", "description": "'+descripcion;
			string += '", "homepage": "https://github.com", "private": false, "has_issues": true,';
			string += '"has_wiki": true, "has_downloads": true}';
			//$("#jsontext").html(string);
			creategit(string);
		});

function getgit(git) {
	var url = API_BASE_URL + git;
	
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,		
		dataType : 'json',		
	}).done(
			function(data, status, jqxhr) {
				var stringhtml = "<h1>ID:</h1> "+ data.id;
				stringhtml +="<p> Nombre:"+data.name;
				stringhtml +="<p> Descripcion:"+data.description;
				
				$("#resultado").html(stringhtml);
				var sting = $.parseJSON(jqxhr.responseText);
				console.log(sting);
			}).fail(function(jqXHR, textStatus) {
		console.log(textStatus);
		$("#resultado").html("Data: FAIL <p>" + textStatus);
	});

}

function updategit(repo, git) {
	var url = API_BASE_URL + repo;
	var logeo = user+':'+pass;

	$.ajax({
		url : url,
		type : 'PATCH',
		crossDomain : true,
		beforeSend: function (request)
		{
		     request.withCredentials = true;
		     request.setRequestHeader("Authorization", "Basic "+ btoa(logeo));
		},
		crossDomain : true,		
		dataType : 'json',
		data : git,
	}).done(
			function(data, status, jqxhr) {
				var stringhtml = "<h1>ID:</h1> "+ data.id;
				stringhtml +="<p> Nombre:"+data.name;
				stringhtml +="<p> Descripcion:"+data.description;
				
				$("#resultado").html(stringhtml);
				var sting = $.parseJSON(jqxhr.responseText);
				console.log(sting);
			}).fail(function(jqXHR, textStatus) {
		console.log(textStatus);
		$("#resultado").html("Data: FAIL <p>" + textStatus);
	});

}
function deletegit(git) {
	var url = API_BASE_URL + git;
	var logeo = user+':'+pass;
	
	$.ajax({
		url : url,
		type : 'DELETE',
		beforeSend: function (request)
		{
		     request.withCredentials = true;
		     request.setRequestHeader("Authorization", "Basic "+ btoa(logeo));
		},
		crossDomain : true,		
		dataType : 'json',		
	}).done(
			function(data, status, jqxhr) {
				
				$("#resultado").html("<h1>Eliminado Correctamente</h1>");				
				console.log(status);
			}).fail(function(jqXHR, textStatus) {
		console.log(textStatus);
		$("#resultado").html("<h1>Delete: FAIL</h1> <p>" + textStatus);
	});

}


function creategit(sting) {
	var url = "https://api.github.com/user/repos";
	var logeo = user+':'+pass;

	$.ajax({

		url : url,
		type : 'POST',
		crossDomain : true,
		data : sting,
		dataType : 'json',
		beforeSend: function (request)
		{
		     request.withCredentials = true;
		     request.setRequestHeader("Authorization", "Basic "+ btoa(logeo));
		},		
	}).done(
			function(data, status, jqxhr) {
				var stringhtml = "<h1>ID:</h1> "+ data.id;
				stringhtml +="<p> Nombre:"+data.name;
				stringhtml +="<p> Descripcion:"+data.description;
				
				$("#resultado").html(stringhtml);
				var sting = $.parseJSON(jqxhr.responseText);
				console.log(sting);
			}).fail(function(jqXHR, textStatus) {
		console.log(textStatus);
		$("#resultado").html("Data: FAIL <p>" + textStatus);
	});
}
function getlist() {
	var url = "https://api.github.com/user/repos";
	var logeo = user+':'+pass;
	
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,	
		beforeSend: function (request)
		{
		     request.withCredentials = true;
		     request.setRequestHeader("Authorization", "Basic "+ btoa(logeo));
		},	
		dataType : 'json',		
	}).done(
			function(data, status, jqxhr) {
				
				var htmlString = "<table class='table'>";
				htmlString += "<tr><th>ID</th><th>NAME</th><th>Descripcion</th><th>SIZE</th><th>UPDATE</th></tr>";
				// Start putting together the HTML string

				// Now start cycling through our array of Flickr photo
				// details
				var sting = $.parseJSON(jqxhr.responseText);
				console.log(sting);
				$.each(data, function(i, s) {
					// Here's where we piece together the HTML
					htmlString += "<tr>";
					htmlString += '<td>' + s.id + '</td>';
					htmlString += '<td>' + s.name + '</td>';
					htmlString += '<td>' + s.description + '</td>';
					htmlString += '<td>' + s.size + '</td>';
					htmlString += '<td>' + s.updated_at + '</td>';
					htmlString += "</tr>";

				});
				htmlString += "</table>";
				$("#resultado").html(htmlString);
				
				
			}).fail(function(jqXHR, textStatus) {
		console.log(textStatus);
		$("#resultado").html("Data: FAIL <p>" + textStatus);
	});

}




