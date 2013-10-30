<%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Página de Registro de Beeter Server</title>
</head>
<body>
<p>
	<center>
		<form name="form" action="/beeter-auth/RegisterServlet" method="post">

			<fieldset>
				<legend>
					<h1>Registre:</h1>
				</legend>

				<label>Nick: </label> <input type="text" name="nick" /><br> <label>Pass:
				</label> <input type="password" name="pass" /><br> <label>Name:
				</label> <input type="text" name="nombre" /><br> <label>Email:
				</label> <input type="text" name="correo" /><br> 
				<input type="hidden" name="action" value="REGISTER">
				<input type="submit"
					value="Sign up" name="registrar" />
			</fieldset>
		</form>
	</center>
	<p>
</body>
</html>