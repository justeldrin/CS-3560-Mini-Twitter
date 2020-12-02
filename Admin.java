package application;
import java.util.*;


public class Admin {
	//Singleton Pattern 
	private static Admin pointer;
	private static HashSet<Entry> entryList; 
	
	//getInstance ensures only one instance of Admin can exist
	public static Admin getInstance() {

		if (pointer == null) {
			synchronized (Admin.class){	
				if(pointer == null) {
					pointer = new Admin();
				}
			}
		}
		return pointer;
	}
	
	private Admin()
	{
		entryList = new HashSet<Entry>();
	}
	
	public void addEntry(Entry entry)
	{
		checkDuplicates(entry);
		entryList.add(entry);
	}
	
	//Checks within UserGroups if there is a duplicate ID 
	private void checkDuplicates(Entry entry)
	{
		Iterator<Entry> itr = entryList.iterator();
		while(itr.hasNext()) 
		{
			Entry tempEntry = itr.next();
			if(tempEntry instanceof UserGroup)
			{
				for(Entry checkEntry: ((UserGroup) tempEntry).getList())
				{
					if (checkEntry.equals(entry))
					{
						entry.setID(entry.generateID());
						checkDuplicates(entry);
					}
				}
			}
		}	
	}
	
	public HashSet<Entry> getEntryList()
	{
		return entryList;
	}
	
	//Searches for Entry by ID, iterates through UserGroup when necessary
	public Entry getEntry(int ID)
	{
		Entry output = null;
		Iterator<Entry> itr = entryList.iterator();
		while(itr.hasNext())
		{
			Entry tempEntry = itr.next();
			if(tempEntry.getID() == ID)
			{
				output = tempEntry;
				break;
			}
			else if(tempEntry instanceof UserGroup)
			{
				for(Entry entry: ((UserGroup) tempEntry).getList())
				{
					if(entry.getID() == ID)
					{
						output = entry;
						break;
					}
					else if(entry instanceof UserGroup)
					{
						output = ((UserGroup) entry).getEntry(ID);
					}
				}
			}
		}
		return output;
	}
}
