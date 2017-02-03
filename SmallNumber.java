/**
 * generate a number from -1 to -10
 */
public class SmallNumber extends ParentNumber
{
	
	public SmallNumber(int x, int y) 
	{
		super(x, y);
		setNumber();
	}
	/**
	 * move number downwards as loops carries on
	 */
	@Override
	public void move() 
	{
		setRectangle(80);
	}

	@Override
	protected void setNumber() 
	{
		setNumber(getNumber() * (-1));
	}
	
}

