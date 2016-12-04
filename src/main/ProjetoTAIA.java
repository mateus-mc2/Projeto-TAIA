package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import graph.Graph;
import io.GraphFileReader;

public class ProjetoTAIA {
  public static String dataDirName = "data/";
  
  public static void main(String[] args) {
    File dir = new File(dataDirName);
    File[] datasets = dir.listFiles();
    List<Graph> graphs = new ArrayList<Graph>();
    
    for (int i = 0; i < datasets.length; ++i) {
      try {
        GraphFileReader reader = new GraphFileReader(datasets[i].getPath());
        graphs.add(reader.getGraphFromFile());
        PageRankCalc.pageRankVertices(reader.getIdMap(), graphs.get(i).getTopicId());
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
//    for (int i = 0; i < graphs.size(); ++i) {
//      Graph graph = graphs.get(i);
//      System.out.println("## TOPIC " + graph.getTopicId() + " ##");
//      graph.printGraph();
//    }
  }
}
