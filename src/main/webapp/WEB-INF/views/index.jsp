<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
			
	<form:form modelAttribute="henkilo" method="POST">
		<table class="tunnit" align=center>
			
			<tr>
				<td>Henkilö:</td>
				
			<td>
			<form:select path="id">
  				  <form:option value="1">Aaro</form:option>
				  <form:option value="4">Joonas</form:option>
				  <form:option value="3">Sami</form:option>
				  <form:option value="5">Jukka</form:option>
				  <form:option value="2">Heikki</form:option>
			</form:select>
			</td>
			</tr>
			<tr>
			<td><p></p> </td>
			</tr>
			<tr>
				<td>Tunnit:</td>
				<td><form:label path="tunnit[0].tunnit"/>
					<form:input path="tunnit[0].tunnit"/>
			</td>
			</tr>
			<tr>
			<td><p></p> </td>
			</tr>
			<tr>
				<td>Kuvaus:</td>
				<td><form:label path="tunnit[0].kuvaus"/>
					<form:input path="tunnit[0].kuvaus" />	
			</td>		
			</tr>
			<tr>
				<td><br> <input type="submit" class="btn btn-success btn-md" value="lisää" /></td>
			</tr>
		</table>
	</form:form>

<div class="container">
  <h4>Henkilön Tunnit yhteensä</h4>
                              
  <table class="table table-hover">
    <thead>
    <tr>
        <th>Etunimi</th>
        <th>Sukunimi</th>
        <th>Tunnit yhteensä</th>
      </tr>
    	<c:forEach items="${henkiloidenTunnit}" var="ht">
      <tr>
        <td>${ht.etunimi}</td>
        <td>${ht.sukunimi}</td>
        <td>${ht.tunnitYhteensa}</td>
      </tr>
     </c:forEach> 
    </thead>
    </table>
    </div>

<div class="container">
  <h4>Tuntilista</h4>
  <form action="/tuntikirjanpito/poista" method="POST">                           
  <table class="table table-hover">
    <thead>
      <tr>
        <th>Etunimi</th>
        <th>Sukunimi</th>
        <th>Tunnit</th>
        <th>Kuvaus</th>
        <th>Päivämäärä</th>
        <th></th>
       
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${henkilot}" var="h">
      <tr>
        <td>${h.etunimi}</td>
        <td>${h.sukunimi}</td>
        <td>${h.tunnit[0].tunnit}</td>
        <td>${h.tunnit[0].kuvaus}</td>
        <td>${h.tunnit[0].pvm}</td>
        <td><button type="submit" name="tunti_id" class="btn btn-danger btn-sm" value="${h.tunnit[0].id }" >Poista</button></td>
      </tr>
     </c:forEach> 
    </tbody>
  </table>
  </form>
</div>

</body>
</html>