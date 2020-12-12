package main.java.algo;

import javax.swing.text.GapContent;
import java.util.Random;

class Test {
    public static void main(String args[]) {
        GraphReader graphReader = new GraphReader();

        System.out.println(graphReader.autoGraph(10, 1, 0, 0, 0));
    }
}
