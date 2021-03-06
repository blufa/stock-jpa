package sn.isi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import sn.isi.entities.User;

public class UserImpl implements IUser {

	private EntityManager em;
	
	public UserImpl() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASuiviStock");
		em = emf.createEntityManager();
	}
	@Override
	public int add(User u) {
		try {
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int update(User u) {
		try {
			em.getTransaction().begin();
			em.merge(u);
			em.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(int id) {
		try {
			em.getTransaction().begin();
			em.remove(em.find(User.class, id));
			em.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<User> getProduit(String mc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getProduitById(int id) {
		
		return em.find(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> lister() {
		try {
			return em.createQuery("select u from User u").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public User logon(String email, String password) {
		try {
			return (User)em.
					createQuery("SELECT u FROM User u WHERE u.email = :em AND u.password = :pass")
					.setParameter("em", email)
					.setParameter("pass", password)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
