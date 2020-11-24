<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="Site Health Track">
  <meta name="author" content="Grupo 49 - FIAP">

  <title>Health Track</title>
  <link rel="shortcut icon" href="./resources/imgs/shortcut.png" type="image/x-icon" />
  <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="resources/css/login.css" rel="stylesheet">

</head>
<body>
	
    <div class="container">
      <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
          <div class="card card-signin my-5">
            <div class="card-body">
              
              <h5 class="card-title text-center">Cadastro</h5>
              <hr class="my-4">
				 <form>
				  <div class="form-group">
				    <label for="InputEmail1">Email</label>
				    <input type="email" name="email" class="form-control" id="InputEmail1" aria-describedby="emailHelp">
				  </div>
				  <div class="form-group">
				    <label for="phone">Telefone/Celular</label>
				    <input type="tel" class="form-control" id="phone" name="phone" pattern="[0-9]{2}-[0-9]{9}">
				    <small>Formato: 11-123456789</small>
				    				    
				  </div>
				  <div class="form-group">
				    <label for="InputName">Nome</label>
				    <input type="text" name="name" class="form-control" id="InputName">
				  </div>
				  <div class="form-group">
				    <label for="InputLastName">Sobrenome</label>
				    <input type="text" name="lastName" class="form-control" id="InputLastName">
				  </div>
				  <div class="form-group">
				    <label for="InputDate">Data de Nascimento</label>
				    <input type="date" name="birthday" class="form-control" id="InputDate" required>
				  </div>
				  <div class="form-group">
				    <label for="InputPassword">Senha</label>
				    <input type="password" name="password" class="form-control" id="InputPassword">
				  </div>
				  <hr class="my-4">
				  <a class="btn btn-lg btn-primary btn-block text-uppercase" href="menu.jsp">Login</a>
				</form>
            </div>
          </div>
        </div>
      </div>
    </div>

</body>
  <script src="resources/jquery/jquery.min.js"></script>
  <script src="resources/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="resources/jquery-easing/jquery.easing.min.js"></script>
  <script src="resources/js/scrolling-nav.js"></script>
</html>