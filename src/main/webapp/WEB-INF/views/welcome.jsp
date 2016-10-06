<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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