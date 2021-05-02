// Parth pushing origin
// Pranav Pushing Origin - Parth Pulls
import javafx.scene.control.Button;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
public class GameButton extends Button{
	public int color;
	public int tileNum;
	GameButton(int number){
		// setting the default instances of the button
		super("");
		this.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		tileNum = number;
		if (number == 0) {
			this.setText("");
		} else {
			this.setText(Integer.toString(number));
		}
		this.setPrefSize(80,80);
	}
	
}
