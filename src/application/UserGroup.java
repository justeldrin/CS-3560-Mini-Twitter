package application;
import java.util.*;

public class UserGroup extends Entry{

	private List<Entry> userList = new ArrayList<Entry>();
	
	protected UserGroup()
	{
		ID = generateID();
		entryName = "Cool People";
	}
	
	protected UserGroup(String name)
	{
		ID = generateID();
		entryName = name;
	}
	
	protected void addUser(User inputUser)
	{
		userList.add(inputUser);
	}
	
	protected void addGroup(UserGroup inputGroup)
	{
		userList.add(inputGroup);
	}
	
	protected Entry getEntry(int inputID)
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
	
	public List<Entry> getList()
	{
		return userList;
	}

	//Visitor Pattern accepts any EntryVisitor
	@Override
	public int accept(EntryVisitor visitor) {
		return visitor.visit(this);
	}
}
