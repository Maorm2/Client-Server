package com.hit.algorithm;

import com.hit.strategy.IAgorithmStrategy;

public class KMPStringMatching implements IAgorithmStrategy {
	private String pattern;
	private String dataSource;
	
	public KMPStringMatching(String pat, String txt) {
		this.pattern = pat;
		this.dataSource = txt;
	}
	public KMPStringMatching() {};
	
    boolean KMPSearch()
    {
        int M = pattern.length();
        int N = dataSource.length();
  
      
        int lps[] = new int[M];
        int j = 0; 
        computeLPSArray(pattern, M, lps);
  
        int i = 0; 
        while (i < N) {
            if (pattern.charAt(j) == dataSource.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                j = lps[j - 1];
                return true;
            }
  
            
            else if (i < N && pattern.charAt(j) != dataSource.charAt(i)) {
              
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
		return false;
    }
  
    void computeLPSArray(String pat, int M, int lps[])
    {
    
        int len = 0;
        int i = 1;
        lps[0] = 0; 
  
       
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else 
            {
              
                if (len != 0) {
                    len = lps[len - 1];
  
                }
                else 
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
    
	@Override
	public boolean algoSearch(String pattern, String dataSource) {
		KMPStringMatching kmp = new KMPStringMatching(pattern, dataSource);
		return pattern.length() == 36 ? kmp.KMPSearch() : false; // check if the length is match to UUID id
	}
	@Override
	public String getCurrentAlgo() {
		return ("Kmp algortihm is running");
	}
	
}