package main.java.algo;

import javax.swing.text.GapContent;
import java.util.Arrays;
import java.util.Random;

class Test {
    public static void main(String args[]) {
       // System.out.println(GraphReader.autoGraph(10, 1, 0, 1, 0,0.2, 0.5));
        System.out.println(GraphReader.autoGraphComplet(15, 1, 0, 1, 0));
        GraphITF g = GraphReader.D1Rem4;
    }
}
