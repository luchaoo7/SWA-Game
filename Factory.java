/**
 * Factory to make number
 * @author danbro
 *
 */
public final class Factory implements FactoryIF
{
	@Override
	public ParentNumber createNumber(int amount, int x, int y) 
	{
		if (amount > 0 && amount <= 5) 
			return (new SmallNumber(x, y));
		else if (amount > 5 && amount <= 10) 
			return (new MediumNumber(x, y));
		else
			return (new BigNumber(x, y));
	}
}

