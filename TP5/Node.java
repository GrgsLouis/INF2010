import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maitr
 */
public class Node {

	public int ordre;
	public Node parent;

	private int valeur;
	private ArrayList<Node> enfants;

	public Node(int valeur) {
		this.valeur = valeur;
		ordre = 0;
		this.parent = null;
		enfants = new ArrayList<Node>();
	}

	public Node(int valeur, Node parent) {
		ordre = 0;
		this.valeur = valeur;
		this.parent = parent;
		enfants = new ArrayList<Node>();
	}

	public int getVal() {
		return valeur;
	}

	public ArrayList<Node> getEnfants() {
		return enfants;
	}

	public void addEnfant(Node enfant) {
		enfants.add(enfant);
	}

	public boolean removeEnfant(Node enfant) {
		if (enfants.contains(enfant)) {
			enfants.remove(enfant);
			return true;
		}
		return false;
	}

	public void removeEnfants(ArrayList<Node> enfants) {
		this.enfants.removeAll(enfants);
	}

	public Node fusion(Node autre) throws DifferentOrderTrees {
		// A completer
		Node ValReturn=null;
		if (this.ordre != autre.ordre) {
			throw new DifferentOrderTrees();
		}
		
		if(this.parent == null && autre.parent == null) {
				if (this.valeur > autre.valeur) {
					this.enfants.add(autre);
					autre.parent = this;
					this.ordre++;
					return ValReturn=this;
				}
				else {
					autre.enfants.add(this);
					this.parent = autre;
					autre.ordre++;
					return ValReturn = autre;
				}
			}
		return ValReturn;
	}

	private void moveUp() {
		// A completer
		int temp=this.valeur;
		if(parent!=null) {
		  this.valeur = parent.valeur;
		  parent.valeur = temp;
		}
	}

	public ArrayList<Node> delete() {
		// A completer
		Node noeud = this;
		while (noeud.parent != null) {
			noeud.moveUp();
			noeud = noeud.parent;
		}
		for (int i=0;i < noeud.enfants.size();i++) {
			noeud.enfants.get(i).parent = null;
		}
		return noeud.enfants;
	}

	public Node findValue(int valeur) {
		// A completer
		Node ElementRechercher=null;
		if (this.valeur == valeur) {
			return ElementRechercher =this;
		}
		else {
			for (int i=0; i<enfants.size() && ElementRechercher ==null;i++) {
				if(valeur <= enfants.get(i).valeur) {
					 ElementRechercher =enfants.get(i).findValue(valeur);
				}
			}
		}
		return ElementRechercher;
	}

	public ArrayList<Integer> getElementsSorted() {
		// A completer
		ArrayList<Integer> ArrayFinal = new ArrayList<Integer>();
		Node maximal;
		ArrayFinal.add(valeur); // c'est l'element 13
		while(!enfants.isEmpty()){
				maximal=PlusGrandNode();
				maximal.moveUp();
				ArrayFinal.add(valeur);
				while(!maximal.enfants.isEmpty()){
						maximal = maximal.PlusGrandNode();
						maximal.moveUp();
					}
		maximal.parent.removeEnfant(maximal);
		}
		return ArrayFinal;
	}
	
	
	public Node PlusGrandNode() {
		Node PlusGrand = new Node(0);
		for (int i=0;i<this.enfants.size();i++) {
			if(this.enfants.get(i).valeur > PlusGrand.valeur) {
				PlusGrand= this.enfants.get(i);
			}
		}
		return PlusGrand;
	}

	public void print() {
        print("", true);
    }

    private void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "'-- " : "|-- ") + valeur);
        for (int i = 0; i < enfants.size() - 1; i++) {
            enfants.get(i).print(prefix + (isTail ? "    " : "|   "), false);
        }
        if (enfants.size() > 0) {
            enfants.get(enfants.size() - 1)
                    .print(prefix + (isTail ?"    " : "|   "), true);
        }
    }
}
