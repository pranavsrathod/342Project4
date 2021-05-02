// Pranav Rathod and Parth Tawde
// CS 342 Project 4

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.scene.paint.Color;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.geometry.Pos;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFXTemplate extends Application {
	private Image img;
	private ImageView view;
	private Stage dummyStage;
	private int num = 0;
	private int zeroIndex;
	private boolean flag;
	private GridPane board;
	private MenuBar menu;
	private Button AI_H1;
	private Button AI_H2;
	private Button solution;
	private MenuItem howToPlay;
	private MenuItem exit;
	//private MenuItem solution;
	private MenuItem newPuzzle;
	private Menu options;
	private int column = 0;
	private int row = 0;
	private int tempArr[];
	// All the puzzles stored in arrays
	private int array1[] = {14, 9, 13, 12, 15, 5, 4, 8, 11, 0, 7, 1, 3, 10, 6, 2};
	private int array2[] = {15, 0, 14, 12, 9, 7, 13, 1, 11, 6, 8, 5, 3, 4, 10, 2};
	private int array3[] = {15, 13, 8, 4, 9, 5, 0, 12, 11, 7, 10, 1, 3, 6, 14, 2};
	private int array4[] = {0, 13, 5, 12, 9, 11, 7, 1, 4, 14, 8, 2, 3, 10, 15, 6};
	private int array5[] = {13, 7, 5, 12, 9, 0, 14, 8, 11, 15, 4, 1, 3, 10, 6, 2};
	private int array6[] = {7, 0, 4, 12, 5, 13, 9, 8, 14, 11, 15, 1, 3, 10, 6, 2};
	private int array7[] = {1, 6, 12, 5, 14, 13, 0, 15, 11, 4, 9, 8, 3, 10, 7, 2};
	private int array8[] = {9, 15, 13, 12, 7, 6, 0, 5, 10, 3, 4, 8, 11, 14, 2, 1};
	private int array9[] = {11, 5, 7, 12, 0, 4, 9, 8, 13, 3, 14, 1, 10, 15, 6, 2};
	private int array10[] = {7, 10, 0, 8, 5, 4, 15, 14, 9, 13, 12, 2, 3, 6, 1, 11};
	// testing array
	private int solving_array[] = {1,0,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
	// Prof.HallenBeck's array
	private int testArray[] = {0, 14, 13, 12, 15, 9, 5, 8, 11, 7, 4, 1, 3, 10, 6, 2};
	// ArrayList of arrays
	private ArrayList<int[]> arrays = new ArrayList<int[]>(Arrays.asList(array1, array2, array3, array4, array5, array6, array7, array8, array9, array10));
	// creating a new arrayList
	ArrayList<Node> solutionPath = new ArrayList<Node>();

	ExecutorService ex = Executors.newFixedThreadPool(10);
	private ArrayList<GameButton> checkArray = new ArrayList<GameButton>();
	//private GameButton checkArray[][] = new GameButton[4][4];
	//private GameButton empty, dummyButton;
	//private boolean emptyFlag = false;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		ex = Executors.newFixedThreadPool(5);
		dummyStage = primaryStage;
		primaryStage.setTitle("Welcome to JavaFX");
		//Scene scene = new Scene(new VBox(), 700,700);
		primaryStage.setScene(welcomeScene());
		PauseTransition halt = new PauseTransition(Duration.seconds(3));
		halt.setOnFinished(e -> {
			dummyStage.setScene(gameScene());
			dummyStage.show();
		});
		halt.play();
		primaryStage.show();
		
	}
	
	// Start Scene
	public Scene welcomeScene() {
		Label label = new Label("Welcome to 15 PUZZEL");
		label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		label.setBackground(new Background(new BackgroundFill(Color.WHITE,null, null)));
		label.setAlignment(Pos.CENTER);
		Image image = new Image("welcome.png");
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(label);
		borderPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));
		return new Scene(borderPane, 400, 400);
	}
	
	// Gamescene
	public Scene gameScene() {
//		 Grid Pane
		AI_H1 = new Button("AI H1");
		AI_H2 = new Button("AI H2");
		solution = new Button("See the Solution");
		solution.setDisable(true);
		AI_H1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		AI_H2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		solution.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		AI_H1.setPrefWidth(70);
		AI_H2.setPrefWidth(70);
		//solution.setPrefWidth(100);
		AI_H1.setPrefHeight(30);
		AI_H2.setPrefHeight(30);
		solution.setPrefHeight(30);
		board = new GridPane();
		board.setVgap(20);
		board.setHgap(20);
		SetConfigurations();
		board.setAlignment(Pos.CENTER);
		// VBox
//		VBox vBox = new VBox(100, AI_H1, AI_H2);
		HBox hBox = new HBox(50, AI_H1, AI_H2, solution);
		VBox vBox = new VBox(15, hBox, board);
		vBox.setAlignment(Pos.CENTER);
		// HBox
//		HBox hBox = new HBox(5, vBox, board, solution);
		
		hBox.setAlignment(Pos.CENTER);
		// Menu Bar
		menu = new MenuBar();
		options = new Menu("options");
		howToPlay = new MenuItem("How To Play");
		exit = new MenuItem("exit");
		newPuzzle = new MenuItem("New Puzzle");

		
		AI_H1.setOnAction(e-> heuristic("heuristicOne",1));
		AI_H2.setOnAction(e->heuristic("heuristicTwo",2));
		
		solution.setOnAction(e -> {
			newPuzzle.setDisable(true);
			solution.setDisable(true);
			board.setDisable(true);
			AI_H1.setDisable(true);
			AI_H2.setDisable(true);
			Graphics();
		});
		howToPlay.setOnAction(e -> {
		      Dialog<String> dialog = new Dialog<String>();
		      dialog.setTitle("How To Play");
		      ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
		      dialog.setContentText("1.This is a 15 puzzle game.\n"
		      		+ "2.You have 15 numbered tile and a blank tiles that represents zero. Click the number next to a blank tile to swap.\n"
		      		+ "3.There is additional two heuristic buttons that provide you with a minimum of 10 steps in total at one time.\n"
		      		+ "4.Once a heuristic button is clicked the solution button is enabled.\n"
		      		+ "5.Pressing the solution button is going to graphically switch the buttons to get the user to the closest solution.\n"
		      		+ "6.You could also play the game manually and win via arranging all the button elements in an ascending order.\n"
		      		+ "7.Enjoy!!!!!!!!!");
		      dialog.getDialogPane().getButtonTypes().add(type);
		      dialog.showAndWait();
		});
		// setting up menu items
		options.getItems().addAll(newPuzzle,howToPlay,exit);
		// setting menu bar
		menu.getMenus().addAll(options);
//		menu.setStyle("-fx-background-color: white");
		exit.setOnAction(e -> {
			ex.shutdown();
			System.exit(0);
		});
		newPuzzle.setOnAction(e -> newGame());
		Image image = new Image("GUI_background.gif");
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		BorderPane pane = new BorderPane();
		pane.setCenter(vBox);
		pane.setTop(menu);
		pane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));
		return new Scene(pane, 500, 500);
	}
	// Building the gridPane
	public void SetConfigurations() {
		checkArray = new ArrayList<GameButton>();
		Random randomNumber = new Random();
		int index = randomNumber.nextInt(arrays.size() -1);
		tempArr = new int[16];
		// making a copy of the random array
		int randomArray[] = arrays.get(index);
		// Copying the elements into the tempArr array
		for(int i = 0; i < 16; i++) {
			tempArr[i] = randomArray[i];
			System.out.print(tempArr[i] + " ");
		}
		System.out.println();
		int k = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
					GameButton box = new GameButton(tempArr[k]);
					num = tempArr[k];
					if (tempArr[k] == 0) {
						zeroIndex = k;
					}
					k++;
					board.add(box, j, i);  // adding buttons to the gridPane
					checkArray.add(box);  // adding buttons to the ArrayList
			}
		}
		// iterating through the ArrayList and assigning event handlers and colors to the buttons
		for (int i = 0; i < 16; i++) {
			configure(checkArray.get(i), i);
			setTileColor(checkArray.get(i), i);
		}
		
		
	}
	
	// Setting the style of buttons in the gridPane
	public void setTileColor(GameButton tile, int index) {
		if (tile.tileNum == index) {
			// For the right position of a button
			tile.setStyle("-fx-background-color: pink");
		} else {
			tile.setStyle ("-fx-background-color: White;");
		}
	}
	
	// AI algorithm function
	public void heuristic(String hType, int hNum){
		solutionPath = new ArrayList<Node>();
		// The heuristic algorithm will work on it's own thread to avoid
		// blocking the application thread.
		Thread t = new Thread(()-> {
			board.setDisable(true);
			Future<ArrayList<Node>> future = ex.submit(new MyCall(tempArr, hNum, hType));
			ex.submit(() -> {
				try {
					solutionPath = future.get();
					board.setDisable(false);
					solution.setDisable(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		});
		t.start();

	}
	
	// Function for swapping conditions
	private void configure(GameButton tile, int buttonPos) {
		tile.setOnAction(e -> {
			//System.out.println(buttonPos);
			solution.setDisable(true);
			
			if (((buttonPos + 4) <= 15) && (checkArray.get(buttonPos + 4).tileNum == 0)) {  
				// Swap with Button Below
				swapTile(tile, checkArray.get(buttonPos + 4));
			} else if (((buttonPos - 4) >= 0) && (checkArray.get(buttonPos - 4).tileNum == 0)) {  
				// Swap with Button Below
				swapTile(tile, checkArray.get(buttonPos - 4));
			} else if((buttonPos % 4 != 0) && (checkArray.get(buttonPos - 1).tileNum == 0)) {  
				// Swap with Button to the Left
				swapTile(tile, checkArray.get(buttonPos - 1));
			} else if (((buttonPos+1) % 4 != 0) && (checkArray.get(buttonPos + 1).tileNum == 0)) {  
				// Swap with Button to the Right
				swapTile(tile, checkArray.get(buttonPos + 1));
			} 
			//else if((buttonPos % 4 == 0) && (checkArray.get(buttonPos + 1).tileNum == 0)) {  
//				// Swap with Button to the Left
//				swapTile(tile, checkArray.get(buttonPos + 1));
//			} else if (((buttonPos+1) % 4 == 0) && (checkArray.get(buttonPos - 1).tileNum == 0)) {  
//				// Swap with Button to the Right
//				swapTile(tile, checkArray.get(buttonPos - 1));
//			}
		});
		
	}
	
	// Swap function
	private void swapTile(GameButton tile1, GameButton tile2) { 
		//System.out.println("Swapping " + tile1.tileNum + " with " + tile2.tileNum);
		String temp = tile1.getText();
		int tempNum = tile1.tileNum;
		tile1.setText(tile2.getText());
		tile1.tileNum = tile2.tileNum;
		tile2.setText(temp);
		tile2.tileNum = tempNum;
		int buttonPos1 = checkArray.indexOf(tile1);
		int buttonPos2 = checkArray.indexOf(tile2);
		
		// swapping the elements in the current array 
		int temp2 = tempArr[buttonPos1];
		tempArr[buttonPos1] = tempArr[buttonPos2];
		tempArr[buttonPos2] = temp2;
//		System.out.println("After Swapping " + tempArr[] + " with " + tile2.tileNum);
//		System.out.println(Arrays.toString(tempArr));
		if (tempArr[buttonPos1] == 0) {
			zeroIndex = buttonPos1;
		} else {
			zeroIndex = buttonPos2;
		}
		setTileColor(tile1, buttonPos1);
		setTileColor(tile2, buttonPos2);
		checkWin();
	}
	
	// Restart function
	public void newGame() {
//		solution.setDisable()
		board = new GridPane();
		SetConfigurations();
		dummyStage.setScene(gameScene());
	}
	
	// Check if all buttons are in order function
	private void checkWin() {
		flag = true;
		
		for (int i = 0; i < 16; i++) {
			int var = checkArray.get(i).tileNum;
			if (var != i) {
				flag = false;
				break;
			}
		}
		if (flag) {
			System.out.println("WON!!");
			PauseTransition halt = new PauseTransition(Duration.seconds(2.5));
			halt.setOnFinished(e -> {
				dummyStage.setScene(winScene());
			});
			halt.play();
		}
	}
	
	// Switch to a congratulating window/scene
	public Scene winScene() {
		Button b1 = new Button("New Game");
		Button b2 = new Button("Exit");
		b1.setOnAction(e -> newGame());
		b2.setOnAction(e-> {
			ex.shutdown();
			System.exit(0);
		});
		Label label = new Label("You Won!!");
		label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		label.setStyle("-fx-bar-fill: white;");
		label.setAlignment(Pos.CENTER);
		HBox hBox = new HBox(10, b1, b2);
		hBox.setAlignment(Pos.CENTER);
		VBox vBox = new VBox(200, label, hBox);
		vBox.setAlignment(Pos.CENTER);
		Image image = new Image("tenor.gif");
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		BorderPane borderPane = new BorderPane();
		borderPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));
		borderPane.setCenter(vBox);
		return new Scene(borderPane, 400, 400);
	}
	// Graphically switches the buttons in the gameScene
	public void printState(Node node){
		
		int[] puzzleArray = node.getKey();
		int zeroAt = 0;
		for(int i =0; i< puzzleArray.length; i++){
			if (puzzleArray[i]==0) {
				zeroAt = i;
				break;
			}
		}
		swapTile(checkArray.get(zeroIndex), checkArray.get(zeroAt));
	
	}
	// Function for switching positions only upto 10 moves in a row
	public void Graphics() {
		AtomicInteger count = new AtomicInteger(1);
//		while(count.get() <= 10) {
		PauseTransition halt2 = new PauseTransition(Duration.seconds(1.5));
		//halt2.play();
		halt2.setOnFinished(e -> {
			if (count.get() <= 10) {
				if (count.get() < solutionPath.size()) {
					printState(solutionPath.get(count.getAndIncrement()));
				}
				halt2.play();
			} else {
				solution.setDisable(true);
				board.setDisable(false);
				AI_H1.setDisable(false);
				AI_H2.setDisable(false);
				newPuzzle.setDisable(false);
			}
		});
		halt2.play();
		
	}	
	
}

