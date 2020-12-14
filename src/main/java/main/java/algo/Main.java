package main.java.algo;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Graph graph = new SingleGraph("Tutorial 1");
        GraphITF g = GraphReader.D1Rem4;
        System.out.println("input du graph --> " + GraphReader.input);
        List<EdgeITF> es = new ArrayList<>();
        List<VertexITF> remains = new ArrayList<>((Collection<? extends VertexITF>) g.vertices());

        for (VertexITF v : g.vertices()){
            for (EdgeITF e : g.incidents(v)){
                VertexITF a = e.origin();
                VertexITF b = e.destination();
                if(graph.edges().anyMatch(n -> n.getId().equals(a.getId()+b.getId()))){
                    break;
                }
                if (graph.nodes().anyMatch(n ->n.getId().equals(a.getId())) || graph.nodes().anyMatch(n ->n.getId().equals(b.getId()))){
                    if(graph.nodes().anyMatch(n ->n.getId().equals(a.getId())) && graph.nodes().noneMatch(n ->n.getId().equals(b.getId()))){
                        es.add(e);
                        graph.addNode(b.getId());
                        remains.remove(b);
                        graph.getNode(b.getId()).setAttribute("ui.label", b.getTag());
                        graph.getNode(b.getId()).setAttribute("ui.style", ( e.destination().getColor().equals(Color.RED) ) ? "fill-color: red;" : "fill-color: blue;");
                        graph.addEdge(a.getId()+b.getId(),a.getId(), b.getId(), true );
                        graph.getEdge(a.getId()+b.getId()).setAttribute("ui.style", ( e.color().equals(Color.RED)) ? "fill-color: red;" : "fill-color: blue;");
                    } else if(graph.nodes().anyMatch(n ->n.getId().equals(b.getId())) && graph.nodes().noneMatch(n ->n.getId().equals(a.getId()))){
                        es.add(e);
                        graph.addNode(a.getId());
                        remains.remove(a);
                        graph.getNode(a.getId()).setAttribute("ui.style", ( a.getColor().toString().equals(Color.RED.toString()) ) ? "fill-color: red;" : "fill-color: blue;");
                        graph.getNode(a.getId()).setAttribute("ui.label", a.getTag());
                        graph.addEdge(a.getId()+b.getId(),a.getId(), b.getId(), true );
                        graph.getEdge(a.getId()+b.getId()).setAttribute("ui.style", ( e.color().equals(Color.RED)) ? "fill-color: red;" : "fill-color: blue;");
                    }else if(graph.nodes().anyMatch(n ->n.getId().equals(b.getId())) && graph.nodes().anyMatch(n ->n.getId().equals(a.getId()))){
                        es.add(e);
                        graph.addEdge(a.getId()+b.getId(),a.getId(), b.getId(), true );
                        graph.getEdge(a.getId()+b.getId()).setAttribute("ui.style", ( e.color().equals(Color.RED)) ? "fill-color: red;" : "fill-color: blue;");
                    }
                }else if(graph.nodes().noneMatch(n ->n.getId().equals(a.getId())) && graph.nodes().noneMatch(n ->n.getId().equals(b.getId()))){
                    graph.addNode(a.getId());
                    graph.addNode(b.getId());
                    remains.remove(a);
                    remains.remove(b);
                    es.add(e);
                    graph.getNode(a.getId()).setAttribute("ui.style", ( a.getColor().equals(Color.RED) ) ? "fill-color: red;" : "fill-color: blue;");
                    graph.getNode(b.getId()).setAttribute("ui.style", ( b.getColor().equals(Color.RED) ) ? "fill-color: red;" : "fill-color: blue;");
                    graph.getNode(b.getId()).setAttribute("ui.label", b.getTag());
                    graph.getNode(a.getId()).setAttribute("ui.label", a.getTag());
                    graph.addEdge(a.getId()+b.getId(),a.getId(), b.getId(), true );
                    graph.getEdge(a.getId()+b.getId()).setAttribute("ui.style", ( e.color().equals(Color.RED) ) ? "fill-color: red;" : "fill-color: blue;");
                }

            }
        }

        for( VertexITF r: remains){
            if(graph.nodes().noneMatch(n-> n.getId().equals(r.getId()))){
                graph.addNode(r.getId());
                graph.getNode(r.getId()).setAttribute("ui.style", ( r.getColor().equals(Color.RED) ) ? "fill-color: red;" : "fill-color: blue;");
                graph.getNode(r.getId()).setAttribute("ui.label", r.getTag());
            }
        }

        System.setProperty("org.graphstream.ui", "swing");
        graph.display();
    }
}
