<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Tunnit</title>
</head>
<body>
<header>

</header>
	<h1>Tuntien kirjaus</h1>
	
	<table class="tunnit" align=center>
			<tr><td>Samin id: 3 </td></tr>
			<tr><td>Joonaksen id: 4</td></tr>
			<tr><td>Aaron id: 1</td></tr>
			<tr><td>Jukan id: 5</td></tr>
			<tr><td>Heikin id: 2</td></tr>
	<form action="TuntiServlet" method="POST">
		<table class="tunnit" align=center>
			
			
			<tr>
				<td>Henkilö id:</td>
				<td><input type="number" step=1 value="" name="henkilo_id" size="2" min="1" max="5" required />
			</td>
			</tr>
			
			<tr>
				<td>Lisää tunnit:</td>
				<td><input type="number" step=0.01 value="" name="tunnit" size="2" min="1" max="24" required />
			</td>
			</tr>
			<tr>
			<td><p></p> </td>
			</tr>

			<tr>
			<td><p></p> </td>
			</tr>
			<tr>
				<td>Kuvaus:</td>
				<td><input type="text" name="kuvaus" required>kuvaus</input></td>
			</tr>
			
				<td><br> <input type="submit" class="btn btn-success btn-md" value="Tallenna" /></td>
			</tr>
		</table>
	</form>


</body>
</html>