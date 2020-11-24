<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp"/>
<body>
<jsp:include page="navbar.jsp"/>
	
	<header class="bg-info text-white">
    <div class="container text-center">
      <h1>Pressão</h1> 
      <p class="lead">Acompanhe as suas medidas de pressão durante os dias.</p>
    </div>
    <div class="container text-center">
    
    <p>
  		<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
   		 Atualize sua pressão
  		</button>
	</p>
	<div class="collapse" id="collapseExample">
  		<div class="card card-body text-white bg-info">
		    <form class="form-inline justify-content-center" action="/action_page.php">
			  <div class="form-group">
			    <label for="text">Pressão Mínima(mmHg/10): </label>
			    <input type="text" class="form-control" id="text" name="pMin">
			  </div>
			  <div class="form-group">
			    <label for="number">Pressão Máxima(mmHg/10):</label>
			    <input type="number" class="form-control" id="number" name="pMax">
			  </div>
			</form>
			<button type="submit" class="btn btn-default">Atualizar</button>
			
  	    </div>
	</div>
    
	</div>
  </header>
  
  <hr>
	
	<table class="table table-hover table table-bordered">
  <thead>
    <tr>
      <th scope="col">Data</th>
      <th scope="col">Pressão Máxima(mmHg/10)</th>
      <th scope="col">Pressão Mínima(mmHg/10)</th>
      <th scope="col">Horário</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">03/10/2020</th>
      <td>12</td>
      <td>9</td>
      <td>14:00</td>
    </tr>
    <tr>
      <th scope="row">04/10/2020</th>
      <td>12</td>
      <td>8</td>
      <td>15:00</td>
    </tr>
    <tr>
      <th scope="row">04/10/2020</th>
      <td>12</td>
      <td>9</td>
      <td>20:00</td>
    </tr>
    <tr>
      <th scope="row">05/10/2020</th>
      <td>12</td>
      <td>8</td>
      <td>10:00</td>
    </tr>
    
  </tbody>
</table>

<jsp:include page="footer.jsp"/>
</body>
</html>