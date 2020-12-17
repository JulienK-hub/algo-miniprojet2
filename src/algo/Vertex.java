package algo;
import java.awt.Color;
import java.util.ArrayList;
public class Vertex {
	private int num;
	private Color color;
	private ArrayList<Edge> edges;
	public Vertex(int i, double p) {
		edges = new ArrayList<Edge>();
		this.num = i;
		double rd = Math.random();
		if(p > rd) {
			this.color = Color.RED;
		}
		else {
			this.color = Color.BLUE;
		}
	}
	public void addEdge(Edge e) {
		edges.add(e);
	}
	public void supprEdges() {
		for (Edge e : edges) {
			e.applyColor();
			e.getVertexPointed().supprEdgeWithOneVertex(this);
		}
	}
	private void supprEdgeWithOneVertex(Vertex vertex) {
		for (Edge e : edges) {
			if(e.getVertexPointed()== vertex) {
				edges.remove(e);
				break;
			}
		}
	}
	public int getNum() {
		return num;
	}
	public Color getColor() {
		return color;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	@Override
	public String toString() {
		String res = "";
		if (num == -1) {
			return res += "   ";
		}
		if(color == Color.RED) {
			res += ConsoleColors.RED + "(" + num + ")";
		}
		else if (color == Color.BLUE) {
			res += ConsoleColors.BLUE + "(" + num + ")";
		}
		else {
			res += "   ";
		}
		res+= ConsoleColors.RESET;
		return res;
	}
	public ArrayList<Edge> getEdges() {
		return edges;
	}
	public int getNbBlueToRed() {
		int res = 0;
		for (Edge e : edges) {
			if(e.getColor() == Color.RED && e.getVertexPointed().getColor() == Color.BLUE) {
				res++;
			}
		}
		return res;
	}

	public int getNbRedToBlue() {
		int res = 0;
		for (Edge e : edges) {
			if(e.getColor() == Color.BLUE && e.getVertexPointed().getColor() == Color.RED) {
				res++;
			}
		}
		return res;
	}
}