<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">	
<link rel="stylesheet" type="text/css" href="resources/styles/common.css">

<script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/locales/bootstrap-datepicker.fi.min.js" charset="UTF-8"></script>
<link rel="stylesheet" type="text/css" href="src/main/resources/styles/form.css"/>


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
		});
	</script>
	<header>
		<div class="container" id="sisalto_lohko">
		<h2>Tuntikirjausjärjestelmä</h2>
		</div>
	</header>
	
	<div class="container">
	  <h4>Henkilön Tunnit yhteensä</h4>
	                              
	  <table class="table table-hover">
	    <thead>
	    	<tr>
	        	<th>Etunimi</th>
	        	<th>Sukunimi</th>
	        	<th>Tunnit yhteensä</th>
	    	</tr>
	    </thead>
	    <tbody>
	    <form action="henkilo" method="POST">
	    	<c:forEach items="${henkiloidenTunnit}" var="ht">
	      		<tr>
	     			<td><button type=submit name="tunti_id" value="${ht.id}" class="btn-link">${ht.etunimi}</button></td>
	        		<td>${ht.sukunimi}</td>
	        		<td>${ht.tunnitYhteensa}</td>
	     	 	</tr>
	    	 </c:forEach> 
	    </form>
	    </tbody>
	    </table>
	    </div>
	
	
	
	<div class="col-lg-6 col-sm-12" id="sisalto_lohko">
		
		<h4 class="hykkonen">Tuntien kirjaus</h4>
		<form:form modelAttribute="henkilo" method="POST">
	
				<div class="input-group input-group-lg" id="syotto_kentat">
				<span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
				<form:select path="id" class="form-control">
					  <form:option value="0" label="Valitse käyttäjä..." />
					  <c:forEach items="${henkiloidenTunnit}" var="ht">
					  <form:option value="${ht.id}">${ht.etunimi} ${ht.sukunimi}</form:option>
					  </c:forEach>
				</form:select>
				</div>
				
				<div class="input-group input-group-lg" id="syotto_kentat">
				<span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-time" aria-hidden="true"></span></span>
				<form:input path="tunnit[0].tunnit" class="form-control"/><form:errors path="tunnit[0].tunnit" cssClass="Virheteksti"/> 
				</div>
				
				<div class="input-group input-group-lg" id="syotto_kentat">
				<span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span></span>
				<form:input path="tunnit[0].kuvaus" rows="5" cols="30" class="form-control" placeholder="Kuvaus..." /><form:errors path="tunnit[0].kuvaus" cssClass="Virheteksti"/>
				</div>
				
				<div class="input-group input-group-lg" id="syotto_kentat">
				<span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
				<form:input path="tunnit[0].stringdate" class="datepicker form-control"/><form:errors path="tunnit[0].stringdate" cssClass="Virheteksti"/>
				</div>
				
					
				<input type="submit" class="btn btn-success btn-md" value="lisää" />
				
		</form:form>
	</div>
	
	
	<div class="container table-responsive visible-md visible-lg visible-xl" id="sisalto_lohko">
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
	        <td><fmt:formatDate pattern="dd.MM.yyyy" 
	            value="${h.tunnit[0].pvm}"/></td>
	        <td><button type="submit" name="tunti_id" class="btn btn-danger btn-sm" value="${h.tunnit[0].id }" >Poista</button></td>
	      </tr>
	     </c:forEach> 
	    </tbody>
	  </table>
	  </form>
	</div>
	
		
	<div class="container table-sm table-responsive visible-sm visible-xs" id="sisalto_lohko">
	  <h4>Tuntilista</h4>
	  <form action="/tuntikirjanpito/poista" method="POST">                           
	  <table class="table">
	    <thead>
	      <tr>
	        <th>Nimi</th>
	        <th>Tunnit</th>
	        <th>Kuvaus</th>
	        <th>Päivämäärä</th>
	        <th></th>
	       
	      </tr>
	    </thead>
	    <tbody>
	      <c:forEach items="${henkilot}" var="h">
	      <tr>
	        <td rowspan="2">${h.etunimi} ${h.sukunimi}	</td>
	        <td rowspan="2">${h.tunnit[0].tunnit}</td>
	<%--         <c:set var="kuvaus" value="${h.tunnit[0].kuvaus}"/> --%>
	<%--         <c:set var="kuvauksen_substring" value="${fn:substring(kuvaus, 0, 10)}"/> --%>
	<%--         <td rowspan="2">${kuvauksen_substring}...</td> --%>
				<td rowspan="2">${h.tunnit[0].kuvaus}</td>
	        <td><fmt:formatDate pattern="dd.MM.yyyy" 
	            value="${h.tunnit[0].pvm}"/></td>
	      </tr>
	      <tr>
	      <td><button type="submit" name="tunti_id" class="btn btn-danger btn-sm" value="${h.tunnit[0].id }" >Poista</button></td>
	      </tr>
	      
	     </c:forEach> 
	    </tbody>
	  </table>
	  </form>
	</div>
</body>
</html>