package ba.ssst.edu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        loadPlaces();
        loadConstraints();
        Graph apa = new Graph(15);
        Graph apb = new Graph(15);
        Graph complex = new Graph(3);
        Graph simple = new Graph(3);
        Graph fiveplaces = new Graph(5);
        Graph tenplaces = new Graph(15);

        fiveplaces = createGraph("five_places.txt", 5);
        saveToReport(fiveplaces, "five_places");

        tenplaces = createGraph("ten_places.txt", 15);
        saveToReport(tenplaces, "ten_places");

        complex = createGraph("complex.txt", 3);
        saveToReport(complex, "complex");

        simple = createGraph("simple.txt", 3);
        saveToReport(simple, "simple");

        apa = createGraph("all_places_a.txt", 15);
        saveToReport(apa, "all_places_a");

        apb = createGraph("all_places_b.txt", 15);
        saveToReport(apb, "all_places_b");

    }
    public static void loadPlaces(){
        File file = new File("places.txt");

        try {
            Scanner s1 = new Scanner(file);
            if (s1.hasNextLine()) s1.nextLine();

            while (s1.hasNextLine()){
                String line = s1.nextLine();
                String[] lineParts = line.split(",");

                char shortcode = lineParts[0].charAt(0);
                String location = lineParts[1].trim();

                Places.add(shortcode,location);
            }
            s1.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static Graph createGraph(String file, int n) {
        int nodes = n;
        Graph graph = new Graph(nodes);
        try {
            File f1 = new File(file);
            Scanner s1 = new Scanner(f1);

            while (s1.hasNextLine()) {
                try {
                    String line = s1.nextLine();
                    String[] lineparts = line.split(" ");

                    char source = lineparts[0].charAt(0);
                    char destination = lineparts[1].charAt(0);
                    Integer weight = Integer.parseInt(lineparts[2]);

                    graph.addNode(source, destination, weight);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            s1.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return graph;
    }
    public static void saveToReport(Graph g, String filename) throws IOException {
        FileWriter fw = new FileWriter("Report-" + filename + ".txt");
        ShortestPath dijkstra = new ShortestPath();
        fw.write("Constraints:\n");
        dijkstra.dijkstraWithConstraints(g,Constraints.getConstraints());
        fw.write(String.valueOf(ShortestPath.report));
        ShortestPath.report.setLength(0);
        fw.close();
    }
    public static void loadConstraints(){
        File file = new File("constraints.txt");

        try {
            Scanner s1 = new Scanner(file);
            if(s1.hasNextLine())s1.nextLine();

            while (s1.hasNextLine()){
                String line = s1.nextLine();
                String[] lineParts = line.split(",");

                char source = lineParts[0].charAt(0);
                char destination = lineParts[1].charAt(0);
                String constraint = lineParts[2].trim();
                Double probability = Double.parseDouble(lineParts[3]);

                Constraints constraintLine = new Constraints(source,destination,constraint,probability);
            }
            s1.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}