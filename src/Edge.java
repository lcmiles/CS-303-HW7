class Edge implements Comparable<Edge> {

  private int u;
  private int v;
  private float weight;

  /*
    Description: This is the constructor for the edge objects
    Parameters:
    int u - The first node to be connected
    int v - The second node to be connected
    float weight - The weight of the edge
    Returns: Nothing
    Sources:
    https://stackoverflow.com/questions/44831436/java-implementing-weighted-graph
    */
  public Edge(int u, int v, float weight)  {
    this.u = u;
    this.v = v;
    this.weight = weight;
  }

  public int getU() {
    return u;
  }

  public int getV() {
    return v;
  }

  public float getWeight() {
    return weight;
  }

  @Override
    public int compareTo(Edge other) { //override the compareTo class to allow edge weights to be compared
        return Float.compare(this.weight, other.weight);  //compare edges based on their weights
    }
}
