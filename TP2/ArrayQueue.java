
public class ArrayQueue<AnyType> implements Queue<AnyType>
{
	private int size = 0;		//Nombre d'elements dans la file.
	private int startindex = 0;	//Index du premier element de la file
	private AnyType[] table;
   
	@SuppressWarnings("unchecked")
	public ArrayQueue() 
	{
		//A completer
		table =  (AnyType[]) new Object[1];
	
	}
	
	//Indique si la file est vide
	public boolean empty() 
	{ 
		return size == 0; 
	}
	
	//Retourne la taille de la file
	public int size() 
	{ 
		return size; 
	}
	
	//Retourne l'element en tete de file
	//Retourne null si la file est vide
	//complexité asymptotique: O(1)
	public AnyType peek()
	{
		//A completer
		if(this.empty())
			return null;
		else {
			return table[startindex]; 	
		}
		
	}
	
	//Retire l'element en tete de file
	//complexité asymptotique: O(1)
	public void pop() throws EmptyQueueException
	{
		//A completer
		if(!this.empty()) {
			table[startindex] =null;
			startindex++;
			size--;
		}
		else {
			throw new EmptyQueueException();
		}
	}
	
	//Ajoute un element a la fin de la file
	//Double la taille de la file si necessaire (utiliser la fonction resize definie plus bas)
	//complexité asymptotique: O(1) ( O(N) lorsqu'un redimensionnement est necessaire )
	public void push(AnyType item)
	{
		//A completer
		//si le nombre des elements dans le tableau sont egales à sa taille
		if (size + startindex == table.length) {// eza 3adad l element bl tableau 2add toulu
			resize(2);
		}
		table[startindex+size] = item;		// x|x|x|x|
		size++;	
		
	}
   
	//Redimensionne la file. La capacite est multipliee par un facteur de resizeFactor.
	//Replace les elements de la file au debut du tableau
	//complexité asymptotique: O(N)
	@SuppressWarnings("unchecked")
	private void resize(int resizeFactor)
	{
		//A completer
		// si la nouvelle capacité est plus petit que son size original
		if(size * resizeFactor < size) {	
			return ;
		}
		else {
			AnyType[] nouveauTableau = (AnyType[]) new Object[resizeFactor * size + 1]; // pour eviter size==0
				for(int i = 0; i < size; i++){
					nouveauTableau[i] = table[i +startindex];
				}
			table = nouveauTableau;
			startindex = 0;
		}
	}   
}

