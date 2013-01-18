package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.NotaDeSaida;

public class NotaDeSaidaRepository {

	private EntityManager manager;
	
	public NotaDeSaidaRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adicionar(NotaDeSaida nota){
		this.manager.persist(nota);
	}
	
	public void atualizar(NotaDeSaida nota){
		this.manager.merge(nota);
	}
	
	public void remove(Long codigo){
		NotaDeSaida nota = this.procura(codigo);
		this.manager.remove(nota);
	}

	public NotaDeSaida procura(Long codigo) {
		return this.manager.find(NotaDeSaida.class, codigo);
	}
	
	@SuppressWarnings("unchecked")
	public List<NotaDeSaida> getLista(){
		Query query = this.manager.createQuery("select x from NotaDeSaida x");
		return query.getResultList();
	}

}
