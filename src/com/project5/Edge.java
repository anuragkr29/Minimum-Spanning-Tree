package com.project5;
//Edge class
//
//CONSTRUCTION: with name of source and destination and the weight of the edge
//
//******************ERRORS********************************
//No error checking is performed

/**
* Edge class used to denote an edge in a graph with source , desination and weight of the edge.
* @author Anurag Kumar
*/

public class Edge implements Comparable<Edge>{
		private String src;
		private String dest;
		private double distance;
		Edge(String s , String d , double distan){
			src = s;
			dest = d;
			distance = distan;
		}
		public String getSrc() {
			return src;
		}
		public void setSrc(String src) {
			this.src = src;
		}
		public String getDest() {
			return dest;
		}
		public void setDest(String dest) {
			this.dest = dest;
		}
		public double getDistance() {
			return distance;
		}
		public void setDistance(double weight) {
			this.distance = weight;
		}
		public int compareTo(Edge o) {
	        Edge e1 = (Edge)o;
	        if(e1.distance==this.distance)
	            return 0;
	        return e1.distance < this.distance ? 1 : -1;
	    }
}
