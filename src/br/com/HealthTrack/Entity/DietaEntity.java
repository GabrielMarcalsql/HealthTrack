package br.com.HealthTrack.Entity;

import java.util.Calendar;
import java.util.Date;
import br.com.HealthTrack.Interface.EntityInterface;

/**
 * Classe que abstrai uma dieta
 * @author Jaelson Apolinario
 * @version 1.0
 */
public class DietaEntity implements EntityInterface {
	private UsuarioEntity usuario;
	private RefeicaoEntity refeicao;
	private double calorias;
	private Calendar data;
	private int id;
	
	/**
	 * Cria uma instancia de Dieta
	 * @param usuario usuario que possui essa dieta
	 * @param refeicao descricao refeicao
	 * @param calorias quantidade de calorias da refeicao
	 * @param data data da criacao do dado
	 */
	public DietaEntity(int id, UsuarioEntity usuario, RefeicaoEntity refeicao, double calorias, Calendar data) {
		super();
		this.setId(id);
		this.setUsuario(usuario);
		this.setRefeicao(refeicao);
		this.setCalorias(calorias);
		this.setData(data);
	}
	
	public DietaEntity(UsuarioEntity usuario, RefeicaoEntity refeicao, double calorias, Calendar data) {
		super();
		this.setUsuario(usuario);
		this.setRefeicao(refeicao);
		this.setCalorias(calorias);
		this.setData(data);
	}
	
	/**
	 * Retorna o usuario
	 * @return the usuario
	 */
	public UsuarioEntity getUsuario() {
		return usuario;
	}
	
	/**
	 * Atribui um valor a usuario
	 * @param usuario the usuario to set
	 */
	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Retorna a refeicao
	 * @return the refeicao
	 */
	public RefeicaoEntity getRefeicao() {
		return refeicao;
	}
	
	/**
	 * Atribui um valor a refeicao
	 * @param refeicao
	 */
	public void setRefeicao(RefeicaoEntity refeicao) {
		this.refeicao = refeicao;
	}
	
	/**
	 * Retorna a calorias
	 * @return calorias
	 */
	public double getCalorias() {
		return calorias;
	}
	
	/**
	 * Atribui um valor a caloria
	 * @param calorias
	 */
	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}
	
	/**
	 * Retorna a data
	 * @return data
	 */
	public Calendar getData() {
		return data;
	}
	
	/**
	 * Atribui um valor a data
	 * @param data 
	 */
	public void setData(Calendar data) {
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}