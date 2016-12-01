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
<link rel="stylesheet" type="text/css" href="/tuntikirjanpito/form.css">

<title><spring:message code="app.title" /></title>

</head>
<body onload="myFunction2()">
<img src="img/kello.jpg" alt="Kello"/> 
	<script>
		$(function(){
			$('.datepicker').datepicker({
				format: "dd.mm.yyyy",
				weekStart: 1,
				todayBtn: "linked",
				autoclose: true
				<c:if test="${pageContext.response.locale == 'fi'}">
				,
				language: "fi"
				</c:if>
			});
		});
	</script>
	<script type="text/javascript">
	$('body').prepend('<a href="#" class="back-to-top">Back to Top</a>');
</script>
	<a href="#" class="back-to-top">Back to Top</a>
	<div class="container">
		<div class="col-lg-6 col-md-6 col-sm-6" id="sisalto_lohko">
			<h2><spring:message code="app.name" /></h2>
		</div>
		<div class="container">
		<div class="col-lg-6 col-md-6 col-sm-6 ylakulma">
			<a href="?language=en"><spring:message code="app.english" /></a> | <a href="?language=fi"><spring:message code="app.finnish" /></a>
			<p><spring:message code="app.loggedin" />: <sec:authentication property="principal.username"/></p>
			<p><a href="/tuntikirjanpito/logout" ><spring:message code="app.logout" /></a></p>
		</div>
		</div>
		 <c:if test="${not empty poisto}">
			 <div id='Poisto' class="alert alert-warning"><strong>Tietue poistettu</strong></div>
			   <script type="text/javascript">
		  			$( '#Poisto' ).show(function(){
		      			$(this).fadeOut(5000);
					});
				</script>
		</c:if>
	<div class="container">
	<div class="container col-lg-6 col-sm-12 tuntikirjaus_lohko" id="sisalto_lohko1" >
			<h4 class="hykkonen"><spring:message code="app.addhours" /></h4>
			<form:form action="/tuntikirjanpito/lisaa" modelAttribute="henkilo" method="POST">
					<spring:message code="app.chooseuser" var="cuser"/>
					<c:set var="Virheteksti1Errors"><form:errors path="id" cssClass="Virheteksti" /></c:set>
					<c:if test="${not empty Virheteksti1Errors}">
					<style type="text/css">
     						#syotto_kentat1{
     						border: 1px solid red;
     						border-radius: 7px;
     						margin-bottom:1em;
     						}
  					</style>
					</c:if>
					<form:errors path="id" cssClass="Virheteksti" />
					<div class="input-group input-group-lg" id="syotto_kentat1">
					<span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
					
					<form:select path="id" class="form-control">
						  <form:option value="-1" label="${cuser}" />
						  <c:forEach items="${henkiloTiedot}" var="ht">
						  <form:option value="${ht.id}">${ht.etunimi} ${ht.sukunimi}</form:option>
						  </c:forEach>
					</form:select>
					</div>
					
					<form:hidden path="etunimi" value="placeholder"/>
					<form:hidden path="sukunimi" value="placeholder"/>
					<form:hidden path="email" value="placeholder@holder.fi" />
					<form:hidden path="salasana" value="placeholder"/>
					<form:hidden path="tunnus" value="123"/>
					
					<c:set var="Virheteksti2Errors"><form:errors path="tunnit[0].tunnit" cssClass="Virheteksti"/></c:set>
					<c:if test="${not empty Virheteksti2Errors}">
					<style type="text/css">
     						#syotto_kentat2{
     						border: 1px solid red;
     						border-radius: 7px;
     						margin-bottom:1em;
     						}
  					</style>
					</c:if>
					<form:errors path="tunnit[0].tunnit" cssClass="Virheteksti"/>
					<div class="input-group input-group-lg" id="syotto_kentat2">
					<span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-time" aria-hidden="true"></span></span>
					<form:input path="tunnit[0].tunnit" class="form-control" />
					</div>
					<spring:message code="app.description" var="desc"/>
					<c:set var="Virheteksti3Errors"><form:errors path="tunnit[0].kuvaus" cssClass="Virheteksti"/></c:set>
					<c:if test="${not empty Virheteksti3Errors}">
					<style type="text/css">
     						#syotto_kentat3{
     						border: 1px solid red;
     						border-radius: 7px;
     						margin-bottom:1em;
     						}
  					</style>
					</c:if>
					
					<form:errors path="tunnit[0].kuvaus" cssClass="Virheteksti"/>
					<div class="input-group input-group-lg" id="syotto_kentat4">
					<span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span></span>
					<form:input path="tunnit[0].kuvaus" rows="5" cols="30" class="form-control" placeholder="${desc} " />
					</div>
					
					<c:set var="Virheteksti4Errors"><form:errors path="tunnit[0].stringdate" cssClass="Virheteksti"/></c:set>
					<c:if test="${not empty Virheteksti4Errors}">
					<style type="text/css">
     						#syotto_kentat4{
     						border: 1px solid red;
     						border-radius: 7px;
     						margin-bottom:1em;
     						}
  					</style>
					</c:if>
					<form:errors path="tunnit[0].stringdate" cssClass="Virheteksti"/>
					<div class="input-group input-group-lg" id="syotto_kentat4">
					<span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
					<form:input path="tunnit[0].stringdate" class="datepicker form-control"/>
					</div>
					
						
					<input type="submit" class="btn btn-success btn-md lisaa_tunteja_button"  value="<spring:message code="app.add" />" >
					
			</form:form>
								<c:if test="${not empty tallennus}">
					<div id='Lisays' class="alert alert-success"><strong>Tallennus onnistui</strong></div>
  					<script type="text/javascript">
  						$( '#Lisays' ).show(function(){
      							$(this).fadeOut(5000);
						});
					</script></c:if>
		</div>
		<div class="col-lg-6 table-responsive" id="sisalto_lohko">
		<form action="/tuntikirjanpito/henkilo" method="POST">
		  <h4><spring:message code="app.person.hour" /></h4>
		                              
		  <table class="table table-hover">
		    <thead>
		    	<tr>
		        	<th><spring:message code="app.fname" /></th>
		        	<th><spring:message code="app.lname" /></th>
		        	<th class="tunnityht"><spring:message code="app.personhourstogether" /></th>
		    	</tr>
		    </thead>
		    <tbody>
		    
		    <c:set var="total" value="${0}"/>
		    	<c:forEach items="${henkiloidenTunnit}" var="ht">
		      		<tr>
		     			<td><button type=submit name="tunti_id" value="${ht.id}" onclick="myFunction()" class="btn-link">${ht.etunimi}</button></td>
		     			<td><button type=submit name="tunti_id" value="${ht.id}" onclick="myFunction()" class="btn-link">${ht.sukunimi}</button></td>
		   
		        		<td class="tunnityht">${ht.tunnitYhteensa}</td>
		        		<c:set var="total" value="${total + ht.tunnitYhteensa}" />
		     	 	</tr>
		    	 </c:forEach>
		    <tr>
		    <td></td>
		    <td class="yht1"><spring:message code="app.total" /></td>	 
		    <td><div class="yht2"><p>${total}</p></div></td>	
		    </tr>
		    </tbody>		     	 	
		    </table>
		    </form>
		</div>

	<div class="container table-responsive visible-md visible-lg visible-xl" id="sisalto_lohko">
	  <h4 id="hykkonen"><spring:message code="app.listofhours" /></h4> <a style="float:right" href="/tuntikirjanpito/" onclick="myFunction()"><spring:message code="app.listall" /></a>
	  <form action="/tuntikirjanpito/poista" method="POST">                           
	  <table class="table table-hover">
	    <thead>
	      <tr>
	        <th><spring:message code="app.fname" /></th>
	        <th><spring:message code="app.lname" /></th>
	        <th><spring:message code="app.hours" /></th>
	        <th><spring:message code="app.description" /></th>
	        <th><spring:message code="app.date" /></th>
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
	        <td><button type="submit" name="tunti_id" class="btn btn-danger btn-xs" value="${h.tunnit[0].id }" ><spring:message code="app.delete" /></button></td>
	      </tr>
	     </c:forEach> 
	    </tbody>
	  </table>
	  </form>
	</div>
