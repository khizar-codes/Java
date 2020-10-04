import java.util.LinkedList;

public class IDedLinkedList <AnyType extends IDedObject>
{

   private LinkedList<AnyType> list;

   /**default constructor*/
   public IDedLinkedList() 
   {
       super();
       list = new LinkedList<AnyType>();
   }

   /**parameterized constructor*/
   public IDedLinkedList(LinkedList<AnyType> list) 
   {
       super();
       this.list = list;
   }
  
   /**to empty the list*/
   public void makeEmpty() 
   {
       list.clear();
   }
  
   /**to find the item using its id*/
   public AnyType findID(int id) 
   {
  
       for(AnyType item : list)
       {
           if(item.getID() == id)
           {
               return item;
           }
       }
       return null;
   }
  
   /**to insert item in the list at begining */
   public boolean insertAtFront(AnyType item) 
   {
       list.addFirst(item);
       return true;
   }
  
   /**to delete the first item*/
   public AnyType deleteFromFront() 
   {
       return list.pollFirst();
   }
  
   /**to delete the item using its id*/
   public AnyType delete(int id) 
   {
       for(int i = 0 ; i< list.size();i++)
       {
           if(list.get(i).getID() == id)
           {
               return list.get(i);
           }
       }
       return null;
   }
  
   /**to print the sum of ids*/
   public int printTotal() 
   {
      
       if(list.isEmpty())
           return -1;
       
       int sum = 0;
       for(AnyType item : list)
       {
           sum = sum + item.getID();
       }
       return sum;
   }
		  
}
