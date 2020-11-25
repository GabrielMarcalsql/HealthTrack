package br.com.HealthTrack.Servlets;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.HealthTrack.DAO.DAOFactory;
import br.com.HealthTrack.DAO.PressaoDAO;
import br.com.HealthTrack.DAO.UsuarioDAO;
import br.com.HealthTrack.Entity.AnatomiaEntity;
import br.com.HealthTrack.Entity.DietaEntity;
import br.com.HealthTrack.Entity.PressaoEntity;
import br.com.HealthTrack.Entity.UsuarioEntity;
import br.com.HealthTrack.Interface.DAOInterface;
import br.com.HealthTrack.Interface.EntityInterface;

/**
 * Servlet implementation class Pressao
 */
@WebServlet("/pressao")
public class Pressao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOInterface dao;
	private DAOInterface daoUsuario;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pressao() {
        super();
        dao = DAOFactory.getInstance(PressaoDAO.class).getInstance();
		daoUsuario = DAOFactory.getInstance(UsuarioDAO.class).getInstance();
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		switch (acao) {
		default:
		case "listar":
			listar(request, response);
			break;
		case "cadastrar":
			cadastrar(request, response);
			break;
		case "excluir":
			excluir(request, response);
			break;
		case "editar":
			editar(request, response);
			break;
		}
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			int id = Integer.parseInt(request.getParameter("id"));

			if (request.getParameter("update") == null) {
				request.setAttribute("updateData", dao.findById(id));
			} else {
				double diastolica = Double.parseDouble(request.getParameter("pMin"));
				double sistolica = Double.parseDouble(request.getParameter("pMax"));

				if (diastolica > 0 && sistolica > 0) {
					PressaoEntity entity = (PressaoEntity) dao.findById(id);

					if (entity == null)
						throw new Exception();

					entity.setDiastolica(diastolica);
					entity.setSistolica(sistolica);
					
					if (dao.update(id, entity)) {
						request.setAttribute("msg", "Dados ataulizados com sucesso!");
						request.setAttribute("updateData", null);
					} else {
						request.setAttribute("erro", "Erro ao tentar ataulizar dados!");
					}
				}
			}
		} catch (Exception ex) {
			request.setAttribute("erro", "Preencha os dados corretamente!");
		}

		request.getRequestDispatcher("pressao?acao=listar").forward(request, response);
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

		request.getRequestDispatcher("pressao?acao=listar").forward(request, response);
		
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			double diastolica = Double.parseDouble(request.getParameter("pMin"));
			double sistolica = Double.parseDouble(request.getParameter("pMax"));

			if (diastolica > 0 && sistolica > 0) {
				int usuarioId = (int) request.getSession().getAttribute("userId");
				UsuarioEntity usuarioLogado = (UsuarioEntity) daoUsuario.findById(usuarioId);
				EntityInterface entity = new PressaoEntity(sistolica, diastolica, Calendar.getInstance(), usuarioLogado);

				if (dao.insert(entity)) {
					request.setAttribute("msg", "Dados cadastrados com sucesso!");
				} else {
					request.setAttribute("erro", "Erro ao tentar cadastrar dados!");
				}
			}
		} catch (Exception ex) {
			request.setAttribute("erro", "Preencha os dados corretamente!");
		}

		request.getRequestDispatcher("pressao?acao=listar").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("lista", dao.getAll());
		request.getRequestDispatcher("pressao.jsp").forward(request, response);
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
