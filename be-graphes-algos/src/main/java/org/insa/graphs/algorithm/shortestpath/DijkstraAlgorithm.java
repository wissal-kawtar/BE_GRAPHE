package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
    	 final ShortestPathData data = getInputData();
         ShortestPathSolution solution = null;
         Graph graph= data.getGraph();
         final int nbLabels = graph.size();
         Label X;
      // Initialize array of predecessors.
     
         Label[] tableauLabel = new Label[nbLabels];
         Node orgNode = data.getOrigin();

         for(Node node :graph.getNodes()) {
         	tableauLabel[node .getId()]=new Label(node,false,null);
         		}	
         
       // cost(origin)=0
         tableauLabel[orgNode.getId()]= new Label (data.getOrigin(),true,null);
         tableauLabel[data.getOrigin().getId()].setCout(0);
         //insert le label du noeaud origin dans  le heap 

         BinaryHeap <Label> HeapLabel = new BinaryHeap <Label>();
        for(Label label : tableauLabel) {
     		HeapLabel.insert(label);
     		}
     
        while(! tableauLabel[data.getDestination().getId()].isMarque()) {
         	X=HeapLabel.deleteMin();
         	
         	
         	X.setMarque(true);
         	// pour tous les succeseurs de X
         	for(Arc arc : X.getSommet().getSuccessors()) {
         	Label labcourant=tableauLabel[arc.getDestination().getId()];
         	if(! labcourant.isMarque() && labcourant.getCost() > X.getCost()+data.getCost(arc)) {
         		labcourant.setCout(X.getCost()+ data.getCost(arc));
         		labcourant.setPère(arc);
         		if(labcourant.isMarque()==true) {
         			HeapLabel.remove(labcourant);
             		HeapLabel.insert(labcourant);
         		}else {
         		HeapLabel.insert(labcourant);
         		}
         	}
         	
         	
         	}
        }
     
        if (!tableauLabel[data.getDestination().getId()].isMarque()) {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        else {

            // The destination has been found, notify the observers.
            notifyDestinationReached(data.getDestination());

            // Create the path from the array of predecessors...
            ArrayList<Arc> arcs = new ArrayList<>();
            Arc arc = tableauLabel[data.getDestination().getId()].getPère();
            while (arc != null) {
                arcs.add(arc);
                arc = tableauLabel[arc.getOrigin().getId()].getPère();
            }

            // Reverse the path...
            Collections.reverse(arcs);

            // Create the final solution.
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
        }

        


        return solution;
    }

}
