package application;
import java.util.*;


public class Admin {
	//Singleton Pattern
	
	private static Admin pointer;
	public static HashSet<Entry> entryList; 
	
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
		entryList.add(entry);
	}
	
	public HashSet<Entry> getEntryList()
	{
		return entryList;
	}

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
