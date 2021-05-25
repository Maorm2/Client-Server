
package com.hit.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.algorithm.Context;
import com.hit.algorithm.KMPStringMatching;
import com.hit.algorithm.KMPmatching;
import com.hit.algorithm.SuffixTree;
import com.hit.dm.DataModel;
import com.hit.service.Controller;
import com.hit.service.SearchingService;
import com.hit.strategy.AlgoContext;

public class DaoFileImpl<T> implements IDao<Serializable, T> {

	private Gson gson = new Gson();
	private String filepath;
	private Writer writer;
	private Reader reader;
	private static String FILE_PATH = "dataSource.json";
	
	// get the size of list
	public int getListSize() throws IOException {
		return getFileToList().size();
	}

	public DaoFileImpl(String filePath) {
		this.filepath = filePath;
	}

	// clear the list
	public void clear() throws IOException {
		writeToFile(new ArrayList<>());
	}

	public List<DataModel<T>> getData() throws IOException {
		return getFileToList();
	}

	// save data to the list
	@Override
	public void save(DataModel<T> entity) {
		try {
			List<DataModel<T>> list = getFileToList();
			list.add(entity);
			writeToFile(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// delete data from the list
	@Override
	public void delete(Serializable id) throws IllegalArgumentException, IOException {
		if (id == null) {
			throw new IllegalArgumentException();
		}
		List<DataModel<T>> list = getFileToList();
		int size = list.size();
		list.removeIf((x) -> x.id.equals(id));

		if (list.size() == size) {
			throw new IOException();
		}
		writeToFile(list);

	}

	// find the data in list
	@Override
	public String find(Serializable id) throws IllegalArgumentException, IOException {
		SearchingService service;
		Random r = new Random();
		if(r.nextBoolean()) {
			service = new SearchingService(new AlgoContext(new KMPStringMatching()));
			System.out.println("kmp");
		}
		else {
			service = new SearchingService(new AlgoContext(new SuffixTree()));
			System.out.println("suffix");
		}
		
		return service.find(id.toString()) ? "The Book is found" : "Not found book that match to this id";

	}

	// convert the list to String so the searching algorithms
	// will use this as input of data source
	public String convertListToString() throws IOException {
		
		List<DataModel<T>> dataList = getFileToList();
		List<String> strings = new ArrayList<>(dataList.size());
		
		for (Object object : dataList) {
		    strings.add(Objects.toString(object, null));
		}
		
		StringBuilder sb = new StringBuilder();
		for (String s : strings)
		{
		    sb.append(s);
		    sb.append("\t");
		}
		
		return sb.toString();

	}
	
	private synchronized List<?> readFileToList(Type type) throws IOException {
		List<?> list = null;

		try {
		reader = new FileReader(FILE_PATH);
		list = gson.fromJson(reader, type);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
		reader.close();
		}
		
		if (list == null) {
			list = new ArrayList<>();
		}
		return list;
	}
	
	private synchronized void writeToFile(List<?> list) throws IOException {
		try {
		writer = new FileWriter(filepath);
		gson.toJson(list, writer);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			writer.close();
		}
	}
	
	public List<DataModel<T>> getFileToList() throws IOException {

		Type type = new TypeToken<List<DataModel<T>>>() {
		}.getType();
		return (List<DataModel<T>>) readFileToList(type);
	}

}
