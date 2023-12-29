package ba.ssst.edu;

import java.util.*;

public class ShortestPath {
    public StringBuilder report = new StringBuilder();
    public void shortestPath(Graph graph, char source) {
        PriorityQueue<Graph.Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        Map<Character, Integer> distance = new HashMap<>();
        Map<Character, Character> parent = new HashMap<>();

        for (int i = 0; i < graph.nodes; i++) {
            char nodeChar = (char) (i + 65);
            if (nodeChar == source) {
                distance.put(nodeChar, 0);
            }
            else {
                distance.put(nodeChar, 21474);
            }
            parent.put(nodeChar, null);
            minHeap.add(new Graph.Node(nodeChar, nodeChar, distance.get(nodeChar)));
        }

        while (!minHeap.isEmpty()) {
            Graph.Node currNode = minHeap.poll();
            char u = currNode.destination;

            LinkedList<Graph.Node> list = graph.adjacencylist[u-65];
            for (Graph.Node neighbor : list) {
                char v = neighbor.destination;
                int alt = distance.get(u) + neighbor.weight;
                if (alt < distance.get(v)) {
                    distance.put(v, alt);
                    parent.put(v, u);
                    minHeap.add(new Graph.Node(v, v, alt));
                }
            }
        }

        report.append("Shortest paths from source ").append(source).append(":\n");
        for (char node : distance.keySet()) {
            report.append("Distance ").append(source).append(" -> ").append(node).append(": ").append(distance.get(node)).append("\n");
        }
        report.append("\n");
    }
}
