package ba.ssst.edu;
import java.util.*;

class Graph {

    protected static class Node {
        char source;
        char destination;
        Integer weight;

        public Node(char source, char destination, Integer weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    int nodes;
    LinkedList<Node> [] adjacencylist;

    Graph(int nodes) {
        this.nodes = nodes;
        adjacencylist = new LinkedList[nodes];
        for (int i = 0; i <nodes ; i++) {
            adjacencylist[i] = new LinkedList<>();
        }
    }

    public void addNode(char source, char destination, Integer weight) {
        if (Places.exists(source) && Places.exists(destination)) {
            Node node = new Node(source, destination, weight);
            adjacencylist[source - 65].addFirst(node);
        }
    }
    public void printGraph(){
        for (int i = 0; i <nodes ; i++) {
            LinkedList<Node> list = adjacencylist[i];
            for (Node node : list) {
                System.out.println("Node " + node.source + " to node " + node.destination + " - " + node.weight + " seconds");
            }
        }
    }
}