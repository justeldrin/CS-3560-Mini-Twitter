package application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminUI extends Application {
	
	private TreeItem<Entry> currentEntry;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//UI LAYOUT BEGIN
			//TOP UI
			Label userIdLbl = new Label("Entry ID:        ");
			Label groupIdLbl = new Label("Group ID:        ");
			
			Button addUserBtn = new Button("Add User");
			Button addGroupBtn = new Button("Add Group");
			addUserBtn.setMinWidth(150);
			addGroupBtn.setMinWidth(150);
			
			VBox topCollectiontextA = new VBox(20,userIdLbl, groupIdLbl);
			VBox topCollectiontextB = new VBox(20,addUserBtn, addGroupBtn);
			HBox topCollection = new HBox(93, topCollectiontextA, topCollectiontextB);
		
			//MID UI
			Text infoText = new Text("Statistics: N/A");
			Button userViewBtn = new Button("Open User View");
			userViewBtn.setMinWidth(300);
			
			VBox midCollection = new VBox(10, userViewBtn, infoText);
			
			//BOT UI
			Button userTotalBtn = new Button("Show User Total");
			Button messageTotalBtn = new Button("Show Messages Total");
			Button groupTotalBtn = new Button("Show Group Total");
			Button positiveBtn = new Button("Show Positive Percentage");
			userTotalBtn.setMinWidth(150);
			messageTotalBtn.setMinWidth(150);
			groupTotalBtn.setMinWidth(150);
			positiveBtn.setMinWidth(150);

			VBox botCollectiontextA = new VBox(userTotalBtn, messageTotalBtn);
			VBox botCollectiontextB = new VBox(groupTotalBtn, positiveBtn);
			HBox botCollection = new HBox(botCollectiontextA, botCollectiontextB);
						
			VBox uiCollection = new VBox(20, topCollection, midCollection, botCollection);
			
			TreeItem<Entry> treeRoot = new TreeItem<>();
			treeRoot.setExpanded(true);
			currentEntry = treeRoot;
			
			TreeView<Entry> treeCollection = new TreeView<Entry>(treeRoot);
			treeCollection.setShowRoot(false);
					
			BorderPane root = new BorderPane();
			root.setPadding(new Insets(20));
			root.setLeft(treeCollection);
			root.setRight(uiCollection);
			Scene scene = new Scene(root,700,250);
			
			
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Mini Twitter: Admin Panel");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//UI LAYOUT END
			
			treeCollection.getSelectionModel().selectedItemProperty()
				.addListener((v, oldValue, newValue) -> {

					currentEntry = (newValue);
					
					if(newValue == treeRoot)
					{
						userIdLbl.setText("Entry ID: N/A     ");
					}
					else
					{
						userIdLbl.setText("Entry ID: " + Integer.toString(currentEntry.getValue().getID()));
					}
					if(newValue.getParent() == treeRoot || newValue == treeRoot)
					{
						groupIdLbl.setText("Group ID: N/A     ");
					}
					else
					{
						groupIdLbl.setText("Group ID: " + Integer.toString(newValue.getParent().getValue().getID()));
					}
			});
			
			userTotalBtn.setOnAction(e ->{
				int counter = 0;
				for(Entry entry: Admin.getInstance().getEntryList())
				{
					counter += entry.accept(new userTotalVisitor());
				}
				infoText.setText("Number of Users: " + counter);
			});
			
			messageTotalBtn.setOnAction(e ->{
				int counter = 0;
				for(Entry entry: Admin.getInstance().getEntryList())
				{
					counter += entry.accept(new messageTotalVisitor());
				}
				infoText.setText("Number of Tweets: " + counter);
			});
						
			groupTotalBtn.setOnAction(e ->{
				int counter = 0;
				for(Entry entry: Admin.getInstance().getEntryList())
				{
					counter += entry.accept(new groupTotalVisitor());
				}
				infoText.setText("Number of Groups: " + counter);
			});
			
			positiveBtn.setOnAction(e ->{
				double counter = 0;
				for(Entry entry: Admin.getInstance().getEntryList())
				{
					counter += entry.accept(new positivePercentVisitor());
				}
				int messageTotal = 0;
				for(Entry entry: Admin.getInstance().getEntryList())
				{
					messageTotal += entry.accept(new messageTotalVisitor());
				}
				infoText.setText("Percentage of Positive Messages: " + Math.round(counter/messageTotal*100) + "%");
			});
			
			
			addUserBtn.setOnAction(e ->
			{
				User tempUser = new User(NamePrompt.display());
	
				
				if(currentEntry.getValue() instanceof UserGroup)
				{
					UserGroup tempGroup = (UserGroup) currentEntry.getValue();
					((UserGroup) Admin.getInstance().getEntry(tempGroup.getID())).addUser(tempUser);
					makeBranch(tempUser, currentEntry);
				}
				else if(currentEntry.getValue() instanceof User)
				{
					if(currentEntry.getParent() == treeRoot)
					{
						makeBranch(tempUser, treeRoot);
						Admin.getInstance().addEntry(tempUser);
					}
					else
					{
						makeBranch(tempUser, currentEntry.getParent());
						UserGroup tempGroup = (UserGroup) currentEntry.getParent().getValue();
						((UserGroup) Admin.getInstance().getEntry(tempGroup.getID())).addUser(tempUser);
					}
					
				}
				else if (currentEntry.getParent() == treeRoot || currentEntry == treeRoot)
				{
					makeBranch(tempUser, treeRoot);
					Admin.getInstance().addEntry(tempUser);
				} 
			});
			
			addGroupBtn.setOnAction(e ->
			{
				
				UserGroup tempGroup = new UserGroup(NamePrompt.display());
				if(currentEntry.getParent() == treeRoot || currentEntry == treeRoot)
				{
					makeBranch(tempGroup, treeRoot);
					Admin.getInstance().addEntry(tempGroup);
				}
				else if(currentEntry.getValue() instanceof User)
				{

					makeBranch(tempGroup, currentEntry.getParent());
					UserGroup parentGroup = (UserGroup) currentEntry.getParent().getValue();
					((UserGroup) Admin.getInstance().getEntry(parentGroup.getID())).addGroup(tempGroup);
				}
				else
				{
					makeBranch(tempGroup, currentEntry);
					Admin.getInstance().addEntry(tempGroup);
				}
			});
			
			userViewBtn.setOnAction(e ->{
				if(currentEntry.getValue() instanceof User)
				{
					User tempUser = (User) currentEntry.getValue();
					tempUser.getPanel();
				}
			});
			
			
			
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private TreeItem<Entry> makeBranch(Entry entry, TreeItem<Entry> parent)
	{
		TreeItem<Entry> item = new TreeItem<>(entry);
		item.setExpanded(true);
		parent.getChildren().add(item);
		return item;
	}
	
	
	public static void open(String[] args) {
		launch(args);
	}
}
