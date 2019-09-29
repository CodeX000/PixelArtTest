import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.Random;

public class MapTest extends Application{
	
	private final String COLOR_RGB = "-fx-background-color: rgb"; //(50,50,50)
	private final String BLACK_BORDER = "-fx-border-color:black";

	public static void main(String[] args) {
		launch(args);
	}
	
	private final int MAX_COLOR_VALUE = 255;
	private final int MIN_COLOR_VALUE = 0;
	
	public class Tile extends Label{
		
		private ColorSelection hoverSelector = new ColorSelection();
		
		public Tile(ColorSelection colorSelector) {
			
			setStyle(COLOR_RGB + "(" + MAX_COLOR_VALUE + "," + MAX_COLOR_VALUE + "," + MAX_COLOR_VALUE + ")");
			
			hoverSelector = colorSelector;
			
			setPrefSize(20, 20);
			setAlignment(Pos.CENTER);
			
			setOnMouseEntered(new HoverHandler());
			
		}
		
		public void setColor(int[] color) {
			setStyle(COLOR_RGB + "(" + color[0] + "," + color[1] + "," + color[2] + ")");
		}
		
		//When tile is clicked replace color with selected option
		public class HoverHandler implements EventHandler<MouseEvent>{
			@Override
			public void handle(MouseEvent whenHovering) {
				setColor(hoverSelector.getRGB());
			}
		}
	}
	
	public class ColorSelection {
		
		//Input your RGB code
		private TextField redValue = new TextField("" + MAX_COLOR_VALUE);
		private TextField greenValue = new TextField("" + MAX_COLOR_VALUE);
		private TextField blueValue = new TextField("" + MAX_COLOR_VALUE);
		
		
		private CheckBox eraser = new CheckBox("Eraser");
		
		//Will change color based on RGB value
		private Label showColor = new Label();
		
		HBox parent = new HBox(redValue, greenValue, blueValue, eraser, showColor);
		
		public ColorSelection() {
			//Creates action handlers for objects
			redValue.setOnAction(new ActionHandler());
			greenValue.setOnAction(new ActionHandler());
			blueValue.setOnAction(new ActionHandler());
			eraser.setOnAction(new ActionHandler());
			
			//Sets defaults for showColor object
			showColor.setPrefSize(30, 30);
			showColor.setStyle(COLOR_RGB + "(" + Integer.parseInt(redValue.getText()) + "," + Integer.parseInt(greenValue.getText()) + ","
					+ Integer.parseInt(blueValue.getText()) + ")");
			
			parent.setPadding(new Insets(15));
			parent.setPrefSize(1000, 50);
			parent.setAlignment(Pos.TOP_CENTER);
			
			parent.setStyle(BLACK_BORDER);
		}
		
		public HBox output() {
			return parent;
		}
		
		public int[] getRGB() {
			int[] colors = new int[3];
			
			//Gets integer value for text fields
			if (eraser.isSelected()) {
				colors[0] = MAX_COLOR_VALUE;
				colors[1] = MAX_COLOR_VALUE;
				colors[2] = MAX_COLOR_VALUE;
			}
			else {
				colors[0] = Integer.parseInt(redValue.getText());
				colors[1] = Integer.parseInt(greenValue.getText());
				colors[2] = Integer.parseInt(blueValue.getText());
			}
			
			//Returns integer array 0 = red 1 = green 2 = blue
			return colors;
			
		}
		
		public class ActionHandler implements EventHandler<ActionEvent>{
			@Override
			public void handle(ActionEvent onAction) {
				//Changes background colors for showColor label
				if (eraser.isSelected()) {
					showColor.setStyle(COLOR_RGB + "(" + MAX_COLOR_VALUE + "," + MAX_COLOR_VALUE + "," + MAX_COLOR_VALUE + ")");
				}
				else {
					showColor.setStyle(COLOR_RGB + "(" + Integer.parseInt(redValue.getText()) + "," + Integer.parseInt(greenValue.getText()) + ","
							+ Integer.parseInt(blueValue.getText()) + ")");
				}			
			}
		}
	}
	
	
	@Override
	public void start(Stage stage) {
		
		ColorSelection color = new ColorSelection();
		GridPane map = new GridPane();
		
		//Sets decor
		map.setStyle(BLACK_BORDER);
		map.setAlignment(Pos.CENTER);
		
		int x = 48;
		int y = 32;
		
		Tile tileMap[][] = new Tile[x][y];
		
		for(int i = 0; i < y; i++) {
			for(int n = 0; n < x; n++) {
				tileMap[n][i] = new Tile(color);
				map.add(tileMap[n][i], n, i);
			}
		}

		VBox parent = new VBox(color.output(), map);
		
		Scene scene = new Scene(parent, 1000, 800);
		stage.setScene(scene);
		stage.setTitle("Color Selection GUI");
		stage.show();
		
	}
	
}
