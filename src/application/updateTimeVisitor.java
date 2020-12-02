package application;

public class updateTimeVisitor implements EntryVisitor{
	
	private static int lastUpdatedUserID = -1;
	private static long lastUpdatedUserTime = -1;
	
	@Override
	public int visit(User user) {
		if(lastUpdatedUserID == -1 && lastUpdatedUserTime == -1)
		{
			lastUpdatedUserID = user.getID();
			lastUpdatedUserTime = user.getLastUpdateTime();
		}
		if (user.getLastUpdateTime() > lastUpdatedUserTime)
		{
			lastUpdatedUserID = user.getID();
			return user.getID();
		}
		else
			return lastUpdatedUserID;
	}
	
	@Override
	public int visit(UserGroup userGroup) {
		for(Entry entry: userGroup.getList())
		{
			if(entry instanceof User)
			{
				lastUpdatedUserID = visit((User)entry);
			}
			else if (entry instanceof UserGroup)
			{
				lastUpdatedUserID = visit((UserGroup) entry);
			}
		}
		
		return lastUpdatedUserID;
	}
}
