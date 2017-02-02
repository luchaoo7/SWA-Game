import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import jdk.nashorn.internal.ir.CaseNode;

public class Mathematician{
	
	private static Mathematician instace = null;

	private Image image;
	private Rectangle rectangle;
	private static String imageFile;
	
	/**
	 * private constructor 
	 * passed the string name of an image
	 * @param imageFile
	 */
	private Mathematician(String imageFile)
	{
		image = new Image(AssigmentTemplate.class.getResource("resource/" + imageFile).toExternalForm(),600, 300, false, false);
		rectangle = new Rectangle(0, 0, 600, 200);
	}
	
	/**
	 * @return the Mathematician object if 
	 * its not already created
	 */
	public static Mathematician getInstace()
	{
		imageFile = "alan_turing.jpg";
		if (instace == null) {
			instace = new Mathematician(imageFile);
		}
		return instace;
	}

	/**
	 * Sets the image depending of the int value 
	 * received
	 * @param fileNumber
	 */
	public void setImage(int fileNumber) 
	{
		switch (fileNumber) {
		case 0:
			imageFile = "alan_turing.jpg";
			break;
		case 1:
			imageFile = "isaac_newton.jpg";
			break;
		case 2:
			imageFile = "al_khwarizmi.jpg";
			break;
		case 3:
			imageFile = "brahmagupta.jpg";
			break;
		case 4:
			imageFile = "galileo_galilei.jpeg";
			break;
		case 5:
			imageFile = "john_von_neumann.jpg";
			break;
		case 6:
			imageFile = "karl_friedrich.jpg";
			break;
		case 7:
			imageFile = "leonhard_euler.jpg";
			break;
		case 8:
			imageFile = "pythagoras.png";
			break;
		case 9:
			imageFile = "rene_descartes.jpg";
			break;
		case 10:
			imageFile = "genius.png";
			break;
		default:
			imageFile = "isaac_newton.jpg";
			break;
		}
		this.image = new Image(AssigmentTemplate.class.getResource("resource/" + imageFile).toExternalForm(),600, 300, false, false);
	}

	/**
	 * @return the rectangle 
	 */
	public Rectangle getRectangle() {
		return rectangle;
	}

	/**
	 * @return image
	 */
	public Image getImage() {
		return image;
	}

}




