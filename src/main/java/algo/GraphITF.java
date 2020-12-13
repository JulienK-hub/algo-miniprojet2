package main.java.algo;
import java.awt.*;

/**
 * An interface for graphs.
 * 
 * Notice that some methods have a different meaning depending on the
 * kind of graph they are applied to (directed or undirected graph).
 * 
 * Notice: for all methods taking Vertex as parameter, calling the method
 * with a vertex which doesn't belong to the graph will raise a
 * BadVertexException exception
 */
public interface GraphITF {
	
	/**
	 * Return the number of vertices
	 */
	public int nbVertices();
	
	/**
	 * Return the number of edges
	 */
	public int nbEdges();
	
	/**
	 * Add a new vertex of tag 'tag' to the graph
	 * and returns that vertex
	 * If 'tag' is already used in that graph, the
	 * method raises a DuplicateTagException exception
	 */
	public VertexITF addVertex(int tag, Color color);
	
	/**
	 * Return the vertex of tag 'tag' from the graph
	 * If no vertex has tag 'tag', the method
	 * returns null
	 */
	public VertexITF getVertex(int tag);
	
	/**
	 * Returns a random vertex from the graph
	 */
	public VertexITF getRandomVertex();

	/**
	 * Adds the new edge ('u','v') with weight 'weight'
	 * to the graph unless this edge is already present
	 * in the graph
	 */
	public void addEdge(VertexITF u, VertexITF v, Color color);
	
	/**
	 * Removes edge ('u','v') from the graph unless
	 * this edge is not present in the graph
	 */
	public void removeEdge(VertexITF u, VertexITF v);
	
	/**
	 * Removes edge 'e' from the graph unless
	 * this edge is not present in the graph
	 */
	public void removeEdge(EdgeITF e);
	
	/**
	 * Returns an iterable object over the vertices
	 * of the graph. The vertices come in random
	 * order
	 */
	public Iterable<VertexITF> vertices();
	
	/**
	 * Returns an iterable object over the edges
	 * of the graph. The edges come in random
	 * order
	 */	
	public Iterable<EdgeITF> edges();
	
	/**
	 * Returns an iterable object over the adjacent
	 * vertices of vertex 'u' in the graph. The adjacents
	 * come in random order
	 */
	public Iterable<VertexITF> adjacents(VertexITF u);
	
	/**
	 * Returns true if 'v' is adjacent to 'u' in
	 * the graph, false otherwise
	 */
	public boolean adjacents(VertexITF u, VertexITF v);
	
	/**
	 * Returns an iterable object over the incident
	 * edges of vertex 'u' in the graph. The incident
	 * edges come in random order. For all incident
	 * edge e, e.origin() = u
	 */
	public Iterable<EdgeITF> incidents(VertexITF u);
	
	/**
	 * Returns the total degree of vertex 'u'
	 * Notice: for DiGraph, degree(v) = inDegree(v) + outDegree(v)
	 */
	public int degree(VertexITF u);
	
	/**
	 * Returns the in-degree of vertex 'u'
	 * Notice: for UnDiGraph, inDegree(v) = degree(v)
	 */	
	public int inDegree(VertexITF u);
	
	/**
	 * Returns the out-degree of vertex 'u'
	 * Notice: for UnDiGraph, outDegree(v) = degree(v)
	 */		
	public int outDegree(VertexITF u);
	
}
