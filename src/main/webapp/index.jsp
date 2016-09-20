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
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Tunnit</title>
</head>
<body>
<header>

</header>
	<h3 class="hykkonen" align=center>Tuntien kirjaus</h3>
	
	<table class="tunnit" align=center>
			
	<form action="TuntiServlet" method="POST">
		<table class="tunnit" align=center>
			
			<tr>
				<td>Henkilö:</td>
				
			<td>
			<select>
  					<option value="1">Aaro</option>
				  <option value="4">Joonas</option>
				  <option value="3">Sami</option>
				  <option value="5">Jukka</option>
				  <option value="2">Heikki</option>
			</select>
			</td>
			</tr>
			<tr>
			<td><p></p> </td>
			</tr>
			<tr>
				<td>Tunnit:</td>
				<td><input type="number" step=0.5 name="tunnit" size="2" min="0.5" max="24" required />
			</td>
			</tr>
			<tr>
			<td><p></p> </td>
			</tr>
			<tr>
				<td>Kuvaus:</td>
				<td><input type="text" name="kuvaus" max="50" required></input></td>
			</tr>
			<tr>
				<td><br> <input type="submit" class="btn btn-success btn-md" value="Tallenna" /></td>
			</tr>
		</table>
	</form>

<div class="container">
  <h4>Tuntilista</h4>
                              
  <table class="table table-hover">
    <thead>
      <tr>
        <th>Etunimi</th>
        <th>Sukunimi</th>
        <th>Tunnit</th>
        <th>Kuvaus</th>
        <th>Päivämäärä</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>1</td>
        <td>Anna</td>
      </tr>
      <tr>
        <td>2</td>
        <td>Debbie</td>
      </tr>
      <tr>
        <td>3</td>
        <td>John</td>
      </tr>
    </tbody>
  </table>
</div>

</body>
</html>