
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class View1 {

//Tab1
	private FlowPane rootTab1;
	//start pane 
	private Pane instructPane;
	private Pane coverPane;
	//add ui and game to rootTab1
	private Pane ui, game;
	private TextField textField;
	private TextField nameField;
	//Button for ui controller
	private Button btnEnterGame;
	private Button coverEnterGame;
	private Button coverInstruction;
	private Button InstructionCover;
	private Button stop;
	private Button start;
	//canvas added to game pane
	private Canvas canvas;
	private GraphicsContext gc;

	private ToggleGroup btnGroup;
	private RadioButton btnVeryEasy;
	private RadioButton btnEasy;
	private RadioButton btnHard;
	private RadioButton btnTough;
	private RadioButton btnRandom;
    private Label label;
	
	public View1()
	{
		rootTab1 = new FlowPane();
		//set up the layout of the cover pane
		//and panes it can reach with the buttons
		coverPane = new Pane();
		coverPane.setPrefSize(800, 600);
		coverPane.setStyle("-fx-background-color: #000; -fx-border-color: #2e8b57; -fx-border-width: 3px;");
		canvas = new Canvas(800, 600);
		coverPane.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		Image coverImage = new Image(AssigmentTemplate.class.getResource("resource/algebra.jpg").toExternalForm(),800, 600, false, false);
		gc.drawImage(coverImage, 0, 0);
		coverInstruction = new Button("Instructions");
		coverInstruction.setLayoutX(337.5);
		coverInstruction.setLayoutY(200);
		coverInstruction.setPrefSize(125, 75);
//		coverInstruction.setOnAction(actionButton);
		coverPane.getChildren().add(coverInstruction);

		coverEnterGame = new Button("Enter");
		coverEnterGame.setLayoutX(337.5);
		coverEnterGame.setLayoutY(325);
		coverEnterGame.setPrefSize(125, 75);
//		coverEnterGame.setOnAction(actionButton);
		coverPane.getChildren().add(coverEnterGame);
//		tab1.setContent(coverPane);

		instructPane = new Pane();
		instructPane.setPrefSize(800, 600);
		instructPane.setStyle("-fx-background-color: #000; -fx-border-color: #2e8b57; -fx-border-width: 3px;");
//Cover page
		canvas = new Canvas(800, 600);
		instructPane.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		Image image3 = new Image(AssigmentTemplate.class.getResource("resource/instructions.jpg").toExternalForm(),800, 600, false, false);
		gc.drawImage(image3, 0, 0);
		btnEnterGame = new Button("Enter");
		btnEnterGame.setLayoutX(337.5);
		btnEnterGame.setLayoutY(200);
		btnEnterGame.setPrefSize(125, 75);
//		btnEnterGame.setOnAction(actionButton);
		instructPane.getChildren().add(btnEnterGame);

		InstructionCover = new Button("Menu");
		InstructionCover.setLayoutX(337.7);
		InstructionCover.setLayoutY(325);
		InstructionCover.setPrefSize(125, 75);
//		InstructionCover.setOnAction(actionButton);
		instructPane.getChildren().add(InstructionCover);
		
		//set up of tab1
		ui = new Pane();
		ui.setPrefSize(200, 600);
		ui.setStyle("-fx-background-color: #ffffc8; -fx-border-color: #2e8b57; -fx-border-width: 3px;");
//		rootTab1.getChildren().add(ui);
			
		//set up of stop button
		stop = new Button("Stop");
		stop.setLayoutX(25);
		stop.setLayoutY(265);
//		stop.setOnAction(actionButton);
		ui.getChildren().add(stop);
		//set up start button
		start = new Button("Start");
		start.setLayoutX(100);
		start.setLayoutY(265);
//		start.setOnAction(actionButton);
		ui.getChildren().add(start);
		
		nameField = new TextField();
		nameField.setLayoutX(15);
		nameField.setLayoutY(220);
		ui.getChildren().add(nameField);
		
		//set up textfield
		textField = new TextField();
		textField.setLayoutX(15);
		textField.setLayoutY(325);
		//not editable
		textField.setVisible(false);
		//key event for text field, when enter is pressed
		//the value is processed
//		textField.setOnKeyPressed(keyEvent);
		ui.getChildren().add(textField);
		
		label = new Label("Enter Name:... press Enter");
		label.setLayoutX(15);
		label.setLayoutY(200);
		ui.getChildren().add(label);
		
		label = new Label("Answer: ");
		label.setLayoutX(15);
		label.setLayoutY(305);
		ui.getChildren().add(label);

		label = new Label("Level: ");
		label.setLayoutX(10);
		label.setLayoutY(20);
		ui.getChildren().add(label);
		
		btnGroup = new ToggleGroup();
		//group
		btnVeryEasy = new RadioButton("V Easy");
		btnVeryEasy.setToggleGroup(btnGroup);
		btnVeryEasy.setLayoutX(10);
		btnVeryEasy.setLayoutY(40);
		btnEasy = new RadioButton("Easy");
		btnEasy.setToggleGroup(btnGroup);
		btnEasy.setLayoutX(10);
		btnEasy.setLayoutY(70);
		btnHard = new RadioButton("Hard");
		btnHard.setToggleGroup(btnGroup);
		btnHard.setLayoutX(10);
		btnHard.setLayoutY(100);
		btnTough = new RadioButton("Tough");
		btnTough.setToggleGroup(btnGroup);
		btnTough.setLayoutX(10);
		btnTough.setLayoutY(130);
		btnRandom = new RadioButton("Random");
		btnRandom.setToggleGroup(btnGroup);
		btnRandom.setLayoutX(10);
		btnRandom.setLayoutY(160);
		ui.getChildren().addAll(btnVeryEasy, btnEasy, btnTough, btnHard, btnRandom);
		
		game = new Pane();
		game.setPrefSize(600, 600);
		game.setStyle("-fx-background-color: #000000;");
		canvas = new Canvas(600, 600);
		game.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		
		rootTab1.getChildren().add(ui);
		rootTab1.getChildren().add(game);
	}
	/**
	 * @return the nameField
	 */
	public TextField getNameField() {
		return nameField;
	}
	/**
	 * @param nameField the nameField to set
	 */
	public void setNameFieldText(String name) {
		this.nameField.setText(name);
	}
	/**
	 * @return the btnRandom
	 */
	public RadioButton getBtnRandom() {
		return btnRandom;
	}
	/**
	 * @return the btnHard
	 */
	public RadioButton getBtnHard() {
		return btnHard;
	}
	/**
	 * @return the btnTough
	 */
	public RadioButton getBtnTough() {
		return btnTough;
	}
	/**
	 * @return the btnVeryEasy
	 */
	public RadioButton getBtnVeryEasy() {
		return btnVeryEasy;
	}

	/**
	 * @return the btnEasy
	 */
	public RadioButton getBtnEasy() {
		return btnEasy;
	}

	/**
	 * set button action event
	 * @param actionButton
	 */
	public void setOnActionButton(EventHandler<ActionEvent> actionButton, 
			EventHandler<KeyEvent> keyEvent)
	{
		nameField.setOnKeyPressed(keyEvent);
		textField.setOnKeyPressed(keyEvent);
		btnVeryEasy.setOnAction(actionButton);
		btnEasy.setOnAction(actionButton);
		btnRandom.setOnAction(actionButton);
		btnTough.setOnAction(actionButton);
		btnHard.setOnAction(actionButton);
		coverInstruction.setOnAction(actionButton);
		coverEnterGame.setOnAction(actionButton);
		InstructionCover.setOnAction(actionButton);
		btnEnterGame.setOnAction(actionButton);
		stop.setOnAction(actionButton);
		start.setOnAction(actionButton);
	}
	/**
	 * @return the rootTab1
	 */
	public FlowPane getRootTab1() {
		return rootTab1;
	}
	/**
	 * @return the instructPane
	 */
	public Pane getInstructPane() {
		return instructPane;
	}
	/**
	 * @return the coverPane
	 */
	public Pane getCoverPane() {
		return coverPane;
	}
	/**
	 * @return the ui
	 */
	public Pane getUi() {
		return ui;
	}
	/**
	 * @return the game
	 */
	public Pane getGame() {
		return game;
	}
	/**
	 * @return the textField
	 */
	public TextField getTextField() {
		return textField;
	}
	/**
	 * @return the btnEnterGame
	 */
	public Button getBtnEnterGame() {
		return btnEnterGame;
	}
	/**
	 * @return the coverEnterGame
	 */
	public Button getCoverEnterGame() {
		return coverEnterGame;
	}
	/**
	 * @return the coverInstruction
	 */
	public Button getCoverInstruction() {
		return coverInstruction;
	}
	/**
	 * @return the instructionCover
	 */
	public Button getInstructionCover() {
		return InstructionCover;
	}
	/**
	 * @return the stop
	 */
	public Button getStop() {
		return stop;
	}
	/**
	 * @return the start
	 */
	public Button getStart() {
		return start;
	}
	/**
	 * @return the canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}
	public void setCanvas(Canvas canvas)
	{
		this.canvas = canvas;
	}
	/**
	 * @return the gc
	 */
	public GraphicsContext getGc() {
		return gc;
	}
}