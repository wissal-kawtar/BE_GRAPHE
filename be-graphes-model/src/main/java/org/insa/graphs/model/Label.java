package org.insa.graphs.model;

import java.util.ArrayList;
import java.util.List;

public class Label {

   private boolean marque;
   private Node sommet;
   private double cout;
   private Arc père ;
   
   public Label(Node sommet, boolean marque, Arc père) {
	   this.sommet=sommet;
	   this.marque=marque;
	   this.père=père;
   }
  /* public Label(Node node) {
	   this.marque=false;
	   this.cout=Double.MAX_VALUE;
	   this.père=null;
   }
   */
   
	
   public boolean getMarque() {
		return marque;
	}

	public void setMarque(boolean marque) {
		this.marque = marque;
	}
   
   public Node getSommet() {
	   return sommet;
   }
   
   public void setSommet(Node sommet) {
	   this.sommet=sommet;
   }
   
   public Arc getPère() {
	   return père;
   }
   
   public void setPère(Arc père) {
	   this.père=père;
   }
   
   public double getCout() {
	   return cout ;
   }
   
   public void setCout(double cout ) {
	   this.cout=cout;
   }
   
   
  public double getCost() {
	  return this.cout;
  }
   
   
   // Associer un label a chaque Noeud 
   
  /* public Label (Node node) {
	   this.sommet=Node.getId();
	   this.cout=
	   this.père=
	   this.marque=
   }*/
}
