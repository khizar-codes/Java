import java.util.LinkedList;
import java.util.List;

public class MyItem implements IDedObject
{
	
	private int itemID;
	private int itemPrice;	
	private List<Integer> itemDescription = new LinkedList<Integer>();
	
	// Default Constructor
	public MyItem()
	{
		super();
	}
	
	// Argument Constructor
	public MyItem(int assignID, int assignPrice, List<Integer> assignDescription)
	{
		super();
	    this.itemID = assignID;
	    this.itemPrice = assignPrice;
	    this.itemDescription = assignDescription;
	}
	
	
	public String printID()
	{
		return ("Item's ID is: " + itemID);
	}
	
	// Functions to get ID,Price,Desc
	public int getID()
	{
		return itemID;
	}
	
	public int getPrice()
	{
		return itemPrice;
	}
	
	public List<Integer> getDescription()
	{
		return itemDescription;
	}
	
	// Functions to set ID, Price, Desc to one passed in 
	public void setID(int id)
	{
		this.itemID = id;
	}
	
	public void setPrice(int price)
	{
		this.itemPrice = price;
	}
	
	public void setDescription(List<Integer> Desc)
	{
		this.itemDescription = Desc;
	}
	
}