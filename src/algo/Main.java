package algo;

import java.awt.Color;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Vertex v1 = new Vertex(1,Color.RED);
		Vertex v2 = new Vertex(2,Color.BLUE);
		Vertex v3 = new Vertex(3,Color.RED);
		Vertex v4 = new Vertex(4,Color.BLUE);
		Vertex v5 = new Vertex(5,Color.RED);
		Vertex v6 = new Vertex(6,Color.RED);
		Vertex v7 = new Vertex(7,Color.RED);
		Vertex v8 = new Vertex(8,Color.RED);
		
		
		Edge edgeV1 = new Edge(v1,v2,Color.RED);
		v1.setNeighbourR(edgeV1);
		v2.setNeighbourL(edgeV1);
		Edge edgeV2 = new Edge(v3,v2,Color.BLUE);
		v2.setNeighbourR(edgeV2);
		v3.setNeighbourL(edgeV2);
		Edge edgeV3 = new Edge(v3,v4,Color.RED);
		v3.setNeighbourR(edgeV3);
		v4.setNeighbourL(edgeV3);
		Edge edgeV4 = new Edge(v4,v5,Color.BLUE);
		v4.setNeighbourR(edgeV4);
		v5.setNeighbourL(edgeV4);
		Edge edgeV5 = new Edge(v6,v5,Color.BLUE);
		v5.setNeighbourR(edgeV5);
		v6.setNeighbourL(edgeV5);
		Edge edgeV6 = new Edge(v6,v7,Color.BLUE);
		v6.setNeighbourR(edgeV6);
		v7.setNeighbourL(edgeV6);
		Edge edgeV7 = new Edge(v8,v7,Color.RED);
		v7.setNeighbourR(edgeV7);
		v8.setNeighbourL(edgeV7);
		
		
		Graph g = new Graph();
		g.add(v1);g.add(v2);g.add(v3);g.add(v4);g.add(v5);g.add(v6);g.add(v7);g.add(v8);
		System.out.println(g);
		System.out.println(g.RougeBleu());
		
		
		
		
	}

}
