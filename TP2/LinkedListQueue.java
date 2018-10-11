

public class LinkedListQueue<AnyType> implements Queue<AnyType>
{	
	// Un noeud de la file
	@SuppressWarnings("hiding")
	private class Node<AnyType> 
	{
		private AnyType data;
		private Node next;
		
		
		public Node(AnyType data, Node next) 
		{
			this.data = data;
			this.next = next;
		}

		public void setNext(Node next) 
		{
			this.next = next;
		}
		
		public Node<AnyType> getNext() 
		{
			return next;
		}
		
		public AnyType getData() 
		{
			return data;
		}
	}
   
	private int size = 0;		//Nombre d'elements dans la file.
	private Node<AnyType> last;	//Dernier element de la liste
	
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
		// si la liste est vide
		if(this.empty())
			return null;
		else {
			return this.last.getNext().getData();
			
		}
	}
	
	//Retire l'element en tete de file
	//complexité asymptotique: O(1)
	public void pop() throws EmptyQueueException
	{
		//A completer
		if(!this.empty()) {
			if(size==1) {
				last=null;size--;
			}
			else {
				last.setNext(last.getNext().getNext());
				size--;
			}
		}
		else {
			throw new EmptyQueueException();
		}
		
	}
	
	//Ajoute un element a la fin de la file
	//complexité asymptotique: O(1)
	public void push(AnyType item)
	{		
		//A completer	
		if(!empty()) {
			size++;
			Node node = new Node (item, last.next);
			this.last.next = node;	
			this.last=node;	
		}
		else {
			size++;
			Node node = new Node (item,null);
			this.last= node;
			this.last.next=node;
		}
		
	}  
}
