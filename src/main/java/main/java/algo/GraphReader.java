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
    public static DiGraph D1Rem3 = diGraph("09.0 $ 12.0 23.1 0 23.1 12.0 1 123.0 $");
    public static String input = autoGraph(9, 0.5, 0.5, 0.6, 0.4, 0, 1);
    public static DiGraph D2 = diGraph(input);
    public static DiGraph D3 = diGraph("A C B D C E C G D A D F E A F B");

    static int TOUR = 0;
    static int INDEX = 0;
    static String VERTEX;

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
        String[] arr;
        while (input.hasNext()) {
            u = input.next();
            arr = u.split("\\.");
            if (arr.length > 1) {
                if (arr[1].startsWith("0")) {
                    colorVU = Color.RED;
                } else if (arr[1].startsWith("1")) {
                    colorVU = Color.BLUE;
                }
            }
            VertexITF uu = addVertex(G, arr[0], colorVU);
            if (input.hasNext()) {
                v = input.next();
                arr = v.split("\\.");//[.]
                if (arr[0].startsWith("$")) {
                    continue;
                } else if (arr[0].startsWith("0")) {
                    colorVV = Color.RED;
                } else if (arr[0].startsWith("1")) {
                    colorVV = Color.BLUE;
                }
            }
            if (input.hasNextInt()) {
                colorE = (input.nextInt() == 0) ? Color.RED : Color.BLUE;
            }
            if (!v.isEmpty()) {
                VertexITF vv = addVertex(G, arr[0], colorVV);
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

    /**
     * @param nbvertex        : nombre de sommets qu'on souhaite
     * @param probaBlueVertex : probabilité d'avoir des vertex bleu dans le graphe
     * @param probaRedVertex  : probabilité d'avoir des vertex rouge dans le graphe
     * @param probaBlueEdges  : probabilité d'avoir des edges bleu dans le graphe
     * @param probaRedEdges   : probabilité d'avoir des edges rouge dans le graphe
     * @param probaNoEdge     : probilité d'avoir des vertex sans edges
     * @return string qui va être lu par le graph reader
     */
    public static String autoGraph(int nbvertex, double probaBlueVertex, double probaRedVertex, double probaBlueEdges, double probaRedEdges, double probaNoEdge, double probaBidirectionnal) {
        StringBuilder sB = new StringBuilder();
        Random r = new Random();
        String[] ARR = new String[2];
        boolean change = false;
        for (int i = 1; i <= nbvertex; i++) {
            //case : 2 sommets crées
            System.out.println("i -->" + i);
            if (TOUR == 2) {
                //edge rouge ou bleu
                sB.append(getRedOrBlueWithProba(probaBlueEdges, probaRedEdges));
                sB.append(" ");
                TOUR = 0;
                INDEX = 0;

                //bidirectionnell avec proba ???
                if (randomBidirectionnal(probaBidirectionnal)) {
                    i += 2;
                    System.out.println("case bidirectionnal");
                    sB.append(ARR[1]).append(".").append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex)).append(" ").append(ARR[0]).append(".").append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex)).append(" ");
                    sB.append(getRedOrBlueWithProba(probaBlueEdges, probaRedEdges));
                    sB.append(" ");

                    int c = r.nextInt(2);
                    VERTEX = ARR[c];
                    change = true;
                    TOUR = 0;
                    INDEX = 0;
                }
            }
            String cur = getRandomNumber();
            ARR[INDEX] = cur;
            INDEX++;

            if (change) {
                sB.append(VERTEX);
                change = false;
            } else sB.append(cur);
            sB.append(".");
            sB.append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex));
            sB.append(" ");
            TOUR++;

            if (TOUR == 1 && randomNoEdges(probaNoEdge)) {
                //case : 1 sommet crée et proba tombe sans edge
                sB.append("$");
                sB.append(" ");
                TOUR = 0;
                INDEX = 0;
            }
        }
        return sB.toString();
    }

    public static String autoGraphComplet(int nbvertex, double probaBlueVertex, double probaRedVertex, double probaBlueEdges, double probaRedEdges) {
        StringBuilder sB = new StringBuilder();
        Random r = new Random();
        int index = 0;
        int tour = 1;
        String[] tab = new String[2];
        boolean step = false;
        for (int i = 0; i <= nbvertex; i++) {
            String cur = getRandomNumber();
            if (tour != 3) {
                tab[index] = cur;
            }

            if (tour == 3) {
                sB.append(getRedOrBlueWithProba(probaBlueEdges, probaRedEdges)).append(" ");
                sB.append(tab[1]).append(".").append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex)).append(" ").append(tab[0]).append(".").append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex)).append(" ").append(getRedOrBlueWithProba(probaBlueEdges, probaRedEdges)).append(" ");
                index = 0;
                tour = 1;
                i += 2;
                step = true;
            }

            if (step) {
                cur = tab[r.nextInt(2)];
                step = false;
                i++;
            }
            sB.append(cur).append(".").append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex)).append(" ");
            tour++;
            index++;
        }

        return sB.toString();
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

    static boolean randomBidirectionnal(double proba) {
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

    public static String getRandomletter() {
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'A');
        return String.valueOf(c);
    }

    public static String getRandomNumber() {
        return String.valueOf(new Random().nextInt(1000));
    }
}
