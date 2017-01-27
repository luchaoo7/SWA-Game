import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
	//creating a flowpane for tab 2
	FlowPane rootTab2;
	//add ui and game to rootTab1
	Pane ui, game;
	//add ui and game to rootTab2
	Pane ui2, game2;

	//tab1
	TextField textField;
	//Button for ui controller
	Button stop;
	Button start;
	//canvas added to game pane
	Canvas canvas;
	GraphicsContext gc;
	
	//tab2
	Label labelTotal;
	Label labelScore;
	Label labelStreak;

	Random rand = new Random();
	
	SmallNumber myNumber;
	Operator operate;
	
	//to pace the game
	Timer timer2 = new Timer();
	
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
	int score = 0;
	int streak = 0;
	int streakGauge = 0;
	
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
				switch (operate.number) {
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
					score++;
					streak++;

					if (streak > streakGauge) 
					{
						streakGauge = streak;
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
					labelScore.setText("Your Score: " + score);
					labelStreak.setText("Your Streak: " + streakGauge);
					if (collectionOfNumbers.isEmpty()) 
					{
						timer2.cancel();
//						System.exit(0);
					}
				}
				else
				{
					//set streak to 0 if miss answer
					streak = 0;
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
			if (event.getSource() == stop) 
			{
				timer2.cancel();
			}
			if (event.getSource() == start) 
			{

			}
		}
	};
	
	
	TimerTask task = new TimerTask() {
		
		int randomNumber = rand.nextInt(4);

		@Override
		public void run() {
			//generate a random number which identifies an operator
			operate = new Operator(randomNumber);
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
			if (operate.number == 2) 
			{
				if (singleNumber.number > singleNumber2.number) 
				{
					leftNumber = singleNumber.number;
					rightNumber = singleNumber2.number;
				}
				else{
					leftNumber = singleNumber2.number;
					rightNumber = singleNumber.number;
				}
				//to make division more do-able
				if (leftNumber < 0) 
				{
					leftNumber = (leftNumber * (-1) * 10);
				}

			}
			else
			{
				leftNumber = singleNumber.number;
				rightNumber = singleNumber2.number;
			}
		

			//convert the number to string
			//so it can be drawn
			int left = (int) leftNumber;
			int right = (int) rightNumber;
			String stringNumber = String.valueOf(left);
			String stringNumber2 = String.valueOf(right);

			
			//to place the operator in the middle of the two numbers 
			double locationOperator = (singleNumber.rectangle.getX() + singleNumber2.rectangle.getX() + 50) / 2 + 15;
			//set the text to white
			gc.setStroke(Color.WHITE);
			gc.strokeText(operate.OPERATOR, locationOperator, singleNumber2.rectangle.getY());

			gc.strokeText(stringNumber, singleNumber.rectangle.getX(), singleNumber.rectangle.getY());
			gc.strokeText(stringNumber2, singleNumber2.rectangle.getX() + 50, singleNumber2.rectangle.getY());
			singleNumber.move();
			singleNumber2.move();

			
			//Every Five seconds delete index 1 and 0
			if (counter % 4 == 0) 
			{
				streak = 0;
				collectionOfNumbers.remove(1);
				collectionOfNumbers.remove(0);
				randomNumber = rand.nextInt(4);
			}
			//If collection is empty, exit game
			if (collectionOfNumbers.isEmpty()) {
//				System.exit(0);
				timer2.cancel();
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
		
		tab1 = new Tab();
		tab1.setText("First Tab");
		tab1.setClosable(false);
		root.getTabs().add(tab1);
		
		tab2 = new Tab();
		tab2.setText("Second Tab");
		tab2.setClosable(false);
		root.getTabs().add(tab2);
		
		rootTab1 = new FlowPane();
		tab1.setContent(rootTab1);
		
		rootTab2 = new FlowPane();
		tab2.setContent(rootTab2);

		//set up of tab1
		ui = new Pane();
		ui.setPrefSize(200, 600);
		ui.setStyle("-fx-background-color: #ffffc8; -fx-border-color: #2e8b57; -fx-border-width: 3px;");
		rootTab1.getChildren().add(ui);
		
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
		labelScore.setText("Your Score: " + score);
		labelScore.setLayoutX(25);
		labelScore.setLayoutY(75);
		ui2.getChildren().add(labelScore);

		labelStreak = new Label();
		labelStreak.setText("Your Streak: " + streak);
		labelStreak.setLayoutX(25);
		labelStreak.setLayoutY(100);
		ui2.getChildren().add(labelStreak);
		
		//set up of stop button
		stop = new Button();
		stop.setText("Stop");
		stop.setLayoutX(25);
		stop.setLayoutY(50);
		stop.setOnAction(actionButton);
		ui.getChildren().add(stop);
		//set up start button
		start = new Button();
		start.setText("Start");
		start.setLayoutX(100);
		start.setLayoutY(50);
		start.setOnAction(actionButton);
		ui.getChildren().add(start);
		//set up textfield
		textField = new TextField();
		textField.setText("Your Answer");
		textField.setLayoutX(15);
		textField.setLayoutY(300);
		//key event for text field, when enter is pressed
		//the value is processed
		textField.setOnKeyPressed(keyEvent);
		ui.getChildren().add(textField);


		game = new Pane();
		game.setPrefSize(600, 600);
		game.setStyle("-fx-background-color: #000000;");
		rootTab1.getChildren().add(game);
		
		canvas = new Canvas(600, 600);
		game.getChildren().add(canvas);
		
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

		//set here to have te maximum score
		labelTotal.setText("Possible Score: " + (collectionOfNumbers.size() / 2));

		//set up graphic context
		gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.LIGHTCYAN);
		//set the font
		gc.setFont(new Font("Arial", 20));
		//stroke the number and the location
		
		//TIMER LOOP
		timer2.schedule(task, 0, 2000);

		stage.show();
	}
}

