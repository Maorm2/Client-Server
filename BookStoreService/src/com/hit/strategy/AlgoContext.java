package com.hit.strategy;

public class AlgoContext {
	
		 private IAgorithmStrategy algoStratergy;
		 
		
		public AlgoContext(IAgorithmStrategy algoStratergy) {
			 this.algoStratergy = algoStratergy;
		 }
		 
		 public String currentAlgoRunning() {
			return algoStratergy.getCurrentAlgo();
		 }

		public boolean algoSearch(String pattern, String filePath) {
			return algoStratergy.algoSearch(pattern, filePath);
			
		}
	
}
