import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Mathematician{
	
	private static Mathematician instace = null;

	private Image image;
	private Rectangle rectangle;
	private static String imageFile;
	
	private Mathematician(String imageFile)
	{
		image = new Image(AssigmentTemplate.class.getResource("resource/" + imageFile).toExternalForm(),600, 200, false, false);
		rectangle = new Rectangle(0, 0, 600, 200);
	}
	
	public static Mathematician getInstace()
	{
		imageFile = "alan_turing.jpg";
		if (instace == null) {
			instace = new Mathematician(imageFile);
		}
		return instace;
	}

	public void setImage(int fileNumber) 
	{
		switch (fileNumber) {
		case 1:
			imageFile = "isaak_newton.jpg";
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
		default:
			imageFile = "isaak_newton.jpg";
			break;
		}
		this.image = new Image(AssigmentTemplate.class.getResource("resource/" + imageFile).toExternalForm(),600, 200, false, false);
	}

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




