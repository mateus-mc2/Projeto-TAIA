package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class GraphFileReader {
  private HashMap<Integer, Vertex> idMap;
  private File file;
  private int topicId;
  
  public GraphFileReader(String path) throws Exception {
    file = new File(path);
    idMap = new HashMap<Integer, Vertex>();
    
    if (!file.exists()) {
      throw new FileNotFoundException("Specified file does not exists.");
    }
    
    String filename = file.getName();
    
    // Topic id is the first occurrence of an integer within the filename.
    int i = 0;
    while (i < filename.length() && !Character.isDigit(filename.charAt(i))) ++i;
    int j = i;
    while (i < filename.length() && Character.isDigit(filename.charAt(j))) ++j;
    
    if (i == j) {  // Or filename.substring(i, j).equals("")
      throw new Exception("No topic id speficied on filename.");
    }
    
    topicId = Integer.parseInt(filename.substring(i, j));
  }
  
  public Graph getGraphFromFile() throws Exception {
    List<Vertex> vertices = new ArrayList<Vertex>();
    List<Edge> edges = new ArrayList<Edge>();
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line;
    String[] splittedLine;
    
    // Get vertices.
    line = reader.readLine();
    splittedLine = line.split(" ");
    int num_vertices = Integer.parseInt(splittedLine[1]);
    
    for (int i = 0; i < num_vertices; ++i) {
      line = reader.readLine();
      splittedLine = line.split(" ");
      String[] splittedLabel = Arrays.copyOfRange(splittedLine, 1, splittedLine.length - 1);
      String label = String.join(" ", splittedLabel);
      Vertex v = new Vertex(Integer.parseInt(splittedLine[0]), label,
                            Integer.parseInt(splittedLine[splittedLine.length - 1]));
      vertices.add(v);
      idMap.put(v.getId(), v);
    }
    
    // Get edges.
    line = reader.readLine();
    splittedLine = line.split(" ");
    int num_edges = Integer.parseInt(splittedLine[1]);
    PrintWriter writer = new PrintWriter(new File("ranked_nodes.txt"));
    
    for (int i = 0; i < num_edges; ++i) {
      line = reader.readLine();
      splittedLine = line.split(" ");
      int srcId = Integer.parseInt(splittedLine[0]);
      int targetId = Integer.parseInt(splittedLine[1]);
      writer.write(srcId + " " + targetId + "\n");
      
      edges.add(new Edge(vertices.get(srcId), vertices.get(targetId),
                         Integer.parseInt(splittedLine[2])));
    }
    
    reader.close();
    writer.close();
    
    return new Graph(vertices, edges, topicId);
  }
  
  public HashMap<Integer, Vertex> getIdMap() {
    return idMap;
  }
}
