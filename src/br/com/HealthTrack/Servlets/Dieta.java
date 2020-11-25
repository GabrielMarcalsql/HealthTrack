package br.com.HealthTrack.Servlets;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.HealthTrack.DAO.DAOFactory;
import br.com.HealthTrack.DAO.DietaDAO;
import br.com.HealthTrack.DAO.RefeicaoDAO;
import br.com.HealthTrack.DAO.UsuarioDAO;
import br.com.HealthTrack.Entity.DietaEntity;
import br.com.HealthTrack.Entity.RefeicaoEntity;
import br.com.HealthTrack.Entity.UsuarioEntity;
import br.com.HealthTrack.Interface.DAOInterface;
import br.com.HealthTrack.Interface.EntityInterface;

/**
 * Servlet implementation class Dieta
 */
@WebServlet("/dieta")
public class Dieta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOInterface dao;
	private DAOInterface daoUsuario;
	private RefeicaoDAO daoRefeicao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dieta() {
        super();
        dao = DAOFactory.getInstance(DietaDAO.class).getInstance();
		daoUsuario = DAOFactory.getInstance(UsuarioDAO.class).getInstance();
		daoRefeicao = DAOFactory.getInstance(RefeicaoDAO.class).getInstance();
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

		request.getRequestDispatcher("dieta?acao=listar").forward(request, response);
		
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String refeicao = request.getParameter("refeicao");
			double calorias = Double.parseDouble(request.getParameter("calorias"));

			if (refeicao != null && refeicao != "" && calorias >= 0) {
				int usuarioId = (int) request.getSession().getAttribute("userId");
				UsuarioEntity usuarioLogado = (UsuarioEntity) daoUsuario.findById(usuarioId);
				RefeicaoEntity obRefeicao = getRefeicao(refeicao);
				
				EntityInterface entity = new DietaEntity(usuarioLogado, obRefeicao, calorias, Calendar.getInstance());

				if (dao.insert(entity)) {
					request.setAttribute("msg", "Dados cadastrados com sucesso!");
				} else {
					request.setAttribute("erro", "Erro ao tentar cadastrar dados!");
				}
			}
		} catch (Exception ex) {
			request.setAttribute("erro", "Preencha os dados corretamente!");
		}

		request.getRequestDispatcher("dieta?acao=listar").forward(request, response);
	}

	private RefeicaoEntity getRefeicao(String refeicao) {
		RefeicaoEntity result = daoRefeicao.findByName(refeicao);
		
		if(result == null) {
			EntityInterface entity = new RefeicaoEntity(refeicao);
			daoRefeicao.insert(entity);
			result = daoRefeicao.findByName(refeicao);
		}
		
		return result;
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("lista", dao.getAll());
		request.getRequestDispatcher("dieta.jsp").forward(request, response);
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
