package PolyNet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class PolyNet {
    private PolyNetNode[] nodes;

    public PolyNet(PolyNetNode[] nodes) {
        this.nodes = nodes;
    }

    // MST (using Prim's algorithm).
    public int computeTotalCableLength() {
        int LongueurFinale = 0;
        PriorityQueue<PolyNetConnection> knownConnections = new PriorityQueue<>();
        HashSet<PolyNetNode> visitedNodes = new HashSet<>();
        
        if(nodes.length != 0)// si il y a un noeud on l'ajoute
        {
        	visitedNodes.add(nodes[0]); // le premier noeud est ajouter ici
        	ArrayList<PolyNetConnection> NextNoeuds = nodes[0].getConnections(); //NextNoeud contient tout les connections du premier noeud
        	for(int i = 0; i < NextNoeuds.size(); i++)
        	{
        		knownConnections.add(NextNoeuds.get(i));	// on met tout les connections du premier noeud dans le "KnownConnections"
        	}
        }
        
        for(int i = 0; i < nodes.length -1;)
        {
        	PolyNetConnection Noeud = knownConnections.poll(); // on met dans Noeud tout les connections connues
        	ArrayList<PolyNetConnection> NextNoeuds = Noeud.getConnectedNode().getConnections(); // NextNoeuds contient les connections des ConnectedNode du Noeud
        	if (!visitedNodes.contains(Noeud.getConnectedNode()))
        	{
        		visitedNodes.add(Noeud.getConnectedNode());	// on met tout les nodes connecter avec le "Noeud" dans visitedNodes
        		LongueurFinale += Noeud.getDistance();	
        		for(int j = 0; j < NextNoeuds.size(); j++)
        		{
        			knownConnections.add(NextNoeuds.get(j));
        		}
        i++;
        	}	
        }
        return LongueurFinale; // on retoune 
    }
}
