package main.java.algo;

import javax.swing.text.GapContent;
import java.util.Arrays;
import java.util.Random;

class Test {
    public static void main(String args[]) {
        GraphReader graphReader = new GraphReader();
        System.out.println(graphReader.autoGraph(10, 1, 0, 1, 0,0.2, 0.5));

        String t[] = graphReader.swapVertex(new String[]{"99", "11"});

        System.out.println(Arrays.toString(t));
    }
}
