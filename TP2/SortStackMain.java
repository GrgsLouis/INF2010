import java.util.Random;
import java.util.Stack;


public class SortStackMain 
{
	static final int COUNT = 30;
	static final int MAX_VALUE = 1000;
	
	public static void main(String[] args) 
	{
		boolean sortIsGood = true;
		
		Random generator = new Random( System.nanoTime() );
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i = 0; i < COUNT; i++)
			stack.push(generator.nextInt(MAX_VALUE));
		
		stack = sortStack(stack);
		
		boolean countIsGood = size(stack) == COUNT;
			
		int tmp = stack.pop();
		while(!stack.isEmpty())
		{
			System.out.print(tmp + ", ");
			
			if(tmp > stack.peek())
				sortIsGood = false;
			
			tmp = stack.pop();
		}
		System.out.println(tmp);
		
		if(!countIsGood)
			System.out.println("Erreur: il manque des elements dans la pile");
		 if(!sortIsGood)
			System.out.println("Erreur: le trie a echoue");
		else
			System.out.println("It's all good");
	}
    
	private static int size(Stack<Integer> stack) {
		//A completer
		int counter = 0;
		int elementRetire;
		Stack<Integer> resultat  = new Stack<Integer>();
		while (!stack.isEmpty()) {	/* ici on fait qu'a chaque fois on retire un element du stack on ajoute 1 au compteur "counter"
		 							   puis a la fin on a besoin que ces elements qu'on a retirer du stack, il faut les remettre dans le stack parce qu'on 
		 							   le reutilise (le stack quon a) a la ligne 24	alors il faut quon aille les elements dans le stack*/
			elementRetire = stack.pop();
			counter++;
			resultat.push(elementRetire);
		}
		while(!resultat.isEmpty()) { /*ici on remet les elements retirer du stack et mit dans "resultat" dans le stack */
			stack.push(resultat.pop());
		}
		return counter;
		
    }
    
	static Stack<Integer> sortStack(Stack<Integer> stack)
	{
		//A completer
		if (stack.isEmpty()) { /*on verifie que le stack n'est pas vide*/
	        return stack;
	    }
	    int ElementSeparateur = stack.pop();	/*c'est l'element celon lequel on compare les elements du stack et on les separes
	     										  dans deux stacks differents, une qui contient les valeurs plus petit que cet element et une plus grand */
	    Stack<Integer> PlusGrandQueSeparateur  = new Stack<Integer>(); /* c'est le stack qui contient les elements plus grand que l'elementSeparateur */
	    Stack<Integer> PlusPetitQueSeparateur= new Stack<Integer>(); /* c'est le stack qui contient les elements plus petit que l'elementSeparateur */
	    while(!stack.isEmpty()) {
	        int y = stack.pop();
	      //on compare l'element qui est au dessus de la pile avec le separateur et selon qu'il est (> Separateur ou < Separateur)
	      //on le place dans le bonne stack
	        if (y > ElementSeparateur) { 
	        	PlusGrandQueSeparateur.push(y);
	        }
	        
	        else {
	        	PlusPetitQueSeparateur.push(y);
	        }
	        
	    }
	    
	    sortStack(PlusGrandQueSeparateur); /* on place les elements dans l'ordre croissant dans le stack PlusGrandQueSeparateur*/ 
	    sortStack(PlusPetitQueSeparateur); /* on place les elements dans l'ordre croissant dans le stack PlusPetitQueSeparateur*/
	    
	    Stack<Integer> MergedStack = new Stack<Integer>(); // on merge les deux Stacks qui sont deja tries du plus petit au plus grand
	      
	      while (!PlusPetitQueSeparateur.isEmpty()) {
	    	  MergedStack.push(PlusPetitQueSeparateur.pop()); //on place les elements du stack qui contient les elements plus petit du separateur dans le stack MergedStack
	      }
	      
	      MergedStack.push(ElementSeparateur); // puis on met le separateur
	      
	      while (!PlusGrandQueSeparateur.isEmpty()) { //on place les elements du stack qui contient les elements plus grand du separateur dans le stack MergedStack
	    	  MergedStack.push(PlusGrandQueSeparateur.pop());
	      }
	      // ici le MergedStack contient tout les elements tries
	      while (!MergedStack.isEmpty()) { //on vide le contenu du MergedStack dans le stack
	        stack.push(MergedStack.pop());
	      }
	      return stack; // on return le stack que maintenant contient les elements tries
	}
}
	 