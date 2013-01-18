package model.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MovimentoDeEntrada {

	@Id
	@GeneratedValue
	private Long codigo;
	@ManyToOne
	private Conta conta;
	@ManyToOne
	private NotaDeEntrada nota;
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

	public NotaDeEntrada getNota() {
		return nota;
	}

	public void setNota(NotaDeEntrada nota) {
		this.nota = nota;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
