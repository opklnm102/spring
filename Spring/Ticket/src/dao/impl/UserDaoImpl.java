package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import model.User;
import dao.UserDao;

public class UserDaoImpl implements UserDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public User getUser(String name) {
		
		//유저명으로 유저를 취득
		List<User> userList = em.createQuery(" FROM User AS user WHERE user.name = :name ",User.class).setParameter("name",name).getResultList();
		
		if(userList.isEmpty()){
			return null;
		}
		
		return userList.get(0);
	}

	@Override
	public User getUser(Integer id) {
		//id로 유저를 취득
		return em.find(User.class, id);
	}

	@Override
	public void updateUser(User user) {
	
		//유저를 갱신
		em.merge(user);
	}

	@Override
	public void addUser(User user) {
		em.persist(user);
		
	}
}
