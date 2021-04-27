import javafx.scene.control.Button;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
public class GameButton extends Button{
	public int color;
	GameButton(){
		// setting the default instances of the button
		super("");
		Button button = new Button();
		button.setPrefSize(200,200);
	}
}
