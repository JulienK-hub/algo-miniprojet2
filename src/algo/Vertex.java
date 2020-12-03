package algo;

import java.awt.Color;

public class Vertex {
	private int num;
	private Color color;
	private Edge neighbourR;
	
	public Vertex(int num, Color color, Edge neighbourR) {
		this.num = num;
		this.color = color;
		this.neighbourR = neighbourR;
	}
	
	public Vertex(int num, Color color) {
		this.num = num;
		this.color = color;
		this.neighbourR = null;
	}
	
	public Vertex() {
		this.neighbourR = null;
		this.color = null;
		this.num = 0;
	}

	public int getNum() {
		return num;
	}

	public Color getColor() {
		return color;
	}

	public Edge getNeighbourR() {
		return neighbourR;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setNeighbourR(Edge neighbourR) {
		this.neighbourR = neighbourR;
	}
	
	@Override
	public String toString() {
		String res = "";
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
	
	public boolean neighbourRSelfPointing() {
		if(this.neighbourR== null) {
			return true;
		}
		else if (neighbourR.getVertexPointed().equals(this)) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
