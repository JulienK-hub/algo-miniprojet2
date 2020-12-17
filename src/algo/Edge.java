package algo;

import java.awt.Color;

public class Edge {
	private Vertex vertexPointed;
	private Vertex vertexExiting;
	private Color color;
	
	public Edge(Vertex vertexExiting, Vertex vertexPointed, double q) {
		this.vertexPointed = vertexPointed;
		this.vertexExiting = vertexExiting;
		double rd = Math.random();
		if(rd < q) { //Si q = 0 -> aucune chance d'avoir un bleu
			this.color = Color.BLUE;
		}
		else {
			this.color = Color.RED;
		}
	}

	public Vertex getVertexPointed() {
		return vertexPointed;
	}
	public Vertex getVertexExiting() {
		return vertexExiting;
	}

	public Color getColor() {
		return color;
	}

	public void setVertexPointed(Vertex vertexPointed) {
		this.vertexPointed = vertexPointed;
	}

	public void setVertexExiting(Vertex vertexExiting) {
		this.vertexExiting = vertexExiting;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public String print(Vertex vertex) {
		String res = "";
		if (color == Color.BLUE) {
			res += ConsoleColors.BLUE;
		}
		else if (color == Color.RED) {
			res += ConsoleColors.RED;
		}
		
		if(vertex == vertexPointed) {
			res += "<---";
		}
		else {
			res += "--->";
		}
		res += ConsoleColors.RESET;
		return res;
	}
	@Override
	public String toString() {
		String res = "";
		if (color == Color.BLUE) {
			res += ConsoleColors.BLUE;
		}
		else if (color == Color.RED) {
			res += ConsoleColors.RED;
		}
		res += "-> " + ConsoleColors.RESET + vertexPointed.toString();
		return res;
	}

	public void applyColor() {
		vertexPointed.setColor(color);
	}
}
