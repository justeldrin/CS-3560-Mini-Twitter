package application;

public class userTotalVisitor implements EntryVisitor{

	@Override
	public int visit(User user) {
		return 1;
	}
	
	@Override
	public int visit(UserGroup userGroup) {
		int counter = 0;
		for(Entry entry: userGroup.getList())
		{
			if (entry instanceof User)
			{
				counter++;
			}
			else if (entry instanceof UserGroup)
			{
				counter += visit((UserGroup) entry);
			}
		}
		
		return counter;
	}
}
