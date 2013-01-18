package model.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MovimentoDeSaida {

	@Id
	@GeneratedValue
	private Long codigo;
	@ManyToOne
	private Conta conta;
	@ManyToOne
	private NotaDeSaida nota;
	private BigDecimal valor;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public NotaDeSaida getNota() {
		return nota;
	}

	public void setNota(NotaDeSaida nota) {
		this.nota = nota;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
