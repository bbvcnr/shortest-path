package ba.ssst.edu;

import java.util.*;

public class ShortestPath {
    public static StringBuilder report = new StringBuilder();
    private static final int infinity = 1000000;
    public void shortestPath(Graph graph, char source) {
        report.setLength(0);
        PriorityQueue<Graph.Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        Map<Character, Integer> distance = new HashMap<>();
        Map<Character, Character> parent = new HashMap<>();


        for (int i = 0; i < graph.nodes; i++) {
            char nodeChar = (char) (i + 65);
            if (nodeChar == source) {
                distance.put(nodeChar, 0);
            }
            else {
                distance.put(nodeChar, infinity);
            }
            parent.put(nodeChar, null);
            minHeap.add(new Graph.Node(nodeChar, nodeChar, distance.get(nodeChar)));
        }

        applyConstraints(graph,Constraints.getConstraints());
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
            if (distance.get(node) == infinity) {
                report.append("Distance ").append(source).append(" -> ").append(node).append(": No path\n");
            } else {
                report.append("Distance ").append(source).append(" -> ").append(node).append(": ").append(distance.get(node)).append("\n");
            }
        }
        report.append("\n");
    }

    public static void applyConstraints(Graph graph, ArrayList<Constraints> constraints){
        double random = Math.random();

        for (Constraints constraint: constraints) {
            char csource = constraint.getSource();
            char cdestination = constraint.getDestination();
            int indexsrc = csource - 65;
            int indexdest = cdestination - 65;

            if (indexsrc < graph.nodes && indexdest < graph.nodes) {
                if (random < constraint.getProbability()) {
                    graph.disablePath(csource, cdestination);
                    report.append("Path from ").append(csource).append(" to ").append(cdestination).append(" is disabled.\n");
                }
            }
        }
    }

}
