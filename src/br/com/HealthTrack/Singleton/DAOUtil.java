package br.com.HealthTrack.Singleton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import br.com.HealthTrack.Entity.AtividadeFisicaEntity;
import br.com.HealthTrack.Entity.RefeicaoEntity;
import br.com.HealthTrack.Entity.UsuarioEntity;
import br.com.HealthTrack.Interface.EntityInterface;

public class DAOUtil {
	private static  DAOUtil instance;
	
	public static  DAOUtil getInstance() {
		if(instance == null) {
			instance = new  DAOUtil();
		}
		
		return instance;
	}
	
	public UsuarioEntity getUsuarioFromResultSet(ResultSet rs) throws SQLException {
		int codigo = rs.getInt("CD_USUARIO");
		String nome = rs.getString("NM_NOME");
		String sobrenome = rs.getString("NM_SOBRENOME");
		String email = rs.getString("DS_EMAIL");
		String celular = rs.getString("NR_CELULAR");
		
		java.sql.Date data = rs.getDate("DT_NASCIMENTO");
	    Calendar dataNascimento = Calendar.getInstance();
	    dataNascimento.setTimeInMillis(data.getTime());
	    
	    String senha = rs.getString("VL_SENHA");
	    boolean ativo = rs.getBoolean("ST_ATIVO");
	    
		return new UsuarioEntity(codigo, nome, sobrenome, email, celular, dataNascimento, senha, ativo);
	}
	
	public AtividadeFisicaEntity getAtividadeFisicaFromResultSet(ResultSet rs) throws SQLException {
		int codigo = rs.getInt("CD_ATIVIDADE");
		String descricao = rs.getString("DS_ATIVIDADE");

		return new AtividadeFisicaEntity(codigo, descricao);
	}
	
	public RefeicaoEntity getRefeicaoFromResultSet(ResultSet rs) throws SQLException {
		int codigo = rs.getInt("CD_REFEICAO");
		String descricao = rs.getString("DS_REFEICAO");

		return new RefeicaoEntity(codigo, descricao);
	}
}
