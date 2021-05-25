package com.hit.algorithm;

import java.util.Map;

import com.hit.strategy.IAgorithmStrategy;

import java.util.HashMap;

public class SuffixTree implements IAgorithmStrategy{
 class Node{
   private final char currentValue;
   private Map<Character,Node> children;
   Node(){
     this.currentValue = '*';
     this.children = new HashMap<Character,Node>();
   }    
   Node(char currentValue){
     this.currentValue = currentValue;
     this.children = new HashMap<Character,Node>();
   }       

   char getValue(){
     return this.currentValue;
   }

   void addChild(Node c){
    this.children.put(c.getValue(),c);
   }

   boolean hasChild(Node c){
    return this.children.containsKey(c.getValue());
   }  

   Node getChild(Node c){
     return this.children.get(c.getValue());
   }

  public String toString(){
    char currentValue = this.getValue();
    StringBuffer keys = new StringBuffer();
    for(char key:this.children.keySet()){
      keys.append("Current key:"+key+"\n");
    }
     return "Current value:"+currentValue+"\n"+
        "Keys:"+keys.toString(); 
   }
  }  

  private Node root;

  private void log(Object l){
     System.out.println(l);
  }
  /*
   * Helper method that initializes the suffix tree
   */
  private Node createSuffixTree(String source,Node root){
    for(int i=0;i<source.length();i++){
      Node parent = new Node(source.charAt(i));
      if(root.hasChild(parent)){
        parent = root.getChild(parent);
      }
      Node current = parent;            
      for(int j=i+1;j<source.length();j++){
        Node temp = new Node(source.charAt(j));
    if(current.hasChild(temp)){
      temp = current.getChild(temp);
    }else{
      current.addChild(temp);
    }
        current = temp; 
      }
      root.addChild(parent);    
    }
    return root;         
  }

  /*
   Creates the suffix tree from the given string
   */
  public SuffixTree(String source){
    this.root = createSuffixTree(source,new Node());
  }   
  
  public SuffixTree() {};


void printMap(Map<Character,Node> map){
     for(char c:map.keySet()){
      System.out.println("Current node has child"+c);
    }
  }  

  boolean search(String target){
    Map<Character,Node> rootChildren = this.root.children;
   for(char c:target.toCharArray()){
      if(rootChildren.containsKey(c)){
        rootChildren = rootChildren.get(c).children;
      }else{
        return false;
      }
    }
  	   
   return true;
	  
   
  
    
  }

@Override
public boolean algoSearch(String pattern, String dataSource) {
	   SuffixTree sTree = new SuffixTree(dataSource);
	   return pattern.length() == 36 ? sTree.search(pattern) : false; // check if the length is match to UUID id
}

public String getCurrentAlgo() {
	return ("Suffix tree algorithm is running");

}
}
