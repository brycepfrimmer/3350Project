package CMMS;

import java.util.ArrayList;

public final class PartsList
{
	private ArrayList<String> partsList;
	
	public PartsList()
	{
		partsList = new ArrayList<String>();
	}
	
	public boolean addPart(String part)
	{
		boolean success = false;
		success = part != null && !part.trim().isEmpty();
		if (success) { partsList.add(part); }
		return success;
	}
	
	public boolean removePart(String part){
		return partsList.remove(part);
	}
	
	public boolean print()
	{
		for( String part : partsList)
		{
			System.out.println(part);
		}
		return true;
	}
	
	public ArrayList<String> getPartsList(){
		return partsList;
	}
	
	public boolean isEmpty(){
		return partsList.isEmpty();
	}
	
}//End PartsList Class

