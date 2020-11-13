package application;

public interface EntryVisitor {

	int visit(User user);
	int visit(UserGroup userGroup);
}
