package CMMS;

import java.util.ArrayList;

final class PartsList
{
	private ArrayList<String> partsList;
	
	protected PartsList()
	{
		partsList = new ArrayList<String>();
	}
	
	protected void addPart(String part)
	{
		partsList.add(part);
	}
	
	protected void print()
	{
		for( String part : partsList)
		{
			System.out.println(part);
		}
	}
	
}//End PartsList Class

