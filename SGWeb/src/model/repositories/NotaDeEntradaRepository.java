package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.NotaDeEntrada;

public class NotaDeEntradaRepository {

	private EntityManager manager;
	
	public NotaDeEntradaRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adicionar(NotaDeEntrada nota){
		this.manager.persist(nota);
	}
	
	public void atualizar(NotaDeEntrada nota){
		this.manager.merge(nota);
	}
	
	public void remove(Long codigo){
		NotaDeEntrada nota = this.procura(codigo);
		this.manager.remove(nota);
	}

	public NotaDeEntrada procura(Long codigo) {
		return this.manager.find(NotaDeEntrada.class, codigo);
	}
	
	@SuppressWarnings("unchecked")
	public List<NotaDeEntrada> getLista(){
		Query query = this.manager.createQuery("select x from NotaDeEntrada x");
		return query.getResultList();
	}
}
