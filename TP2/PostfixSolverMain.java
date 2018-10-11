import java.io.*;
import java.util.Stack;

public class PostfixSolverMain 
{
	public static void main(String[] args) throws IOException 
	{
		Stack<Double> stack = new Stack<Double>();
		
		String s = "25 5 2 * + 15 3 / 5 - +";
		
		//L'expression est separee en tokens selon les espaces
		double r=0;
		for(String token : s.split("\\s")) 
		{
			  /* si le token est different de "+ - * /", c'est a dire qu'on a les nombres, on les transform sous forme de double
			   	 et on les met dans le stack	*/ 
			  if(!(token.contentEquals("+")) && !(token.contentEquals("-")) && !(token.contentEquals("*")) && !(token.contentEquals("/")) ) {
				  stack.push( Double.parseDouble(token));

				}
			else {
				/* les elements qu'on a mit dans le stack on va les avoir en faisant pop et puis on effectue les operations de calcule
				 * selon l'operateur */
				double x=stack.pop();
				double y=stack.pop();
				switch(token) {
					case "+" : r=x+y;
						break;
				    case "-" : r=y-x;
				    	break;
				    case "*" : r=x*y;
				    	break;
				    case "/" : r=y/x;
				    	break;
				
				}
				/* on push le resultat du calcule obtenue des deux nombre qu'on les a pop a la ligne 25 et 26, pour qu'on puisse refaire le
				 * calcule sur ce resultat jusqu'a temps qu'on arrive a la fin de notre string s */
				stack.push(r);
			}
		}
			
			
		System.out.println("25 + 5*2 + 15/3 - 5 = "+stack.peek());
		if(stack.peek() == 35)
			System.out.println("It's all good");
		else
			System.out.println("Erreur: mauvais resultat");
     }
}

