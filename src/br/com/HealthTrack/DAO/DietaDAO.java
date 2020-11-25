package br.com.HealthTrack.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.HealthTrack.Entity.*;
import br.com.HealthTrack.Interface.*;
import br.com.HealthTrack.Singleton.ConnectionManager;
import br.com.HealthTrack.Singleton.DAOUtil;

/**
 * Classe DAO da entidade ExercicioEntity
 * 
 * @author Andrea Serpeloni - aserpeloni@hotmail.com
 * @author Fernando Grieco Feres - fegferes@gmail.com
 * @author Gabriel Silva Marçal - gmarcal6@gmail.com
 * @author Herbert de Souza Souto - herbert-93@hotmail.com
 * @author Jaelson Apolinário de Oliveira - jaelson.apolinario@gmail.com
 * 
 * @version 1.0
 */
public class DietaDAO implements DAOInterface {
	private Connection conexao;

	@Override
	public boolean insert(EntityInterface entity) {
		DietaEntity dado = (DietaEntity) entity;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HT_DIETA "
					+ "(ID, T_HT_USUARIO_CD_USUARIO, T_HT_REFEICAO_CD_REFEICAO, VL_CALORIAS, DT_DATA) " + "VALUES "
					+ "(SQ_DIETA.NEXTVAL, ?, ?, ?, ? )";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, dado.getUsuario().getCodigo());
			stmt.setInt(2, dado.getRefeicao().getCodigo());
			stmt.setDouble(3, dado.getCalorias());
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
	public List<EntityInterface> getAll() {
		List<EntityInterface> lista = new ArrayList<EntityInterface>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_DIETA DI, T_HT_USUARIO US, T_HT_REFEICAO RE"
					+ " WHERE US.CD_USUARIO = DI.T_HT_USUARIO_CD_USUARIO"
					+ " AND RE.CD_REFEICAO = DI.T_HT_REFEICAO_CD_REFEICAO");
			rs = stmt.executeQuery();

			while (rs.next()) {

				UsuarioEntity usuario = DAOUtil.getInstance().getUsuarioFromResultSet(rs);
				RefeicaoEntity refeicao = DAOUtil.getInstance().getRefeicaoFromResultSet(rs);
				int id = rs.getInt("ID");
				double calorias = rs.getDouble("VL_CALORIAS");
				java.sql.Date data = rs.getDate("DT_DATA");
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(data.getTime());
				EntityInterface dado = new DietaEntity(id, usuario, refeicao, calorias, dataCalendar);
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
	public boolean update(int id, EntityInterface entity) {
		DietaEntity dado = (DietaEntity) entity;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_HT_DIETA SET T_HT_USUARIO_CD_USUARIO = ?, T_HT_REFEICAO_CD_REFEICAO = ?, VL_CALORIAS = ?, DT_DATA = ? WHERE ID = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, dado.getUsuario().getCodigo());
			stmt.setInt(2, dado.getRefeicao().getCodigo());
			stmt.setDouble(3, dado.getCalorias());
			java.sql.Date data = new java.sql.Date(dado.getData().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.setInt(5, dado.getId());
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
			String sql = "DELETE FROM T_HT_DIETA WHERE ID = ?";
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
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_DIETA DI, T_HT_USUARIO US, T_HT_REFEICAO RE"
					+ " WHERE US.CD_USUARIO = DI.T_HT_USUARIO_CD_USUARIO"
					+ " AND RE.CD_REFEICAO = DI.T_HT_REFEICAO_CD_REFEICAO" + " AND DI.ID = ?");

			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				UsuarioEntity usuario = DAOUtil.getInstance().getUsuarioFromResultSet(rs);
				RefeicaoEntity refeicao = DAOUtil.getInstance().getRefeicaoFromResultSet(rs);
				double calorias = rs.getDouble("VL_CALORIAS");
				java.sql.Date data = rs.getDate("DT_DATA");
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(data.getTime());
				result = new DietaEntity(id, usuario, refeicao, calorias, dataCalendar);
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