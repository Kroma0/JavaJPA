package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
/**
 * Clase per gestionar la persistència de les dades de les persones
 * @author adnan
 *
 */
public class TreballadorDAO {

	private EntityManager em;

	public TreballadorDAO(EntityManager em) {
		this.em = em;
	}
		
	public List<Treballador> getList() {
		//Case sensitive!!!
		return em.createQuery("select p from Treballador p", model.Treballador.class).getResultList();
	}

	public boolean save(Treballador treballador){

		EntityTransaction tx = em.getTransaction(); 
		tx.begin();
		
		if(em.find(Productes.class, treballador.getIdTreballador()) != null){
			em.merge(treballador);
		}else{
			em.persist(treballador); 
		}

		tx.commit();
		return true;
	}
	
	public boolean saveEntrada(Treballador treballador){
		java.util.Date entrada = treballador.getEntrada();
		java.sql.Date sqlentrada = new java.sql.Date(entrada.getTime());
		
		EntityTransaction tx = em.getTransaction(); 
		tx.begin();
		
		if(em.find(Treballador.class, treballador.getIdTreballador()) != null){
			em.merge(treballador);
		}else{
			em.persist(treballador); 
		}

		tx.commit();
		return true;
	}
	
	public boolean saveSortida(Treballador treballador){
		java.util.Date sortida = treballador.getSortida();
		java.sql.Date sqlsortida = new java.sql.Date(sortida.getTime());
		
		EntityTransaction tx = em.getTransaction(); 
		tx.begin();
		
		if(em.find(Treballador.class, treballador.getIdTreballador()) != null){
			em.merge(treballador);
		}else{
			em.persist(treballador); 
		}

		tx.commit();
		return true;
	}

	public boolean delete(Integer id){

		EntityTransaction tx = em.getTransaction(); 
		tx.begin();
		em.remove(em.find(Treballador.class, id));
		tx.commit();
		return true;
	}

	public Treballador find(Integer id){

		return em.find(Treballador.class, id);

	}

	public void showAll(){ 
		Query query = em.createNativeQuery("select * from treballador", model.Treballador.class);
		@SuppressWarnings("unchecked")
		List<Treballador> resultados = query.getResultList();

		for(Treballador p : resultados) { 
			p.toString();
		} 
	}
}

