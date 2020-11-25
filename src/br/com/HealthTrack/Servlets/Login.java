package br.com.HealthTrack.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.HealthTrack.DAO.DAOFactory;
import br.com.HealthTrack.DAO.UsuarioDAO;
import br.com.HealthTrack.Entity.UsuarioEntity;
import br.com.HealthTrack.Interface.DAOInterface;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UsuarioDAO dao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        dao = DAOFactory.getInstance(UsuarioDAO.class).getInstance();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch(acao) {
		case "logout":
			logout(request, response);
			break;
		default:
			break;
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nextPage = "login.jsp";
		UsuarioEntity ususarioLogado = dao.doLogin(email, password);
		
		if(ususarioLogado == null) {
			request.setAttribute("erro", "Usuário e/ou senha inválidos");
		}
		else {
			nextPage = "menu.jsp";
			HttpSession session = request.getSession();
			session.setAttribute("userId", ususarioLogado.getCodigo());
		}
		
		request.getRequestDispatcher(nextPage).forward(request, response);
	}

}
