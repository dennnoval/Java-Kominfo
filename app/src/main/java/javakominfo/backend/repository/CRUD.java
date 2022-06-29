package javakominfo.backend.repository;

import java.util.List;

public interface CRUD<T> {

	public void create(T t);

	public List<T> read();

	public T readById(String id);

	public void update(T t);

	public void delete(T t);

}
