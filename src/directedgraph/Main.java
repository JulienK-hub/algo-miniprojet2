package directedgraph;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        List<Vertex> vertexList = new ArrayList<>();
        Vertex v1 = new Vertex("R1");
        Vertex v2 = new Vertex("B2");
        Vertex v3 = new Vertex("R3");
        Vertex v4 = new Vertex("B4");
        Vertex v5 = new Vertex("B5");
        Vertex v6 = new Vertex("R6");
        Vertex v7 = new Vertex("R7");
        Vertex v8 = new Vertex("B8");

        //Set edges
        v1.addOutgoingEdge(v2,0, Color.RED);
        v2.addIncomingEdge(v3,0, Color.BLUE);
        v3.addOutgoingEdge(v4,0, Color.RED);
        v4.addOutgoingEdge(v5,0, Color.BLUE);
        v5.addIncomingEdge(v6,0, Color.BLUE);
        v6.addOutgoingEdge(v7,0, Color.BLUE);
        v7.addIncomingEdge(v8,0, Color.RED);

        vertexList.add(v1);
        vertexList.add(v2);
        vertexList.add(v3);
        vertexList.add(v4);
        vertexList.add(v5);
        vertexList.add(v6);
        vertexList.add(v7);
        vertexList.add(v8);

        vertexList.forEach(graph::addVertex);

        //vertexList.forEach(v-> System.out.println(v));

        System.out.println(graph.display());

    }
}
