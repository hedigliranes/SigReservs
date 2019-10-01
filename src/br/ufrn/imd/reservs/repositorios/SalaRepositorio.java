package br.ufrn.imd.reservs.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrn.imd.reservs.dominio.Sala;

@Stateless
public class SalaRepositorio {
	
	@PersistenceContext
	private EntityManager em;
	
	public Sala adicionar(Sala sala) {
		if(sala.getId() == 0) {
			em.persist(sala);
		}else {
			em.merge(sala);
		}
		return sala;
	}
	
	public void remover(Sala sala) {
		sala = em.find(Sala.class, sala.getId());
		em.remove(sala);
	}
	
	@SuppressWarnings("unchecked")	
	public List<Sala> listarMateriais(){
		return (List<Sala>) em.createQuery("select m from Sala m").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Sala> buscaSalaPorCodigo(Sala sala){
		String jpa1 = "select m from Sala m where m.sala.id = :idSala";
		
		Query q = em.createQuery(jpa1);
		q.setParameter("idSala", sala.getId());
		return q.getResultList();
	}
	
	public Sala buscarSala(String codigo) {
		String jpa1 = "select m from Sala m where m.codigo = :codigo";
		
		Query q = em.createQuery(jpa1);
		q.setParameter("codigo",codigo);
		
		try {
			return (Sala) q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
}
