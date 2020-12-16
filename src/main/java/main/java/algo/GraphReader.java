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
    public static String input = autoGraphComplet(3, 0.5, 0.5, 0.6, 0.4);
    public static DiGraph D2 = diGraph(input);
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
        int i = 0;
        while (input.hasNext()) {
            u = input.next();
            arr = u.split("[.]");
            if (arr.length > 1) {
                if (arr[1].startsWith("0")) {
                    colorVU = Color.RED;
                } else if (arr[1].startsWith("1")) {
                    colorVU = Color.BLUE;
                }
            }
            System.out.println("La couleur VU "+ i +colorVU);
            VertexITF uu = addVertex(G, arr[0], colorVU);
            if (input.hasNext()) {
                v = input.next();
                arr = v.split("[.]");//[.]
                if (arr[1].startsWith("0")) {
                    colorVV = Color.RED;
                } else if (arr[1].startsWith("1")) {
                    colorVV = Color.BLUE;
                }
            }
            if (input.hasNextInt()) {
                int nb = input.nextInt();
                System.out.println(nb);
                colorE = (nb == 0) ? Color.RED : Color.BLUE;
            }
            if (!v.isEmpty()) {
                System.out.println("La couleur VV"+i+colorVV);
                VertexITF vv = addVertex(G, arr[0], colorVV);
                G.addEdge(uu, vv, colorE);
            }
            v = "";
            i++;
        }
    }
    private static VertexITF addVertex(GraphITF G, String u, Color color) {
        VertexITF v = G.getVertex(u);
        if (v == null)
            return G.addVertex(u, color);
        return v;
    }
    public static DiGraph D1Rem4 = diGraph(autoGraphComplet(3, 0.5, 0.5, 0.6, 0.4));
    public static String autoGraphComplet(int nbvertex, double probaBlueVertex, double probaRedVertex, double probaBlueEdges, double probaRedEdges) {
        StringBuilder sB = new StringBuilder();
        int nbvertexBase = nbvertex;
        if(nbvertex%2 == 0)
            nbvertex = nbvertex/2;
        else
            nbvertex = (nbvertex/2)+1;
        Map<String, String> l = new HashMap<>();
        String cur1 ="";
        String cur2 ="";
        int pv1;int pv2;int pe1;int pe2;
        for(int i = 0 ; i < nbvertex ; i++){
            //Lorsque le nombre de vertex et impaire on va quitter cette boucle pour créer un dernier noeud et le relier avec tous les autres dans la liste
            if (nbvertexBase %2 == 1 && i == nbvertex-1)
                break;
            cur1 = String.valueOf(new Random().nextInt(1999999999));
            cur2 = String.valueOf(new Random().nextInt(1999999999));
            //Faire l'insertion sb
            pv1 = getRedOrBlueWithProba(probaBlueVertex, probaRedVertex);
            pv2 = getRedOrBlueWithProba(probaBlueVertex, probaRedVertex);
            pe1 = getRedOrBlueWithProba(probaBlueVertex, probaRedVertex);
            pe2 = getRedOrBlueWithProba(probaBlueVertex, probaRedVertex);
            sB.append(cur1).append(".").append(pv1).append(" ").append(cur2).append(".").append(pv2).append(" ").append(pe1).append(" ");
            sB.append(cur2).append(".").append(pv2).append(" ").append(cur1).append(".").append(pv1).append(" ").append(pe2).append(" ");
            int s = l.size();
            if (s == 0){
                l.put(cur1, String.valueOf(pv1));
                l.put(cur2, String.valueOf(pv1));
                continue;
            }
            for (Map.Entry<String, String> value : l.entrySet()) {
                //faire les combinaisons avec cur1 -> l.get(j) cur1 <- l.get(j) && cur2 -> l.get(j) cur2 <- l.get(j)
                pv1 = getRedOrBlueWithProba(probaBlueVertex, probaRedVertex);pe1 = getRedOrBlueWithProba(probaBlueEdges, probaRedEdges);pe2 = getRedOrBlueWithProba(probaBlueEdges, probaRedEdges);
                sB.append(cur1).append(".").append(pv1).append(" ").append(value.getKey()).append(".").append(value.getValue()).append(" ").append(pe1).append(" ");
                sB.append(value.getKey()).append(".").append(value.getValue()).append(" ").append(cur1).append(".").append(pv1).append(" ").append(pe2).append(" ");
                pv1 = getRedOrBlueWithProba(probaBlueVertex, probaRedVertex);pe1 = getRedOrBlueWithProba(probaBlueEdges, probaRedEdges);pe2 = getRedOrBlueWithProba(probaBlueEdges, probaRedEdges);
                sB.append(cur2).append(".").append(pv1).append(" ").append(value.getKey()).append(".").append(value.getValue()).append(" ").append(pe1).append(" ");
                sB.append(value.getKey()).append(".").append(value.getValue()).append(" ").append(cur2).append(".").append(pv1).append(" ").append(pe2).append(" ");
            }
            l.put(cur1, String.valueOf(pv1));
            l.put(cur2, String.valueOf(pv1));
        }
        if (nbvertexBase %2 ==1){
            String cur3 = String.valueOf(new Random().nextInt(1999999999));
            for (Map.Entry<String, String> value : l.entrySet()){
                //faire les combinaisons avec cur1 -> l.get(j) cur1 <- l.get(j) && cur2 -> l.get(j) cur2 <- l.get(j)
                pv1 = getRedOrBlueWithProba(probaBlueVertex, probaRedVertex);pe1 = getRedOrBlueWithProba(probaBlueEdges, probaRedEdges);pe2 = getRedOrBlueWithProba(probaBlueEdges, probaRedEdges);
                sB.append(cur3).append(".").append(pv1).append(" ").append(value.getKey()).append(".").append(value.getValue()).append(" ").append(pe1).append( " ");
                sB.append(value.getKey()).append(".").append(value.getValue()).append(" ").append(cur3).append(".").append(pv1).append(" ").append(pe2).append( " ");
            }
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