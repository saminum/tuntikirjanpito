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
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">	


<script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/locales/bootstrap-datepicker.fi.min.js" charset="UTF-8"></script>

<title><spring:message code="app.title" /></title>

<!-- <link rel="stylesheet" type="text/css" href="/common.css"> -->

</head>

<body>
	<div>Language : <a href="?language=en"><spring:message code="app.english" /></a>|<a href="?language=fi"><spring:message code="app.finnish" /></a></div>

	<div class="container">
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <h3><spring:message code="app.name" /></h3> 
            <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title"><spring:message code="app.loggingin" /></div>
                    </div>     

                    <div style="padding-top:30px" class="panel-body" >
                        <form:form action="/tuntikirjanpito/register" modelAttribute="registerhenkilo" method="POST">        
                            <spring:message code="app.fname" var="fname"/>
							<form:errors path="etunimi" cssClass="Virheteksti" var=""/>
							<span class="input-group-row" id="sizing-addon1"></span>
							<form:input path="etunimi" rows="5" cols="30" class="form-control" style="margin-bottom: 15px;" placeholder="${fname} " />
                           
                            <spring:message code="app.lname" var="lname"/>
                            <form:errors path="sukunimi" cssClass="Virheteksti"/>
							<span class="input-group-row" id="sizing-addon1"></span>
							<form:input path="sukunimi" rows="5" cols="30" class="form-control" style="margin-bottom: 15px;" placeholder="${lname} " />
                               
							<spring:message code="app.username" var="username"/>
                            <form:errors path="tunnus" cssClass="Virheteksti"/>
							<span class="input-group-row" id="sizing-addon1"></span>
							<form:input path="tunnus" rows="5" cols="30" class="form-control" style="margin-bottom: 15px;" placeholder="${username} " />
                                
                            <spring:message code="app.password" var="password"/>
                            <form:errors path="salasana" cssClass="Virheteksti"/>
							<span class="input-group-row" id="sizing-addon1"></span>
							<form:input path="salasana" type="password" rows="5" cols="30" class="form-control" style="margin-bottom: 15px;" placeholder="${password} " />
                            
                            <spring:message code="app.email" var="email"/>
                            <form:errors path="email" cssClass="Virheteksti"/>
							<span class="input-group-row" id="sizing-addon1"></span>
							<form:input path="email" type="email" rows="5" cols="30" class="form-control" style="margin-bottom: 15px;" placeholder="${email} " />
                                                         
                            <a href="/tuntikirjanpito/"><spring:message code="app.registercancel"/></a>
                            <input type="submit" class="btn btn-success btn-md lisaa_tunteja_button" style="float:right;" value="<spring:message code="app.register" />" />
						</form:form>                
    				</div>
    		</div>
   		</div>
   </div> 
</body>
</html>