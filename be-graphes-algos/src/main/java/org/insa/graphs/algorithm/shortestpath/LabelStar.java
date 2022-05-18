package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Point;

public class LabelStar  extends Label{
	
	public double coutDest;
	
	//LabelStar prend un argument de plus
	public LabelStar(Node sommet, boolean marque, Arc père, ShortestPathData data) {
		super(sommet, marque, père);
		
		this.coutDest = Point.distance(sommet.getPoint(), data.getDestination().getPoint());

	}
	
	
	@Override
	
	public double getTotalCost() {
		return this.cout+ this.coutDest;
	}

}
