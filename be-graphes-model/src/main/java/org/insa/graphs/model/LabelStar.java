package org.insa.graphs.model;

public class LabelStar  extends Label{
	
	public coutDest;

	public LabelStar(Node sommet, boolean marque, Arc père, Node dest) {
		super(sommet, marque, père);
		
		this.coutDest = Point.distance(sommet.getPoint(), dest.getPoint());

	}

}
