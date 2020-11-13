package application;

public class userTotalVisitor implements EntryVisitor{

	@Override
	public int visit(User user) {
		return 1;
	}
	
	@Override
	public int visit(UserGroup user) {
		return 0;
	}
}
