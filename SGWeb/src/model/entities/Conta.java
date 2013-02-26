package model.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoReduzido;
	private String contaContabil;
	private String descricao;
	private String tipoConta;
	private String contaPai;

	public Long getCodigoReduzido() {
		return codigoReduzido;
	}

	public void setCodigoReduzido(Long codigoReduzido) {
		this.codigoReduzido = codigoReduzido;
	}

	public String getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(String contaContabil) {
		this.contaContabil = contaContabil;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getContaPai() {
		return contaPai;
	}

	public void setContaPai(String contaPai) {
		this.contaPai = contaPai;
	}

}