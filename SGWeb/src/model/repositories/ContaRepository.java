package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.Conta;

public class ContaRepository {

	private EntityManager manager;
	
	public ContaRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adiciona(Conta conta){
		this.manager.persist(conta);
	}
	
	public void remove(Long codigoReduzido){
		Conta conta = this.procura(codigoReduzido);
		this.manager.remove(conta);
	}
	
	public void atualiza(Conta conta){
		this.manager.merge(conta);
	}

	public Conta procura(Long codigoReduzido) {
		return this.manager.find(Conta.class, codigoReduzido);
	}
	
	public List<Conta> getLista(){
		Query query = this.manager.createQuery("select x from Conta x");
		return query.getResultList();
	}
}
