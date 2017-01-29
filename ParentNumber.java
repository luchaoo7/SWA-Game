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

	public abstract void move();

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
	 * all sub classes will have to implement this method
	 */
	protected abstract void setNumber();
}

