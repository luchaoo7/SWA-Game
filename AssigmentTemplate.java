import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

	//Tab1 contents
	View1 mainTab1 = new View1();
	//Tab2 contents
	View2 mainTab2 = new View2();

	Operator operate;
	//to pace the game
	Timer timer2;
	//task to be ran
	TimerTask task;
	
	ArrayList<ParentNumber> collectionOfNumbers = new ArrayList<ParentNumber>();
	Factory numberMaker = new Factory();
	Level difficultyLvl = new Level();
	Score scoreClass = new Score();
	Mathematician mathGuy = Mathematician.getInstace();
	Random rand = new Random();
	
	//counter to determine when to delete numbers
	int counter = 7;
	//the value of each side of the operation
	//will be stored in this variables
	double leftNumber;
	double rightNumber;
	//player
	String name = "Annoni";
	
	//and compares it with the user input
	//when ENTER is pressed
	EventHandler<KeyEvent> keyEvent = new EventHandler<KeyEvent>() 
	{
		@Override
		public void handle(KeyEvent event) 
		{
			if (event.getCode() == KeyCode.ENTER) 
			{
				//compute operation 
				double result = operate.compute(leftNumber, rightNumber);

				//get userinput from textfield
				String numberInput = mainTab1.getTextField().getText();
				//check numeric value of user input
				double userInput = operate.userInput(numberInput);

				if (userInput == result) 
				{
					mainTab1.getGc().setStroke(Color.YELLOW);
					mainTab1.getGc().strokeText("Correct", 400, 100);
					mainTab1.getGc().drawImage( new Image(AssigmentTemplate.class.getResource("/resource/happy_boy.png").toExternalForm()), 400, 100);
					
					//update score
					scoreClass.setScore();
					scoreClass.setStreak();

					//set new image
					mathGuy.setImage(scoreClass.getScore());
					mainTab2.getGcp1().drawImage(mathGuy.getImage(), mathGuy.getRectangle().getX(), mathGuy.getRectangle().getY());

					mathGuy.setImage(scoreClass.getStreak());
					mainTab2.getGcp2().drawImage(mathGuy.getImage(), mathGuy.getRectangle().getX(), mathGuy.getRectangle().getY());
					if (scoreClass.getStreak() > scoreClass.getStreakGauge()) 
						scoreClass.setStreakGauge(scoreClass.getStreak());
					//Generate a random operation
					if (difficultyLvl.getClickedLevel() == 2) 
						difficultyLvl.setLevel(rand.nextInt(4));
					
					//remove operations answered correctly
					operate.removeNumbers(collectionOfNumbers);
					//if arraylist is empty
					endGame();
					// to have 8 seconds for the next
					//operation
					counter = 7;
					//clear text field
					mainTab1.getTextField().clear();
					//check if collection is empty
					//if it is, exit
					mainTab2.getLabelScore().setText("Your Score: " + scoreClass.getScore());
					mainTab2.getLabelStreak().setText("Your Streak: " + scoreClass.getStreakGauge());
				}
				else
				{
					//set streak to 0 if miss answer
					scoreClass.setStreakZero();
					//clear text field
					mainTab1.getTextField().clear();
					mainTab1.getGc().setStroke(Color.RED);
					mainTab1.getGc().drawImage( new Image(AssigmentTemplate.class.getResource("/resource/think.png").toExternalForm()), 400, 100);
				}
			}
		}
	};
	
	private void endGame()
	{
		//If collection is empty, exit game
		if (collectionOfNumbers.isEmpty()) {
			
			mainTab1.getGc().setFill(Color.BLACK);
			mainTab1.getGc().fillRect(0, 0, mainTab1.getCanvas().getWidth(), mainTab1.getCanvas().getHeight());
			scoreClass.insertScore(name, scoreClass.getScore(), scoreClass.getStreakGauge());

			String allScore = scoreClass.getTop10Scores();
			mainTab1.getGc().strokeText( allScore, 10, 50);
			timer2.cancel();
			//prevent from entering text
			mainTab1.getTextField().setEditable(false);
			mainTab1.getNameField().setEditable(false);
		}
	}
	//eventHandler to action the stop and start button
	EventHandler<ActionEvent> actionButton = new EventHandler<ActionEvent>() 
	{
		@Override
		public void handle(ActionEvent event) 
		{
			//setting the scenes depending on buttons clicked
			if (event.getSource() == mainTab1.getCoverInstruction()) 
				tab1.setContent(mainTab1.getInstructPane());
			if (event.getSource() == mainTab1.getInstructionCover())
				tab1.setContent(mainTab1.getCoverPane());
			if (event.getSource() == mainTab1.getBtnEnterGame()|| 
					event.getSource() == mainTab1.getCoverEnterGame()) 
				tab1.setContent(mainTab1.getRootTab1());
			if (event.getSource() == mainTab1.getStop()) 
			{
				if (task != null)
					task.cancel();

				mainTab1.getTextField().setVisible(false);
				mainTab1.getNameField().setEditable(true);
				name = "Annoni";
				mainTab1.getNameField().clear();
			}

			if (event.getSource() == mainTab1.getBtnRandom()) 
			{
				difficultyLvl.setLevel(4);
				difficultyLvl.setClickedLevel(2);
			}
			if (event.getSource() == mainTab1.getBtnVeryEasy()) 
			{
				difficultyLvl.setLevel(0);
				difficultyLvl.setClickedLevel(1);
			}

			if (event.getSource() == mainTab1.getBtnEasy()) 
			{
				difficultyLvl.setLevel(1);
				difficultyLvl.setClickedLevel(1);
			}
			if (event.getSource() == mainTab1.getBtnHard()) 
			{
				difficultyLvl.setLevel(2);
				difficultyLvl.setClickedLevel(1);
			}
			if (event.getSource() == mainTab1.getBtnTough()) 
			{
				difficultyLvl.setLevel(3);
				difficultyLvl.setClickedLevel(1);
			}
			if (event.getSource() ==  mainTab1.getStart()) 
			{
				//regulating name entry
				String userName = mainTab1.getNameField().getText().trim();
				if (userName.length() >= 6) 
					userName = mainTab1.getNameField().getText().trim().substring(0, 6);
				if (userName.length() != 0)
					name = userName;
				mainTab1.setNameFieldText(name);

				//set image to start image
				mainTab1.getNameField().setText(name);
				mathGuy.setImage(0);
				mainTab2.getGcp1().drawImage(mathGuy.getImage(), mathGuy.getRectangle().getX(), mathGuy.getRectangle().getY());
				mainTab2.getGcp2().drawImage(mathGuy.getImage(), mathGuy.getRectangle().getX(), mathGuy.getRectangle().getY());
				//set button difficulty visible
				if (difficultyLvl.getClickedLevel() == 0)
					difficultyLvl.setLevel(0);
				//can type in textfield
				mainTab1.getTextField().setVisible(true);
				mainTab1.getTextField().setEditable(true);;
				mainTab1.getNameField().setEditable(false);
				mainTab1.getTextField().clear();

				//set level to easy when you start
				//set time to answer question back to 0
				counter = 7;
				//focus text field when starting
				mainTab1.getTextField().requestFocus();;

				//clear textfield message once programme start
				if (mainTab1.getTextField().getText().equalsIgnoreCase("Your Answer")) 
					mainTab1.getTextField().clear();
				//cancel task if running and start again
				if (task != null)
					task.cancel();
				collectionOfNumbers.clear();
				timer2 = new Timer();
				task = new TimerTask() {
					
					@Override
					public void run() {
						runnable.run();
					}
				};
				scoreClass.reset(scoreClass, mainTab2.getLabelScore(), mainTab2.getLabelStreak());
				populateArrayOfNumbers();
				timer2.schedule(task, 0, 1000);
			}
		}
	};
	/**
	 * Method to populate array of numbers
	 */
	private void populateArrayOfNumbers()
	{
		//clear collection
		collectionOfNumbers.clear();
		for (int x = 1; x <= (difficultyLvl.getOperations() * 2); x++) 
		{
			int randomNumber = rand.nextInt(15) + 1;
			//reset location the numbers to start at
			int xlocation = x % 6;
			if (xlocation == 0) 
				xlocation = 6;
			//System.out.println(randomNumber);
			collectionOfNumbers.add(numberMaker.createNumber(randomNumber, 50 * xlocation, 50));
		}
		//Display the amount of operations in array
		mainTab2.getLabelTotal().setText("Possible Score: " + (collectionOfNumbers.size() / 2));
	}
	/**
	 * Runnable for task to run
	 */
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			//check if random button has been pressed
			//generate a random operation each time
			if (difficultyLvl.getClickedLevel() == 2 && difficultyLvl.getLevel() == 4) 
				difficultyLvl.setLevel(rand.nextInt(4));
				//generate a random number which identifies an operator
			operate = new Operator(difficultyLvl.getLevel());
			//background colour
			mainTab1.getGc().setFill(Color.BLACK);
			mainTab1.getGc().fillRect(0, 0, mainTab1.getCanvas().getWidth(), mainTab1.getCanvas().getHeight());
			
			//assigning numbers in the array to left or right side of the operation 
			ParentNumber singleNumber = collectionOfNumbers.get(0);
			ParentNumber singleNumber2 = collectionOfNumbers.get(1);

			//always divide big number by smaller number
			if (operate.getNumber() == 2) 
			{
					rightNumber = Math.abs(singleNumber2.getNumber()) / 2;
					leftNumber = (Math.abs(singleNumber.getNumber()) / 2) * rightNumber;
			}
			else
			{
				leftNumber = singleNumber.getNumber();
				rightNumber = singleNumber2.getNumber();
			}

			//to place the operator in the middle of the two numbers 
			double locationOperator = (singleNumber.getRectangle().getX() + singleNumber2.getRectangle().getX() + 50) / 2 + 15;
			//set the text to white
			mainTab1.getGc().setFont(new Font(20));
			mainTab1.getGc().setStroke(Color.WHITE);
			mainTab1.getGc().strokeText(operate.getOPERATOR(), locationOperator, singleNumber2.getRectangle().getY());

			//Draw numbers
			mainTab1.getGc().strokeText((int)leftNumber + "", singleNumber.getRectangle().getX(), singleNumber.getRectangle().getY());
			mainTab1.getGc().strokeText((int)rightNumber +" ", singleNumber2.getRectangle().getX() + 50, singleNumber2.getRectangle().getY());
			singleNumber.move();
			singleNumber2.move();

			mainTab1.getGc().setFont(new Font(40));
			mainTab1.getGc().strokeText(counter + "", 500, 50);
			//Every 7 seconds delete index 1 and 0
			counter--;
			if (counter  == 0) 
			{
				scoreClass.setStreakZero();
				operate.removeNumbers(collectionOfNumbers);
				endGame();

				if (difficultyLvl.getClickedLevel() == 2)
					difficultyLvl.setLevel(rand.nextInt(4));
				counter = 7;
			}
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

		mainTab1.setOnActionButton(actionButton , keyEvent);

		tab1.setContent(mainTab1.getCoverPane());
		tab2.setContent(mainTab2.getRootTab2());
		mainTab1.setCanvas(new Canvas(600, 600));
		//set the font
		mainTab1.getGc().setFont(new Font("Arial", 20));
		//stroke the number and the location
		stage.show();
	}
}