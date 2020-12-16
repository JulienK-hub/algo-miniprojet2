package algo;

import java.awt.Color;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Graph g = new Graph(10,0.1,0.5);
		System.out.println(g.heuristique1());
		System.out.println(g.heuristique2());
	}
}