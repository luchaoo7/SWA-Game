import java.util.ArrayList;
import java.util.Random;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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
	
	Scene scene;
	TabPane root;
	Tab tab1, tab2;
	
	//creating a flowpane for tab 1
	FlowPane rootTab1;
	//add ui and game to rootTab1
	Pane ui, game;
	TextField textField;
	//Button for ui controller
	Button stop;
	Button start;
	//canvas added to game pane
	Canvas canvas;
	GraphicsContext gc;

	Random rand = new Random();
	
	SmallNumber myNumber;
	Operator operate;
	
	Factory numberMaker = new Factory();
	//amount of numbers in the arraylist
	int i = 10;
	ArrayList<ParentNumber> collectionOfNumbers = new ArrayList<ParentNumber>();
	
	
	double leftNumber;
	double rightNumber;
	
	EventHandler<KeyEvent> keyEvent = new EventHandler<KeyEvent>() 
	{

		@Override
		public void handle(KeyEvent event) 
		{
			double result = 0;
			if (event.getCode() == KeyCode.ENTER) 
			{
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
//				System.out.println(textField.getText());
//				int result = leftNumber + rightNumber;
			}
				System.out.println(result);
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
				timer.stop();
			}
			if (event.getSource() == start) 
			{
				timer.start();
			}
		}
		
	};
	
	AnimationTimer timer = new AnimationTimer() 
	{

		//counter
		int counter = 1;
		int randomNumber = rand.nextInt(4);
		@Override
		public void handle(long now) 
		{
			System.out.println(counter);
			//generate a random number which identifies an operator
			operate = new Operator(randomNumber);
			//background colour
			gc.setFill(Color.BLACK);
			gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

			//int number
//			int number = objectNumber.number;
			//convert the number to string
//			String stringNUumber = String.valueOf(number);
			//the programme stalls every second

			try 
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			ParentNumber singleNumber = collectionOfNumbers.get(0);
			ParentNumber singleNumber2 = collectionOfNumbers.get(1);

			//int number
			leftNumber = singleNumber.number;
			rightNumber = singleNumber2.number;

			//assigning numbers in the array to left or right side of the operation 
			//so user input can be checked against later

			//convert the number to string
			//so it can be drawn
			String stringNumber = String.valueOf(leftNumber);
			String stringNumber2 = String.valueOf(rightNumber);

			gc.strokeText(stringNumber, singleNumber.rectangle.getX(), singleNumber.rectangle.getY());
			gc.strokeText(stringNumber2, singleNumber2.rectangle.getX(), singleNumber2.rectangle.getY());
			singleNumber.move();
			singleNumber2.move();

			
			//to place the operator in the middle of the two numbers 
			double locationOperator = (singleNumber.rectangle.getX() + singleNumber2.rectangle.getX()) / 2;
			
			gc.strokeText(operate.OPERATOR, locationOperator, 100);
			
			
			if (counter % 5 == 0) 
			{
				collectionOfNumbers.remove(1);
				collectionOfNumbers.remove(0);
				randomNumber = rand.nextInt(4);
			}
			if (collectionOfNumbers.isEmpty()) {
				System.exit(0);
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

		ui = new Pane();
		ui.setPrefSize(200, 600);
		ui.setStyle("-fx-background-color: #ffffc8; -fx-border-color: #2e8b57; -fx-border-width: 3px;");
		rootTab1.getChildren().add(ui);
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
			int randomNumber = rand.nextInt(16) - 5;
			//reset location the numbers to start at
			int xlocation = x % 6;
			if (xlocation == 0) {
				xlocation = 6;
			}
			//System.out.println(randomNumber);
			collectionOfNumbers.add(numberMaker.createNumber(randomNumber, 50 * xlocation, 50));
		}


		//set up graphic context
		gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.LIGHTCYAN);
		//set the font
		gc.setFont(new Font("Arial", 20));
		//stroke the number and the location
		

		timer.start();
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
		if (amount < 0) 
		{
			return (new SmallNumber(x, y));
		}
		else if (amount >= 0 && amount <= 5) 
		{
			return (new MediumNumber(x, y));
		}
		else if (amount > 5) 
		{
			return (new BigNumber(x, y));
		}
		else 
		{
			return null;
		}
	}
}

interface MarkerIF
{
	
}

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
		number = random.nextInt(100) + 1;
	}

	public abstract void move();
}

/**
 * generate a number from 10 to a 1000 in powers of 
 * ten
 * @author danbro
 *
 */
class BigNumber extends ParentNumber
{

	public BigNumber(int x, int y) 
	{
		super(x, y);
		number = number * 10;
	}

	@Override
	public void move() 
	{

		rectangle.setY(rectangle.getY() + 50);
//		rectangle.setX(rectangle.getX() + 50);
//		if (rectangle.getX() > 600) 
//		{
//			rectangle.setX(40);
//			rectangle.setY(rectangle.getY() + 100);
//		}
	}
}

/**
 * generate a number from -100 to 0
 * @author danbro
 *
 */
class SmallNumber extends ParentNumber
{
	
	public SmallNumber(int x, int y) 
	{
		super(x, y);
		number = number - 100;
	}

	public void move() 
	{
		rectangle.setY(rectangle.getY() + 50);
//		rectangle.setX(rectangle.getX() + 50);
//		if (rectangle.getX() > 600) 
//		{
//			rectangle.setX(40);
//			rectangle.setY(rectangle.getY() + 100);
//		}
	}
}

/**
 * Generate a number from 1 to 100;
 * @author danbro
 *
 */
class MediumNumber extends ParentNumber
{
	
	public MediumNumber(int x, int y) 
	{
		super(x, y);
	}

	public void move() 
	{
		rectangle.setY(rectangle.getY() + 50);
	}
}




