package org.insa.graphes.model;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.BinaryPathReader;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.io.PathReader;

public class DijkstraTest {
	  
	  public void run() throws Exception {
	  // Visit these directory to see the list of available files on Commetud.
    final String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
    final String pathName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Paths/path_fr31insa_rangueil_r2.path";

    // Create a graph reader.
    final GraphReader reader = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

    // TODO: Read the graph.
    final Graph graph = reader.read();
 // TODO: Create a PathReader.
    final PathReader pathReader = new BinaryPathReader(
    		new DataInputStream(new BufferedInputStream(new FileInputStream(pathName))));
    		

    // TODO: Read the path.
    final Path path = pathReader.readPath(graph);
    
	  }

}
