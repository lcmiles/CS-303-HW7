import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HW7 {
    /*
    Description: Executable function that is responsible for reading the text file and calling the functions in the Graph class that build, print, and perform BFS on the graph
    Parameters:
    String[] args - Runtime arguments
    Returns: Nothing
    Sources:
    https://stackoverflow.com/questions/26448352/counting-the-number-of-lines-in-a-text-file-java
    https://www.youtube.com/watch?v=X1LdtRW88c0
    https://chat.openai.com/share/037460fb-c57f-412a-8cd0-43e7559d09d2
    */
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
                    line = line.replaceAll("\\s+", " ").trim(); //remove extra spaces around and between the number characters, leaving only one space between each
                    String[] parts = line.split(" "); //split each line at the space using a regex 
                    int node1 = Integer.parseInt(parts[0]); //parse the int value and assign it to node1
                    int node2 = Integer.parseInt(parts[1]); //parse the int value and assign it to node2
                    float weight = Float.parseFloat(parts[2]); //parse the float value and assign it to weight
                    graph.addEdge(node1, node2, weight); //call addEdge() on both nodes and weight to add the weighted edge between them
                }
            }
            reader.close();
            graph.printAdjList(); //call printAdjList() to print the adjacency list representation of the graph
            long timeInit = System.nanoTime(); //records initial system time in nanoseconds
            WeightedGraph mst = graph.findMST(); //call prim's algorithm on graph and assign it to a new WeightedGraph object
            long timeFinal = System.nanoTime(); // records final system time in nanoseconds
            long time = timeFinal - timeInit; //calculates time taken for BFS algorithm
            System.out.println("The MST found using Prim's algoirthm:");
            mst.printAdjList(); //print adjacency list of MST graph
            System.out.println("Prim's Algorithm Time: " + time + " nanoseconds, " + (float)time/1000000 + " milliseconds, or " + (float)time/1000000000 + " seconds");
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
