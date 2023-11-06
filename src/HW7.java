import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HW7 {
    public static void main(String[] args) throws Exception {
        String filename = "tinyDG.txt"; //initialize filename string variable
        try {
            long numberOfLines = Files.lines(Paths.get(filename)).count(); //count the number of lines in the text file
            WeightedGraph graph = new WeightedGraph((int)numberOfLines); //initialize graph object with the number of lines in the text file with the number of nodes
            BufferedReader reader = new BufferedReader(new FileReader(filename)); //initialize reader object
            String line;
            int lineCount = 0; //initialize line count
            while ((line = reader.readLine()) != null) { //iterate through the text file with a while loop
                lineCount++; //iterate the line count variable forward 1 with each loop
                if (lineCount == 1) { //case for first line
                    graph.setVertices(Integer.parseInt(line)); //parse the int value representing the number of vertices and assign it to the graph object
                }
                else if (lineCount == 2) { //case for second line
                    continue; //skip because addEdge() adds edges
                }
                else {
                    String[] nodes = line.split(" "); //split each line at the space using a regex
                    int node1 = Integer.parseInt(nodes[0]); //parse the int value and assign it to node1 variable
                    int node2 = Integer.parseInt(nodes[1]); //parse the int value and assing it to node2 variable
                    float weight = Float.parseFloat(nodes[2]); //parse the float value and assing it to weight variable
                    graph.addEdge(node1, node2, weight); //call addEdge() on both nodes to add them to the graph
                }
            }
            reader.close();
            graph.printAdjList(); //call printAdjList() to print the adjacency list representation of the graph
            long timeInit = System.nanoTime(); //records initial system time in nanoseconds
            //TODO: add Prim's algorithm
            long timeFinal = System.nanoTime(); // records final system time in nanoseconds
            long time = timeFinal - timeInit; //calculates time taken for BFS algorithm
            System.out.println("Breadth-First Search Time: " + time + " nanoseconds, " + (float)time/1000000 + " milliseconds, or " + (float)time/1000000000 + " seconds");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
