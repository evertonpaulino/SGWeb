package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;

public abstract class GenericRepository<T>{

	private EntityManager manager;
	
	public GenericRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adicionar(T entity){
		this.manager.persist(entity);
	}
	
	public void atualizar(T entity){
		this.manager.merge(entity);
	}
	
	public void remove(Long codigo){
		T entity = this.procura(codigo);
		this.manager.remove(entity);
	}

	public T procura(Long codigo) {
		Class<T> c = null;
		return this.manager.find(c, codigo);
	}
	
	public abstract List<T> getLista();
	
	public EntityManager getManager(){
		return this.manager;
	}

}
