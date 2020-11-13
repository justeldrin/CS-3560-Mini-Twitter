package application;

import java.util.Random;

public abstract class Entry {
	int ID;
	String entryName;
	
	public int generateID()
	{
		String tempID = "";
		Random ran = new Random();
		
		for (int i = 0; i < 7; i++)
		{
			int tempNum = ran.nextInt(9);
			tempID = tempID + tempNum;
		}
		return Integer.parseInt(tempID);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entry other = (Entry) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	
	public abstract int accept(EntryVisitor visitor);

	public int getID()
	{
		return ID;
	}
	
	public String getName()
	{
		return entryName;
	}
	
	public String toString()
	{
		return entryName;
	}
}
