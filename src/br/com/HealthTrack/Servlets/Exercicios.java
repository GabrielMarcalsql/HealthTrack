package br.com.HealthTrack.Servlets;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.HealthTrack.DAO.AtividadeFisicaDAO;
import br.com.HealthTrack.DAO.DAOFactory;
import br.com.HealthTrack.DAO.ExercicioDAO;
import br.com.HealthTrack.DAO.UsuarioDAO;
import br.com.HealthTrack.Entity.AtividadeFisicaEntity;
import br.com.HealthTrack.Entity.ExercicioEntity;
import br.com.HealthTrack.Entity.UsuarioEntity;
import br.com.HealthTrack.Interface.DAOInterface;
import br.com.HealthTrack.Interface.EntityInterface;

/**
 * Servlet implementation class Exercicios
 */
@WebServlet("/exercicios")
public class Exercicios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOInterface dao;
	private DAOInterface daoUsuario;
	private AtividadeFisicaDAO daoAtFisica;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Exercicios() {
        super();
        dao = DAOFactory.getInstance(ExercicioDAO.class).getInstance();
		daoUsuario = DAOFactory.getInstance(UsuarioDAO.class).getInstance();
		daoAtFisica = DAOFactory.getInstance(AtividadeFisicaDAO.class).getInstance();
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		switch (acao) {
		case "listar":
			listar(request, response);
			break;
		case "cadastrar":
			cadastrar(request, response);
			break;
		case "excluir":
			excluir(request, response);
			break;
		default:
			break;
		}
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			EntityInterface entity = dao.findById(id);

			if (entity != null) {
				if (dao.delete(id)) {
					request.setAttribute("msg", "Dados excluidos com sucesso!");
				} else {
					request.setAttribute("erro", "Erro ao tentar excluir dados!");
				}
			}else {
				throw new Exception();
			}
		} catch (Exception ex) {
			request.setAttribute("erro", "Dados não encontrados!");
		}

		request.getRequestDispatcher("exercicios?acao=listar").forward(request, response);
		
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String ativiadade = request.getParameter("atividade");
			int duracao = Integer.parseInt(request.getParameter("duracao"));

			if (ativiadade != null && ativiadade != "" && duracao >= 0) {
				int usuarioId = (int) request.getSession().getAttribute("userId");
				UsuarioEntity usuarioLogado = (UsuarioEntity) daoUsuario.findById(usuarioId);
				AtividadeFisicaEntity obAtividade = getAtividadeFisica(ativiadade);
				
				EntityInterface entity = new ExercicioEntity(usuarioLogado, obAtividade, duracao, Calendar.getInstance());

				if (dao.insert(entity)) {
					request.setAttribute("msg", "Dados cadastrados com sucesso!");
				} else {
					request.setAttribute("erro", "Erro ao tentar cadastrar dados!");
				}
			}
		} catch (Exception ex) {
			request.setAttribute("erro", "Preencha os dados corretamente!");
		}

		request.getRequestDispatcher("exercicios?acao=listar").forward(request, response);
	}

	private AtividadeFisicaEntity getAtividadeFisica(String atividadeFisica) {
		AtividadeFisicaEntity result = daoAtFisica.findByName(atividadeFisica);
		
		if(result == null) {
			EntityInterface entity = new AtividadeFisicaEntity(atividadeFisica);
			daoAtFisica.insert(entity);
			result = daoAtFisica.findByName(atividadeFisica);
		}
		
		return result;
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("lista", dao.getAll());
		request.getRequestDispatcher("exercicios.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
