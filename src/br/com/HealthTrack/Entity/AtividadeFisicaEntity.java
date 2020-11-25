/**
 * 
 */
package br.com.HealthTrack.Entity;

import br.com.HealthTrack.Interface.EntityInterface;

/**
 * Classe que abstrai uma atividade fisica
 * 
 * @author Andrea Serpeloni - aserpeloni@hotmail.com
 * @author Fernando Grieco Feres - fegferes@gmail.com
 * @author Gabriel Silva Marçal - gmarcal6@gmail.com
 * @author Herbert de Souza Souto - herbert-93@hotmail.com
 * @author Jaelson Apolinário de Oliveira - jaelson.apolinario@gmail.com
 * 
 * @version 1.0
 */
public class AtividadeFisicaEntity implements EntityInterface  {
	private int codigo;
	private String descricao;

	/**
	 * Cria uma instancia de AtividadeFisica
	 * 
	 * @param codigo    codigo da atividade
	 * @param descricao descricao da atividade
	 */
	public AtividadeFisicaEntity(int codigo, String descricao) {
		this.setCodigo(codigo);
		this.setDescricao(descricao);
	}
	
	public AtividadeFisicaEntity(String descricao) {
		this.setDescricao(descricao);
	}

	/**
	 * Retorna o codigo
	 * 
	 * @return codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Atribui valor ao codigo
	 * 
	 * @param codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna a descricao
	 * 
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Atribui valor a descricao
	 * 
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return this.getCodigo() +" - " +this.getDescricao();
	}
}
