package br.com.HealthTrack.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.HealthTrack.Entity.*;
import br.com.HealthTrack.Interface.*;
import br.com.HealthTrack.Singleton.ConnectionManager;
import br.com.HealthTrack.Singleton.DAOUtil;

public class UsuarioDAO implements DAOInterface {
	private Connection conexao;

	@Override
	public boolean insert(EntityInterface entity) {
		UsuarioEntity dado = (UsuarioEntity) entity;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HT_USUARIO (CD_USUARIO, NM_NOME, NM_SOBRENOME, DS_EMAIL, NR_CELULAR, DT_NASCIMENTO, VL_SENHA, ST_ATIVO)"
					+ " VALUES(SQ_USUARIO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, dado.getNome());
			stmt.setString(2, dado.getSobrenome());
			stmt.setString(3, dado.getEmail());
			stmt.setString(4, dado.getCelular());
			java.sql.Date data = new java.sql.Date(dado.getDtNascimento().getTimeInMillis());
			stmt.setDate(5, data);
			stmt.setString(6, dado.getSenha());
			stmt.setInt(7, (dado.isAtivo() ? 1 : 0));
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
			stmt = conexao.prepareStatement("SELECT CD_USUARIO, NM_NOME, NM_SOBRENOME, DS_EMAIL, NR_CELULAR, DT_NASCIMENTO, VL_SENHA, ST_ATIVO FROM T_HT_USUARIO");
			rs = stmt.executeQuery();

			while (rs.next()) {
				EntityInterface dado = DAOUtil.getInstance().getUsuarioFromResultSet(rs);
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
		UsuarioEntity dado = (UsuarioEntity) entity;
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_HT_USUARIO SET NM_NOME = ?, NM_SOBRENOME = ?, DS_EMAIL = ?, NR_CELULAR = ?, DT_NASCIMENTO = ?, VL_SENHA = ?, ST_ATIVO = ? WHERE CD_USUARIO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, dado.getNome());
			stmt.setString(2, dado.getSobrenome());
			stmt.setString(3, dado.getEmail());
			stmt.setString(4, dado.getCelular());
			java.sql.Date data = new java.sql.Date(dado.getDtNascimento().getTimeInMillis());
			stmt.setDate(5, data);
			stmt.setString(6, dado.getSenha());
			stmt.setInt(7, dado.isAtivo() ? 1 : 0);
			stmt.setInt(8, id);
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
	public boolean delete(int id){
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_HT_USUARIO WHERE CD_USUARIO = ?";
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
			stmt = conexao.prepareStatement("SELECT CD_USUARIO, NM_NOME, NM_SOBRENOME, DS_EMAIL, NR_CELULAR, DT_NASCIMENTO, VL_SENHA, ST_ATIVO FROM T_HT_USUARIO"
					+ " WHERE CD_USUARIO = ?");
			
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				result = DAOUtil.getInstance().getUsuarioFromResultSet(rs);
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
	
	public UsuarioEntity doLogin(String email, String password) {
		UsuarioEntity result = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT CD_USUARIO, NM_NOME, NM_SOBRENOME, DS_EMAIL, NR_CELULAR, DT_NASCIMENTO, VL_SENHA, ST_ATIVO FROM T_HT_USUARIO"
					+ " WHERE DS_EMAIL = ? AND VL_SENHA = ?");
			
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();

			if (rs.next()) {
				result = DAOUtil.getInstance().getUsuarioFromResultSet(rs);
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
