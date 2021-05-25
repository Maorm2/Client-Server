package com.hit.dao;

import java.io.IOException;
import java.io.Serializable;

import com.hit.dm.DataModel;

public interface IDao<ID extends Serializable, T> {

	void delete(ID id) throws IllegalArgumentException, IOException;

	String find(Serializable id) throws IllegalArgumentException, IOException;

	void save(DataModel<T> entity);

}
