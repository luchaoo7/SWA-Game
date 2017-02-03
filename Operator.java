/**
 * class to dictate what operation 
 * will take place
 * @author danbro
 */
public class Operator
{
	private String OPERATOR = "";
	private int number;

	public Operator(int operator) 
	{
		//store the passed number to identify 
		//the operator user later
		number = operator;

		switch (operator) 
		{
		case 0:
			OPERATOR = "+";
			break;
		case 1:
			OPERATOR = "-";
			break;
		case 2:
			OPERATOR = "/";
			break;
		case 3:
			OPERATOR = "*";
			break;
		}
	}
	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * @return operator to be used
	 */
	public String getOPERATOR() {
		return OPERATOR;
	}
}