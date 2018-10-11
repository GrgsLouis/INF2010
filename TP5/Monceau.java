import java.util.ArrayList;

public class Monceau {
	ArrayList<Node> arbres;

	public Monceau() {
		arbres = new ArrayList<Node>();
	}

	public void fusion(Monceau autre) {
		// A completer
		/*on trouve le grand monceau pour que son size soit le size de la boucle for*/
		Monceau GrandMonceau, PetitMonceau;
		if(autre.arbres.size() >= arbres.size()) {
			GrandMonceau = autre;
			PetitMonceau = this;
		}
		else {
			GrandMonceau = this;
			PetitMonceau = autre;
		}

		ArrayList<Node> ArbreQuiContientMinEtMax = new ArrayList<Node>();
		Node RETENUE = null;
		int ForSize = GrandMonceau.arbres.size();/*size de la boucle for = nb elements dans le grand monceau*/
		for (int j =0; j<ForSize; j++) { 
			//On prend les elements du GrandMonceau et PetitMonceau et on les met dans ArbreQuiContientMinEtMax
			if(RETENUE!=null)	// La RETENUE sera modifie plus tard selon le case (ArbreQuiContientMinEtMax.size())
				ArbreQuiContientMinEtMax.add(RETENUE);
			if(j<PetitMonceau.arbres.size()) {
				if(PetitMonceau.arbres.get(j) != null) {
					ArbreQuiContientMinEtMax.add(PetitMonceau.arbres.get(j));
				}
			}
			if(GrandMonceau.arbres.get(j)!=null) {
				ArbreQuiContientMinEtMax.add(GrandMonceau.arbres.get(j));
			}
			//On modifie GrandMonceau parce qu'elle contient le nombre d'elements qu'on va avoir à la fin
			switch (ArbreQuiContientMinEtMax.size()) {
				case 0:
					//On ne fait rien GrandMonceau ne contient rien
					break;
				case 1:
					//On copie l'arbre à l'indice 'j'
					GrandMonceau.arbres.set(j,ArbreQuiContientMinEtMax.get(0));
					if(RETENUE!=null) RETENUE = null; //Si on a utilisé la RETENUE, il faut la vider
					break;
				case 2:
					GrandMonceau.arbres.set(j,null); //on a pas d'arbre d'ordre j
					try {
						//on les fusionnes et place l’arbre résultat, d’ordre j+1, dans la retenue.
						RETENUE = ArbreQuiContientMinEtMax.get(0).fusion(ArbreQuiContientMinEtMax.get(1));
					}
					catch (DifferentOrderTrees differentOrderTrees) {
						// en cas d'exception un msg sera envoyer
						differentOrderTrees.printStackTrace();
					}
					break;
				case 3:
					GrandMonceau.arbres.set(j,ArbreQuiContientMinEtMax.get(0)); //on place dans le monceau final qui est GrandMonceau
					try {
						RETENUE = ArbreQuiContientMinEtMax.get(1).fusion(ArbreQuiContientMinEtMax.get(2));
					}
					catch (DifferentOrderTrees differentOrderTrees) {
						// en cas d'exception un msg sera envoyer
					differentOrderTrees.printStackTrace();
				}
					break;
			}
			//On place la RETENUE dans GrandMonceau
			if(j == ForSize-1 && RETENUE != null) {
				GrandMonceau.arbres.add(RETENUE);
			}
			//On vide ArbreQuiContientMinEtMax pour qu'on la modifie à chaque itèration:
			ArbreQuiContientMinEtMax.clear();
		}
		/* après avoir modifier GrandMonceau, elle contient tout les informations qu'on a besoin
		on copie ces informations dans this.arbres qui est l'arbre qu'on a, on laisse pas les information dans
		GrandMonceau parce que c'est une variable locale qui n'existera plus en dehors de la fonction
		donc les changements serai fait pour rien dans ce cas*/
		this.arbres = GrandMonceau.arbres;
	}

	public void insert(int val) {
		// A completer
		Node noeud = new Node(val);
		Monceau Nmonceau = new Monceau();
		Nmonceau.arbres.add(noeud);
		fusion(Nmonceau);
	}

	public boolean delete(int val) {
		// A completer
		Monceau Nmonceau = new Monceau();
		boolean Deleted=false;	// boolean qui indique que le noeud est removed
		//On verifie si le noeud qui a la valeur "val" existe dans l'arbre, si oui "Deleted" est true sinon c'est false
		for (int i=0;i< arbres.size();i++){
			if(arbres.get(i)!=null && arbres.get(i).findValue(val)!=null) { //Si le noeud n'est pas null et sa valeur n'est pas null aussi
					Deleted=true;			
			}
		}
		// Si on ne trouve pas l'element dans l'abre
		if (Deleted == false) {
			return Deleted;
			}
		//Si on le trouve
		else
			for (int i=0;i< arbres.size();i++) {
				if(arbres.get(i)!=null && arbres.get(i).findValue(val) != null) {
					//on met dans Nmonceau.arbres toutes les sous arbres obtenue apres qu'on delete le noeud rechercher *celui qui contien la (val)"
					Nmonceau.arbres = arbres.get(i).findValue(val).delete();
					arbres.set(i, null);
					//ici on fait un appel recursif au cas ou on a 2 valeurs qui sont egaux, alors il faut supprimer les deux
					Nmonceau.delete(val);
					//fusion le Nmonceau avec le monceau qu'on a au depart (this)
					fusion(Nmonceau);
				}
			}
		return true;
	}

	public void print() {
		for (Node node : arbres) {
			if (node != null) { // condition ajouter
			System.out.println("\n\nordre : " + node.ordre);
			node.print();
			}
		}
	}

}
