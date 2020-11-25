package br.com.HealthTrack.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.HealthTrack.Entity.*;
import br.com.HealthTrack.Interface.DAOInterface;
import br.com.HealthTrack.Interface.EntityInterface;
import br.com.HealthTrack.Singleton.ConnectionManager;
import br.com.HealthTrack.Singleton.DAOUtil;

public class PressaoDAO implements DAOInterface {
	private Connection conexao;
	
	@Override
	public boolean insert(EntityInterface entity) {
		PressaoEntity dado = (PressaoEntity) entity;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HT_PRESSAO "
					+ "(ID, T_HT_USUARIO_CD_USUARIO, VL_SISTOLICA, VL_DIASTOLICA, DT_DATA) " + "VALUES "
					+ "(SQ_DIETA.NEXTVAL, ?, ?, ?, ? )";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, dado.getUsuario().getCodigo());
			stmt.setDouble(2, dado.getSistolica());
			stmt.setDouble(3, dado.getDiastolica());
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
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_PRESSAO PR, T_HT_USUARIO US"
					+ " WHERE US.CD_USUARIO = PR.T_HT_USUARIO_CD_USUARIO");
			rs = stmt.executeQuery();

			while (rs.next()) {

				UsuarioEntity usuario = DAOUtil.getInstance().getUsuarioFromResultSet(rs);
				int id = rs.getInt("ID");
				double sistolica = rs.getDouble("VL_SISTOLICA");
				double diastolica = rs.getDouble("VL_DIASTOLICA");
				java.sql.Date data = rs.getDate("DT_DATA");
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(data.getTime());
				EntityInterface dado = new PressaoEntity(id, sistolica, diastolica, dataCalendar, usuario);
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
		PressaoEntity dado = (PressaoEntity) entity;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_HT_PRESSAO SET T_HT_USUARIO_CD_USUARIO = ?, VL_SISTOLICA = ?, VL_DIASTOLICA = ?, DT_DATA = ? WHERE ID = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, dado.getUsuario().getCodigo());
			stmt.setDouble(2, dado.getSistolica());
			stmt.setDouble(3, dado.getDiastolica());
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
			String sql = "DELETE FROM T_HT_PRESSAO WHERE ID = ?";
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
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_PRESSAO PR, T_HT_USUARIO US"
					+ " WHERE US.CD_USUARIO = PR.T_HT_USUARIO_CD_USUARIO"
					+ " AND PR.ID = ?");

			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				UsuarioEntity usuario = DAOUtil.getInstance().getUsuarioFromResultSet(rs);
				double sistolica = rs.getDouble("VL_SISTOLICA");
				double diastolica = rs.getDouble("VL_DIASTOLICA");
				java.sql.Date data = rs.getDate("DT_DATA");
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(data.getTime());
				result = new PressaoEntity(id, sistolica, diastolica, dataCalendar, usuario);
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
