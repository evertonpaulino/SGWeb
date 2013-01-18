package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.MovimentoDeSaida;


public class MovimentoDeSaidaRepository extends GenericRepository<MovimentoDeSaida>{

	public MovimentoDeSaidaRepository(EntityManager manager) {
		super(manager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovimentoDeSaida> getLista() {
		Query query = this.getManager().createQuery("select x MovimentoDeSaida x"); 
		return query.getResultList();
	}

}
