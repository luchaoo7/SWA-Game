
/**
 * Generate a numbers from 1 to 10;
 * @author danbro
 */
public class MediumNumber extends ParentNumber{
	
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
//		rectangle.setY(rectangle.getY() + 50);
		setRectangle(50);
	}

	@Override
	protected void setNumber() {
		// TODO Auto-generated method stub
		
	}

}