interface FactoryIF
{
	public ParentNumber createNumber(int amount, int x, int y);
}

class Factory implements FactoryIF
{
	@Override
	public ParentNumber createNumber(int amount, int x, int y) 
	{
		if (amount > 0 && amount <= 5) 
		{
			return (new SmallNumber(x, y));
		}
		else if (amount > 5 && amount <= 10) 
		{
			return (new MediumNumber(x, y));
		}
		else
		{
			return (new BigNumber(x, y));
		}
	}
}

interface MarkerIF
{
	
}

/**
 * class to dictate what operation 
 * will take place
 * @author danbro
 */
class Operator
{
	public String OPERATOR = "";
	public int number;

	public Operator(int operator) 
	{
		//store the passed number to identify 
		//the operator user later
		number = operator;

		switch (operator) 
		{
		case 0:
			OPERATOR = "+";
			break;
		case 1:
			OPERATOR = "-";
			break;
		case 2:
			OPERATOR = "/";
			break;
		case 3:
			OPERATOR = "*";
			break;
		}
	}
	
}

abstract class ParentNumber implements MarkerIF
{
	Rectangle rectangle;
	Random random;
	int number;
	
	public ParentNumber(int x, int y) 
	{
		//create rectangle at x,y coordinate and size w,h
		rectangle = new Rectangle(x, y, 100, 100);
		random = new Random();
		number = random.nextInt(10) + 1;
	}

	public abstract void move();
}

/**
 * generate a number from 10 to a 100 in powers of 
 * ten
 * @author danbro
 */
class BigNumber extends ParentNumber
{
	public BigNumber(int x, int y) 
	{
		super(x, y);
		number = number * 10;
	}

	/**
	 * move number downwards as loops carries on
	 */
	@Override
	public void move() 
	{
		rectangle.setY(rectangle.getY() + 50);
	}
}
/**
 * generate a number from -1 to 10
 * @author danbro
 */
class SmallNumber extends ParentNumber
{
	
	public SmallNumber(int x, int y) 
	{
		super(x, y);
		number = number * (-1);
	}

	/**
	 * move number downwards as loops carries on
	 */
	@Override
	public void move() 
	{
		rectangle.setY(rectangle.getY() + 50);
	}
}
/**
 * Generate a numbers from 1 to 10;
 * @author danbro
 */
class MediumNumber extends ParentNumber
{
	
	public MediumNumber(int x, int y) 
	{
		super(x, y);
	}

	/**
	 * move number downwards as loops carries on
	 */
	@Override
	public void move() 
	{
		rectangle.setY(rectangle.getY() + 50);
	}
}
