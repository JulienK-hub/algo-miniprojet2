package main.java.algo;

import java.awt.*;
import java.util.*;
import java.util.List;

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
    public static DiGraph D1Rem4 = diGraph(autoGraphComplet(2, 0.5, 0.5, 0.6, 0.4));
    public static String input = autoGraphComplet(3, 0.5, 0.5, 0.6, 0.4);
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
            String cur = String.valueOf(new Random().nextInt(1000));
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
        List<String> l = new ArrayList<>();
        if((nbvertex%2) == 1){
            nbvertex+=1;
        }
        for(int i = 0 ; i <= nbvertex/2 ; i++){
            String cur1 = String.valueOf(new Random().nextInt(1000));
            String cur2 = String.valueOf(new Random().nextInt(1000));

            //Faire l'insertion sb
            sB.append(cur1).append(".").append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex)).append(" ").append(cur2).append(".").append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex)).append(" ").append(getRedOrBlueWithProba(probaBlueEdges, probaRedEdges)).append(" ");
            sB.append(cur2).append(".").append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex)).append(" ").append(cur1).append(".").append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex)).append(" ").append(getRedOrBlueWithProba(probaBlueEdges, probaRedEdges)).append(" ");

            int s = l.size();
            if (s == 0){
                l.add(cur1);
                l.add(cur2);
                continue;
            }
            for (int j = 0 ; j < s ; j++){
                //faire les combinaisons avec cur1 -> l.get(j) && cur2-> l.get(j)
                sB.append(cur1).append(".").append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex)).append(" ").append(l.get(j)).append(".").append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex)).append(" ").append(getRedOrBlueWithProba(probaBlueEdges, probaRedEdges)).append( " ");
                sB.append(l.get(j)).append(".").append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex)).append(" ").append(cur1).append(".").append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex)).append(" ").append(getRedOrBlueWithProba(probaBlueEdges, probaRedEdges)).append( " ");
                sB.append(cur2).append(".").append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex)).append(" ").append(l.get(j)).append(".").append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex)).append(" ").append(getRedOrBlueWithProba(probaBlueEdges, probaRedEdges)).append( " ");
                sB.append(l.get(j)).append(".").append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex)).append(" ").append(cur2).append(".").append(getRedOrBlueWithProba(probaBlueVertex, probaRedVertex)).append(" ").append(getRedOrBlueWithProba(probaBlueEdges, probaRedEdges)).append( " ");
            }
            l.add(cur1);
            l.add(cur2);
        }
        System.out.println("sB.toString() = " + sB.toString());
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
}
