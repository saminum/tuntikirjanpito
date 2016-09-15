<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		
	<form action="" method="post">
		<table class="tunnit" align=center>

			<tr>
				<td>Lisää tunnit:</td>
				<td><input type="number" step=0.01 value="" name="tunnit" size="2" min="1" max="24" required />
			</td>
			</tr>
			<tr>
			<td><p></p> </td>
			</tr>
			<tr>
				<td>Henkilö:</td>
				<td><input type="text" value="" name="nimi" size="30" required title="" /></td>
	
			</tr>
			<tr>
			<td><p></p> </td>
			</tr>
			<tr>
				<td>Kuvaus:</td>
				<td><input type="text" name="kuvaus" required>kuvaus</input></td>
			</tr>
			
				<td><br>
				<a href="<%=response.encodeURL("lisaaTunnit") %>" class="btn btn-info btn-sm" role="button">Takaisin</a>&nbsp;&nbsp;
  				
			</td>
				<td><br> <input type="submit" class="btn btn-success btn-md" value="Tallenna" /></td>
			</tr>
		</table>
	</form>


</body>
</html>
