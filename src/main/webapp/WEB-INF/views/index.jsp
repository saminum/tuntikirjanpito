<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/locales/bootstrap-datepicker.fi.min.js" charset="UTF-8"></script>
<title>Tunnit</title>
</head>
<body>
<script>
$(function(){
	
	$('.datepicker').datepicker({
		format: "dd.mm.yyyy",
		
		weekStart: 1,
		todayBtn: "linked",
		autoclose: true,
		language: "fi"
	});
	
	$(".datepicker").datepicker("setDate", new Date());
});
</script>

<header>

</header>

	<h3 class="hykkonen" align=center>Tuntien kirjaus</h3>
	
	<table class="tunnit" align=center>
			
	<form:form modelAttribute="henkilo" method="POST">
		<table class="tunnit" align=center>
			
			<tr>
				<td>Henkil�:</td>
				
			<td>
			<form:select path="id">
				  <form:option value="0" label="Valitse k�ytt�j�..." />
				  <c:forEach items="${henkiloidenTunnit}" var="ht">
				  <form:option value="${ht.id}">${ht.etunimi} ${ht.sukunimi}</form:option>
				  </c:forEach>
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
					<form:input path="tunnit[0].kuvaus" rows="5" cols="30" />	
			</td>		
			</tr>

						<tr>
				<td>P�iv�:</td>
				<td><form:label path="tunnit[0].stringdate"/>
					<form:input path="tunnit[0].stringdate" cssClass="datepicker" />
			</td>		
			</tr>

			<tr>
				<td><br> <input type="submit" class="btn btn-success btn-md" value="lis��" /></td>
			</tr>
		</table>
	</form:form>

<div class="container">
  <h4>Henkil�n Tunnit yhteens�</h4>
                              
  <table class="table table-hover">
    <thead>
    <tr>
        <th>Etunimi</th>
        <th>Sukunimi</th>
        <th>Tunnit yhteens�</th>
      </tr>
            <form action="henkilo" method="POST">
    	<c:forEach items="${henkiloidenTunnit}" var="ht">
      <tr>
     	<td><button type=submit name="tunti_id" value="${ht.id}" class="btn-link">${ht.etunimi}</button></td>
        <td>${ht.sukunimi}</td>
        <td>${ht.tunnitYhteensa}</td>
      </tr>
     </c:forEach> 
         </form>
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
        <th>P�iv�m��r�</th>
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
        <td><fmt:formatDate pattern="dd.MM.yyyy" 
            value="${h.tunnit[0].pvm}"/></td>
        <td><button type="submit" name="tunti_id" class="btn btn-danger btn-sm" value="${h.tunnit[0].id }" >Poista</button></td>
      </tr>
     </c:forEach> 
    </tbody>
  </table>
  </form>
</div>

</body>
</html>