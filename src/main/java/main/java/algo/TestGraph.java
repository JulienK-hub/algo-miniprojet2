package main.java.algo;
import java.awt.*;

/**
 * A class to demonstrate the use of the graph methods
 */
public class TestGraph {
	
	/**
	 * Displays all information about the graph 'G'
	 * (directed or undirected) named 'name'
	 */
	private static void testGraphMethods(String name, GraphITF G) {
		System.out.println(name);
		System.out.println(G);

		System.out.println("\nVertices enumeration:");
		for ( VertexITF vertex :  G.vertices() )
			System.out.print(vertex.getTag() + " ");
		System.out.println();
		
		System.out.println("\nEdges enumeration:");
		for ( EdgeITF edge :  G.edges() )
			System.out.print("(" + edge.origin() + "," + edge.destination() + "," + ((edge.color().equals(Color.RED)) ? "RED" : "BLUE" )+") ");
		System.out.println();
		
		System.out.println("\nAdjacents enumeration:");
		for ( VertexITF vertex :  G.vertices() ) {
			System.out.print("Adjacents of vertex " + vertex + ": ");
			for ( VertexITF adjacent : G.adjacents(vertex) )
				System.out.print(adjacent + " ");
			System.out.println();
		}	
		
		System.out.println("\nIncidents enumeration:");
		for ( VertexITF vertex :  G.vertices() ) {
			System.out.println("Incident edges of vertex " + vertex + ":");
			for ( EdgeITF edge : G.incidents(vertex) )
				System.out.println("   " + edge + ", origin = " + edge.origin() + ", destination = " + edge.destination());
			System.out.println();
		}
		
		System.out.println("\nIn-degree of vertices:");
		for ( VertexITF vertex :  G.vertices() )
			System.out.println("in-degree(" + vertex + ") = " + G.inDegree(vertex));
		System.out.println();		
		
		System.out.println("\nOut-degree of vertices:");
		for ( VertexITF vertex :  G.vertices() )
			System.out.println("out-degree(" + vertex + ") = " + G.outDegree(vertex));
		System.out.println();
		
		System.out.println("\n(total) degree of vertices:");
		for ( VertexITF vertex :  G.vertices() )
			System.out.println("degree(" + vertex + ") = " + G.degree(vertex));
		System.out.println();
		
	}

	/**
	 * You can display information about graph here
	 */
	public static void main(String[] args) {
		testGraphMethods("D1:",GraphReader.D1Rem2);
		//// you can test more graphs here
	}
}
