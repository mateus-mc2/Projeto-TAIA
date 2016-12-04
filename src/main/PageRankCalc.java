package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.TreeSet;

import edu.uci.ics.jung.algorithms.scoring.PageRank;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import graph.Graph;
import graph.Pair;
import graph.Vertex;

public class PageRankCalc {
  DirectedGraph<Integer, String> g = new DirectedSparseGraph<Integer, String>();
  
  private void readFile(String filename, String delim) throws IOException {
    FileReader fr = new FileReader(filename);
    BufferedReader br = new BufferedReader(fr);

    String line;

    while ((line = br.readLine()) != null) {
      String[] result = line.split(delim);
      g.addEdge(result[0] + " " + result[1],
                Integer.parseInt(result[0]),
                Integer.parseInt(result[1]));
    }

    br.close();
  }
  
  public static void pageRankVertices(HashMap<Integer, Vertex> idMap, int fileNum)
      throws IOException {        
    PageRankCalc prc = new PageRankCalc();        
    prc.readFile("ranked_nodes.txt", " ");
    PageRank<Integer, String> pr = new PageRank<Integer, String>(prc.g, 0.15);
    pr.evaluate();
    double sum = 0;
    Set<Integer> sortedVerticesSet = new TreeSet<Integer>(prc.g.getVertices());
    File file = new File("file" + fileNum + ".txt");

    double[] prScores = new double[sortedVerticesSet.size()];
    int i = 0;
    
    for (Integer key : sortedVerticesSet) {
      prScores[i] = pr.getVertexScore(key);
      sum += prScores[i];
      ++i;
    }
    
    ArrayIndexComparator comparator = new ArrayIndexComparator(prScores);
    Integer[] indexes = comparator.createIndexArray();
    Arrays.sort(indexes, comparator);

    PrintWriter writer = new PrintWriter(file);
    
    for (int j = 0; j < prScores.length; ++j) {
      Vertex v = idMap.get(indexes[j]);
      writer.println(v.getLabel());
    }
        
    writer.println("s = " + sum);    
    writer.close();
  }
  
//  public static void bla(HashMap<Integer, Vertex> idMap, Graph graph) throws IOException {
//    double[] pr = new double[graph.getSize()];
//    HashMap<Vertex, List<Pair<Vertex, Integer>>> adjList = graph.getAdjList();
//    Set<Vertex> verticesSet = adjList.keySet();
//    
//    double sum = 0.0;
//    
//    for (Vertex v : verticesSet) {
//      List<Pair<Vertex, Integer>> neighbors = adjList.get(v);
//      for (int i = 0; i < neighbors.size(); ++i) {
//        pr[v.getId()] += neighbors.get(i).getSecond();
//      }
//      
//      sum += pr[v.getId()];
//    }
//    
//    double prSum = 0;
//    for (int i = 0; i < pr.length; ++i) {
//      pr[i] /= sum;
//      prSum += pr[i];
//      System.out.println(pr[i]);
//    }
//    
//    System.out.println(prSum);
//    
//    ArrayIndexComparator comparator = new ArrayIndexComparator(pr);
//    Integer[] indexes = comparator.createIndexArray();
//    Arrays.sort(indexes, comparator);
//  
//    File file = new File("file" + graph.getTopicId() + ".txt");
//    PrintWriter writer = new PrintWriter(file);
//    
//    for (int j = 0; j < indexes.length; ++j) {
//      Vertex v = idMap.get(indexes[j]);
//      writer.println(v.getLabel());
//    }
//        
//    writer.close();
//  }
}