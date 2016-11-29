<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false"%>
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
<link rel="stylesheet" type="text/css" href="/tuntikirjanpito/common.css">
</head>
<body>

	<div><a href="?language=en"><spring:message code="app.english" /></a>|<a href="?language=fi"><spring:message code="app.finnish" /></a></div>

	
			 <c:if test="${update='eok'}">
	 <div id='update' class="alert alert-warning"><strong><spring:message code="app.updatenotok" /></strong></div>
	   <script type="text/javascript">
  $( '#update' ).show(function(){
      $(this).fadeOut(5000);
});
</script>
</c:if>
	<div class="container">

        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <h3><spring:message code="app.name" /></h3> 
            <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title"><spring:message code="app.resetpw" /></div>
                    </div>     

                    <div style="padding-top:30px" class="panel-body" >

                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                        
                        <c:if test="${param.error != null}">
						        <div id=>
						        	<spring:message code="app.wrong_password"/>
						        </div>      
         				</c:if>
                        
                        
                        <form:form id="loginform" class="form-horizontal" action="/tuntikirjanpito/unohdus" modelAttribute="unohdus" method="post">
                            
                               <form:errors path="tunnus" cssClass="Virheteksti"/>
                            <div style="margin-bottom: 25px" class="input-group">
                            			
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <spring:message code="app.username" var="username"/>
                                        <form:input id="login-username" type="text" class="form-control" path="tunnus" value="" placeholder="${username}" />                                        
                                    </div>
                                    
                                    <form:errors path="email" cssClass="Virheteksti"/>
                            <div style="margin-bottom: 25px" class="input-group">
                            			
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <spring:message code="app.email" var="email"/>
                                        <form:input id="login-email" type="email" class="form-control" path="email" placeholder="${email}" />
                                    </div>
                                
                                <form:errors path="salasana" cssClass="Virheteksti"/>
                            <div style="margin-bottom: 25px" class="input-group">
                            			
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <spring:message code="app.password" var="password"/>
                                        <form:input id="login-password" type="password" class="form-control" path="salasana" placeholder="${password}" />
                                    </div>
                                                        
                                <div style="margin-top:10px" class="form-group">
                                    <!-- Button -->

                                    <div class="col-sm-12 controls">
                                    <spring:message code="app.resetpw" var="res"/>
                                    <input id="btn-login" type="submit" class="btn btn-success btn-md" style="float:right; margin-top:10px; clear:both;" value="${res}" />
                                    </div>
                                </div>


         </form:form>                       
    </div>
    </div>
   </div>
   </div> 



</body>
</html>