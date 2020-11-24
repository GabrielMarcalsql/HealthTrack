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
      <h1>Seja Bem vindo(a) ao Health Track!</h1> <!--Mudar o x para o genero do usuario que estiver logado com o jsp param  -->
      <p class="lead">O Health Track foi desenvolvido para você<br>
        Acompanhar, monitorar e se informar sobre como melhorar a qualidade de vida</p>
    </div>
  </header>

  <section id="about">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <h2>Sobre o Health Track</h2>
          <p class="lead">Desenvolvido por alunos do primeiro ano de Análise e Desenvolvimento de Sistemas FIAP
            (2020/21)<br>
            <b>Com o HT, você pode:</b></p>
          <ul>
            <li>Seguir seu progresso de peso e IMC.</li>
            <li>Acompanhar suas medições de pressão durante os dias.</li>
            <li>Monitorar sua dieta e projetar o que é mais adequado ao seu perfil.</li>
            <li>Progresso das atividades físicas com os dias em que foram realizadas e tempo de execução</li>
          </ul>
        </div>
      </div>
    </div>
  </section>


  <section id="contact" class='bg-light'>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <h2>Contato</h2>
          <p class="lead"><b>Dúvidas?</b><br> Não hesite em nos contatar, estamos a sua disposição!</p>
          <p>Atendimento: atendimento@healthtrack.com.br</p>
        </div>
      </div>
    </div>
  </section>
<jsp:include page="footer.jsp"/>
</body>
</html>