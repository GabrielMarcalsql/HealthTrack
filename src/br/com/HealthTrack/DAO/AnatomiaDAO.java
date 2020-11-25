package br.com.HealthTrack.DAO;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import br.com.HealthTrack.Entity.*;
import br.com.HealthTrack.Interface.DAOInterface;
import br.com.HealthTrack.Interface.EntityInterface;
import br.com.HealthTrack.Singleton.ConnectionManager;
import br.com.HealthTrack.Singleton.DAOUtil;

/**
 * Classe DAO da entidade Entity
 * 
 * @author Andrea Serpeloni - aserpeloni@hotmail.com
 * @author Fernando Grieco Feres - fegferes@gmail.com
 * @author Gabriel Silva Marçal - gmarcal6@gmail.com
 * @author Herbert de Souza Souto - herbert-93@hotmail.com
 * @author Jaelson Apolinário de Oliveira - jaelson.apolinario@gmail.com
 * 
 * @version 1.0
 */
public class AnatomiaDAO implements DAOInterface {

	private Connection conexao;

	/**
	 * Cria uma instancia de RefeicaoDAO
	 */
	public AnatomiaDAO() {
	}

	/**
	 * Retorna todos os itens
	 * 
	 * @return ArrayList lista de itens do DAO
	 */
	public List<EntityInterface> getAll() {
		// Cria uma lista de colaboradores
		List<EntityInterface> lista = new ArrayList<EntityInterface>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_ANATOMIA AN, T_HT_USUARIO US"
					+ " WHERE US.CD_USUARIO = AN.T_HT_USUARIO_CD_USUARIO");
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				double altura = rs.getDouble("VL_ALTURA");
				double peso = rs.getDouble("VL_PESO");
				java.sql.Date data = rs.getDate("DT_DATA");
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(data.getTime());

				UsuarioEntity usuario = DAOUtil.getInstance().getUsuarioFromResultSet(rs);

				EntityInterface dado = new AnatomiaEntity(id, usuario, altura, peso, dataCalendar);
				lista.add(dado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public boolean insert(EntityInterface entity) {
		AnatomiaEntity dado = (AnatomiaEntity) entity;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HT_ANATOMIA (ID, T_HT_USUARIO_CD_USUARIO, VL_ALTURA, VL_PESO, DT_DATA) VALUES (SQ_ANATOMIA.NEXTVAL, ?,?,?,?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, dado.getUsuario().getCodigo());
			stmt.setDouble(2, dado.getAltura());
			stmt.setDouble(3, dado.getPeso());
			java.sql.Date data = new java.sql.Date(dado.getData().getTimeInMillis());
			stmt.setDate(4, data);

			stmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override

	public boolean update(int id, EntityInterface entity) {
		AnatomiaEntity dado = (AnatomiaEntity) entity;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_HT_ANATOMIA SET T_HT_USUARIO_CD_USUARIO = ?, VL_ALTURA = ?, VL_PESO = ?, DT_DATA = ?  WHERE ID = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, dado.getUsuario().getCodigo());
			stmt.setDouble(2, dado.getAltura());
			stmt.setDouble(3, dado.getPeso());
			java.sql.Date data = new java.sql.Date(dado.getData().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.setInt(5, id);
			stmt.executeUpdate();

			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public boolean delete(int id) {
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_HT_ANATOMIA WHERE ID = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public EntityInterface findById(int id) {
		EntityInterface result = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_ANATOMIA AN, T_HT_USUARIO US"
					+ " WHERE US.CD_USUARIO = AN.T_HT_USUARIO_CD_USUARIO" + " AND AN.ID = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				double altura = rs.getDouble("VL_ALTURA");
				double peso = rs.getDouble("VL_PESO");
				java.sql.Date data = rs.getDate("DT_DATA");
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(data.getTime());

				UsuarioEntity usuario = DAOUtil.getInstance().getUsuarioFromResultSet(rs);

				result = new AnatomiaEntity(id, usuario, altura, peso, dataCalendar);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
