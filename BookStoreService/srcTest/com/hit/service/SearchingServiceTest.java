package com.hit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hit.algorithm.Context;
import com.hit.algorithm.IAlgoStringMatching;
import com.hit.algorithm.KMPStringMatching;
import com.hit.algorithm.SuffixTree;
import com.hit.dm.Book;
import com.hit.dm.DataModel;
import com.hit.strategy.AlgoContext;

class SearchingServiceTest { 

	private static String filePath = "dataSource.json";
	private static SearchingService kmpService;
	Book a = new Book("Romeo And Julia", "Mike Jhones", "Stanford", 1570, 550);
	Book b = new Book("Pinoccio", "John White", "Pinguin", 1980, 220);
	Book c = new Book("Blue Shark", "Anthony Shore", "Stanford", 1995, 300);

	@BeforeAll
	static void buildTheServices() throws IOException {
		kmpService = new SearchingService(new AlgoContext(new KMPStringMatching()));
		new SearchingService(new AlgoContext(new SuffixTree(filePath)));
	}

	@AfterEach
	void clearTheList() throws IOException {
		kmpService.getDao().clear();
	}


	@Test
	void saveToFileTest() throws IOException {
		UUID id1 = ServiceId.getInstance().generateId();
		UUID id2 = ServiceId.getInstance().generateId();
		UUID id3 = ServiceId.getInstance().generateId();

		DataModel<Object> input1 = new DataModel<Object>(a, id1);
		DataModel<Object> input2 = new DataModel<Object>(b, id2);
		DataModel<Object> input3 = new DataModel<Object>(c, id3);

		kmpService.getDao().save(input1);
		kmpService.getDao().save(input2);
		kmpService.getDao().save(input3);

		int size = kmpService.getDao().getListSize();

		assertEquals(3, size);
	}

	@Test
	void saveToFileNullTest() throws IOException {
		kmpService.getDao().save(null);
		int size = kmpService.getDao().getListSize();

		assertEquals(1, size);
	}

	
	@Test
	void nullByIdTest() {
		Assertions.assertThrows(NullPointerException.class, () -> kmpService.getDao().find(null));
	}


	@Test
	void deleteTest() throws IllegalArgumentException, IOException {
		
		UUID id1 = ServiceId.getInstance().generateId();
		UUID id2 = ServiceId.getInstance().generateId();
		UUID id3 = ServiceId.getInstance().generateId();

		DataModel<Object> input1 = new DataModel<Object>(a, id1);
		DataModel<Object> input2 = new DataModel<Object>(b, id2);
		DataModel<Object> input3 = new DataModel<Object>(c, id3);

		kmpService.getDao().save(input1);
		kmpService.getDao().save(input2);
		kmpService.getDao().save(input3);

		kmpService.getDao().delete(input1.id);

		int size = kmpService.getDao().getListSize();

		assertEquals(2, size);
	}

	@Test
	void deleteNotFoundTest() {
		UUID id = ServiceId.getInstance().generateId();

		DataModel<Object> input = new DataModel<Object>(b, id);
		SearchingService.instance().save(input);
		Assertions.assertThrows(IOException.class, () -> SearchingService.instance().delete(9999));
	}

	@Test
	void deleteNullTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> kmpService.getDao().delete(null));
	}

}
