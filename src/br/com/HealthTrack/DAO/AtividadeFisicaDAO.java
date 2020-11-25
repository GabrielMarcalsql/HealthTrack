package br.com.HealthTrack.DAO;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.HealthTrack.Entity.*;
import br.com.HealthTrack.Interface.DAOInterface;
import br.com.HealthTrack.Interface.EntityInterface;
import br.com.HealthTrack.Singleton.ConnectionManager;

/**
 * Classe DAO da entidade AtividadeFisicaEntity
 * 
 * @author Andrea Serpeloni - aserpeloni@hotmail.com
 * @author Fernando Grieco Feres - fegferes@gmail.com
 * @author Gabriel Silva Marçal - gmarcal6@gmail.com
 * @author Herbert de Souza Souto - herbert-93@hotmail.com
 * @author Jaelson Apolinário de Oliveira - jaelson.apolinario@gmail.com
 * 
 * @version 1.0
 */
public class AtividadeFisicaDAO implements DAOInterface {

	private Connection conexao;

	/**
	 * Cria uma instancia de AtividadeFisicaDAO
	 */
	public AtividadeFisicaDAO() {
	}

	/**
	 * Retorna todos os itens
	 * 
	 * @return ArrayList lista de itens do DAO
	 */
	public List<EntityInterface> getAll() {
		List<EntityInterface> lista = new ArrayList<EntityInterface>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT CD_ATIVIDADE, DS_ATIVIDADE FROM T_HT_AT_FISICA");
			rs = stmt.executeQuery();

			while (rs.next()) {
				int codigo = rs.getInt("CD_ATIVIDADE");
				String descricao = rs.getString("DS_ATIVIDADE");

				EntityInterface dado = new AtividadeFisicaEntity(codigo, descricao);
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
		AtividadeFisicaEntity dado = (AtividadeFisicaEntity) entity;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HT_AT_FISICA (CD_ATIVIDADE, DS_ATIVIDADE) VALUES (SQ_AT_FISICA.NEXTVAL, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, dado.getDescricao());

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
		AtividadeFisicaEntity dado = (AtividadeFisicaEntity) entity;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_HT_AT_FISICA SET DS_ATIVIDADE = ? WHERE CD_ATIVIDADE = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, dado.getDescricao());
			stmt.setInt(2, id);
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
			String sql = "DELETE FROM T_HT_AT_FISICA WHERE CD_ATIVIDADE = ?";
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
			stmt = conexao.prepareStatement("SELECT CD_ATIVIDADE, DS_ATIVIDADE FROM T_HT_AT_FISICA WHERE CD_ATIVIDADE = ?");
			stmt.setInt(1,id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int codigo = rs.getInt("CD_ATIVIDADE");
				String descricao = rs.getString("DS_ATIVIDADE");

				result = new AtividadeFisicaEntity(codigo, descricao);
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
	
	public AtividadeFisicaEntity findByName(String name) {
		AtividadeFisicaEntity result = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT CD_ATIVIDADE, DS_ATIVIDADE FROM T_HT_AT_FISICA WHERE DS_ATIVIDADE = ?");
			stmt.setString(1, name);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int codigo = rs.getInt("CD_ATIVIDADE");
				String descricao = rs.getString("DS_ATIVIDADE");

				result = new AtividadeFisicaEntity(codigo, descricao);
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
