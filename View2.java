import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class View2 {

	//Tab2
	private FlowPane rootTab2;
	//add ui and game to rootTab2
	private Pane ui2;
	private FlowPane game2; //vertical flowpane
	//Pane for game2
	private Pane picture1;
	private Pane picture2;
	private GraphicsContext gcp1;
	private GraphicsContext gcp2;

	private Label labelTotal;
	private Label labelScore;
	private Label labelStreak;
	
	public View2( Score scoreClass, View1 mainTab1, Mathematician mathGuy)
	{
		
		rootTab2 = new FlowPane();
		rootTab2.setPrefHeight(600);
		
		
			//set up of tab2
		ui2 = new Pane();
		ui2.setPrefSize(200, 600);
		ui2.setStyle("-fx-background-color: #ffffc8; -fx-border-color: #2e8b57; -fx-border-width: 3px;");
		rootTab2.getChildren().add(ui2);
		
		labelTotal = new Label();
		//This line of code is at the bottom
		//when the array of numbers if filled
		labelTotal.setLayoutX(25);
		labelTotal.setLayoutY(50);
		ui2.getChildren().add(labelTotal);

		labelScore = new Label();
		labelScore.setText("Your Score: " + scoreClass.getScore());
		labelScore.setLayoutX(25);
		labelScore.setLayoutY(75);
		ui2.getChildren().add(labelScore);

		labelStreak = new Label();
		labelStreak.setText("Your Streak: " + scoreClass.getStreak());
		labelStreak.setLayoutX(25);
		labelStreak.setLayoutY(100);
		ui2.getChildren().add(labelStreak);

		game2 = new FlowPane(Orientation.HORIZONTAL); 
		game2.setPrefSize(600, 800);
		game2.setStyle("-fx-background-color: #000000;");
		rootTab2.getChildren().add(game2);
		
		picture1 = new Pane();
		picture1.setPrefSize(600, 300);
		picture1.setStyle("-fx-background-color: #4283f4;");
		//canvas for picture1 Pane
		mainTab1.setCanvas(new Canvas(600, 400));
		picture1.getChildren().add(mainTab1.getCanvas());
		game2.getChildren().add(picture1);
		gcp1 = mainTab1.getCanvas().getGraphicsContext2D();
		
//		setting images
		gcp1.drawImage(mathGuy.getImage(), mathGuy.getRectangle().getX(), mathGuy.getRectangle().getY());

		picture2 = new Pane();
		picture2.setPrefSize(600, 300);
		picture2.setStyle("-fx-background-color: #42f47d;");
		//canvas for picture2 Pane
		mainTab1.setCanvas(new Canvas(600, 400));
		picture2.getChildren().add(mainTab1.getCanvas());
		game2.getChildren().add(picture2);;
		gcp2 = mainTab1.getCanvas().getGraphicsContext2D();

		//draw streak image
		gcp2.drawImage(mathGuy.getImage(), mathGuy.getRectangle().getX(), mathGuy.getRectangle().getY());
		
	}
	/**
	 * @return the rootTab2
	 */
	public FlowPane getRootTab2() {
		return rootTab2;
	}

	/**
	 * @return the ui2
	 */
	public Pane getUi2() {
		return ui2;
	}

	/**
	 * @return the game2
	 */
	public FlowPane getGame2() {
		return game2;
	}

	/**
	 * @return the picture1
	 */
	public Pane getPicture1() {
		return picture1;
	}

	/**
	 * @return the picture2
	 */
	public Pane getPicture2() {
		return picture2;
	}

	/**
	 * @return the gcp1
	 */
	public GraphicsContext getGcp1() {
		return gcp1;
	}

	/**
	 * @return the gcp2
	 */
	public GraphicsContext getGcp2() {
		return gcp2;
	}

	/**
	 * @return the labelTotal
	 */
	public Label getLabelTotal() {
		return labelTotal;
	}

	/**
	 * @return the labelScore
	 */
	public Label getLabelScore() {
		return labelScore;
	}

	/**
	 * @return the labelStreak
	 */
	public Label getLabelStreak() {
		return labelStreak;
	}
}