<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>		
<nav class="navbar navbar-expand-md navbar-dark bg-secondary navbar-static-top">
	<div class="container">
		<a class="navbar-brand" href="menu.jsp"> <img
			src='./resources/imgs/vector/default-monochrome.svg' width='120'
			height='35'>
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link"
					href="anatomia?acao=listar">Anatomia</a></li>
				<li class="nav-item"><a class="nav-link"
					href="dieta?acao=listar">Dieta</a></li>
				<li class="nav-item"><a class="nav-link"
					href="pressao?acao=listar">Pressão</a></li>
				<li class="nav-item"><a class="nav-link"
					href="exercicios?acao=listar">Exercícios</a></li>
				<li class="nav-item"><a class="nav-link btn-outline-danger"
					href="login?acao=logout">Logout</a></li>
			</ul>
		</div>
	</div>
</nav>
<c:if test="${not empty msg }">
		<div class="alert alert-success" style="margin-bottom: 0px" role="alert">${msg}</div>
	</c:if>
	<c:if test="${not empty erro }">
		<div class="alert alert-danger alert-custom" style="margin-bottom: 0px" role="alert">${erro}</div>
	</c:if>