package ba.ssst.edu;

import java.util.*;

public class ShortestPath {
    private static final int infinity = 1000000;
    public static StringBuilder report = new StringBuilder();
    public static void shortestPath(Graph graph, char source) {
        if (source<65 || source>=(65 + graph.nodes)){

            report.append("Shortest paths from source ").append(source).append(": -1, nonexistent place in graph\n");
            return;
        }

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
            if (Places.exists(source) && Places.exists(node)) {
                if (distance.get(node) == infinity || distance.get(node) < 0) {
                    distance.put(node, -1);
                    report.append("Distance ").append(source).append(" -> ").append(node).append(": ").append(distance.get(node)).append(", no path\n");
                } else {
                    report.append("Distance ").append(source).append(" -> ").append(node).append(": ").append(distance.get(node)).append("\n");
                }
            }
            else {
                distance.put(node, -1);
                report.append("Distance ").append(source).append(" -> ").append(node).append(": ").append(distance.get(node)).append(", nonexistent place\n");
            }
        }
        report.append("\n");
    }

    public static List<String> applyConstraints(Graph graph, ArrayList<Constraints> constraints) {
        double globalRandomValue = Math.random();
        List<String> appliedConstraints = new ArrayList<>();

        for (Constraints constraint : constraints) {
            char source = constraint.getSource();
            char destination = constraint.getDestination();

            if (source >= 'A' && source < 'A' + graph.nodes && destination >= 'A' && destination < 'A' + graph.nodes) {
                if (globalRandomValue < constraint.getProbability() && globalRandomValue != 0) {
                    graph.disablePath(source, destination);
                    appliedConstraints.add("Path from " + source + " to " + destination + " is disabled due to " + constraint.getConstraint());
                }
            }
        }

        return appliedConstraints;
    }

    public String dijkstraWithConstraints(Graph graph, ArrayList<Constraints> constraints) {
        resetReport();
        List<String> appliedConstraints = applyConstraints(graph, constraints);
        report.append(graph.toString()).append("\n");

        if (appliedConstraints.isEmpty()) {
            report.append("No constraints applied\n\n");
        } else {
            report.append("Applied constraints:\n");
            for (String constraint : appliedConstraints) {
                report.append(constraint).append("\n");
            }
            report.append("\n");
        }

        for (int i = 0; i < graph.nodes; i++) {
            shortestPath(graph, (char) (i + 65));
        }

        return report.toString();
    }

    public static void resetReport() {
        report.setLength(0);
    }
}
