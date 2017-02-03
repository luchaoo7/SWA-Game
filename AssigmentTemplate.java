import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AssigmentTemplate extends Application
{

	public static void main(String[] args) 
	{
		launch(args);
	}
	
	//main screen
	Scene scene;
	TabPane root;
	Tab tab1, tab2;
	
	//set up for both tabs
	//creating a flowpane for tab 1
	FlowPane rootTab1;
	//start pane 
	Pane instructPane;
	Pane coverPane;
	//creating a flowpane for tab 2
	FlowPane rootTab2;
	//add ui and game to rootTab1
	Pane ui, game;
	//add ui and game to rootTab2
	Pane ui2;
	FlowPane game2; //vertical flowpane
	//Pane for game2
	Pane picture1;
	Pane picture2;
	Canvas canvasStart;
	Canvas canvasP1;
	Canvas canvasP2;
	GraphicsContext gcStart;
	GraphicsContext gcp1;
	GraphicsContext gcp2;

	//tab1
	TextField textField;
	//Button for ui controller
	Button btnEnterGame;
	Button coverEnterGame;
	Button coverInstruction;
	Button InstructionCover;
	Button stop;
	Button start;
	Label welcomeLabel; 
	//canvas added to game pane
	Canvas canvas;
	GraphicsContext gc;
	
	//tab2
	Label labelTotal;
	Label labelScore;
	Label labelStreak;

	Random rand = new Random();
	int level = 0;

	Button btnHard;
	Button btnTough;
	Button btnEasy;
	Button btnVeryEasy;
	Button btnRandom;

	private int clickedLevel = 0;
	
	Operator operate;
	
	//to pace the game
	Timer timer2;
	//task to be ran
	TimerTask task;
	
	Mathematician mathGuy;
	Factory numberMaker = new Factory();
	//amount of numbers in the arraylist,
	//should only be even numbers
	int i = 20;
	
	//counter to determine when to delete numbers
	int counter = 1;
	ArrayList<ParentNumber> collectionOfNumbers = new ArrayList<ParentNumber>();
	
	//the value of each side of the operation
	//will be stored in this variables
	double leftNumber;
	double rightNumber;
	
	Score scoreClass = new Score();
	
	//key even that processes the operation 
	//and compares it with the user input
	//when ENTER is pressed
	EventHandler<KeyEvent> keyEvent = new EventHandler<KeyEvent>() 
	{
		@Override
		public void handle(KeyEvent event) 
		{

			double result = 0;
			if (event.getCode() == KeyCode.ENTER) 
			{
				//compute operation 
				switch (operate.getNumber()) {
				case 0:
					result = leftNumber + rightNumber;
					break;
				case 1:
					result = leftNumber - rightNumber;
					break;
				case 2:
					result = leftNumber / rightNumber;
					break;
				case 3:
					result = leftNumber * rightNumber;
					break;
				}

				String numberInput = textField.getText();
				double valueInput = 0;
				try 
				{
					valueInput = Double.parseDouble(numberInput);
				} 
				catch (Exception e) 
				{
//					System.out.println("only numbers");
				}
				

				if (valueInput == result) 
				{
					gc.setStroke(Color.YELLOW);
					gc.strokeText("Yes!!You are right", 400, 100);
					//update score
//					score++;
					scoreClass.setScore();
					scoreClass.setStreak();

					//set new image
					mathGuy.setImage(scoreClass.getScore());
					gcp1.drawImage(mathGuy.getImage(), mathGuy.getRectangle().getX(), mathGuy.getRectangle().getY());
					mathGuy.setImage(scoreClass.getStreak());
					gcp2.drawImage(mathGuy.getImage(), mathGuy.getRectangle().getX(), mathGuy.getRectangle().getY());
					if (scoreClass.getStreak() > scoreClass.getStreakGauge()) 
					{
						scoreClass.setStreakGauge(scoreClass.getStreak());
					}
					//Generate a random operation
					if (clickedLevel == 2) {
						
						level = rand.nextInt(4);
					}
					
					//remove operations answered correctly
					collectionOfNumbers.remove(1);
					collectionOfNumbers.remove(0);
					//reset counter
					// to have 8 seconds for the next
					//operation
					counter = 1;
					//clear text field
					textField.clear();
					//check if collection is empty
					//if it is, exit
					labelScore.setText("Your Score: " + scoreClass.getScore());
					labelStreak.setText("Your Streak: " + scoreClass.getStreakGauge());
					if (collectionOfNumbers.isEmpty()) 
					{
						timer2.cancel();
						//hide textfield on game termination
						textField.setEditable(false);
					}
				}
				else
				{
					//set streak to 0 if miss answer
					scoreClass.setStreakZero();
					//clear text field
					textField.clear();
//					gc.setFill(Color.YELLOW);
					gc.setStroke(Color.RED);
					gc.strokeText("Keep Trying", 400, 100);
				}
			}
		}
	};
	
	//eventHandler to action the stop and start button
	EventHandler<ActionEvent> actionButton = new EventHandler<ActionEvent>() 
	{
		@Override
		public void handle(ActionEvent event) 
		{
			if (event.getSource() == coverInstruction) 
			{
				tab1.setContent(instructPane);
			}
			if (event.getSource() == InstructionCover) {

				tab1.setContent(coverPane);
				
			}
			if (event.getSource() == btnEnterGame || event.getSource() == coverEnterGame) 
			{
				tab1.setContent(rootTab1);
			}
			if (event.getSource() == stop) 
			{
				if (task != null)
				{
					task.cancel();
				}
				textField.setEditable(false);
//				textField.requestFocus();
				setButtonsVisible();
			}
			if (event.getSource() == btnRandom) 
			{
				setLevel(4);
				btnEasy.setVisible(false);
				btnHard.setVisible(false);
				btnTough.setVisible(false);
				btnVeryEasy.setVisible(false);
				setClickedLevel(2);
			}
			if (event.getSource() == btnVeryEasy) 
			{
				setLevel(0);
				btnEasy.setVisible(false);
				btnHard.setVisible(false);
				btnTough.setVisible(false);
				btnRandom.setVisible(false);
				setClickedLevel(1);
			}

			if (event.getSource() == btnEasy) 
			{
				setLevel(1);
				btnVeryEasy.setVisible(false);
				btnHard.setVisible(false);
				btnTough.setVisible(false);
				btnRandom.setVisible(false);
				setClickedLevel(1);
			}
			if (event.getSource() == btnHard) 
			{
				setLevel(2);
				btnVeryEasy.setVisible(false);
				btnTough.setVisible(false);
				btnEasy.setVisible(false);
				btnRandom.setVisible(false);
				setClickedLevel(1);
			}
			if (event.getSource() == btnTough) 
			{
				setLevel(3);
				btnVeryEasy.setVisible(false);
				btnHard.setVisible(false);
				btnEasy.setVisible(false);
				btnRandom.setVisible(false);
				setClickedLevel(1);
			}
			//
			if (event.getSource() == start) 
			{
				//set image to start image
				mathGuy.setImage(0);
				gcp1.drawImage(mathGuy.getImage(), mathGuy.getRectangle().getX(), mathGuy.getRectangle().getY());
				mathGuy.setImage(0);
				gcp2.drawImage(mathGuy.getImage(), mathGuy.getRectangle().getX(), mathGuy.getRectangle().getY());
				//set button difficulty visible
				if (clickedLevel == 0)
				{
					setInvisible();
					setLevel(0);
				}
				//can type in textfield
				textField.setEditable(true);
				textField.clear();

				//set level to easy when you start
				//set time to answer question back to 1
				counter = 1;
				//focus text field when starting
				textField.requestFocus();
				//clear textfield message once programme start
				if (textField.getText().equalsIgnoreCase("Your Answer")) 
				{
					textField.clear();
				}
				//cancel task if running and start again
				if (task != null)
				{
					task.cancel();
				}
				collectionOfNumbers.clear();
				timer2 = new Timer();
				task = new TimerTask() {
					
					@Override
					public void run() {
						runnable.run();
						
					}
				};
				//call method populate
				reset();
				populateArrayOfNumbers();
				timer2.schedule(task, 0, 1000);
			}
		}
	};
	/**
	 * check to see if a level as been picked
	 */
	private void setClickedLevel(int level)
	{
		this.clickedLevel = level;
	}
	/**
	 * set all buttons to invisible
	 */
	private void setInvisible()
	{
		btnVeryEasy.setVisible(false);
		btnHard.setVisible(false);
		btnEasy.setVisible(false);
		btnRandom.setVisible(false);
		btnTough.setVisible(false);
	}
	/**
	 * set the buttons visible
	 */
	private void setButtonsVisible()
	{
		btnTough.setVisible(true);
		btnVeryEasy.setVisible(true);
		btnHard.setVisible(true);
		btnEasy.setVisible(true);
		btnRandom.setVisible(true);
	}
	/**
	 * reset game score
	 */
	private void reset() 
	{
		scoreClass.setStreakZero();
		scoreClass.setScoreZero();;
		labelScore.setText("Your Score: " + scoreClass.getScore());
		scoreClass.setStreakGauge(0);
		labelStreak.setText("Your Streak: " + scoreClass.getStreakGauge());
	}
	/**
	 * Method to populate array of numbers
	 */
	private void populateArrayOfNumbers()
	{
		//clear collection
		collectionOfNumbers.clear();
		for (int x = 1; x <= i; x++) 
		{
			int randomNumber = rand.nextInt(15) + 1;
			//reset location the numbers to start at
			int xlocation = x % 6;
			if (xlocation == 0) {
				xlocation = 6;
			}
			//System.out.println(randomNumber);
			collectionOfNumbers.add(numberMaker.createNumber(randomNumber, 50 * xlocation, 50));
		}
		//Display the amount of operations in array
		labelTotal.setText("Possible Score: " + (collectionOfNumbers.size() / 2));
	}
	/**
	 * set the difficulty of the game
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * Runnable for task to run
	 */
	Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			//check if random button has been pressed
			//generate a random operation each time
			if (clickedLevel == 2 && level == 4) 
			{
				level = rand.nextInt(4);
			}
				//generate a random number which identifies an operator
			operate = new Operator(level);
			//background colour
			gc.setFill(Color.BLACK);
			gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
			
			//assigning numbers in the array to left or right side of the operation 
			ParentNumber singleNumber = collectionOfNumbers.get(0);
			ParentNumber singleNumber2 = collectionOfNumbers.get(1);

			//double
//			leftNumber = singleNumber.number;
//			rightNumber = singleNumber2.number;
			//always divide big number by smaller number
			if (operate.getNumber() == 2) 
			{
				if (singleNumber.getNumber() < 0 && singleNumber2.getNumber() < 0) 
				{
					//make the both positive numbers
//					leftNumber = singleNumber.getNumber() * (-1);
//					rightNumber = singleNumber2.getNumber() * (-1);
					leftNumber = Math.abs(singleNumber.getNumber());

					rightNumber = Math.abs(singleNumber2.getNumber());
					leftNumber = leftNumber * rightNumber;
				}
				else if (singleNumber.getNumber() < 0 && singleNumber2.getNumber() > 0) {
					
					leftNumber = Math.abs(singleNumber.getNumber());
					rightNumber = singleNumber2.getNumber();
					
					leftNumber = leftNumber * rightNumber;
				}
				else if (singleNumber2.getNumber() < 0 && singleNumber.getNumber() > 0 ) {

					rightNumber = Math.abs(singleNumber2.getNumber());
					leftNumber = singleNumber.getNumber();
					
					leftNumber = leftNumber * rightNumber;
					
				}
				else if (singleNumber.getNumber() > 0 && singleNumber2.getNumber() > 0) {
					
					leftNumber = singleNumber.getNumber();
					rightNumber = singleNumber2.getNumber();
					
					leftNumber = leftNumber * rightNumber;
				}
			}
			else
			{
				leftNumber = singleNumber.getNumber();
				rightNumber = singleNumber2.getNumber();
			}

			//convert the number to string
			//so it can be drawn
			int left = (int) leftNumber;
			int right = (int) rightNumber;
			String stringNumber = String.valueOf(left);
			String stringNumber2 = String.valueOf(right);

			
			//to place the operator in the middle of the two numbers 
			double locationOperator = (singleNumber.getRectangle().getX() + singleNumber2.getRectangle().getX() + 50) / 2 + 15;
			//set the text to white
			gc.setStroke(Color.WHITE);
			gc.strokeText(operate.getOPERATOR(), locationOperator, singleNumber2.getRectangle().getY());

			gc.strokeText(stringNumber, singleNumber.getRectangle().getX(), singleNumber.getRectangle().getY());
			gc.strokeText(stringNumber2, singleNumber2.getRectangle().getX() + 50, singleNumber2.getRectangle().getY());
			singleNumber.move();
			singleNumber2.move();

			
			//Every Five seconds delete index 1 and 0
			if (counter % 7 == 0) 
			{
				scoreClass.setStreakZero();
				collectionOfNumbers.remove(1);
				collectionOfNumbers.remove(0);

				if (clickedLevel == 2) {
					
					level = rand.nextInt(4);
				}
				counter = 1;
			}
			//If collection is empty, exit game
			if (collectionOfNumbers.isEmpty()) {
//				System.exit(0);
				timer2.cancel();
				//hide texfield
				textField.setEditable(false);
			}
			
			counter++;
		}
	};
	
	@Override
	public void start(Stage stage) throws Exception 
	{
		
		root = new TabPane();
		scene = new Scene(root, 800, 600);
		stage.setScene(scene);
		stage.setResizable(false);
		
		tab1 = new Tab();
		tab1.setText("First Tab");
		tab1.setClosable(false);
		root.getTabs().add(tab1);
		
		tab2 = new Tab();
		tab2.setText("Second Tab");
		tab2.setClosable(false);
		root.getTabs().add(tab2);
		
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
		coverInstruction.setOnAction(actionButton);
		coverPane.getChildren().add(coverInstruction);

		coverEnterGame = new Button("Enter");
		coverEnterGame.setLayoutX(337.5);
		coverEnterGame.setLayoutY(325);
		coverEnterGame.setPrefSize(125, 75);
		coverEnterGame.setOnAction(actionButton);
		coverPane.getChildren().add(coverEnterGame);
		tab1.setContent(coverPane);
		

		instructPane = new Pane();
		instructPane.setPrefSize(800, 600);
		instructPane.setStyle("-fx-background-color: #000; -fx-border-color: #2e8b57; -fx-border-width: 3px;");
//Cover page
		canvas = new Canvas(800, 600);
		instructPane.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
//		Image image3 = new Image(AssigmentTemplate.class.getResource("resource/genius.png").toExternalForm(),800, 600, false, false);
		Image image3 = new Image(AssigmentTemplate.class.getResource("resource/instructions.jpg").toExternalForm(),800, 600, false, false);
		gc.drawImage(image3, 0, 0);
		btnEnterGame = new Button("Enter");
		btnEnterGame.setLayoutX(337.5);
		btnEnterGame.setLayoutY(200);
		btnEnterGame.setPrefSize(125, 75);
		btnEnterGame.setOnAction(actionButton);
		instructPane.getChildren().add(btnEnterGame);

		InstructionCover = new Button("Menu");
		InstructionCover.setLayoutX(337.7);
		InstructionCover.setLayoutY(325);
		InstructionCover.setPrefSize(125, 75);
		InstructionCover.setOnAction(actionButton);
		instructPane.getChildren().add(InstructionCover);
		
		rootTab2 = new FlowPane();
//		
		rootTab2.setPrefHeight(600);
		tab2.setContent(rootTab2);

		//set up of tab1
		ui = new Pane();
		ui.setPrefSize(200, 600);
		ui.setStyle("-fx-background-color: #ffffc8; -fx-border-color: #2e8b57; -fx-border-width: 3px;");
		rootTab1.getChildren().add(ui);
		
		btnVeryEasy = new Button("V Easy");
		btnVeryEasy.setLayoutX(10);
		btnVeryEasy.setLayoutY(10);
		btnVeryEasy.setOnAction(actionButton);
		ui.getChildren().add(btnVeryEasy);
		btnEasy = new Button("Easy");
		btnEasy.setLayoutX(10);
		btnEasy.setLayoutY(40);
		btnEasy.setOnAction(actionButton);
		ui.getChildren().add(btnEasy);
		btnHard = new Button("Hard");
		btnHard.setLayoutX(10);
		btnHard.setLayoutY(70);
		btnHard.setOnAction(actionButton);
		ui.getChildren().add(btnHard);
		btnTough = new Button("Tough");
		btnTough.setLayoutX(10);
		btnTough.setLayoutY(100);
		btnTough.setOnAction(actionButton);
		ui.getChildren().add(btnTough);
		btnRandom = new Button("Random");
		btnRandom.setLayoutX(10);
		btnRandom.setLayoutY(130);
		btnRandom.setOnAction(actionButton);
		ui.getChildren().add(btnRandom);

		welcomeLabel = new Label("Welcome");
		welcomeLabel.setLayoutX(20);
		welcomeLabel.setLayoutY(230);
		welcomeLabel.setTextFill(Color.web("#ccff33"));
		welcomeLabel.setFont(Font.font("Arial", 32));
		ui.getChildren().add(welcomeLabel);
		
		//set up of tab2
		ui2 = new Pane();
		ui2.setPrefSize(200, 600);
		ui2.setStyle("-fx-background-color: #ffffc8; -fx-border-color: #2e8b57; -fx-border-width: 3px;");
		rootTab2.getChildren().add(ui2);
		
		labelTotal = new Label();
		//This line of code is at the bottom
		//when the array of numbers if filled
//		labelTotal.setText("Possible Score: " + (collectionOfNumbers.size() / 2));
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
		
		//set up of stop button
		stop = new Button();
		stop.setText("Stop");
		stop.setLayoutX(25);
		stop.setLayoutY(200);
		stop.setOnAction(actionButton);
		ui.getChildren().add(stop);
		//set up start button
		start = new Button();
		start.setText("Start");
		start.setLayoutX(100);
		start.setLayoutY(200);
		start.setOnAction(actionButton);
		ui.getChildren().add(start);
		//set up textfield
		textField = new TextField();
		textField.setText("Your Answer");
		textField.setLayoutX(15);
		textField.setLayoutY(300);
		//not editable
		textField.setEditable(false);
		//key event for text field, when enter is pressed
		//the value is processed
		textField.setOnKeyPressed(keyEvent);
		ui.getChildren().add(textField);


		game = new Pane();
		game.setPrefSize(600, 600);
		game.setStyle("-fx-background-color: #000000;");
		rootTab1.getChildren().add(game);
		
		game2 = new FlowPane(Orientation.HORIZONTAL); 
		game2.setPrefSize(600, 800);
		game2.setStyle("-fx-background-color: #000000;");
		rootTab2.getChildren().add(game2);
		
		picture1 = new Pane();
		picture1.setPrefSize(600, 300);
		picture1.setStyle("-fx-background-color: #4283f4;");
		//canvas for picture1 Pane
		canvasP1 = new Canvas(600, 400);
		picture1.getChildren().add(canvasP1);
		game2.getChildren().add(picture1);
		gcp1 = canvasP1.getGraphicsContext2D();
		
//		setting images
//		Maths mathGuy = Maths.getInstace();
		mathGuy = Mathematician.getInstace();
		gcp1.drawImage(mathGuy.getImage(), mathGuy.getRectangle().getX(), mathGuy.getRectangle().getY());

		picture2 = new Pane();
		picture2.setPrefSize(600, 300);
		picture2.setStyle("-fx-background-color: #42f47d;");
		//canvas for picture2 Pane
		canvasP2 = new Canvas(600, 400);
		picture2.getChildren().add(canvasP2);
		game2.getChildren().add(picture2);;
		gcp2 = canvasP2.getGraphicsContext2D();

		//draw streak image
		gcp2.drawImage(mathGuy.getImage(), mathGuy.getRectangle().getX(), mathGuy.getRectangle().getY());

		canvas = new Canvas(600, 600);
		game.getChildren().add(canvas);
		
		//set here to have the maximum score

		//set up graphic context
		gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.LIGHTCYAN);
		//set the font
		gc.setFont(new Font("Arial", 20));
		//stroke the number and the location
		
		stage.show();
	}
}