package graph;

public class Vertex {
  private int id;
  private String label;
  private int num_cited;
  
  public Vertex(int id, String label, int num_cited) {
    this.id = id;
    this.label = label;
    this.num_cited = num_cited;
  }

  public int getId() {
    return id;
  }

  public String getLabel() {
    return label;
  }
  
  public int getNumCited() {
    return num_cited;
  }
}
