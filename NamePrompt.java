package application;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NamePrompt {

	private static String output;
	
	public static String display()
	{
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Choose Name");
		window.setMinHeight(200);
		window.setMinWidth(250);
		
		Label promptLbl = new Label("Enter name for Entry");
		
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
		
		return output;
	} 
}
