package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
/**
 * Clase per gestionar la persistència de les dades de les persones
 * @author adnan
 *
 */
public class ProductesDAO {

	private EntityManager em;

	public ProductesDAO(EntityManager em) {
		this.em = em;
	}
		
	public List<Productes> getList() {
		//Case sensitive!!!
		return em.createQuery("select p from productes p", model.Productes.class).getResultList();
	}

	public boolean save(Productes productes){

		EntityTransaction tx = em.getTransaction(); 
		tx.begin();
		
		if(em.find(Productes.class, productes.getIdProduct()) != null){
			em.merge(productes);
		}else{
			em.persist(productes); 
		}

		tx.commit();
		return true;
	}

	public boolean delete(Integer id){

		EntityTransaction tx = em.getTransaction(); 
		tx.begin();
		em.remove(em.find(Productes.class, id));
		tx.commit();
		return true;
	}

	public Productes find(Integer id){

		return em.find(Productes.class, id);

	}

	public void showAll(){ 
		Query query = em.createNativeQuery("select * from productes", model.Productes.class);
		@SuppressWarnings("unchecked")
		List<Productes> resultados = query.getResultList();

		for(Productes p : resultados) { 
			p.toString();
		} 
	}
}

