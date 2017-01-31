/**
 * generate a number from -1 to -10
 * @author danbro
 */
public class SmallNumber extends ParentNumber
{
	
	public SmallNumber(int x, int y) 
	{
		super(x, y);
//		number = number * (-1);
		setNumber();
	}

	/**
	 * move number downwards as loops carries on
	 */
	@Override
	public void move() 
	{
//		rectangle.setY(rectangle.getY() + 50);
		setRectangle(80);
	}

	@Override
	protected void setNumber() 
	{
		setNumber(getNumber() * (-1));
	}
	
}

