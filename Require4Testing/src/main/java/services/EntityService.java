package services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import dao.DAO;
import jakarta.faces.model.DataModel;
import jakarta.faces.model.ListDataModel;
import jakarta.inject.Inject;

public abstract class EntityService<T> {
	@Inject
	protected DAO<T> dao;

	private Class<T> type;

	protected List<T> elements;
	
	public EntityService(Class<T> type) {
		this.type = type;
	}

	public T add() {
		T element;
		try {
			element = type.getDeclaredConstructor().newInstance();
			getElements().add(element);
			dao.add(element);
			return element;
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public T add(T element) {
		getElements().add(element);
		dao.add(element);
		return element;
	}

	public T getById(int id) {
		return dao.getById(id);
	}

	public List<T> getElements() {
		if (elements == null)
			elements = dao.getElements();
		return elements;
	}

	public void setElements(List<T> elements) {
		this.elements = elements;
	}

	public void update(T element) {
		dao.update(element);
	}

	public void delete(T element) {
		getElements().remove(element);
		dao.delete(element);
	}
}
