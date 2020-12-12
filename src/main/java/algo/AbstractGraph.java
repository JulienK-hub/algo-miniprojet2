package main.java.algo;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * A class for abstract graph. An abstract graph is the common 
 * implementation between directed graph (DiGraph) and undirected
 * graph (UnDiGraph)
 */
public abstract class AbstractGraph implements GraphITF {

	// the adjacency list
	protected Map<VertexITF,List<VertexITF>> adjacencyList;
	// the number of edges
	protected int nbEdges;
	// the set of all tags
	private Map<String,VertexITF> tags;
	// the set of edges
	protected Map<VertexITF,Map<VertexITF,EdgeITF>> edges;

	/**
	 * Builds an abstract graph with no vertices
	 */
	public AbstractGraph() {
		adjacencyList = new HashMap<VertexITF,List<VertexITF>>();
		tags = new HashMap<String,VertexITF>();
		edges = new HashMap<VertexITF,Map<VertexITF,EdgeITF>>();
		nbEdges = 0;
	}

	/////////////// public methods ///////////////
	
	/**
	 * Returns the number of vertices
	 */
	public int nbVertices() {
		return adjacencyList.size();
	}

	/**
	 * Returns the number of edges
	 */
	public int nbEdges() {
		return nbEdges;
	}
	
	/**
	 * Add a new vertex of tag 'tag' to the graph
	 * and returns that vertex
	 * If 'tag' is already used in that graph, the
	 * method raises a DuplicateTagException exception
	 */
	public VertexITF addVertex(String tag,Color color) {
		if ( tags.containsKey(tag) )
			throw new DuplicateTagException(tag);
		InnerVertex v = new InnerVertex(this,tag,color);
		tags.put(tag, v);
		adjacencyList.put(v,new LinkedList<VertexITF>());
		return v;
	}
	
	/**
	 * Return the vertex of tag 'tag' from the graph
	 * If no vertex has tag 'tag', the method
	 * returns null
	 */
	public VertexITF getVertex(String tag) {
		if ( ! tags.containsKey(tag) )
			return null;
		return tags.get(tag);
	}
	
	
	/**	
	 * Returns a random vertex from the graph
	 */
	public VertexITF getRandomVertex() {
		return adjacencyList.keySet().iterator().next();
	}
	
	/**
	 * Adds the new edge ('u','v') to the graph unless
	 * this edge is already present in the graph
	 */
	public void addEdge(VertexITF u, VertexITF v) {
		checkVertex(u);
		checkVertex(v);
		addEdge(u,v,Color.BLACK);
	}

	/**
	 * Removes edge 'e' from the graph unless
	 * this edge is not present in the graph
	 */
	public void removeEdge(EdgeITF e) {
		removeEdge(e.origin(), e.destination());
	}
	
	/**
	 * Returns an iterable object over the vertices
	 * of the graph. The vertices come in random
	 * order
	 */
	public Iterable<VertexITF> vertices() {
		return adjacencyList.keySet();
	}
	
	/**
	 * Returns an iterable object over the edges
	 * of the graph. The edges come in random
	 * order
	 */		
	public Iterable<EdgeITF> edges() {
		return new EdgeIterator();
	}

	/**
	 * Returns an iterable object over the adjacent
	 * vertices of vertex 'u' in the graph. The adjacents
	 * come in random order
	 */
	public Iterable<VertexITF> adjacents(VertexITF u) {
		checkVertex(u);
		return adjacencyList.get(u);
	}

	/**
	 * Returns true if 'v' is adjacent to 'u' in
	 * the graph, false otherwise
	 */
	public boolean adjacents(VertexITF u, VertexITF v) {
		checkVertex(u);
		checkVertex(v);		
		for ( VertexITF ve : adjacencyList.get(u) )
			if ( ve == v )
				return true;
		return false;
	}

	/**
	 * Returns an iterable object over the incident
	 * edges of vertex 'u' in the graph. The incident
	 * edges come in random order. For all incident
	 * edge e, e.origin() = u
	 */
	public Iterable<EdgeITF> incidents(VertexITF u) {
		checkVertex(u);
		return new IncidentEdgeIterator(u);
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "number of verticies: " + nbVertices();
		s += "\nnumber of edges: " + nbEdges;
		s += "\nvertices:";
		for ( VertexITF u : vertices() )
			s += " " + u;
		s += "\nedges :";
		for ( EdgeITF e : edges() )
			s += " " + e;
		return s;
	}
	
	/////////////// public abstract methods ///////////////

	/**
	 * Returns the total degree of vertex 'u'
	 * Notice: for DiGraph, degree(v) = inDegree(v) + outDegree(v)
	 */
	public abstract int degree(VertexITF u);

	/**
	 * Adds the new edge ('u','v') with weight 'weight'
	 * to the graph unless this edge is already present
	 * in the graph
	 */
	public abstract void addEdge(VertexITF u, VertexITF v, Color color);

