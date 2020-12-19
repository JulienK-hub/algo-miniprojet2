package algo;

public class Main {

	/** Petit guide pour afficher un graphe avec un visualisateur:
		 * Décommenter la ligne 15 et 16
		 *
		 * Le résultat sera affiché sous forme de matrice d'adjacence avec CurrentVertex -> [ VertexA, VertexB, VertexC ...]
		 *
		 * Pour afficher les couleurs, vérifier que la console puisse afficher des couleurs
	 * Petit guide pour afficher dans la console, les différents états lors des itération dans l'heuristique 1 par exemple:
		 *
		 * Aller dans la classe Graph et décommenter les lignes suivantes : 46, 47
		 * Cela va itération sur l'affichage de l'état actuel du graphe et son nombre de suppression
		 *
	 * Petit guide pour afficher dans la console, les moyennes pour un heuristique 1 par exemple:
		 *
		 * Décommenter les lignes suivantes : 39
		 *
	 *
	 * @param args
	 */
	public static void main(String[] args) {

//		g = new Graph(20,0.5,0.5);
//		System.out.println(g);

		System.out.println();
		int moyenne = 0;
		Graph g;
		for (double p = 0 ; 1 >= p ; p += 0.1){
			System.out.println("p="+ p);
			for (double q = 0 ; 1 >= q ; q += 0.1){
				moyenne = 0;
				for (int i = 0; i < 100; i++) {
					g = new Graph(100,p,q);
					moyenne+=g.heuristique3();
				}
//				System.out.print(" | q="+q+" " + moyenne/100);
			}
			System.out.println();
		}
		System.out.println();
	}
}