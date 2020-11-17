package application;

public class positivePercentVisitor implements EntryVisitor{

	private String[] positiveWords = new String[] {"cool", "nice", "excellent"};
	
	@Override
	public int visit(User user) {
		int counter = 0;
		if (user.getTweetCollection().size() == 0)
		{
			return 0;
		}
		for(String message: user.getTweetCollection())
		{
			
			String[] splitWords = message.split(" ");
			outerloop:
			for(String word: splitWords)
			{
				for (String positive: positiveWords)
				{
					if (word.toLowerCase().equals(positive.toLowerCase()))
					{
						counter++;
						break outerloop;
					}
				}
			}
		}
		return counter;
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