	/**
	 * Removes edge 'e' from the graph unless
	 * this edge is not present in the graph
	 */
	public abstract void removeEdge(VertexITF u, VertexITF v);
	
	/////////////// protected abstract methods ///////////////
	
	protected abstract EdgeITF findEdge(VertexITF u, VertexITF v);
	
	/////////////// protected methods ///////////////
	
	protected boolean add(VertexITF u, VertexITF v, Color color) {
		if ( adjacencyList.get(u).contains(v) )
			return false;
		adjacencyList.get(u).add(v);
		return true;
	}
	
	protected boolean remove(VertexITF u, VertexITF v) {
		if ( adjacencyList.get(u).contains(v) )
			return false;
		adjacencyList.get(u).remove(v);
		return false;
	}
	
	protected void checkVertex(VertexITF v) {
		if ( ((InnerVertex) v).fromGraph != this )
			throw new BadVertexException(v.getTag());
	}
	
	protected void storeEdge(VertexITF u , VertexITF v, Color color) {
		if ( edges.get(u) == null )
			edges.put(u, new HashMap<VertexITF,EdgeITF>());
		edges.get(u).put(v,new InnerEdge(u,v,color));
	}
	
	/////////////// private class and methods ///////////////

	// A class for vertices
	private class InnerVertex implements VertexITF {

		AbstractGraph fromGraph; // back link to the host graph
		String tag; // the tag of the vertex
		Color color; // the weight of the vertex (mainly for the Dijkstra algorithm)
		String id;
		// Builds a vertex of tag 'tag' and linked to the
		// graph 'fromGraph'
		InnerVertex(AbstractGraph fromGraph, String tag, Color color) {
			this.id = String.valueOf(new Random().nextInt(1000000));
			this.tag = tag;
			this.fromGraph = fromGraph;
			this.color = color;
		}

		/**
		 * Returns the tag of the vertex
		 */
		public String getTag() {
			return tag;
		}

		@Override
		public String getId() {
			return id;
		}

		/**
		 * Returns the weight of the vertex
		 */	
		public Color getColor() {
			return this.color;
		}

		/**
		 * Sets the weight of the vertex to 'weight'
		 */
		public void setColor(Color color) {
			this.color = color;
		}

		@Override
		public String toString() {
			return tag;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			InnerVertex that = (InnerVertex) o;
			return id.equals(that.id);
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}
	}

	// a class for edges
	private class InnerEdge implements EdgeITF {

		VertexITF x; // the origin of the edge
		VertexITF y; // the destination of the edge
		Color color; // the weight of the edge

		/**
		 * builds the edge (x,y,color)
		 */
		InnerEdge(VertexITF x, VertexITF y, Color color) {
			this.x = x;
			this.y = y;
			this.color = color;
		}

		/**
		 * Returns the origin of the edge
		 */
		public VertexITF origin() {
			return x;
		}

		/**
		 * Returns the destination of the edge
		 */
		public VertexITF destination() {
			return y;
		}

		/**
		 * Returns the weight of the edge
		 */		
		public Color color() {
			return color;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}

		@Override
		public boolean equals(Object o) {
			InnerEdge e = (InnerEdge) o;
			return x == e.x && y == e.y;
		}
	}
	
	// an inner class to build iterators over all edges
	private class EdgeIterator implements Iterable<EdgeITF>, Iterator<EdgeITF> {
		
		Iterator<Map.Entry<VertexITF,Map<VertexITF,EdgeITF>>> firstMapIterator;
		Iterator<Map.Entry<VertexITF,EdgeITF>> secondMapIterator;
		
		EdgeIterator() {
			firstMapIterator = edges.entrySet().iterator();
			if ( firstMapIterator.hasNext() ) {
				secondMapIterator = firstMapIterator.next().getValue().entrySet().iterator();
			}
				
		}
		
		public Iterator<EdgeITF> iterator() {
			return this;
		}

		public boolean hasNext() {
			return secondMapIterator.hasNext() || firstMapIterator.hasNext();
		}
		
		public EdgeITF next() {
			if ( ! secondMapIterator.hasNext() )
				secondMapIterator = firstMapIterator.next().getValue().entrySet().iterator();
			return secondMapIterator.next().getValue();
		}		
	}

	// an inner class to iterate over the incident edges
	private class IncidentEdgeIterator implements Iterable<EdgeITF>, Iterator<EdgeITF> {

		VertexITF origin;
		Iterator<VertexITF> adjacents;

		IncidentEdgeIterator(VertexITF u) {
			origin = u;
			adjacents = adjacencyList.get(u).iterator();
		}

		public Iterator<EdgeITF> iterator() {
			return this;
		}

		public boolean hasNext() {
			return adjacents.hasNext();
		}

		public EdgeITF next() {
			return findEdge(origin,adjacents.next());
		}

		public void remove() {
			throw new UnsupportedOperationException(); 
		}		
	}
}
