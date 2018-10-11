import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType>
{
   static int p = 46337;
   
   QuadraticSpacePerfectHashing<AnyType>[] data;
   int a, b;
   
   LinearSpacePerfectHashing()
   {
      a=b=0; data = null;
   }
   
   LinearSpacePerfectHashing(ArrayList<AnyType> array)
   {
      AllocateMemory(array);
   }
   
   public void SetArray(ArrayList<AnyType> array)
   {
      AllocateMemory(array);
   }
      
   
   public int genererIndex(AnyType x){
	   int m= data.length;
	   return ( ( a*x.hashCode() + b ) % p ) % m;
   }
   @SuppressWarnings("unchecked")
   private void AllocateMemory(ArrayList<AnyType> array)
   {
      Random generator = new Random( System.nanoTime() );
      
      if(array == null || array.size() == 0)
      {
         // A completer
    	 data=null;
         return;
      }
      if(array.size() == 1)
      {
         a = b = 0;
         
         // A completer
         data = (QuadraticSpacePerfectHashing<AnyType>[]) new Object[1]; 
         data[genererIndex(array.get(0))]=new QuadraticSpacePerfectHashing<AnyType>(array);
         return;
      }
      
      // A completer
      
     ArrayList<AnyType>[] listeTableau=  new ArrayList[array.size()];
      a=1+generator.nextInt(p-1); // 0<a<p
      b=generator.nextInt(p);	// 0<=b<p
      
      data = new QuadraticSpacePerfectHashing[array.size()];
      
      for(int i=0;i<array.size();i++) {
    	  listeTableau[i]= new ArrayList<AnyType>(); 
    	  data[i]=new QuadraticSpacePerfectHashing<AnyType>();
      }
      for(int i=0; i<array.size();i++) {
    	  listeTableau[genererIndex(array.get(i))].add(array.get(i));	  
      }
           
      for(int i=0;i<listeTableau.length;i++) {
    	  data[i]=new QuadraticSpacePerfectHashing<AnyType>(listeTableau[i]);
      }
   }
   
   public int Size()
   {
      if( data == null ) return 0;
      
      int size = 0;
      for(int i=0; i<data.length; ++i)
      {
         size += (data[i] == null ? 1 : data[i].Size());
      }
      return size;
   }
   
   public boolean contains(AnyType x )
   {
      if( Size() == 0 ) return false;
      
      int m = data.length;
      
      int index = ( ( a*x.hashCode() + b ) % p ) % m;
      
      index = ( index < 0 ? index + m : index );
      
      return ((data[index] != null) && (data[index].contains(x)));
   }
}
