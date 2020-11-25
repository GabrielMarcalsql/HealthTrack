package br.com.HealthTrack.Entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import br.com.HealthTrack.Interface.EntityInterface;

public class ExercicioEntity implements EntityInterface {
	private int id;
	private UsuarioEntity usuario;
	private AtividadeFisicaEntity atividade;
	private int tempoExecutado;
	private Calendar data;
	
	/**
	 * Cria uma instancia de Exercicio
	 * @param usuario usuario que executou o exercicio
	 * @param atividade descricao atividade fisica
	 * @param tempoExecutado quantidade de tempo executado em minutos
	 * @param data data da criacao do dado
	 */
	public ExercicioEntity(int id, UsuarioEntity usuario, AtividadeFisicaEntity atividade, int tempoExecutado, Calendar data) {
		this.setId(id);
		this.setUsuario(usuario);
		this.setAtividade(atividade);
		this.setTempoExecutado(tempoExecutado);
		this.setData(data);
	}
	
	public ExercicioEntity( UsuarioEntity usuario, AtividadeFisicaEntity atividade, int tempoExecutado, Calendar data) {
		this.setUsuario(usuario);
		this.setAtividade(atividade);
		this.setTempoExecutado(tempoExecutado);
		this.setData(data);
	}
	
	/**
	 * Retorna o id
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Atribui um valor a id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retorna o usuario
	 * @return usuario
	 */
	public UsuarioEntity getUsuario() {
		return usuario;
	}

	/**
	 * Atribui um valor a usuario
	 * @param usuario
	 */
	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	/**
	 * Retorna a atividade
	 * @return atividade
	 */
	public AtividadeFisicaEntity getAtividade() {
		return atividade;
	}

	/**
	 * Atribui um valor a atividade
	 * @param atividade
	 */
	public void setAtividade(AtividadeFisicaEntity atividade) {
		this.atividade = atividade;
	}

	/**
	 * Retorna o tempoExecutado
	 * @return tempoExecutado
	 */
	public int getTempoExecutado() {
		return tempoExecutado;
	}

	/**
	 * Atribui um valor a tempoExecutado
	 * @param tempoExecutado 
	 */
	public void setTempoExecutado(int tempoExecutado) {
		this.tempoExecutado = tempoExecutado;
	}

	/**
	 * Retorna a data
	 * @return data
	 */
	public Calendar getData() {
		return data;
	}

	/**
	 * Atrobui um valor a data
	 * @param data
	 */
	public void setData(Calendar data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        String strDate = dateFormat.format(this.data.getTime());  
        
		return this.usuario.getNome() +", "+
				this.atividade.getDescricao() +", "+
				strDate +", "+
				this.tempoExecutado;
	}
}
