package graph;

public class Edge {
  private Vertex src;
  private Vertex target;
  private int weight;
  
  public Edge(Vertex src, Vertex target, int weight) {
    this.src = src;
    this.target = target;
    this.weight = weight;
  }

  public Vertex getSrc() {
    return src;
  }

  public Vertex getTarget() {
    return target;
  }

  public int getWeight() {
    return weight;
  }
}
