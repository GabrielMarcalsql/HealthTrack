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
              <img src='resources/imgs/cover.png' class='w-100 rounded'>
              <hr class="my-4">
              <h5 class="card-title text-center">Login</h5>
              <hr class="my-4">
              <span class="navbar-text text-danger">
              	${erro}
              </span>
              <form class="form-signin" method="post" action="login">
                <div class="form-label-group">
                  <input type="email" id="inputEmail" name="email" class="form-control required autofocus" placeholder="Insira aqui seu e-mail">
                  
                  <label for="inputEmail">E-mail</label>
                </div>

                <div class="form-label-group">
                  <input type="password" id="inputPassword" name="password" class="form-control required" placeholder="Insira aqui sua senha">
                  
                  <label for="inputPassword">Senha</label>
                </div>

                <div class="custom-control custom-checkbox mb-3">
                  <input type="checkbox" class="custom-control-input" id="customCheck1">
                  <label class="custom-control-label" for="customCheck1">Mantenha-me conectado</label>
                </div>
                <input type="submit" value="Login" class="btn btn-lg btn-primary btn-block text-uppercase" />
                <hr class="my-4">
                <div class="justify-content-center">
                <a class="btn btn-lg btn-link btn-block text-uppercase" type="submit" href="cadastro.jsp">Cadastre-se aqui</a>
                </div>
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