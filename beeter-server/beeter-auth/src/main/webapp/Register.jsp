<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link rel="stylesheet" type="estilos" href="beeter-auth/estilos.css">
<!-- Bootstrap -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.1/css/bootstrap.min.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Página de Registro de Beeter Server</title>
</head>
<body>
	<p>div class="container">
	<div class="row">
		<div class="col-sm-6 col-md-4 col-md-offset-4">
			<h1 class="text-center login-title">Registre</h1>
			<div class="account-wall">
				<img class="profile-img"
					src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
					alt="">
				<form name="form" class="form-signin"
					action="/beeter-auth/RegisterServlet" method="post">
					<label>Nick: </label> <input type="text" class="form-control"
						name="nick" /><br> <label>Pass: </label> <input
						type="password" class="form-control" name="pass" /><br> <label>Name:
					</label> <input type="text" class="form-control" name="nombre" /><br>
					<label>Email: </label> <input type="text" class="form-control"
						name="correo" /><br> <input type="hidden" name="action"
						value="REGISTER"> <input type="submit" value="Sign up"
						name="registrar" />
				</form>
			</div>

		</div>
	</div>
	</div>


</body>
</html>