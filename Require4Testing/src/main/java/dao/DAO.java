package dao;
import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class DAO<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private Class<T> type;

	protected EntityManager entityManager = Persistence.createEntityManagerFactory("require4testing").createEntityManager();
	
	public void setType(Class<T> type) {
        this.type = type;
    }

	@SuppressWarnings("unchecked")
	public List<T> getElements() {
		return (List<T>)entityManager.createQuery("select e from %s as e".formatted(type.getName())).getResultList();
	}

	public T getById(int id) {
		return entityManager.find(type, id);
	}

	public void add(T element) {
		EntityTransaction t = entityManager.getTransaction();
		try {
			t.begin();
			entityManager.persist(element);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
	}

	public void update(T element) {
		EntityTransaction t = entityManager.getTransaction();
		try {
			t.begin();
			entityManager.merge(element);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}

	}

	public void delete(T element) {
		EntityTransaction t = entityManager.getTransaction();
		try {
			t.begin();
			entityManager.remove(entityManager.merge(element));
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
	}
}
