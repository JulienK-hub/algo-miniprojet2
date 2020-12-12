package main.java.algo;

import java.awt.*;
import java.util.*;

/**
 * This is a convenience class to build a graph from an input String.
 * <p>
 * Non weighted graphs are given as a sequence of pairs of vertex tags like "A B A C ..."
 * which will add the vertices A, B, C, etc. and the edge (A,B), (A,C), etc. to the graph.
 * <p>
 * Weighted graphs are given as sequence of three items, two vertices followed by a weight
 * (a double value) like "A B 2.5 A C 5.2 ..." which will add vertices A, B, C, etc. and the
 * weighted edges (A,B,2.5), (A,C,5.2), etc. to the graph.
 * <p>
 * Additionally the class has the graphs mentioned in the labs already defined as public
 * static items which can be used in lab code, like GraphReader.U1, GraphReader.D1, etc.
 */
public class GraphReader {
    //Colors : 0 = Rouge ; 1 = Bleu
    //"Node1.ColorN1 Node2.ColorN2 ColorEdge" Pour deux noeud liés
    //"Node1.Color1 $" (Pour un noeud seul)
    public static DiGraph D1Rem = diGraph("A.R E.B 0 B.R D.B 1 B.B F.B 1 C.R A.R 1");
    public static DiGraph D1Rem2 = diGraph("K.0 $ A.0 E.1 0 A.0 J.1 1 J.1 A.0 1 A.0 P.0 1 A.0 D.1 0 D.1 A.0 0 P.1 S.0 0 Q.1 H.0 0 ");
    public static DiGraph D2 = diGraph("A.R B.R 0 B.R A.R 1");
    public static DiGraph D3 = diGraph("A C B D C E C G D A D F E A F B");

    /**
     * Returns an DiGraph build from the String
     * representation 'input'
     */
    public static DiGraph diGraph(String input) {
        DiGraph G = new DiGraph();
        readGraph(G, input);
        return G;
    }

    private static void readGraph(GraphITF G, String input) {
        Scanner scanner = new Scanner(input);
        readGraph(G, scanner);
        scanner.close();
    }

    private static void readGraph(GraphITF G, Scanner input) {
        String u, v = "";
        Color colorVU = Color.BLACK, colorVV = Color.BLACK, colorE = Color.BLACK;
        while (input.hasNext()) {
            u = input.next();
            if (u.startsWith("0", 2)) {
                colorVU = Color.RED;
            } else if (u.startsWith("1", 2)) {
                colorVU = Color.BLUE;
            }

            VertexITF uu = addVertex(G, u.substring(0, 1), colorVU);
            if (input.hasNext()) {
                v = input.next();
                if (v.startsWith("$")) {
                    continue;
                } else if (v.startsWith("0", 2)) {
                    colorVV = Color.RED;
                } else if (v.startsWith("1", 2)) {
                    colorVV = Color.BLUE;
                }
            }
            if (input.hasNextInt()) {
                colorE = (input.nextInt() == 0) ? Color.RED : Color.BLUE;
            }
            if (!v.isEmpty()) {
                VertexITF vv = addVertex(G, v.substring(0, 1), colorVV);
                G.addEdge(uu, vv, colorE);
            }
            v = "";

        }
    }

    private static VertexITF addVertex(GraphITF G, String u, Color color) {
        VertexITF v = G.getVertex(u);
        if (v == null)
            return G.addVertex(u, color);
        return v;
    }

    static int getRedOrBlueWithProba(double probaBleu, double probaRed) {
        if (probaBleu + probaRed == 1) {
            Random rng = new Random();
            double rand = rng.nextDouble();
            if (rand < probaBleu) {
                return 1;
            } else if (rand < probaBleu + probaRed) {
                return 0;
            }
            return 99;
        } else
            return 99;
    }

    static boolean randomNoEdges(double proba) {
        boolean res = false;

        Random rng = new Random();
        double rand = rng.nextDouble();
        if (rand < proba) {
            res = true;
        } else if (rand < proba + (1 - proba)) {
            res = false;
        }
        return res;
    }

    public String autoGraph(int nbvertex, double probaBlueVertex, double probaRedVertex, double probaBlueEdges, double probaRedEdges, double probaNoEdge) {
        StringBuilder sB = new StringBuilder();
        int i = 1;
        for (; i <= nbvertex; i++) {
            sB.append(getRandomletter());
            sB.append(".");
            sB.append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex));
            sB.append(" ");
            if (randomNoEdges(probaNoEdge)) {
                sB.append("$");
            }

            //todo random edges

			//todo random color of edges
        }
        return sB.toString();
    }

    public String getRandomletter() {
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a');
        return String.valueOf(c);
    }
}
