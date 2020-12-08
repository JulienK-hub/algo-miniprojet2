package algo;

import javafx.scene.layout.StackPane;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Graph graph = new SingleGraph("Tutorial 1");
        GraphITF g = GraphReader.D1Rem;

        for (VertexITF v : g.vertices()){
            for (EdgeITF e : g.incidents(v)){
                if (graph.nodes().anyMatch(n ->n.getId().equals(e.origin().getTag())) || graph.nodes().anyMatch(n ->n.getId().equals(e.destination().getTag()))){
                    if(graph.nodes().anyMatch(n ->n.getId().equals(e.origin().getTag()))){
                        graph.addNode(e.destination().getTag());
                        graph.getNode(e.destination().getTag()).setAttribute("ui.style", ( e.destination().getColor().equals(Color.RED) ) ? "fill-color: red;" : "fill-color: blue;");
                        graph.addEdge(e.origin().getTag()+e.destination().getTag(),e.origin().getTag(), e.destination().getTag(), true );
                        graph.getEdge(e.origin().getTag()+e.destination().getTag()).setAttribute("ui.style", ( e.color().equals(Color.RED)) ? "fill-color: red;" : "fill-color: blue;");
                    }else if(graph.nodes().anyMatch(n ->n.getId().equals(e.destination().getTag()))){
                        graph.addNode(e.origin().getTag());
                        graph.getNode(e.origin().getTag()).setAttribute("ui.style", ( e.origin().getColor().toString().equals(Color.RED.toString()) ) ? "fill-color: red;" : "fill-color: blue;");
                        graph.addEdge(e.origin().getTag()+e.destination().getTag(),e.origin().getTag(), e.destination().getTag(), true );
                        graph.getEdge(e.origin().getTag()+e.destination().getTag()).setAttribute("ui.style", ( e.color().equals(Color.RED)) ? "fill-color: red;" : "fill-color: blue;");
                    }
                }else{
                    System.out.println("OUCCH");
                    graph.addNode(e.origin().getTag());
                    graph.addNode(e.destination().getTag());
                    graph.getNode(e.origin().getTag()).setAttribute("ui.style", ( e.origin().getColor().equals(Color.RED) ) ? "fill-color: red;" : "fill-color: blue;");
                    graph.getNode(e.destination().getTag()).setAttribute("ui.style", ( e.destination().getColor().equals(Color.RED) ) ? "fill-color: red;" : "fill-color: blue;");
                    graph.addEdge(e.origin().getTag()+e.destination().getTag(),e.origin().getTag(), e.destination().getTag(), true );
                    graph.getEdge(e.origin().getTag()+e.destination().getTag()).setAttribute("ui.style", ( e.color().equals(Color.RED) ) ? "fill-color: red;" : "fill-color: blue;");
                }
            }
        }
        System.setProperty("org.graphstream.ui", "swing");
        graph.display();
    }
}
