package br.com.HealthTrack.Entity;
import br.com.HealthTrack.Interface.EntityInterface;

/**
 * Classe que abstrai uma refeicao
 * @author Jaelson Apolinario
 * @version 1.0
 */
public class RefeicaoEntity implements EntityInterface {

	private int codigo;
	private String descricao;
	
	/**
	 * Cria uma instancia de Refeicao
	 * @param codigo codigo da refeicao
	 * @param descricao descricao da refeicao
	 */
	public RefeicaoEntity(int codigo, String descricao) {
		super();
		this.setCodigo(codigo);
		this.setDescricao(descricao);
	}
	
	public RefeicaoEntity(String descricao) {
		super();
		this.setDescricao(descricao);
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
	 * Retorna a descricao
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Atribui valor a descricao
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}