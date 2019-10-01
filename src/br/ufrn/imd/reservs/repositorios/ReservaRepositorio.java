package br.ufrn.imd.reservs.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrn.imd.reservs.dominio.Reserva;
import br.ufrn.imd.reservs.dominio.Usuario;

@Stateless
public class ReservaRepositorio {
	
	@PersistenceContext
	private EntityManager em;
	
	public Reserva adicionar(Reserva reserva) {
		if(reserva.getId() == 0) {
			em.persist(reserva);
		}else {
			em.merge(reserva);
		}
		return reserva;
	}
	
	public void remover(Reserva reserva) {
		reserva = em.find(Reserva.class, reserva.getId());
		em.remove(reserva);
	}
	
	@SuppressWarnings("unchecked")	
	public List<Reserva> listarMateriais(){
		return (List<Reserva>) em.createQuery("select m from Reserva m").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Reserva> buscarReservaPorUsuario(Usuario usuario){
		String jpa1 = "select m from Reserva m where m.usuario.id = :idUsuario";
		
		Query q = em.createQuery(jpa1);
		q.setParameter("idUsuario", usuario.getId());
		return q.getResultList();
	}
	
	public Reserva buscarReserva(String codigo) {
		String jpa1 = "select m from Reserva m where m.codigo = :codigo";
		
		Query q = em.createQuery(jpa1);
		q.setParameter("codigo",codigo);
		
		try {
			return (Reserva) q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
}
