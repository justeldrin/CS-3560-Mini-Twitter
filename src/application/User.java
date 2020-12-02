package application;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User extends Subject implements Observer {
	
	private long lastUpdateTime;
	private String currentTweet = "";
	private UserPanelUI userPanel = new UserPanelUI();	
	//User Tweet Collection collects user's own tweets
	private List<String> userTweetcollection = new ArrayList<String>();
	//Current following used to update userView on open
	private ObservableList<User> currentFollowing = FXCollections.observableArrayList();
	private ObservableList<String> tweetFeed = FXCollections.observableArrayList();
	
	
	//Constructors
	//Default Constructor
	protected User()
	{
		ID = generateID();
		entryName = "Average Joe";
		creationTime = System.currentTimeMillis();
		lastUpdateTime = creationTime;
	}
	
	protected User(String name)
	{
		ID = generateID();
		entryName = name;
		creationTime = System.currentTimeMillis();
		lastUpdateTime = creationTime;


	}
	
	protected User(User user)
	{
		this.ID = user.getID();
		this.entryName = user.getName();
		creationTime = System.currentTimeMillis();
		lastUpdateTime = creationTime;

	}
	//End Constructors
	
	//Takes in tweet String then updates observers
	protected void makeTweet(String tweet)
	{
		currentTweet = tweet;
		tweetFeed.add(this + ": " + currentTweet);
		this.userTweetcollection.add(currentTweet);
		this.lastUpdateTime = System.currentTimeMillis();
		System.out.println(this + "'s last updated time is: " + new Date(lastUpdateTime));
		notifyObservers(this);
	}
	
	//Adds user to currentFollowing
	protected void addFollowing(User user)
	{
		currentFollowing.add(user);
	}
	
	//Observer Pattern update
	@Override
	public void update(User user) 
	{
		this.tweetFeed.add(user + ": " + user.getCurrentTweet());
		this.lastUpdateTime = System.currentTimeMillis();
		System.out.println(this + "'s last updated time is: " + new Date(lastUpdateTime));
	}
	
	///Visitor pattern accepts any EntryVisitor
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
	
	public long getLastUpdateTime()
	{
		return lastUpdateTime;
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
