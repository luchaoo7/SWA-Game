import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
	
	private Canvas canvas;
	
	public View2()
	{
		rootTab2 = new FlowPane();
		rootTab2.setPrefHeight(600);
		
			//set up of tab2
		ui2 = new Pane();
		ui2.setPrefSize(200, 600);
		ui2.setStyle("-fx-background-color: #ffffc8; -fx-border-color: #2e8b57; -fx-border-width: 3px;");
		rootTab2.getChildren().add(ui2);
		
		labelTotal = new Label();
		labelTotal.setLayoutX(25);
		labelTotal.setLayoutY(50);
		ui2.getChildren().add(labelTotal);

		labelScore = new Label("Your Score: 0");
		labelScore.setLayoutX(25);
		labelScore.setLayoutY(75);
		ui2.getChildren().add(labelScore);

		labelStreak = new Label("Your Streak: 0");
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
		canvas = new Canvas(600, 400);
		picture1.getChildren().add(canvas);
		game2.getChildren().add(picture1);
		gcp1 = canvas.getGraphicsContext2D();
//		setting images
		gcp1.drawImage(new Image(View2.class.getResource("resource/math_fun2.jpg").toExternalForm()), 0, 0);

		picture2 = new Pane();
		picture2.setPrefSize(600, 300);
		picture2.setStyle("-fx-background-color: #42f47d;");
		//canvas for picture2 Pane
		canvas = new Canvas(600, 400);
		picture2.getChildren().add(canvas);
		game2.getChildren().add(picture2);;
		gcp2 = canvas.getGraphicsContext2D();
		//draw streak image
		gcp2.drawImage(new Image(View2.class.getResource("resource/math_fun.jpg").toExternalForm()), 0, 0);
	}
	/**
	 * @return the canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}
	/**
	 * @param canvas the canvas to set
	 */
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
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