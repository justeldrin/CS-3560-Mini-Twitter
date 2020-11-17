package application;

public class groupTotalVisitor implements EntryVisitor{

	@Override
	public int visit(User user) {
		return 0;
	}
	
	@Override
	public int visit(UserGroup userGroup) {
		
		int counter = 0;
		for(Entry entry: userGroup.getList())
		{
			if (entry instanceof UserGroup)
			{
				counter += visit((UserGroup)entry);
			}
		}
		
		return 1 + counter;
	}

}
