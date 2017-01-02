import java.util.ArrayList;
import java.util.Random;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
	
	FlowPane rootTab1;
	Pane ui, game;
	Canvas canvas;
	GraphicsContext gc;
	Random rand = new Random();
	
	SmallNumber myNumber;
	Operator operate;
	
	Factory numberMaker = new Factory();
	//amount of numbers in the arraylist
	int i = 2;
	ArrayList<ParentNumber> collectionOfNumbers = new ArrayList<ParentNumber>();
	
	
	int leftNumber;
	int rightNumber;
	
	
	
	AnimationTimer timer = new AnimationTimer() 
	{
		//to determine what type of number to create
//		int randomNumber = rand.nextInt(10) + 1;
		//create number object
//		ParentNumber objectNumber = numberMaker.createNumber(randomNumber, 100, 100);


		@Override
		public void handle(long now) 
		{
			operate = new Operator(1);
			//background colour
			gc.setFill(Color.BLACK);
			gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

			//int number
//			int number = objectNumber.number;
			//convert the number to string
//			String stringNUumber = String.valueOf(number);
			try 
			{
				Thread.sleep(750);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			for (int i = 0; i < collectionOfNumbers.size(); i++) 
			{
				ParentNumber singleNumber = collectionOfNumbers.get(i);

				//int number
				int number = singleNumber.number;

				//assigning numbers in the array to left or right side of the operation 
				//so user input can be checked against later
				int checker = i+1;
				if (checker % 2 != 0) 
				{
					leftNumber = number;
				}
				else 
				{
					rightNumber = number;
				}
				

				//convert the number to string
				String stringNumber = String.valueOf(number);

				gc.strokeText(stringNumber, singleNumber.rectangle.getX(), singleNumber.rectangle.getY());
				singleNumber.move();
			}
				gc.strokeText(operate.ADDITION, 400, 100);

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
		
		game = new Pane();
		game.setPrefSize(600, 600);
		game.setStyle("-fx-background-color: #000000;");
		rootTab1.getChildren().add(game);
		
		canvas = new Canvas(600, 600);
		game.getChildren().add(canvas);
		
		for (int x = 1; x <= i; x++) 
		{
			int randomNumber = rand.nextInt(16) - 5;
			//System.out.println(randomNumber);
			collectionOfNumbers.add(numberMaker.createNumber(randomNumber, 50 * x, 50));
		}

		
		//create a number object
//		myNumber = new SmallNumber(400, 100);
		
		//get the number generated with//in the object
//		int number = myNumber.number;
		//convert the number to string
//		String stringNUumber = String.valueOf(number);

		//set up graphic context
		gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.LIGHTCYAN);
		//set the font
		gc.setFont(new Font("Arial", 20));
		//stroke the number and the location
//		myNumber.move();
//		gc.strokeText(stringNUumber, myNumber.rectangle.getX(), myNumber.rectangle.getY());
		
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
		else if (amount >= 0 && amount < 5) 
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
	public String ADDITION = "";

	public Operator(int operator) 
	{
		switch (operator) 
		{
		case 1:
			ADDITION = "+";
			break;
		case 2:
			ADDITION = "-";
			break;
		case 3:
			ADDITION = "/";
			break;
		case 4:
			ADDITION = "*";
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




