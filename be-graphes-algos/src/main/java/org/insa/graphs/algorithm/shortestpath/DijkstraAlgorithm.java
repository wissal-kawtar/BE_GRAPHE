package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.List;

import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.Node;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = this.getInputData();
        ShortestPathSolution solution = null;
        Graph graph= data.getGraph();
        // TODO:
        //Associer un label a un Noeud
        	final int nbLabels = graph.size();
        	Label[] tableauLabel = new Label[nbLabels];
        	for(Node node :graph.getNodes()) {
        	tableauLabel[node .getId()]=new Label(node,false,null);
        }
        
        BinaryHeap<Label> HeapLabel = new BinaryHeap<Label>();
        	for(Label label : tableauLabel) {
        		HeapLabel.insert(label);
        		
        	}

        while(!) {
        	Label labMin = HeapLabel.findMin();
        	labMin.setMarque(true);
        	for ()
        }
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	

        return solution;
    }

}
