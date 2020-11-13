package application;

public class groupTotalVisitor implements EntryVisitor{

	@Override
	public int visit(User user) {
		return 0;
	}
	
	@Override
	public int visit(UserGroup user) {
		return 1;
	}

}
