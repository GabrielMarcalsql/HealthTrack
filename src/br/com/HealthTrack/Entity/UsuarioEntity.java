package br.com.HealthTrack.Entity;

import java.util.Calendar;

import br.com.HealthTrack.Interface.EntityInterface;

public class UsuarioEntity implements EntityInterface {
	private int codigo;
	private String nome;
	private String sobrenome;
	private String email;
	private String celular;
	private Calendar dtNascimento;
	private String senha;
	private boolean ativo;
	
	/**
	 * Cria uma instancia de Usuario
	 * @param codigo codigo do usuario
	 * @param nome nome do usuario
	 * @param sobrenome sobrenome do usuario
	 * @param email email do usuario
	 * @param celular numero de ceular do usuario
	 * @param dtNascimento data de nascimento
	 * @param senha senha para autenticacao
	 * @param ativo determina se usuario esta ativo
	 */
	public UsuarioEntity(int codigo, String nome, String sobrenome, String email, String celular, Calendar dtNascimento,
			String senha, boolean ativo) {
		this.codigo = codigo;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.celular = celular;
		this.dtNascimento = dtNascimento;
		this.senha = senha;
		this.ativo = ativo;
	}

	/**
	 * Retorna o codigo
	 * @return codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Atribui valor ao codigo
	 * @param codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Retorna o nome
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Atribui valor ao nome
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Retorna o sobrenome
	 * @return sobrenome
	 */
	public String getSobrenome() {
		return sobrenome;
	}
	
	/**
	 * Atribui valor ao sobrenome
	 * @param sobrenome
	 */
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	/**
	 * Retorna o email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Atribui valor ao email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Retorna o celular
	 * @return celular
	 */
	public String getCelular() {
		return celular;
	}
	
	/**
	 * Atribui valor ao celular
	 * @param celular
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	/**
	 * Retorna a senha
	 * @return senha
	 */
	public String getSenha() {
		return senha;
	}
	
	/**
	 * Atribui vaor a senha
	 * @param senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	/**
	 * Retorna a dtNascimento
	 * @return dtNascimento
	 */
	public Calendar getDtNascimento() {
		return dtNascimento;
	}
	
	/**
	 * Atribui valor a dtNascimento
	 * @param dtNascimento
	 */
	public void setDtNascimento(Calendar dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	
	/**
	 * Retorna isAtivo
	 * @return isAtivo
	 */
	public boolean isAtivo() {
		return ativo;
	}
	
	/**
	 * Atribui valor a ativo
	 * @param ativo
	 */
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
