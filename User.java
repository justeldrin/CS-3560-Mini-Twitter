package application;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User extends Subject implements Observer {
	
	
	
	private String currentTweet = "";
	private UserPanelUI userPanel = new UserPanelUI();	
	private List<String> userTweetcollection = new ArrayList<String>();
	private ObservableList<User> currentFollowing = FXCollections.observableArrayList();
	private ObservableList<String> tweetFeed = FXCollections.observableArrayList();
	
	
	//Constructors
	//Default Constructor
	public User()
	{
		ID = generateID();
		entryName = "Average Joe";
	}
	
	public User(String name)
	{
		ID = generateID();
		entryName = name;
	}
	
	public User(User user)
	{
		this.ID = user.getID();
		this.entryName = user.getName();
		
	}
	
	public void makeTweet(String tweet)
	{
		currentTweet = tweet;
		tweetFeed.add(this + ": " + currentTweet);
		this.userTweetcollection.add(currentTweet);
		notifyObservers(this);
	}
	
	public void addFollowing(User user)
	{
		currentFollowing.add(user);
	}
	
	@Override
	public void update(User user) 
	{
		this.tweetFeed.add(user + ": " + user.getCurrentTweet());
	}
	
	@Override
	public int accept(EntryVisitor visitor) {
		return visitor.visit(this);
	}
	
	private String getCurrentTweet()
	{
		return currentTweet;
	}
	
	public List<String> getTweetCollection()
	{
		return userTweetcollection;
	}
	
	public void getPanel()
	{
		userPanel.display(this);
	}

	public ObservableList<String> getTweets()
	{
		return tweetFeed;
	}
	
	public ObservableList<User> getFollowing()
	{
		return currentFollowing;
	}


}
