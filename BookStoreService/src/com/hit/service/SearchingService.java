package com.hit.service;

import java.io.IOException;

import com.hit.dao.DaoFileImpl;
import com.hit.strategy.AlgoContext;

public class SearchingService  {
	private AlgoContext algo;
	private static String filePath = "dataSource.json";
	private static DaoFileImpl<Object> idao;

	public SearchingService(AlgoContext algo) throws IOException {
		this.algo = algo;
		this.idao = new DaoFileImpl<>(filePath);
		this.filePath = idao.convertListToString();
	}


	public static DaoFileImpl<Object> instance() {
		return idao == null ? new DaoFileImpl<>(filePath) : idao;
	}

	public DaoFileImpl<Object> getDao() {
		return idao;
	}


	public String getAlgo() {
		return algo.currentAlgoRunning();
	}
	
	public void setAlgo(AlgoContext algo) {
		this.algo = algo;
	}
	
	public boolean find(String pattern) {
		return algo.algoSearch(pattern, filePath);
	}
	
}
