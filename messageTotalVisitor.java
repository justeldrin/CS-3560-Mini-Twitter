package application;

public class messageTotalVisitor implements EntryVisitor{

	@Override
	public int visit(User user) {
		return user.getTweetCollection().size();
	}
	
	@Override
	public int visit(UserGroup userGroup) {
		int counter = 0;
		for(Entry entry: userGroup.getList())
		{
			if(entry instanceof User)
			{
				counter += visit((User)entry);
			}
			else if (entry instanceof UserGroup)
			{
				counter += visit((UserGroup) entry);
			}
		}
		
		return counter;
	}
}
