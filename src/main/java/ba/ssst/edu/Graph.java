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

    public Graph copyGraph() {
        Graph copy = new Graph(this.nodes);
        for (int i = 0; i < this.adjacencylist.length; i++) {
            copy.adjacencylist[i] = new LinkedList<>();
            for (Node node : this.adjacencylist[i]) {
                copy.adjacencylist[i].add(new Node(node.source, node.destination, node.weight));
            }
        }
        return copy;
    }


    public void addNode(char source, char destination, Integer weight) {
            Node node = new Node(source, destination, weight);
            adjacencylist[source - 65].addFirst(node);
    }

    public void disablePath(char source, char destination) {
        int index = source - 65;
        adjacencylist[index].removeIf(node -> node.destination == destination);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nodes; i++) {
            char nodeChar = (char) (i + 65);
            sb.append("Node ").append(nodeChar).append(": ");
            LinkedList<Node> list = adjacencylist[i];
            for (Node node : list) {
                sb.append("(").append(node.source).append("->").append(node.destination).append(":").append(node.weight).append("), ");
            }
            sb.setLength(sb.length() - 2); // Remove trailing comma and space
            sb.append("\n");
        }
        return sb.toString();
    }
}