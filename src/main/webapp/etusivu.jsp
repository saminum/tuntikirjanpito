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
			<a href="?language=en"><spring:message code="app.english" /></a> | <a href="?language=fi"><spring:message code="app.finnish" /></a>
			<p><spring:message code="app.loggedin" />: <sec:authentication property="principal.username"/></p>
			<p><a href="/tuntikirjanpito/logout" ><spring:message code="app.logout" /></a></p>
		</div>	
	</div>
	
	<div class="container kaksi">
	<sec:authorize access="hasRole('ADMIN')"><h3 class="ylaotsikko"><spring:message code="app.admin_controlpanel"/></h3></sec:authorize>
		<div class="col-lg-7 table-responsive" id="sisalto_lohko_etuS">
		<form:form id="columnarForm" modelAttribute="Projekti" method="POST">                            
		  <table class="table table-hover">
		    <thead>
		    	<tr>
			        <td class="yht1"><spring:message code="app.proj_pname"/></td>
			        <td class="katoaa"><spring:message code="app.proj_des" /></td>
			        <td class="katoaaPvm"><spring:message code="app.proj_start"/></td>
			       	<td class="katoaaPvm"><spring:message code="app.proj_finish"/></td>
			       	<td></td>
		    	</tr>
		    </thead>
		    <tbody>	    
		    <c:forEach items="${projektit}" var="p">
	      <tr>
	        <td><button type="submit" name="projekti_id" onclick="submitForm('/tuntikirjanpito/listaa_projektit')" value="${p.projekti_id}" class="btn-link">${p.nimi}</button></td>
	        <td class="katoaa2">${p.kuvaus}</td>
	        <td class="katoaaPvm2">${p.alku_pvm}</td>
	       	<td class="katoaaPvm2">${p.loppu_pvm}</td>
	       	<sec:authorize access="hasRole('ADMIN')"><td><button type="submit" name="projekti_id" onclick="submitFormDelete('/tuntikirjanpito/poista_projekti')" class="btn btn-danger btn-xs" value="${p.projekti_id}" ><spring:message code="app.delete" /></button></td></sec:authorize>
		 </tr>
	     </c:forEach> 
		    </tbody>		     	 	
		    </table>
		    </form:form>
		</div>
<sec:authorize access="hasRole('ADMIN')">
<div class="osa1">
	<div class="col-md-12">
	<h4 class="hKolmonen"><spring:message code="app.create_new_project"/></h4>
	<form:form action="/tuntikirjanpito/luo_projekti" modelAttribute="projekti" method="POST">
		
	<spring:message code="app.proj_pname" var="proj_name"/>
	<form:errors path="nimi" cssClass="Virheteksti"/>
		<div class="input-group input-group-lg" id="syotto_kentat">
			<span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span></span>
			<form:input path="nimi" class="form-control" placeholder="${proj_name} " />
		</div>
		<p class="Virheteksti">${proj_luonti_virhe}</p>
	
	<spring:message code="app.proj_desc" var="proj_desc"/>
	<form:errors path="kuvaus" cssClass="Virheteksti"/>
		<div class="input-group input-group-lg" id="syotto_kentat">
			<span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span></span>
			<form:input path="kuvaus" class="form-control" placeholder="${proj_desc} " />
		</div>
	<p></p>	
	<input type="submit" class="btn btn-success btn-md lisaa_tunteja_button" value="<spring:message code="app.add" />" />	
	</form:form>
	</div>
</div>
<div class="osa2">
	<div class="col-md-12">
	<h4 class="hKolmonen"><spring:message code="app.add_person_to_project"/></h4>
	<form:form action="/tuntikirjanpito/lisaa_henkilo_projektiin" modelAttribute="henkiloProjektiFormi" method="POST">
			
			<spring:message code="app.choose_project" var="cproj"/>
			<form:errors path="projekti_id" cssClass="Virheteksti"/>
			<div class="input-group input-group-lg" id="syotto_kentat">
			<span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span></span>
			<form:select path="projekti_id" class="form-control">
				<form:option value="-1" label="${cproj}" />
				<c:forEach items="${henkiloProjekti.projektit}" var="pt">
					<form:option value="${pt.projekti_id}">${pt.nimi}</form:option>
				</c:forEach>
			</form:select>	
			</div>
			<p></p>	
			<spring:message code="app.choose_person" var="cuser"/>
			<form:errors path="henkilo_id" cssClass="Virheteksti"/>
			<div class="input-group input-group-lg" id="syotto_kentat">
			<span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
			<form:select path="henkilo_id" class="form-control">
				<form:option value="-1" label="${cuser}" />
				<c:forEach items="${henkiloProjekti.henkilot}" var="ht">
					<form:option value="${ht.id}">${ht.etunimi} ${ht.sukunimi}</form:option>
				</c:forEach>
			</form:select>
			
			</div>
			<p class="Virheteksti">${virhe}</p>	
		
		<input type="submit" class="btn btn-success btn-md lisaa_tunteja_button" value="<spring:message code="app.add" />" />
	</form:form>
	<br>
	</div>
</div>	
	</sec:authorize>
	</div>
	<spring:message code="app.delete_project" var="teksti"/>
<script>
    function submitForm(action)
    {
        document.getElementById('columnarForm').action = action;
        document.getElementById('columnarForm').submit();
    }
    function submitFormDelete(action)
    {
    	if(confirm('${teksti}') == true){
	        document.getElementById('columnarForm').action = action;
	        document.getElementById('columnarForm').submit();
    	}
    }
</script>	
</body>
</html>