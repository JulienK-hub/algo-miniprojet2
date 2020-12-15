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

	private void getListREDVertex() {
		for (Vertex v: listVertex) {
			if (v.getColor()==Color.RED) {
				listREDVertex.add(v);
			}
		}
		
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
	
	
	
	public String RougeBleu() {
		String res= "";
		getListREDVertex();
		
		/*Vertex vertexToSuppr = getNextRedVertexWithoutBlueExitingEdges();
		while (vertexToSuppr != null) {
			
			res += suppr(vertexToSuppr);
			vertexToSuppr = getNextRedVertexWithoutBlueExitingEdges();
			System.out.println(this);
		}*/
		Vertex vertexToSuppr = getNextRedVertexWhichDoesntChangeRedNeighbourToBlue();
		
		while (vertexToSuppr != null) {
			res += suppr(vertexToSuppr);
			vertexToSuppr = getNextRedVertexWhichDoesntChangeRedNeighbourToBlue();
			System.out.println(this);
		}
		
		return res;
		
	}
	
	private Vertex getNextRedVertexWhichDoesntChangeRedNeighbourToBlue() {
		Vertex res = null;
		
		for (Vertex v: listREDVertex) {
			res = v;
			
			if(v.getNeighbourL() != null) {
				if (v.getNeighbourL().getColor() == Color.BLUE && v.getNeighbourL().getVertexExiting()==v && v.getNeighbourL().getVertexPointed().getColor() == Color.RED) {
					res = null; // si on change le voisin de gauche de rouge à bleu
				}
			}
			if(v.getNeighbourR() != null) {
				if (v.getNeighbourR().getColor() == Color.BLUE && v.getNeighbourR().getVertexExiting()==v && v.getNeighbourR().getVertexPointed().getColor() == Color.RED) {
					res = null; // si on change le voisin de droite de rouge à bleu
				}
			}
			if (res != null){
				return res;
			}
		}
		
		return res;
	}

	private Vertex getNextRedVertexWithoutBlueExitingEdges() {
		Vertex res = null;
		
		for (Vertex v: listREDVertex) {
			res = v;
			
			if(v.getNeighbourL() != null) {
				if (v.getNeighbourL().getColor() == Color.BLUE) {
					if (v.getNeighbourL().getVertexPointed() != v) {
						res = null;
					}
				}
			}
			if(v.getNeighbourR() != null) {
				if (v.getNeighbourR().getColor() == Color.BLUE) {
					if (v.getNeighbourR().getVertexPointed() != v) {
						res = null;
					}
				}
			}
			if (res != null){
				return res;
			}
		}
		
		return res;
	}

	public String suppr(Vertex v) {
		String res = "" ;
		if(v == null) {
			System.out.println("null pointeur exception lol");
		}
		else if (v.getColor()==Color.BLUE) {
			System.out.println("tu peux pas suppr un bleu wesh");
		}
		else {
			res = v.getNum() + ",";
			if(v.getNeighbourL()!=null) {
				Vertex neigbourL = v.getVertexNeighbourL();
				neigbourL.supprEdgeR(); // on supprime l'arête de droite du voisin de gauche
				v.setNeighbourL(null);  // on supprime l'arête de gauche de v
				if (!listREDVertex.contains(neigbourL) && neigbourL.getColor() == Color.RED ) { 
					listREDVertex.add(neigbourL);
				}
				
			}
			else {
				//cas où v n'a pas de voisin à gauche
			}
			if(v.getNeighbourR()!=null) {
				Vertex neigbourR = v.getVertexNeighbourR();
				neigbourR.supprEdgeL(); // on supprime l'arête de gauche du voisin de droite
				v.setNeighbourR(null);  // on supprime l'arête de droite de v
				if (!listREDVertex.contains(neigbourR) && neigbourR.getColor() == Color.RED ) {
					listREDVertex.add(neigbourR);
				}
			}
			else {
				//cas où v n'a pas de voisin à droite
			}
			supprVertexInLists(v);
		}
		return res;
	}
	
	private void supprVertexInLists(Vertex v) {
		int i = 0;
		
		for (Vertex v2 : listVertex) {
			
			if (v.getNum() == v2.getNum()) {
				
				listVertex.get(i).setNum(0);
				break;
			}
			i++;
		}
		listREDVertex.remove(v);
		
	}

	@Override
	public String toString() {
		String res = "";
		for (int i=0; i < listVertex.size();i++) {
			Vertex vertexAct = listVertex.get(i);
			res += vertexAct.toString();
			if(vertexAct.getNeighbourR() != null) {
				res += vertexAct.getNeighbourR().print(vertexAct);
			}
			else {
				res += "    ";
			}
			
		}
		return res;
	}
}
