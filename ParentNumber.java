import java.util.Random;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author danbro
 */
//MarkerIF not doing nothing yet
public abstract class ParentNumber implements MarkerIF
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

