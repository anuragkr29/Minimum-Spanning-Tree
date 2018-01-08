package com.project5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
//Kruskals class
//
//
//******************PUBLIC OPERATIONS*********************
//List<Edge> readEdges --> returns the edges read from a file
//public int getNumVertices() --> returns number of vertices
//public int getIntValue(String edgeName) --> returns the mapped int value to an edge weight in the Map
//

/**
* Kruskals Class implements minimum spanning tree on an external file with adjacency list representation.
* of a graph
* @author Anurag kumar
*/
public class Kruskals {
	
	public List<Edge> readEdges(String filename){

		try {
			readEdgeList(filename);
		} catch (IOException e) {
			System.out.println("Unable to open file");
			System.exit(0);
		}
		return edges;
	}
	private void readEdgeList(String filename) throws IOException{
		
		FileReader fin = new FileReader(filename );
        BufferedReader bin = new BufferedReader( fin );
        
        vertexMapping = new HashMap<String, Integer>();
        String currentLine;
        while ((currentLine = bin.readLine()) != null) {
        	String[] tempStr = currentLine.split(",");
        	vertexMapping.put(tempStr[0], NUM_VERTICES++);
        }
        fin = new FileReader(filename );
        bin = new BufferedReader( fin );
        while ((currentLine = bin.readLine()) != null) {
        	String[] tempStr = currentLine.split(",");
        	for(int i=1;i<tempStr.length;i+=2)
        	{
        		if(vertexMapping.get(tempStr[0]) <= vertexMapping.get(tempStr[i]))
        		{
        			Edge temp = new Edge(tempStr[0], tempStr[i], Double.parseDouble(tempStr[i+1]));
        			edges.add(temp);
        		}
        	}
		}
		bin.close();
	}
	public int getNumVertices() {
		return NUM_VERTICES;
	}
	public void setNumVertices(int nUM_VERTICES) {
		NUM_VERTICES = nUM_VERTICES;
	}
	public int getIntValue(String edgeName){
		return vertexMapping.get(edgeName);
	}
	private List<Edge> edges = new ArrayList<>();
	private Map<String, Integer> vertexMapping;
	private int NUM_VERTICES = 0;
	
	
	
	public static void main(String[] args) throws IOException {
		
		Kruskals k = new Kruskals();
		List<Edge> edgeList = k.readEdges("data.csv");
		Queue<Edge> priorityQueue = new PriorityQueue<>(edgeList);
		int numVertices = k.getNumVertices();
		double totalSumOfDistanceInMST=0.0;
		DisjSets ds = new DisjSets(numVertices);
		List<Edge> mst = new ArrayList<>();
		
		while(mst.size() != numVertices-1){
			
			Edge e = priorityQueue.poll();
			int u = ds.find(k.getIntValue(e.getSrc()));
			int v = ds.find(k.getIntValue(e.getDest()));
			if(u != v){
				mst.add(e);
				ds.union(u, v);
			}
		}
		System.out.println("-----------The Edges of the Minimum Spanning Tree------------ ");
		System.out.println();
		for(int i=0;i<mst.size();i++){
			Edge e = mst.get(i);
			totalSumOfDistanceInMST += e.getDistance();
			System.out.println(e.getSrc() +" --"+ e.getDistance() + "--> " + e.getDest() );
		}
		System.out.println();
		System.out.println("------------------------------------------------------------");
		System.out.println();
		System.out.println("The sum of distances in the MST is : " + totalSumOfDistanceInMST);
	}

}
