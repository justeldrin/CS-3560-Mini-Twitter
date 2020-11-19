package application;

import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//User Panel view
public class UserPanelUI {
	
	public void display(User user) {
			//UI SetupBegin
			Stage window = new Stage();
			Label userID = new Label("User ID: " + user.getID());
			Button followBtn = new Button("Follow User");
			userID.setMinWidth(150.0);

			followBtn.setMinWidth(177.0);
			HBox topUI = new HBox(5, userID, followBtn);
			
			Label followLbl = new Label("Currently Following:");
			Label feedLbl = new Label("News Feed:");
			
			ListView<User> followingView = new ListView<User>();
			followingView.setItems(user.getFollowing());

			TextField tweetPrompt = new TextField("Enter Tweet");
			Button postBtn = new Button("Post Tweet");
			postBtn.setMinWidth(177.0);
			tweetPrompt.setMinWidth(150);
			
			HBox midUI = new HBox(tweetPrompt, postBtn);
			
			ListView<String> newsView = new ListView<String>();
			newsView.setItems(user.getTweets());
			
			VBox uiCollection = new VBox(10, topUI, followLbl, followingView, midUI, feedLbl, newsView);
		
			uiCollection.setPadding(new Insets(10));
			BorderPane root = new BorderPane();
			root.setCenter(uiCollection);
		
			Scene scene = new Scene(root, 350, 525);
			window.setTitle("User Panel: " + user);
			window.setScene(scene);
			window.show();
			//UI Setup End
			
			//Follow Button takes in ID, and attaches given user to user's observers and updates followingView
			followBtn.setOnAction(e ->
			{
				User tempUser = (User) Admin.getInstance().getEntry(FollowPrompt.display());
				tempUser.attach(user);
				followingView.getItems().add(tempUser);
			});
			
			//Post Button submits tweet in textfield and calls makeTweet
			postBtn.setOnAction(e -> 
			{				
				user.makeTweet(tweetPrompt.getText());
			});
			
			//Changes ListView whenever a new tweet is added to a user's feed
			user.getTweets().addListener(new ListChangeListener<String>() {
				@Override
				public void onChanged(Change<? extends String> arg0) {
					newsView.setItems(user.getTweets());
				}	
			});
	
	}
}
	
