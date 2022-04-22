package org.insa.graphs.model;

import java.util.ArrayList;
import java.util.List;

public class Label implements Comparable<Label>{

   private boolean marque;
   private Node sommet;
   private double cout;
   private Arc père ;
   
   public Label(Node sommet, boolean marque, Arc père) {
	   this.sommet=sommet;
	   this.marque=marque;
	   this.père=père;
	   this.cout = Double.MAX_VALUE;
   }
  
   
	
   public boolean isMarque() {
		return this.marque;
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



@Override
 public int compareTo(Label label) {
	int comparaison=Double.compare(this.getCost(), label.getCost());
			return comparaison;
}
   
   

}
