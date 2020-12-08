package algo;

import java.awt.*;
import java.util.*;

/**
 * This is a convenience class to build a graph from an input String.
 * 
 * Non weighted graphs are given as a sequence of pairs of vertex tags like "A B A C ..."
 * which will add the vertices A, B, C, etc. and the edge (A,B), (A,C), etc. to the graph.
 * 
 * Weighted graphs are given as sequence of three items, two vertices followed by a weight
 * (a double value) like "A B 2.5 A C 5.2 ..." which will add vertices A, B, C, etc. and the
 * weighted edges (A,B,2.5), (A,C,5.2), etc. to the graph.
 * 
 * Additionally the class has the graphs mentioned in the labs already defined as public 
 * static items which can be used in lab code, like GraphReader.U1, GraphReader.D1, etc.
 * 
 */
public class GraphReader {

	public static DiGraph D1Rem = diGraph("A.R E.B 0 B.R D.B 1 B.B F.B 1 C.R A.R 1");
	public static DiGraph D1Rem2 = diGraph("A.R E.B 0 A.R J.B 1 J.B P.R 1 A.R P.R 1 A.R D.B 0");
	public static DiGraph D2 = diGraph("A C A E B D D F D G E C F B");
	public static DiGraph D3 = diGraph("A C B D C E C G D A D F E A F B");
	/**
	 * Returns an DiGraph build from the String
	 * representation 'input'
	 */
	public static DiGraph diGraph(String input) {
		DiGraph G = new DiGraph();
		readGraph(G,input);
		return G;
	}

	private static void readGraph(GraphITF G, String input) {
		Scanner scanner = new Scanner(input);
		readGraph(G,scanner);
		scanner.close();
	}

	private static void readGraph(GraphITF G, Scanner input) {
		String u, v="";
		Color colorVU = Color.BLACK, colorVV = Color.BLACK, colorE = Color.BLACK;
		while ( input.hasNext() ) {
			u = input.next();
			if(u.startsWith("R", 2)){
				colorVU = Color.RED;
			}else if(u.startsWith("B", 2)){
				colorVU = Color.BLUE;
			}
			if ( input.hasNext() ){
				v = input.next();
				if(v.startsWith("R", 2)){
					colorVV = Color.RED;
				}else if(v.startsWith("B", 2)){
					colorVV = Color.BLUE;
				}
			}
			if(input.hasNextInt()){
				colorE = (input.nextInt() == 0) ? Color.RED : Color.BLUE;
			}

			VertexITF uu = addVertex(G,u.substring(0,1), colorVU);
			VertexITF vv = addVertex(G,v.substring(0,1),colorVV);
			G.addEdge(uu, vv, colorE);
		}
	}
	
	private static VertexITF addVertex(GraphITF G, String u, Color color) {
		VertexITF v = G.getVertex(u);
		if ( v == null )
			return G.addVertex(u, color);
		return v;
	}
}
