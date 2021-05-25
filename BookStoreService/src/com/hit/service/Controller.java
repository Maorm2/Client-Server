package com.hit.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.hit.dao.DaoFileImpl;
import com.hit.dm.DataModel;

public class Controller<T> implements Runnable {

	private static Controller service;
	private String filePath = "dataSource.json";
	private DaoFileImpl<T> dao = new DaoFileImpl<T>(filePath);

	private Controller() {
	}

	public static Controller getInstance() {
		if (service == null)
			service = new Controller();

		return service;
	}

	@Override
	public void run() {
		service = new Controller();
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public List<DataModel<T>> getData() throws IOException {
		return readFileToList();
	}
	
	public String find(Serializable id) throws IllegalArgumentException, IOException {
		return dao.find(id);
	}
	
	private List<DataModel<T>> readFileToList() throws IOException {
		return dao.getFileToList();
	}

	public void save(DataModel<T> data) {
		dao.save(data);
	}

	public void delete(UUID id) throws IllegalArgumentException, IOException {
		dao.delete(id);
	}

}