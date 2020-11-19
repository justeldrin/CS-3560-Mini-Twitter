package application;

//Visitor interface entryVisitor
public interface EntryVisitor {

	int visit(User user);
	int visit(UserGroup userGroup);
}
