package main.java.algo;


import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * A class for directed graph
 */
public class DiGraph extends AbstractGraph {

    // inDegree[u] is the in-degree of u
    private Map<VertexITF, Integer> inDegree;

    /**
     * builds a directed graph with no vertex
     */
    public DiGraph() {
        super();
        inDegree = new HashMap<VertexITF, Integer>();
    }

    @Override
    public VertexITF addVertex(String tag, Color color) {
        VertexITF v = super.addVertex(tag, color);
        inDegree.put(v, 0);
        return v;
    }

    @Override
    public void addEdge(VertexITF u, VertexITF v, Color color) {
        checkVertex(u);
        checkVertex(v);
        if (add(u, v, color)) {
            storeEdge(u, v, color);
            nbEdges++;
            inDegree.put(v, inDegree.get(v) + 1);
        }
    }

    @Override
    public void removeEdge(VertexITF u, VertexITF v) {
        checkVertex(u);
        checkVertex(v);
        if (remove(u, v)) {
            nbEdges--;
            inDegree.put(v, inDegree.get(v) - 1);
        }
    }

    @Override
    public boolean removeVertex(VertexITF v) {
        for (Iterator<Map<VertexITF, EdgeITF>> iter = edges.values().iterator(); iter.hasNext(); ) {
            Map<VertexITF, EdgeITF> m2 = iter.next();
            for (Iterator<EdgeITF> es = m2.values().iterator(); es.hasNext(); ) {
                EdgeITF edge = es.next();
                edge.destination().setColor(edge.color());
                if (edge.destination().equals(v)) {
                    es.remove();
                    for (Iterator<Map.Entry<VertexITF, List<VertexITF>>> adr = adjacencyList.entrySet().iterator(); adr.hasNext(); ){
                        Map.Entry<VertexITF, List<VertexITF>> cur = adr.next();
                        cur.getValue().removeIf(vert -> vert.equals(v));
                    }
                }
            }
        }
        edges.remove(v);
        vertices().remove(v);
        return true;
    }

    protected boolean remove(VertexITF u, VertexITF v) {
        if (adjacencyList.get(u).contains(v))
            return false;
        adjacencyList.get(u).remove(v);
        return false;
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
