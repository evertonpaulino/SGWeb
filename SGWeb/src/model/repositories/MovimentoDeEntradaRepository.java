package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.MovimentoDeEntrada;


public class MovimentoDeEntradaRepository extends GenericRepository<MovimentoDeEntrada>{

	public MovimentoDeEntradaRepository(EntityManager manager) {
		super(manager);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MovimentoDeEntrada> getLista() {
		Query query = this.getManager().createQuery("select x MovimentoDeEntrada x"); 
		return query.getResultList();
	}




}
