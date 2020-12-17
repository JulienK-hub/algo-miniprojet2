package algo;

import java.awt.Color;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
//
//		Graph g = new Graph(100, 0.1, 0.9);
//
//		System.out.println(g.heuristique2());

		int moyenne = 0;
		Graph g;
		for (double p = 0 ; 1 >= p ; p += 0.1){
			System.out.println("p="+ p);
			for (double q = 0 ; 1 >= q ; q += 0.1){
				moyenne = 0;
				for (int i = 0; i < 100; i++) {
					g = new Graph(100,p,q);
					moyenne+=g.heuristique1();
				}
				System.out.print(" | q="+q+" " + moyenne/100);
			}
			System.out.println();
		}

		System.out.println();
	}
}