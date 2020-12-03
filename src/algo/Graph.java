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
	
	
	
	public void RougeBleu(int k) {
		getListREDVertex();
		ArrayList<Vertex> sequenceRouge = new ArrayList<Vertex>();
		ArrayList<Vertex> listVertexSeen = new ArrayList<Vertex>();
		String res = findSequenceRouge(k,listVertexSeen);
		System.out.println(res);
	}
	
	
	
	private String findSequenceRouge(int k,ArrayList<Vertex> listVertexSeen) {
		String res = "";
		System.out.println(listREDVertex);
		System.out.println(this);
		if(listREDVertex.size() == 0 || k == 0) {
		}
		else {
			int i = 0;
			int j = listREDVertex.size();
			while (res.length() <= k*2 || i <= j ) {
				
				int nextRedVertexPos = findPos(listREDVertex.get(i));
				res += this.getListVertex().get(nextRedVertexPos).getNum();
				this.suppr(this.getListVertex().get(nextRedVertexPos));
				i++;
				return  res + ","+ findSequenceRouge(k-1,listVertexSeen );
				
				
			}
				
		}
		return res;
	}

	private int getNextRedVertexPos() {
		for (int i = 0; i < listVertex.size(); i++) {
			if (listVertex.get(i).getColor() == Color.RED) {
				return i;
			}
		}
		return -1;
	}

	public void suppr(Vertex v) {
		int posInList = findPos(v);
		listREDVertex.remove(v);
		if (v.getColor() != Color.RED) {
			System.out.println("tu peux pas wesh il est pas rouge");
		}
		else if (posInList == 0){
			if(v.neighbourRSelfPointing()) {
			}
			else {
				if(v.getNeighbourR().getColor() == Color.RED 
						&& v.getNeighbourR().getVertexPointed().getColor() == Color.BLUE) {
					listREDVertex.add(v.getNeighbourR().getVertexPointed());
				}
				else if(v.getNeighbourR().getColor() == Color.BLUE 
						&& v.getNeighbourR().getVertexPointed().getColor() == Color.RED) {
					listREDVertex.remove(v.getNeighbourR().getVertexPointed());
				}
				v.getNeighbourR().getVertexPointed().setColor(v.getNeighbourR().getColor());
			}
			listVertex.set(posInList, new Vertex());
		}
		
		else if (listVertex.get(posInList-1).getNum() == 0){
			if(v.neighbourRSelfPointing()) {
			}
			else {
				if(v.getNeighbourR().getColor() == Color.RED 
						&& v.getNeighbourR().getVertexPointed().getColor() == Color.BLUE) {
					listREDVertex.add(v.getNeighbourR().getVertexPointed());
				}
				else if(v.getNeighbourR().getColor() == Color.BLUE 
						&& v.getNeighbourR().getVertexPointed().getColor() == Color.RED) {
					listREDVertex.remove(v.getNeighbourR().getVertexPointed());
				}
				v.getNeighbourR().getVertexPointed().setColor(v.getNeighbourR().getColor());
			}
			listVertex.set(posInList, new Vertex());
		}
		else {
			
			Vertex neighbourL = listVertex.get(posInList-1);
			if(neighbourL.neighbourRSelfPointing()) {
				if(neighbourL.getColor() == Color.RED 
						&& neighbourL.getNeighbourR().getColor() == Color.BLUE) {
					listREDVertex.remove(neighbourL);
				}
				else if(listVertex.get(posInList -1).getColor() == Color.BLUE 
						&& listVertex.get(posInList -1).getNeighbourR().getColor() == Color.RED) {
					listREDVertex.add(v.getNeighbourR().getVertexPointed());
				}
				
				listVertex.get(posInList -1).setColor(neighbourL.getNeighbourR().getColor());
				neighbourL.setNeighbourR(null);
				
			}
			else {
				neighbourL.setNeighbourR(null);
			
			}
			if(v.neighbourRSelfPointing()) {
			}
			else {
				if(v.getNeighbourR().getColor() == Color.RED 
						&& v.getNeighbourR().getVertexPointed().getColor() == Color.BLUE) {
					listREDVertex.add(v.getNeighbourR().getVertexPointed());
				}
				else if(v.getNeighbourR().getColor() == Color.BLUE 
						&& v.getNeighbourR().getVertexPointed().getColor() == Color.RED) {
					listREDVertex.remove(v.getNeighbourR().getVertexPointed());
				}
				v.getNeighbourR().getVertexPointed().setColor(v.getNeighbourR().getColor());
			}
			listVertex.set(posInList, new Vertex());
		}
		
		
	}
	
	public int findPos(Vertex v) {
		int i = 0;
		for (Vertex vertex: listVertex) {
			i++;
			if (vertex.equals(v)) {
				break;
			}
		}
		return i -1;
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
