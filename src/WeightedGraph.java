import java.util.ArrayList;
import java.util.List;

public class WeightedGraph {

    private int vertices; //number of vertices in the graph
    private int edges; //number
    private List<List<Edge>> adjList;

    /*
    Description: This is the constructor for the graph object
    Parameters:
    int nodes - The number of nodes the graph will contain
    Returns: Nothing
    Sources:
    https://chat.openai.com/share/8b4c2e60-b4e3-4b2a-909b-4a3300ec4287
    https://www.youtube.com/watch?v=X1LdtRW88c0
    https://stackoverflow.com/questions/44831436/java-implementing-weighted-graph
    */
    public WeightedGraph(int nodes) {
    this.vertices = nodes; //initializes the number of vertices as the number of nodes
    this.edges = 0; //initializes the number of edges to 0
    adjList = new ArrayList<>();
    for (int i = 0; i < nodes; i++) {
        adjList.add(new ArrayList<>());
    }
    }

    /*
    Description: This function adds undirected edges to the graph represented as a 1 in the adjacency matrix both ways
    Parameters:
    int node1 - A node read from the text file to be connected to node2 via an edge
    int node2 - A node read from the text file to be connected to node1 via an edge
    Returns: Nothing
    Sources:
    https://chat.openai.com/share/8b4c2e60-b4e3-4b2a-909b-4a3300ec4287
    https://www.youtube.com/watch?v=X1LdtRW88c0
    https://stackoverflow.com/questions/44831436/java-implementing-weighted-graph
    */
    public void addEdge(int u, int v, float weight) {
    adjList.get(u).add(new Edge(u,v, weight)); //gets the adjacency list for node1 and adds a new node object to that list with the correct weight and connected nodes
    edges++; //counts edges
    }

    public List<Edge> getAdjacentEdges(int u) { //getter for the adjList of a certain node
    return adjList.get(u);
    }

    public int getVertices() { //getter for vertices 
    return this.vertices;
    }

    public void setVertices(int vertices) { //setter for vertices
		this.vertices = vertices;
	}

    /*
    Description: This function iterates through the list of adjacency lists for each node alond with the weight of each edge, appending each element to a string and printing each of them to form an adjacency list for the graph
    Parameters: None
    Returns: Nothing
    Sources:
    https://www.youtube.com/watch?v=X1LdtRW88c0
    */
    public void printAdjList() {
        StringBuilder string = new StringBuilder(); //initialize StringBuilder object
        string.append("The graph contains "+ edges + " edges and " + vertices + " vertices. \n"); 
        for (int i = 0; i < vertices; i++) { //iterates through the list of adjacency lists using using a for loop stopping at the number of vertices
            string.append("Adjacency list for node " + (i + 1) + ": ");
            for (Edge neighbor : adjList.get(i)) { //for each neighbor contained in the nested adjacency list 
                string.append(neighbor.getV() + " ");
                string.append("(Weight: " + neighbor.getWeight() + ") ");
            }
            string.append("\n");
        }
        System.out.println(string);
    }

}
