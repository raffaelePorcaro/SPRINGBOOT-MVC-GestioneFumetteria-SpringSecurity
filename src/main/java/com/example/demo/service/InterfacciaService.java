package com.example.demo.service;

import java.util.List;

public interface InterfacciaService<T> {

	List<T> getAllObjects();
	
	T getObjectById(int id);
	
	void saveObject(T object);
	
	void deleteObjectById(int id);
	
}
