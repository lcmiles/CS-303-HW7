import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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
    /*
    Description: This function implements Prim's aglorithm, finding the MST of the graph by repeatedly selecting the edge with the smallest weight, connecting visited vertices to unvisited vertices, adding them to the MST, and continuing this process until the MST contains (vertices - 1) edges or there are no more edges to consider
    Parameters: None
    Returns:
    WeightedGaph mst - A WeightedGraph object that represents the found MST
    Sources:
    https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/
    https://chat.openai.com/share/c6ed91be-1c2b-4dbf-b7a9-749de512e504
    */
    public WeightedGraph findMST() {
        WeightedGraph mst = new WeightedGraph(vertices); //create a new graph for the MST
        boolean[] visited = new boolean[vertices]; //initialize visited flag
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(); //initialize priority queue

        visited[0] = true; //set first vertex to visited because it will always be visited
        for (Edge edge : adjList.get(0)) { //iterate through edges adjacent to first vertex
            priorityQueue.offer(edge); //add each to the prioritt queue; the priority queue prioritizes edges with the lowest weights
        }

        while (mst.edges < vertices - 1 && !priorityQueue.isEmpty()) { //continue until the the maximum number of edges in an MST have been addded to the graph and the priority queue is empty
            Edge minEdge = priorityQueue.poll(); //remove the edge with the lowest weight from the queue
            int u = minEdge.getU(); //get the edges first connnected node 
            int v = minEdge.getV(); //get the edges second connected node
            float weight = minEdge.getWeight(); //get the edges weight

            if (visited[u] && !visited[v]) { //if one edge is connected and the other is not
                mst.addEdge(u, v, weight); //add an edge between them in the MST
                visited[v] = true; //mark the v vertex visited because it is now included in the MST

                for (Edge neighbor : adjList.get(v)) { //iterate through the edges adjacent to the vertex v
                    if (!visited[neighbor.getV()]) { //if the other endpoint of the edge is not visited
                        priorityQueue.offer(neighbor); //add it to the priority queue to consider it in the next iteration
                    }
                }
            }
        }
        return mst;
    }

}
