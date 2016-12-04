package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Graph {
  private HashMap<Vertex, List<Pair<Vertex, Integer>>> adjList;
  private int num_edges;
  private int topic_id;
  
  public Graph(List<Vertex> vertices, List<Edge> edges, int topic_id) throws Exception {
    this.topic_id = topic_id;
    this.num_edges = edges.size();
    this.adjList = new HashMap<Vertex, List<Pair<Vertex, Integer>>>();
    
    for (int i = 0; i < vertices.size(); ++i) {
      adjList.put(vertices.get(i), new ArrayList<Pair<Vertex, Integer>>());
    }
    
    for (int i = 0; i < edges.size(); ++i) {
      if (!adjList.containsKey(edges.get(i).getSrc())) {
        throw new Exception("Vertex and edge lists are not consistent.");
      } else {
        List<Pair<Vertex, Integer>> neighbors = adjList.get(edges.get(i).getSrc());
        neighbors.add(new Pair<Vertex, Integer>(edges.get(i).getTarget(),
                                                edges.get(i).getWeight()));
      }
    }
  }
  
  public double[] getPageRank() {
    return new double[1];
  }
  
  public void printGraph() {
    System.out.println("\t*Vertices " + adjList.size());
    Set<Vertex> keySet = adjList.keySet();
    for (Vertex v : keySet) {
      System.out.println("\t" + v.getId() + " " + v.getLabel() + " " + v.getNumCited());
    }
    
    System.out.println("\t*Edges " + num_edges);
    for (Vertex v : keySet) {
      List<Pair<Vertex, Integer>> neighbors = adjList.get(v);
      for (int i = 0; i < neighbors.size(); ++i) {
        System.out.println("\t" + v.getId() + " " + neighbors.get(i).getFirst().getId() + 
                           " " + neighbors.get(i).getSecond());
      }
    }
  }
  
  public int getTopicId() {
    return topic_id;
  }
  
  public HashMap<Vertex, List<Pair<Vertex, Integer>>> getAdjList() {
    return adjList;
  }
  
  public int getSize() {
    return adjList.size();
  }
}
