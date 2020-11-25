package br.com.HealthTrack.Servlets;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.HealthTrack.DAO.AnatomiaDAO;
import br.com.HealthTrack.DAO.DAOFactory;
import br.com.HealthTrack.DAO.UsuarioDAO;
import br.com.HealthTrack.Entity.AnatomiaEntity;
import br.com.HealthTrack.Entity.UsuarioEntity;
import br.com.HealthTrack.Interface.DAOInterface;
import br.com.HealthTrack.Interface.EntityInterface;

/**
 * Servlet implementation class Anatomia
 */
@WebServlet("/anatomia")
public class Anatomia extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOInterface dao;
	private DAOInterface daoUsuario;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Anatomia() {
		super();
		dao = DAOFactory.getInstance(AnatomiaDAO.class).getInstance();
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
				double peso = Double.parseDouble(request.getParameter("peso"));
				double altura = Double.parseDouble(request.getParameter("altura"));
				if (peso > 0 && altura > 0 && id > 0) {
					AnatomiaEntity entity = (AnatomiaEntity) dao.findById(id);

					if (entity == null)
						throw new Exception();

					entity.setAltura(altura);
					entity.setPeso(peso);

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

		request.getRequestDispatcher("anatomia?acao=listar").forward(request, response);

	}

	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			EntityInterface entity = dao.findById(id);

			if (entity != null) {
				if (dao.delete(id)) {
					request.setAttribute("msg", "Dados excluidos com sucesso!");
				} else {
					request.setAttribute("erro", "Erro ao tentar excluir dados!");
				}
			} else {
				throw new Exception();
			}
		} catch (Exception ex) {
			request.setAttribute("erro", "Dados não encontrados!");
		}

		request.getRequestDispatcher("anatomia?acao=listar").forward(request, response);

	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			double peso = Double.parseDouble(request.getParameter("peso"));
			double altura = Double.parseDouble(request.getParameter("altura"));

			if (peso > 0 && altura > 0) {
				int usuarioId = (int) request.getSession().getAttribute("userId");
				UsuarioEntity usuarioLogado = (UsuarioEntity) daoUsuario.findById(usuarioId);
				EntityInterface entity = new AnatomiaEntity(usuarioLogado, peso, altura, Calendar.getInstance());

				if (dao.insert(entity)) {
					request.setAttribute("msg", "Dados cadastrados com sucesso!");
				} else {
					request.setAttribute("erro", "Erro ao tentar cadastrar dados!");
				}
			}
		} catch (Exception ex) {
			request.setAttribute("erro", "Preencha os dados corretamente!");
		}

		request.getRequestDispatcher("anatomia?acao=listar").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("lista", dao.getAll());
		request.getRequestDispatcher("anatomia.jsp").forward(request, response);
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
