package algo;
import java.awt.Color;
import java.util.ArrayList;

public class Graph {
	private ArrayList<Vertex> listVertex;
	private ArrayList<Vertex> listREDVertex;
	public Graph() {
		listVertex = new ArrayList<Vertex>();
		listREDVertex = new ArrayList<Vertex>();
	}
	public Graph(int vertexNumber, double p, double q) {
		listVertex = new ArrayList<Vertex>();
		listREDVertex = new ArrayList<Vertex>();
		// création des vertex
		for (int i=0;i < vertexNumber; i++) {
			Vertex v = new Vertex(i,p);
			listVertex.add(v);
		}
		// création des arêtes
		for (Vertex v: listVertex) {
			for (Vertex u: listVertex) {
				if (v.equals(u)) {
					break;
				}
				Edge e1 = new Edge(v,u,q);
				Edge e2 = new Edge(u,v,q);
				v.addEdge(e1);
				u.addEdge(e2);
			}
		}
	}

	/**
	 * Heuristique 1 qui va tester la suppression de chacun des noeuds, et ensuite supprimer les noeuds qui changent le + de noeuds bleus en noeuds rouges
	 *
	 * @return la taille de la sequence rouge
	 */
	public int heuristique1() {
		int moreBlueToRed = 0; // La valeur la plus haute de changement de Blue to Red apres suppression du noeud (utilisé pour comparaison avec les autres)
		int currentBlueToRed; // Valeur de l'itération actuelle qui (simule et ) compte le nombre de changement de Blue to Red apres suppression du noeud itéré
		int res = 0; // La taille de la séquence finale de suppression de noeud
		Vertex vertexToDelete = null;
		getListREDVertex();
		while (listREDVertex.size()>0) {
//			System.out.println(res);
//			System.out.println(this);
			moreBlueToRed = -1 ;
			for (Vertex v : listREDVertex) {
				currentBlueToRed = v.getNbBlueToRed();
				//On va actualiser la taille de changement de noeuds bleus en noeuds rouges
				if (currentBlueToRed > moreBlueToRed) {
					moreBlueToRed = currentBlueToRed;
					vertexToDelete = v;
				}
			}
			if(vertexToDelete != null) {
				suppr(vertexToDelete);
			}
			getListREDVertex();
			res++;
		}
//		System.out.println(this);
		return res;
	}

	/**
	 * Heuristique pour tester la suppression de chacun des noeuds, et d’ensuite supprimer le noeud qui change le moins de voisins de Rouge à Bleu
	 * @return la taille de la sequence rouge
	 */
	public int heuristique2() {
		int lowerRedToBlue = 0; // Valeur la plus basse de changement de Red to Blue apres suppression du vertex (utilisé pour comparaison entre autres)
		int currentRedToBlue; // Valeur de l'itération actuelle qui (simule et ) compte le nombre de changement de Red to Bleu apres suppression du vertex itéré
		int res = 0; // La taille de la séquence final de suppression de vertex
		Vertex vertexToDelete = null; //Vertex supprimé apres avoir trouver celui qui change le moins de voisins de Rouge à Bleu
		getListREDVertex();
		while (listREDVertex.size()>0) {
//			System.out.println(res);
//			System.out.println(this);
			lowerRedToBlue = listVertex.size() ;
			for (Vertex v : listREDVertex) {
				currentRedToBlue = v.getNbRedToBlue();
				//On va actualiser la taille de changement de noeud rouges en noeuds bleus
				if (currentRedToBlue < lowerRedToBlue) {
					lowerRedToBlue = currentRedToBlue;
					vertexToDelete = v;
				}
			}
			if(vertexToDelete != null) {
				suppr(vertexToDelete);
			}
			getListREDVertex();
			res++;
		}
//		System.out.println(this);
		return res;
	}

	public int heuristique3(){
		int lowerRedToBlueAndMoreBlueToRed;
		int currentRedToBlueAndMoreBlueToRed;
		int res = 0;
		Vertex vertexToDelete = null;
		getListREDVertex();
		while (listREDVertex.size()>0) {
//			System.out.println(res);
//			System.out.println(this);
			lowerRedToBlueAndMoreBlueToRed = listVertex.size() ;
			for (Vertex v : listREDVertex) {
				currentRedToBlueAndMoreBlueToRed = v.getNbRedToBlueAndMoreBlueToRed();
				// On va actualiser la taille de changement de noeud rouges en noeuds bleus
				if (currentRedToBlueAndMoreBlueToRed < lowerRedToBlueAndMoreBlueToRed) {
					lowerRedToBlueAndMoreBlueToRed = currentRedToBlueAndMoreBlueToRed;
					vertexToDelete = v;
				}
			}
			if(vertexToDelete != null) {
				suppr(vertexToDelete);
			}
			getListREDVertex();
			res++;
		}
//		System.out.println(this);
		return res;
	}

	public void suppr(int num) {
		for (Vertex v: listVertex) {
			if (v.getNum() == num){
				v.supprEdges();
				listVertex.remove(v);
				listREDVertex.remove(v);
				break;
			}
		}
	}

	public void suppr(Vertex v) {
		v.supprEdges();
		listVertex.remove(v);
		listREDVertex.remove(v);
	}
	public ArrayList<Vertex> getListVertex() {
		return listVertex;
	}
	public void setListVertex(ArrayList<Vertex> listVertex) {
		this.listVertex = listVertex;
	}
	public void add(Vertex v) {
		listVertex.add(v);
	}

	private void getListREDVertex() {
		listREDVertex.removeIf(v -> v.getColor() == Color.BLUE);

		for (Vertex v: listVertex) {
			if (v.getColor()==Color.RED) {
				if (!listREDVertex.contains(v)) {
					listREDVertex.add(v);
				}
			}
		}
	}
	@Override
	public String toString() {
		String res = "";
		for (int i=0; i < listVertex.size();i++) {
			Vertex vertexAct = listVertex.get(i);
			res += "\nVertex " + vertexAct + ": " +  vertexAct.getEdges().toString();
		}
		return res;
	}
}