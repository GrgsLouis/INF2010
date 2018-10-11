import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> 
{
   static int p = 46337;
   
   int a, b;
   AnyType[] items;
      
   QuadraticSpacePerfectHashing()
   {
      a=b=0; items = null;
   }		
   
   QuadraticSpacePerfectHashing(ArrayList<AnyType> array)
   {
      AllocateMemory(array);
   }
   
   public void SetArray(ArrayList<AnyType> array)
   {
      AllocateMemory(array);
   }
   
   public int Size()
   {
      if( items == null ) return 0;
      
      return items.length;
   }
   
   public boolean contains(AnyType x ) // --> Return true if x is present
   {
      if( Size() == 0 ) return false;
      
      if( a == 0 ) return ( items[0].equals(x) );
      int m = items.length;
      int index = ( ( a*x.hashCode() + b ) % p ) % m;
      
      index = ( index < 0 ? index + m : index );
      
      return ( ( items[index] != null ) &&
             ( items[index].equals(x) ) );
   }
   
   
   
   private int genererIndex(AnyType x) {
	   int m= items.length;
	   return ( ( a*x.hashCode() + b ) % p ) % m;
   }
   
   
   @SuppressWarnings("unchecked")
   private void AllocateMemory(ArrayList<AnyType> array)
   {
      Random generator = new Random( System.nanoTime() );
      
      if(array == null || array.size() == 0)
      {
         // A completer
    	  items =  null;
         return;
      }
      if(array.size() == 1)
      {
         a = b = 0;
         // A completer			
        items =  (AnyType[]) new Object[1];
        items[genererIndex(array.get(0))]= array.get(0);
         return;
      }
      
      do
      {
         items = null;
         // A completer
         //un tableau de taille m = n^2 = la taille de array multiplié par lui meme
         items = (AnyType[]) new Object[array.size()*array.size()];
         
         // des valeurs aléatoires pour a et b selon les conditions qu'on a dans la donnée
         a=1+generator.nextInt(p-1); // 0<a<p
         b=generator.nextInt(p);	// 0<=b<p
         
         for(int i=0 ;i<array.size();i++) {        	 
        	 items[genererIndex(array.get(i))]=array.get(i);
        	 
        	
         }  
         
         
      }
      
      while( collisionExists( array ) );
   }
   
   @SuppressWarnings("unchecked")
   private boolean collisionExists(ArrayList<AnyType> array)
   {
      // A completer
	   for(int i=0; i<array.size();i++) {
		   if(items[genererIndex(array.get(i))] !=array.get(i)) {
			   return true;
		   }
	   }
	   
      
      return false;
   }
}
