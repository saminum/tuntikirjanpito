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
	
	<div class="row">
	<div class="col-md-6">
	<h3><spring:message code="app.add_person_to_project"/></h3>
	<form:form action="/tuntikirjanpito/lisaa_henkilo_projektiin" modelAttribute="henkiloProjekti" method="POST">
			
			<spring:message code="app.choose_project" var="cproj"/>
			
			<div class="input-group input-group-lg" id="syotto_kentat">
			<span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span></span>
			<form:select path="projekti_id" class="form-control">
				<form:option value="0" label="${cproj}" />
				<c:forEach items="${henkiloProjekti.projektit}" var="pt">
					<form:option value="${pt.projekti_id}">${pt.nimi}</form:option>
				</c:forEach>
			</form:select>	
			</div>
			
			<spring:message code="app.choose_person" var="cuser"/>
			
			<div class="input-group input-group-lg" id="syotto_kentat">
			<span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
			<form:select path="henkilo_id" class="form-control">
				<form:option value="0" label="${cuser}" />
				<c:forEach items="${henkiloProjekti.henkilot}" var="ht">
					<form:option value="${ht.id}">${ht.etunimi} ${ht.sukunimi}</form:option>
				</c:forEach>
			</form:select>
			
			</div>
			<p>${virhe}</p>	
		
		<input type="submit" class="btn btn-success btn-md lisaa_tunteja_button" value="<spring:message code="app.add" />" />
	</form:form>
	
	</div>
	
	
	<div class="col-md-6">
	<h3><spring:message code="app.create_new_project"/></h3>
	<form:form action="/tuntikirjanpito/luo_projekti" modelAttribute="projekti" method="POST">
		
	<spring:message code="app.proj_name" var="proj_name"/>
		<div class="input-group input-group-lg" id="syotto_kentat">
			<span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span></span>
			<form:input path="nimi" class="form-control" placeholder="${proj_name} " />
		</div>
		<p>${proj_luonti_virhe}</p>
	
	<spring:message code="app.proj_desc" var="proj_desc"/>
		<div class="input-group input-group-lg" id="syotto_kentat">
			<span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span></span>
			<form:input path="kuvaus" class="form-control" placeholder="${proj_desc} " />
		</div>

	<input type="submit" class="btn btn-success btn-md lisaa_tunteja_button" value="<spring:message code="app.add" />" />	
	
	</form:form>
	</div>
	</div>
	<div class="row">
	<div class="col-md-12">
	<h3><spring:message code="app.delete_project"/></h3>
	<form:form action="/tuntikirjanpito/poista_projekti" modelAttribute="projekti" method="POST">
		
	<div class="input-group input-group-lg" id="syotto_kentat">
			<span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span></span>
			<form:select path="projekti_id" class="form-control">
				<form:option value="0" label="${cproj}" />
				<c:forEach items="${henkiloProjekti.projektit}" var="pt">
					<form:option value="${pt.projekti_id}">${pt.nimi}</form:option>
				</c:forEach>
			</form:select>	
			</div>

	<input type="submit" class="btn btn-danger btn-md lisaa_tunteja_button" value="<spring:message code="app.delete" />" />	
	
	</form:form>
	
	</div>
	</div>
	</div>
	</sec:authorize>
	
	<div class="container">
		<h2><spring:message code="app.choose_project"/></h2>
	<style>
table, th, td {
    border: 1px solid black;
    padding: 5px;
}
table {
    border-spacing: 5px;
}
</style>

	<table>
	
		  <tr>
	        <td><spring:message code="app.proj_pid" /></td>
	        <td><spring:message code="app.proj_pname"/></td>
	        <td><spring:message code="app.proj_des" /></td>
	        <td><spring:message code="app.proj_start"/></td>
	       	<td><spring:message code="app.proj_finish"/></td>
		 </tr>
		 	<form:form action="/tuntikirjanpito/listaa_projektit" modelAttribute="Projekti" method="POST">
		<c:forEach items="${projektit}" var="p">
	      <tr>
	        <td><button type="submit" name="projekti_id"  value="${p.projekti_id}">${p.projekti_id}</button></td>
	        <td>${p.nimi}</td>
	        <td>${p.kuvaus}</td>
	        <td>${p.alku_pvm}</td>
	       	<td>${p.loppu_pvm}</td>
		 </tr>
	     </c:forEach> 
	     </table>
	     </form:form>
	</div>
	
</body>
</html>