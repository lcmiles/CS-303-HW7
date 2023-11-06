import java.util.ArrayList;
import java.util.List;

public class WeightedGraph {

    private int vertices; //number of vertices in the graph
    private int edges; //number
    private List<List<Edge>> adjList;

    public WeightedGraph(int nodes) {
    this.vertices = nodes; //initializes the number of vertices as the number of nodes
    this.edges = 0; //initializes the number of edges to 0
    adjList = new ArrayList<>();
    for (int i = 0; i < nodes; i++) {
        adjList.add(new ArrayList<>());
    }
    }

    public void addEdge(int u, int v, float weight) {
    adjList.get(u).add(new Edge(u,v, weight));
    }

    public List<Edge> getAdjacentEdges(int u) {
    return adjList.get(u);
    }

    public int getVertices() {
    return adjList.size();
    }

    public void setVertices(int vertices) { //setter for vertices
		this.vertices = vertices;
	}

    public void printAdjList() {
        StringBuilder string = new StringBuilder(); //initialize StringBuilder object
        string.append("The graph contains "+ edges + " edges and " + vertices + " vertices. \n"); 
        for (int i = 0; i < vertices; i++) { //iterates through the list of adjacency lists using using a for loop stopping at the number of vertices
            string.append("Adjacency list for node " + i + ": ");
            for (Edge neighbor : adjList.get(i)) { //for each neighbor contained in the nested adjacency list 
                string.append(neighbor.getV() + " ");
                string.append("(Weight: " + neighbor.getWeight() + ") ");
            }
            string.append("\n");
        }
        System.out.println(string);
    }

}
