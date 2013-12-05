var API_BASE_URL = "http://localhost:8000/beeter-api";

// Don't execute any code until the DOM is ready!
$(document).ready(function() {

	$('#get').validate({
		rules : {
			id : {
				minlength : 1,
				required : true
			},
			autor : {
				minlength : 2,
				required : true
			},
			subjet : {
				minlength : 2,
				required : true
			},
			contenido : {
				minlength : 2,
				required : true
			// email: true
			},
			num : {
				minlength : 2,
				required : true
			},
			offset : {
				minlength : 2,
				required : true
			},
			
		},
		messages : {
			id : "Please enter a number",
			autor : "Please enter a name",
			subjet : "Please enter a subjet",
			contenido : "por favo rintroduce un texto",
			num  : "por favo rintroduce un texto",
			offset : "por favo rintroduce un texto",
		},
		submitHandler : function(form) {
			form.submit();
		}
	});

}); // end document.ready

$("#button_get_sting").click(function(e) {
	e.preventDefault();
	var id = $('#id').val();
	getSting(id);
});

$("#button_list_sting").click(function(e) {
	e.preventDefault();
	var num = $('#num').val();
	var offset = $('#offset').val();
	var name = $('#autor').val();
	getListSting(num, offset, name);
});

$("#button_update_sting").click(
		function(e) {
			e.preventDefault();
			var id = $('#id').val();
			var subjet = $('#subjet').val();
			var autor = $('#autor').val();
			var contenido = $('#contenido').val();

			var sting = '{"content": ' + '"' + contenido + '"';
			sting += ', "subject": ' + '"' + subjet + '"' + ',' + '"username":'
					+ '"' + autor + '"' + "}";
			updateSting(id, sting);
		});

$("#button_delete_sting").click(function(e) {
	e.preventDefault();
	var id = $('#id').val();
	deleteSting(id);
});

$("#button_post_sting").click(
		function(e) {
			e.preventDefault();
			var subjet = $('#subjet').val();
			var author = $('#autor').val();
			var content = $('#contenido').val();

			var sting = '{"content": ' + '"' + content + '"';
			sting += ', "subject": ' + '"' + subjet + '"' + ',' + '"username":'
					+ '"' + author + '"' + "}";
			$("#jsontext").html(sting);
			createSting(sting);
		});

function getSting(stingid) {
	var url = API_BASE_URL + "/stings/" + stingid;
	// var url = "http://localhost:8000/better-api/stings/85";

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
	}).done(
			function(data, status, jqxhr) {
				$("#resultado").html(
						"Sting transmitido: OK <p>Autor: " + data.author
								+ "<p> Subjet:" + data.subject
								+ "<p> Contenido:" + data.content);
				var sting = $.parseJSON(jqxhr.responseText);
				console.log(sting);
			}).fail(function(jqXHR, textStatus) {
		console.log(textStatus);
		$("#resultado").html("Data: FAIL <p>" + textStatus);
	});

}

function updateSting(stingid, sting) {
	var url = API_BASE_URL + "/stings/" + stingid;
	// var url = "http://localhost:8000/better-api/stings/85";

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
		data : sting,
	}).done(
			function(data, status, jqxhr) {
				$("#resultado").html(
						"Data: OK <p>Autor: " + data.author + "<p> Contenido:"
								+ data.content);
				var sting = $.parseJSON(jqxhr.responseText);
				console.log(sting);
			}).fail(function(jqXHR, textStatus) {
		console.log(textStatus);
		$("#resultado").html("Data: FAIL <p>" + textStatus);
	});

}

function deleteSting(stingid) {
	var url = API_BASE_URL + '/stings/' + stingid;

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

	}).done(function(data, status, jqxhr) {
		console.log(status);
		$("#resultado").text("Delete: OK ");
	}).fail(function(jqXHR, textStatus) {
		console.log(textStatus);
		$("#resultado").text("Delete: FAIL <p>" + textStatus);
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
	}).done(
			function(data, status, jqxhr) {
				console.log(status);
				$("#resultado").html(
						"Sting transmitido: OK <p>Autor: " + data.author
								+ "<p> Subjet:" + data.subjet
								+ "<p> Contenido:" + data.content
								+ "<p> IDsting:" + data.stingid);
				var sting = $.parseJSON(jqxhr.responseText);
				console.log(sting);
			}).fail(function(jqXHR, textStatus) {
		console.log(textStatus);
		$("#resultado").html("Data: FAIL <p>" + textStatus);
	});
}

function getListSting(num, offset, name) {
	var url = API_BASE_URL + "/stings?offset=" + offset + "&length=" + num
			+ "&username=" + name;

	$
			.ajax(
					{
						url : url,
						type : 'GET',
						crossDomain : true,
						username : name,
						password : name,
						dataType : 'json',
						headers : {
							"Accept" : "application/vnd.beeter.api.sting.collection+json",
						},
					})
			.done(
					function(data, status, jqxhr) {
						// var sting= $.parseJSON(jqxhr.responseText);
						var htmlString = "<table class='table'>";
						htmlString += "<tr><th>ID</th><th>Autor</th><th>Subject</th><th>Contenido</th><th>Timestamp</th></tr>";
						// Start putting together the HTML string

						// Now start cycling through our array of Flickr photo
						// details
						$.each(data.stings, function(i, s) {
							// Here's where we piece together the HTML
							htmlString += "<tr>";
							htmlString += '<td>' + s.stingId + '</td>';
							htmlString += '<td>' + s.author + '</td>';
							htmlString += '<td>' + s.subject + '</td>';
							htmlString += '<td>' + s.content + '</td>';
							htmlString += '<td>' + s.lastModified + '</td>';
							htmlString += "</tr>";

						});
						htmlString += "</table>";
						$("#resultado").html(htmlString);
						var sting = $.parseJSON(jqxhr.responseText);
						console.log(sting);
					}).fail(function(jqXHR, textStatus) {
				console.log(textStatus);
				$("#resultado").html("Data: FAIL <p>" + jqxhr.responseText);
			});

}