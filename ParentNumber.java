import java.util.Random;
import javafx.scene.shape.Rectangle;
/**
 * 
 * @author danbro
 */
public abstract class ParentNumber
{
	private Rectangle rectangle;
	private Random random;
	private int number;
	
	public ParentNumber(int x, int y) 
	{
		//create rectangle at x,y coordinate and size w,h
		rectangle = new Rectangle(x, y, 100, 100);
		random = new Random();
		number = random.nextInt(10) + 1;
	}
	/**
	 * @return a number
	 */
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(int setY) {
		this.rectangle.setY(this.rectangle.getY() + setY);
	}
	/**
	 * A method for all subclasses to implement
	 * and generate a number in a given range.
	 */
	protected abstract void setNumber();
	/**
	 * A method for all subclasses to implement
	 * to move their location
	 */
	public abstract void move();
}

