package br.com.HealthTrack.Entity;

import java.util.Calendar;
import java.util.Date;
import br.com.HealthTrack.Interface.EntityInterface;

/**
 * Classe que abstrai uma anatomia
 * @author Jaelson Apolinario
 * @version 1.0
 */
public class AnatomiaEntity implements EntityInterface {
	private int id;
	private UsuarioEntity usuario;
	private double altura;
	private double peso;
	private Calendar data;
	
	/**
	 * Cria uma instancia de Anatomia
	 * @param usuario usuario que possui essa anatomia
	 * @param altura altura do usuario
	 * @param peso peso do usuario
	 * @param data data do momento da criacao do dado
	 */
	public AnatomiaEntity(int id, UsuarioEntity usuario, double altura, double peso, Calendar data) {
		super();
		this.setId(id);
		this.setUsuario(usuario);
		this.setAltura(altura);
		this.setPeso(peso);
		this.setData(data);
	}
	
	public AnatomiaEntity( UsuarioEntity usuario, double altura, double peso, Calendar data) {
		super();
		this.setUsuario(usuario);
		this.setAltura(altura);
		this.setPeso(peso);
		this.setData(data);
	}

	/**
	 * Retorna o usuario
	 * @return usuario
	 */
	public UsuarioEntity getUsuario() {
		return usuario;
	}

	/**
	 * Atribui um valor para usuario
	 * @param usuario
	 */
	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	/**
	 * Retorna a altura
	 * @return altura
	 */
	public double getAltura() {
		return altura;
	}

	/**
	 * Atribui um valor a altura
	 * @param alturat
	 */
	public void setAltura(double altura) {
		this.altura = altura;
	}

	/**
	 * Retorna o peso
	 * @return peso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * Atribui um valor a peso
	 * @param peso
	 */
	public void setPeso(double peso) {
		this.peso = peso;
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