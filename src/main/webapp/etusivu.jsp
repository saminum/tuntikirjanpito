<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">	
<link rel="stylesheet" type="text/css" href="/tuntikirjanpito/common.css">

<script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/locales/bootstrap-datepicker.fi.min.js" charset="UTF-8"></script>


<title><spring:message code="app.title" /></title>

</head>
<body>
	
	<div class="container">
		
		<div class="col-lg-6 col-md-6 col-sm-6" id="sisalto_lohko">
			<h2><spring:message code="app.name" /></h2>
		</div>
		
		<div class="col-lg-6 col-md-6 col-sm-6 ylakulma">
			<spring:message code="app.language" />: <a href="?language=en"><spring:message code="app.english" /></a> | <a href="?language=fi"><spring:message code="app.finnish" /></a>
			<p><spring:message code="app.loggedin" />: <sec:authentication property="principal.username"/></p>
			<p><a href="/tuntikirjanpito/logout" ><spring:message code="app.logout" /></a></p>
		</div>	
	</div>
	
	
	<sec:authorize access="hasRole('ADMIN')">
	<div class="container">
	<h2><spring:message code="app.admin_controlpanel"/></h2>
	
	<h3><spring:message code="app.add_person_to_project"/></h3>
	<form:form action="/tuntikirjanpito/lisaa_henkilo_projektiin" modelAttribute="henkiloProjekti" method="POST">
	
			<p><spring:message code="app.choose_project" var="cproj"/></p>
			
			<form:select path="projekti_id" class="form-control">
				<form:option value="0" label="${cproj}" />
				<c:forEach items="${henkiloProjekti.projektit}" var="pt">
					<form:option value="${pt.projekti_id}">${pt.nimi}</form:option>
				</c:forEach>
			</form:select>	
			
			<p><spring:message code="app.choose_person" var="cuser"/></p>
			
			<form:select path="henkilo_id" class="form-control">
				<form:option value="0" label="${cuser}" />
				<c:forEach items="${henkiloProjekti.henkilot}" var="ht">
					<form:option value="${ht.id}">${ht.etunimi} ${ht.sukunimi}</form:option>
				</c:forEach>
			</form:select>			
		
		<input type="submit" class="btn btn-success btn-md lisaa_tunteja_button" value="<spring:message code="app.add" />" />
	</form:form>
	
	<h3><spring:message code="app.create_new_project"/></h3>
	<form:form action="/tuntikirjanpito/luo_projekti" modelAttribute="projekti" method="POST">
		
		<form:input path="nimi" class="form-control"/>
		<form:input path="kuvaus" class="form-control"/>
		
	<input type="submit" class="btn btn-success btn-md lisaa_tunteja_button" value="<spring:message code="app.add" />" />	
	
	</form:form>
	
	</div>
	</sec:authorize>
	
	<div class="container">
		<h2><spring:message code="app.choose_project"/></h2>
		<p>tähän tulee listaus projekteista</p>	
		<p>tähän tulee listaus projekteista</p>	
		<p>tähän tulee listaus projekteista</p>	
		<p>tähän tulee listaus projekteista</p>	
		<p>tähän tulee listaus projekteista</p>	
		<p>tähän tulee listaus projekteista</p>	
	</div>
	
</body>
</html>