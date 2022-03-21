
<%@page import="domaine.CreditModel"%>
<%
 CreditModel model = (CreditModel)request.getAttribute("creditModel");
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <title>Crédit bancaire</title>
 </head>
 <body>
 <p class="spacer"></p>
  <div class="container">
  <!-- <div class="col-md-6 col-xm-12 col-sm-6 col-md-offset-3"> -->
   <div class="col-md-6 col-xm-12 col-sm-6 col-md-offset-3">
    <div class="panel panel-primary">
    <div class="panel-heading">Simulateur de crédit</div>
    <div class="panel-body">
     <form action="calculerMensualite.do" method="post">
      <div class="form-group">
       <label class="control-label">Montant</label>
       <input class="form-control" type="text" name="montant" value="<%=model.getMontant()%>"/>
      </div>
      <div class="form-group">
       <label class="control-label">Taux</label>
       <input class="form-control" type="text" name="taux" value="<%=model.getTaux()%>"/>
      </div>
      <div class="form-group">
       <label class="control-label">Durée</label>
       <input class="form-control" type="text" name="duree" value="<%=model.getDuree()%>"/>
      </div>
      <button class="btn btn-primary">Calculer</button>
     </form>
     <div class="spacer">
      <label>Mensualité: </label>
      <label><%=model.getMensualite()%></label>
     </div>
    </div>
   </div>
   </div>
   
  </div>  
 </body>
</html>