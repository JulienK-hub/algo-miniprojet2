package algo;

import java.awt.Color;

public class Vertex {
	private int num;
	private Color color;
	private Edge neighbourR;
	private Edge neighbourL;
	
	public Vertex(int num, Color color, Edge neighbourR, Edge neighbourL ) {
		this.num = num;
		this.color = color;
		this.neighbourR = neighbourR;
		this.neighbourL = neighbourL;
	}
	
	public Vertex(int num, Color color) {
		this.num = num;
		this.color = color;
		this.neighbourR = null;
		this.neighbourL = null;
	}
	
	public Vertex() {
		this.neighbourL = null;
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
	public Edge getNeighbourL() {
		return neighbourL;
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
	public void setNeighbourL(Edge neighbourL) {
		this.neighbourL = neighbourL;
	}
	
	@Override
	public String toString() {
		String res = "";
		if (num == 0) {
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

	public Vertex getVertexNeighbourL() {
		if(neighbourL.getVertexExiting() == this) {
			return neighbourL.getVertexPointed();
		}
		else {
			return neighbourL.getVertexExiting();
		}
		
	}
	
	public Vertex getVertexNeighbourR() {
		if(neighbourR.getVertexExiting() == this) {
			return neighbourR.getVertexPointed();
		}
		else {
			return neighbourR.getVertexExiting();
		}
		
	}

	public void supprEdgeR() {
		if(neighbourR != null) {
			if(neighbourR.getVertexPointed() == this) {
				setColor(neighbourR.getColor());
				neighbourR = null;
			}
			else {
				neighbourR = null;
			}
		}
		
	}
	
	public void supprEdgeL() {
		if(neighbourL != null) {
			if(neighbourL.getVertexPointed() == this) {
				setColor(neighbourL.getColor());
				neighbourL = null;
			}
			else {
				neighbourL = null;
			}
		}
		
	}
	
}
