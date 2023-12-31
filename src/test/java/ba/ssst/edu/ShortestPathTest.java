package ba.ssst.edu;

import junit.framework.TestCase;

public class ShortestPathTest extends TestCase {

    public void testIfThereIsAPathToDestination() {
        Main.loadPlaces();
        Graph graph = new Graph(3);

        graph.addNode('A','B', 10);
        graph.addNode('B','A', 5);
        graph.addNode('A','C', 15);

        ShortestPath.shortestPath(graph,'A');
        String expectedResultsA = "Shortest paths from source A:\n" +
                "Distance A -> A: 0\n" +
                "Distance A -> B: 10\n" +
                "Distance A -> C: 15";
        assertEquals(expectedResultsA.trim(), ShortestPath.report.toString().trim());
        ShortestPath.resetReport();

        ShortestPath.shortestPath(graph,'B');
        String expectedResultsB = "Shortest paths from source B:\n" +
                "Distance B -> A: 5\n" +
                "Distance B -> B: 0\n" +
                "Distance B -> C: 20";
        assertEquals(expectedResultsB.trim(), ShortestPath.report.toString().trim());
        ShortestPath.resetReport();

        ShortestPath.shortestPath(graph,'C');
        String expectedResultsC = "Shortest paths from source C:\n" +
                "Distance C -> A: -1, no path\n" +
                "Distance C -> B: -1, no path\n" +
                "Distance C -> C: 0";
        assertEquals(expectedResultsC.trim(), ShortestPath.report.toString().trim());
        ShortestPath.resetReport();
    }

    public void testGraphWithSelfLoop() {
        Main.loadPlaces();

        Graph graph = new Graph(3);
        graph.addNode('A', 'B', 10);
        graph.addNode('A', 'C', 5);
        graph.addNode('A', 'A', 12);

        ShortestPath.resetReport();
        ShortestPath.shortestPath(graph,'A');
        String expectedResults = "Shortest paths from source A:\n" +
                "Distance A -> A: 0\n" +
                "Distance A -> B: 10\n" +
                "Distance A -> C: 5";

        assertEquals(expectedResults.trim(), ShortestPath.report.toString().trim());
    }

    public void testGraphWithInvalidSource() {
        Main.loadPlaces();

        Graph graph = new Graph(3);
        graph.addNode('A', 'B', 10);
        graph.addNode('B', 'C', 5);
        graph.addNode('C', 'A', 12);

        ShortestPath.shortestPath(graph,'D');
        String expectedResults = "Shortest paths from source D: -1, nonexistent place in graph\n";

        assertEquals(expectedResults.trim(), ShortestPath.report.toString().trim());
    }

    public void testGraphWithInvalidTime() {
        Main.loadPlaces();

        Graph graph = new Graph(3);
        graph.addNode('A', 'B', 10);
        graph.addNode('A', 'C', -5);
        graph.addNode('C', 'A', 12);

        ShortestPath.resetReport();
        ShortestPath.shortestPath(graph,'A');
        String expectedResults = "Shortest paths from source A:\n" +
                "Distance A -> A: 0\n" +
                "Distance A -> B: 10\n" +
                "Distance A -> C: -1, no path";

        assertEquals(expectedResults.trim(), ShortestPath.report.toString().trim());
    }

  /*  public void testGraphWithNoConstraints() {
        Main.loadPlaces();
        Main.loadConstraints();

        Graph graph = new Graph(3);
        graph.addNode('A', 'B', 10);
        graph.addNode('A', 'C', 5);
        graph.addNode('C', 'B', 12);

        ShortestPath dijkstra = new ShortestPath();
        String actual = dijkstra.dijkstraWithConstraints(graph,Constraints.getConstraints());

        String expected =
                "Shortest paths from source A:\n" +
                "Distance A -> A: 0\n" +
                "Distance A -> B: 10\n" +
                "Distance A -> C: 5\n" +
                "\n" +
                "Shortest paths from source B:\n" +
                "Distance B -> A: -1, no path\n" +
                "Distance B -> B: 0\n" +
                "Distance B -> C: -1, no path\n" +
                "\n" +
                "Shortest paths from source C:\n" +
                "Distance C -> A: -1, no path\n" +
                "Distance C -> B: 12\n" +
                "Distance C -> C: 0\n" +
                "\n";
        assertEquals(expected.trim(), actual.trim());
    }


   */

}