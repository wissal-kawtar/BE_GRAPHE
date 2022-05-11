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
         // Nb Nodes = Nblabels 
         Label[] tableauLabel = new Label[nbLabels];
         Node orgNode = data.getOrigin(); // origin Node 
         //initialiser le tableau de labels
         for(Node node :graph.getNodes()) {
         	tableauLabel[node .getId()]=new Label(node,false,null);
         		}	
         // initialiser le cout du Origin Node , on la marque 
         // cost(origin)=0
         tableauLabel[orgNode.getId()]= new Label (data.getOrigin(),true,null);
         tableauLabel[data.getOrigin().getId()].setCout(0);
         //inserer le label du noeaud origin dans  le heap 
         BinaryHeap <Label> HeapLabel = new BinaryHeap <Label>();
        for(Label label : tableauLabel) {
     		HeapLabel.insert(label);
     		}
     
        while(! tableauLabel[data.getDestination().getId()].isMarque()) {
        	// X c'est le sommet courant 
         	X=HeapLabel.deleteMin();
   // on supprime le min du tas et on le marque 
         	X.setMarque(true);
         	
         	// pour tous les succeseurs de X noeud courant 
         for(Arc arc : X.getSommet().getSuccessors()) { 
        	 // on verifie si on peut vraiment prendre cet arc 
  			if (!data.isAllowed(arc)) {
                continue;
            }
    		 
            double w = data.getCost(arc);
            //on recupere le label du sommet suivant 
         	Label labSuiv =tableauLabel[arc.getDestination().getId()];
         	if(! labSuiv.isMarque() ) {
         		//si c'est le meilleur cout 
         		if(labSuiv.getCost() > X.getCost()+ w ) {

         		labSuiv.setCout(X.getCost()+  w );
         		labSuiv.setPère(arc);
         if(labSuiv.isMarque()==true) {
         			HeapLabel.remove(labSuiv);
             		HeapLabel.insert(labSuiv);
         		}else {
         		HeapLabel.insert(labSuiv);
         		}
        
         		
         		
         	}
         	}
         	
         	}
        }
     
        // si la destination n'a pas de predecesseur la solution est  infeasible
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
