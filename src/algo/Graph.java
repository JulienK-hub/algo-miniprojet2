package algo;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

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
	public int heuristique1() {
		int moreBlueToRed = 0;
		int currentBlueToRed;
		int res = 0;
		Vertex vertexToDelete = null;
		getListREDVertex();
		while (listREDVertex.size()>0) {
			System.out.println(res);
			System.out.println(this);
			moreBlueToRed = -1 ;
			for (Vertex v : listREDVertex) {
				currentBlueToRed = v.getNbBlueToRed();
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
		System.out.println(this);
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