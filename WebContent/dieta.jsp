<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp"/>
<body>
<jsp:include page="navbar.jsp"/>
	
	<header class="bg-info text-white">
    <div class="container text-center">
      <h1>Dieta</h1> 
      <p class="lead">Monitore sua dieta e adeque o melhor pra voc�!</p>
    </div>
    <div class="container text-center">
    
    <p>
  		<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
   		 Atualize sua dieta
  		</button>
	</p>
	<c:if test="${empty updateData}">
	<div class="collapse" id="collapseExample">
  		<div class="card card-body text-white bg-info">
		    <form class="form-inline justify-content-center" method="post" action="dieta?acao=cadastrar">
			  <div class="form-group">
			    <label for="text">Refei��o: </label>
			    <input type="text" class="form-control" id="text" name="refeicao">
			  </div>
			  <div class="form-group">
			    <label for="number">Calorias (kCal): </label>
			    <input type="number" class="form-control" id="number" name="calorias">
			  </div>
			  <input type="submit" class="btn btn-default" value="Atualizar">
			</form>
			
  	    </div>
	</div>
	</c:if>
	<c:if test="${not empty updateData}">
		<div class="collapse show" id="collapseExample">
		<div class="card card-body text-white bg-info">
		    <form class="form-inline justify-content-center" method="post" action="dieta?acao=editar">
		    	<input type="hidden" class="form-control" name="id" value="${updateData.id}">
		    	<input type="hidden" class="form-control" name="update" value="true">
			  <div class="form-group">
			    <label for="text">Refei��o: </label>
			    <input type="text" class="form-control" id="text" name="refeicao" value="${updateData.refeicao.descricao}">
			  </div>
			  <div class="form-group">
			    <label for="number">Calorias (kCal): </label>
			    <input type="number" class="form-control" id="number" name="calorias" value="${updateData.calorias}">
			  </div>
			  <input type="submit" class="btn btn-default" value="Atualizar">
			</form>
  	    </div>
  	    </div>
	</c:if>
    
	</div>
  </header>
  
  <hr>
	
	<table class="table table-hover table table-bordered">
  <thead>
    <tr>
      <th scope="col">Data</th>
      <th scope="col">Hor�rio</th>
      <th scope="col">Calorias(kCal)</th>
      <th scope="col">Refei��o</th>
      <th scope="col">A��es</th>
    </tr>
  </thead>
  <tbody>
  <c:if test="${not empty lista}">
  <c:forEach var="item" items="${lista}">
    <tr>
      <th scope="row">
      	<fmt:formatDate value="${item.data.time}" pattern="dd/MM/yyyy" />
      </th>
      <td>
      	<fmt:formatDate value="${item.data.time}" pattern="HH:mm:ss" />
      </td>
      <td>${item.calorias}</td>
      <td>${item.refeicao.descricao}</td>
      <td>
      	<a href="dieta?acao=editar&id=${item.id}">
	      	<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
	          stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
	          class="feather feather-edit-3">
	          <path d="M12 20h9"></path>
	          <path d="M16.5 3.5a2.121 2.121 0 0 1 3 3L7 19l-4 1 1-4L16.5 3.5z"></path>
	        </svg>
        </a>
        <a href="dieta?acao=excluir&id=${item.id}">
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
          stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
          class="feather feather-trash-2">
          <polyline points="3 6 5 6 21 6"></polyline>
          <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
          <line x1="10" y1="11" x2="10" y2="17"></line>
          <line x1="14" y1="11" x2="14" y2="17"></line>
        </svg>
        </a>
        </td>
    </tr>
    </c:forEach>
    </c:if>
  </tbody>
</table>

<jsp:include page="footer.jsp"/>
</body>
</html>