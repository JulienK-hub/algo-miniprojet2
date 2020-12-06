package algo;

import java.awt.Color;

public class Edge {
	private Vertex vertexPointed;
	private Vertex vertexExiting;
	private Color color;
	
	public Edge(Vertex vertexExiting, Vertex vertexPointed, Color color) {
		this.vertexPointed = vertexPointed;
		this.color = color;
		this.vertexExiting = vertexExiting;
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
}
