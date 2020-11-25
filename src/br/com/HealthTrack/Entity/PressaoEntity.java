package br.com.HealthTrack.Entity;

import java.util.Calendar;

import br.com.HealthTrack.Interface.EntityInterface;

public class PressaoEntity implements EntityInterface {
	private int id;
	private double sistolica;
	private double diastolica;
	private Calendar data;
	private UsuarioEntity usuario;
	
	public PressaoEntity(int id, double sistolica, double diastolica, Calendar data, UsuarioEntity usuario) {
		this.setId(id);
		this.setSistolica(sistolica);
		this.setDiastolica(diastolica);
		this.setData(data);
		this.setUsuario(usuario);
	}
	
	public PressaoEntity(double sistolica, double diastolica, Calendar data, UsuarioEntity usuario) {
		this.setId(id);
		this.setSistolica(sistolica);
		this.setDiastolica(diastolica);
		this.setData(data);
		this.setUsuario(usuario);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getSistolica() {
		return sistolica;
	}
	public void setSistolica(double sistolica) {
		this.sistolica = sistolica;
	}
	public double getDiastolica() {
		return diastolica;
	}
	public void setDiastolica(double diastolica) {
		this.diastolica = diastolica;
	}
	public UsuarioEntity getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	
}