</div>	

	<div class="container table-responsive visible-sm visible-xs" id="sisalto_lohko" >
	  <h4 id="hykkonen2"><spring:message code="app.listofhours" /></h4> <a style="float:right" href="/tuntikirjanpito/" onclick="myFunction4()"><spring:message code="app.listall" /></a>

	  <form action="/tuntikirjanpito/poista" method="POST">                           
	  <table class="table listaus_mobile">
	    
	    <tbody>
	      <c:forEach items="${henkilot}" var="h">
	      <tr>
	        <td>${h.etunimi} ${h.sukunimi}	</td>
	        <td>${h.tunnit[0].tunnit} h <fmt:formatDate pattern="dd.MM." value="${h.tunnit[0].pvm}"/></td>
			<td>${h.tunnit[0].kuvaus} </td>
	        <td><button type="submit" name="tunti_id" class="btn btn-danger btn-xs" value="${h.tunnit[0].id }" ><spring:message code="app.delete" /></button></td>
	      </tr>

	     </c:forEach> 
	    </tbody>
	  </table>
	  </form>
	  </div>
	  </div>	
<script>
var on = 0;
var on2 = 0;
function myFunction() {
	on = 1;
	localStorage.setItem("on", on);
	
}

function myFunction4() {
	on2 = 1;
	localStorage.setItem("on2", on2);
	
}
	
function myFunction2() {
	var onOff = localStorage.getItem("on");
	var onOff2 = localStorage.getItem("on2");
	if (onOff == 1){
    window.location.href = "#hykkonen";
    window.location.href = "#hykkonen2";
    localStorage.clear();
    return false;
	}
	if (onOff2 == 1){
	    window.location.href = "#hykkonen2";
	    localStorage.clear();
	    return false;
		}
}

</script>
<script>
var amountScrolled = 300;

$(window).scroll(function() {
	if ( $(window).scrollTop() > amountScrolled ) {
		$('a.back-to-top').fadeIn('slow');
	} else {
		$('a.back-to-top').fadeOut('slow');
	}
});
</script>
<script>
$('a.back-to-top').click(function() {
	$('html, body').animate({
		scrollTop: 0
	}, 700);
	return false;
});
</script>
<div class="propsit"><a href="http://PPDigital.openphoto.net/gallery/">Darren Hester</a> for <a href="http://openphoto.net/gallery/image/view/6263">openphoto.net</a></div>
</body>
</html>