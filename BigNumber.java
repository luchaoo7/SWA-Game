/**
 * generate a number from 10 to a 100 in powers of 
 * ten
 * @author danbro
 */
public class BigNumber extends ParentNumber
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

