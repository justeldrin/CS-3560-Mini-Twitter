package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Window that opens up to prompt users to follow another user through given ID
public class FollowPrompt {
	
	private static String output;
	
	//Displays prompt and outputs the inputted ID
	public static int display()
	{
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Enter User ID");
		window.setMinHeight(200);
		window.setMinWidth(250);
		
		Label promptLbl = new Label("Enter ID of User you want to follow");
		
		Button submitBtn = new Button("Submit");
		TextField textPrompt = new TextField();
		
		
		textPrompt.setMinSize(150, 50);
		textPrompt.setPadding(new Insets(20));
		
		submitBtn.setOnAction(e ->
		{
			output = textPrompt.getText();
			window.close();
		});
		
		VBox container = new VBox(15, promptLbl, textPrompt, submitBtn);
		BorderPane root = new BorderPane();
		root.setCenter(container);
		
		root.setPadding(new Insets(20));
		
		Scene scene = new Scene(root);
		window.setTitle("Enter Name");
		window.setScene(scene);
		window.showAndWait();
		
		return Integer.parseInt(output);
	} 
}
