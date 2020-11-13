package application;

public class messageTotalVisitor implements EntryVisitor{

	@Override
	public int visit(User user) {
		return user.getTweetCollection().size();
	}
	
	@Override
	public int visit(UserGroup user) {
		return 0;
	}
}
