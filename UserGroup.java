package application;
import java.util.*;

public class UserGroup extends Entry{

	
	private List<Entry> userList = new ArrayList<Entry>();
	
	public UserGroup()
	{
		ID = generateID();
		entryName = "Cool People";
	}
	
	public UserGroup(String name)
	{
		ID = generateID();
		entryName = name;
	}
	
	public void addUser(User inputUser)
	{
		userList.add(inputUser);
	}
	
	public void addGroup(UserGroup inputGroup)
	{
		userList.add(inputGroup);
	}
	
	public Entry getEntry(int inputID)
	{
		Entry output = null;
		Iterator<Entry> itr = userList.iterator();
		while(itr.hasNext()) 
		{
			Entry tempUser = itr.next();
			if(tempUser.getID() == inputID)
			{
				output = tempUser;
			}
			else if(tempUser instanceof UserGroup)
			{
				output = ((UserGroup) tempUser).getEntry(inputID);
			}
				
		}
		return output;
	}

	@Override
	public int accept(EntryVisitor visitor) {
		return visitor.visit(this);
	}
}
