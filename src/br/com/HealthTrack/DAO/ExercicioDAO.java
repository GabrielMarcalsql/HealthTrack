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
public class ExercicioDAO implements DAOInterface {
	private Connection conexao;

	@Override
	public boolean insert(EntityInterface entity) {
		ExercicioEntity dado = (ExercicioEntity) entity;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HT_EXEC "
					+ "(ID, T_HT_USUARIO_CD_USUARIO, T_HT_AT_FISICA_CD_ATIVIDADE, VL_TEMPO_EXEC, DT_DATA) " + "VALUES "
					+ "(SQ_EXEC.NEXTVAL, ?, ?, ?, ? )";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, dado.getUsuario().getCodigo());
			stmt.setInt(2, dado.getAtividade().getCodigo());
			stmt.setInt(3, dado.getTempoExecutado());
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
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_EXEC EX, T_HT_AT_FISICA AF, T_HT_USUARIO US "
					+ "WHERE AF.CD_ATIVIDADE = EX.T_HT_AT_FISICA_CD_ATIVIDADE "
					+ " AND US.CD_USUARIO = EX.T_HT_USUARIO_CD_USUARIO");
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				int tempoExec = rs.getInt("VL_TEMPO_EXEC");
				java.sql.Date data = rs.getDate("DT_DATA");
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(data.getTime());

				UsuarioEntity usuario = DAOUtil.getInstance().getUsuarioFromResultSet(rs);
				AtividadeFisicaEntity atividadeFisica = DAOUtil.getInstance().getAtividadeFisicaFromResultSet(rs);

				EntityInterface dado = new ExercicioEntity(id, usuario, atividadeFisica, tempoExec, dataCalendar);
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
		ExercicioEntity dado = (ExercicioEntity) entity;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_HT_EXEC SET T_HT_USUARIO_CD_USUARIO = ?, T_HT_AT_FISICA_CD_ATIVIDADE = ?, VL_TEMPO_EXEC = ?, DT_DATA = ? WHERE T_HT_EXEC = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, dado.getUsuario().getCodigo());
			stmt.setInt(2, dado.getAtividade().getCodigo());
			stmt.setInt(3, dado.getTempoExecutado());
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
			String sql = "DELETE FROM T_HT_EXEC WHERE ID = ?";
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
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_EXEC EX, T_HT_AT_FISICA AF, T_HT_USUARIO US "
					+ "WHERE AF.CD_ATIVIDADE = EX.T_HT_AT_FISICA_CD_ATIVIDADE "
					+ " AND US.CD_USUARIO = EX.T_HT_USUARIO_CD_USUARIO" + " AND EX.ID = ?");

			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int tempoExec = rs.getInt("VL_TEMPO_EXEC");
				java.sql.Date data = rs.getDate("DT_DATA");
				Calendar dataCalendar = Calendar.getInstance();
				dataCalendar.setTimeInMillis(data.getTime());

				UsuarioEntity usuario = DAOUtil.getInstance().getUsuarioFromResultSet(rs);
				AtividadeFisicaEntity atividadeFisica = DAOUtil.getInstance().getAtividadeFisicaFromResultSet(rs);

				result = new ExercicioEntity(id, usuario, atividadeFisica, tempoExec, dataCalendar);
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
