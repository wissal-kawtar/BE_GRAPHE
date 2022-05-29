package org.insa.graphs.algorithm.utils;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.Graph;

import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.junit.Test;


public class AstarTest {
	 	
			@SuppressWarnings("deprecation")
			public void testScenario(String mapName, int Mode, int origine, int destination) throws Exception {

				// Create a graph reader.
				GraphReader reader = new BinaryGraphReader(
						new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

				// Read the graph.
				Graph graph = reader.read();
				System.out.println("Size graph :"+graph.size());

				if (Mode!=0 && Mode!=1) {
					System.out.println("Argument invalide");
				} else {
					if (origine<0 || destination<0 || origine>=(graph.size()-1) || destination>=(graph.size()-1)) { // On est hors du graphe. / Sommets inexistants
						System.out.println("ERREUR : Paramètres invalides ");
						
					} else {
						ArcInspector arcInspectorAstar;
						
						if (Mode == 0) { //Temps
							System.out.println("Mode : Temps");
							arcInspectorAstar = ArcInspectorFactory.getAllFilters().get(2);
						} else {
							System.out.println("Mode : Distance");
							arcInspectorAstar = ArcInspectorFactory.getAllFilters().get(0);
						}
						
						
						System.out.println("Chemin de la carte : "+mapName);
						System.out.println("Origine : " + origine);
						System.out.println("Destination : " + destination);
						
						if(origine==destination) {
							System.out.println("Origine et Destination identiques");
							System.out.println("Cout solution: 0");
							
						} else {			
							ShortestPathData data = new ShortestPathData(graph, graph.get(origine),graph.get(destination), arcInspectorAstar);
				
							BellmanFordAlgorithm B = new BellmanFordAlgorithm(data);
							AStarAlgorithm A = new AStarAlgorithm(data);
							
							// Recuperation des solutions de Bellman et Astar  
							ShortestPathSolution solution = A.run();
							ShortestPathSolution expected = B.run();
			
							
							if (solution.getPath() == null) {
								assertEquals(expected.getPath(), solution.getPath());
								System.out.println("PAS DE SOLUTION");
								System.out.println("(**infini**) ");
							}
							// on trouve le PCC
							else {
								double costSolution;
								double costExpected;
								if(Mode == 0) { //Temps
									//Le  cout de la solution 
									costSolution = solution.getPath().getMinimumTravelTime();
									costExpected = expected.getPath().getMinimumTravelTime();
								} else {
									costSolution = solution.getPath().getLength();
									costExpected = expected.getPath().getLength();
								}
								assertEquals(costExpected, costSolution, 0.001);
								System.out.println("Le Cout de la solution: " + costSolution);
							}
						}
					}
				}
				System.out.println();
				System.out.println();
			}
			
			
		
			
			@Test
			public void SenarioDistanceBordeauxTest() throws Exception {
				
				String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bordeaux.mapgr";
				
				AstarTest test = new  AstarTest();
				int origine;
				int destination;
				
				System.out.println("************************* Test de validité d'Algo sur une carte *************************");
				System.out.println("******************* Carte : bordeaux ******************* ");
				System.out.println("******************* Mode de Test : DISTANCE *******************");
				System.out.println();
				
				
				System.out.println("***************** Cas d'un chemin nul *****************");
				origine = 0 ;
				destination = 0;
				test.testScenario(mapName, 1,origine,destination);    // mode 1 :distance 
				
				System.out.println("*****************  Cas d'un chemin simple ***************** ");
				origine = 14646;
				destination = 7559;
				test.testScenario(mapName, 1,origine,destination);  
				
				System.out.println("*****************  Cas d'un chemin origine inexistant ***************** ");
				origine = -1 ;
				destination = 7559;
				test.testScenario(mapName, 1,origine,destination);  
				
				System.out.println("----- Cas d'un chemin destination inexistant ------");
				origine =  36000;
				destination = 70000;
				test.testScenario(mapName, 1,origine,destination);  
			}
			
			@Test
			public void SenarioTempsBordeauxTest() throws Exception {
				String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bordeaux.mapgr";				
				DijkstraTest test = new  DijkstraTest();
				int origine;
				int destination;
				
				System.out.println("************************* Test de validité d'Algo sur une carte *************************");
				System.out.println("******************* Carte : bordeaux ******************* ");
				System.out.println("******************* Mode de Test : DISTANCE *******************");
				System.out.println();
				
				System.out.println("***************** Cas d'un chemin nul *****************");
				origine = 0 ;
				destination = 0;
				test.testScenario(mapName, 0,origine,destination);    // mode 1 :distance 
				
				System.out.println("*****************  Cas d'un chemin simple ***************** ");
				origine = 14646;
				destination = 7559;
				test.testScenario(mapName, 0,origine,destination);  
				
				System.out.println("*****************  Cas d'un chemin origine inexistant ***************** ");
				origine = -1 ;
				destination = 7559;
				test.testScenario(mapName, 0,origine,destination);  
				
				System.out.println("----- Cas d'un chemin destination inexistant ------");
				origine =  36000;
				destination = 70000;
				test.testScenario(mapName, 0,origine,destination);  
			}
	}
