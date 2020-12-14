package graph.main;

import java.awt.*;
import java.util.*;

/**
 * A class for directed graph
 */
public class DiGraph extends AbstractGraph {

	// inDegree[u] is the in-degree of u	
	private Map<VertexITF,Integer> inDegree;
	
	/**
	 * builds a directed graph with no vertex
	 */
	public DiGraph() {
		super();
		inDegree = new HashMap<VertexITF,Integer>();
	}
	
	@Override
	public VertexITF addVertex(String tag,Color color) {
		VertexITF v = super.addVertex(tag,color);
		inDegree.put(v,0);
		return v;
	}
		
	@Override
	public void addEdge(VertexITF u, VertexITF v, Color color) {
		checkVertex(u);
		checkVertex(v);		
		if ( add(u,v,color) ) {
			storeEdge(u,v,color);
			nbEdges++;
			inDegree.put(v,inDegree.get(v)+1);
		}
	}

	@Override
	public void removeEdge(VertexITF u, VertexITF v) {
		checkVertex(u);
		checkVertex(v);		
		if ( remove(u,v) ) {
			nbEdges--;
			inDegree.put(v,inDegree.get(v)-1);
		}
	}
	
	@Override
	public int degree(VertexITF u) {
		checkVertex(u);
		return outDegree(u) + inDegree(u);
	}

	/**
	 * returns the in-degree of u
	 */	
	public int inDegree(VertexITF u) {
		checkVertex(u);
		return inDegree.get(u);
	}
	
	/**
	 * returns the out-degree of u
	 */
	public int outDegree(VertexITF u) {
		checkVertex(u);
		return adjacencyList.get(u).size();
	}
	
	@Override
	public String toString() {
		return "Directed Graph\n" + super.toString();
	}

	@Override
	protected EdgeITF findEdge(VertexITF u, VertexITF v) {
		return edges.get(u).get(v);
	}
}
