package com.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.model.User;

@Service(value="userDao")
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public UserDaoImpl() {
		super();
	}

	public UserDaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public List<User> list() {
		@SuppressWarnings("unchecked")
		List<User> listUser = sessionFactory.openSession()
		.createCriteria(User.class)
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		System.out.println(sessionFactory.openSession());
		return listUser;
	}
	
	public static void main(String[] args) {
		UserDaoImpl list = new UserDaoImpl();
		System.out.println(list.list().get(0).getEmail());
	}

	public User get(int id) {
		String hql = "from User where id = " + id;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<User> listUser = query.list();
		
		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		return null;
	}

	public void saveOrUpdate(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		
	}

	public void delete(int id) {
		User userToDelete = new User();
		userToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(userToDelete);
		
	}

}
