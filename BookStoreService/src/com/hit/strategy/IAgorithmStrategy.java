package com.hit.strategy;

public interface IAgorithmStrategy {

	boolean algoSearch(String pattern, String dataSource);

	 String getCurrentAlgo();

}
