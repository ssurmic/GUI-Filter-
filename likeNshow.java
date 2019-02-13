import java.awt.image.BufferedImage;


import java.io.File;
import javax.imageio.ImageIO;
import javax.script.Bindings;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.sun.glass.ui.CommonDialogs.ExtensionFilter;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPaneBuilder;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import javafx.stage.FileChooser;
 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.RowConstraints;


/**
 * @author zizhao zhang ,Jianbo Huang,Yijing Li
 * @pid: A92121287, A91082706, A99108545
 * @compile: javac likeNshow.java
 * @run: java likeNshow
 *   the class to pass in all the variables into the parameter of the myButton
 *   in order to create four buttons; On the other initialized the gridpane, 
 *   scenes, and stages to perform the display of the javafx effect. 
 *
 */
public class likeNshow extends Application{


	//launching the stage to show
	public static void main(String[] args) {
		Application.launch(args);
	}

	//declaring all the global variables
	public Image Upload;
	public GridPane pane;
	public Stage stage;
	public MyButton dislike;
	public Label label1;
	public MyButton upload;
	public File file;
	public MyButton like;
	public MyButton edit;
	public String filePath;
	public Image base;
	public ImageView IM;
	public double imgX = 0;
	public double imgY = 0;
	public double initTranslateX = 0;
	public double initTranslateY = 0;
	//initializing the stage , scene, and gridpane
	/**
	 * @param : The stage for displaying all the elements
	 * the method assign appropriate variables to the call the method
	 * Mybutton in order to create elements on the stage.
	 */

	public void start(Stage stage) throws Exception {

		try{

			//showing the image
			//setting up the file choosers
			FileChooser fileChooser = new FileChooser();
			//Declaring local pictures for all the buttons and backgrounds. 

			Image unlike= new Image("./img/DISLIKE2.jpg");
			Image Like = new Image("./img/LIKE2.jpg");
			Image edit2 = new Image("./img/editLast.jpg");
			Image background1 = new Image("./img/smartphone1_meitu_2.jpg");
			Image start= new Image("./img/instagram-512.jpg");

			//set up the button for dislike
			dislike = new MyButton(unlike,MyButton.BUTTON_DISLIKE,this);

			//set up the button for like
			like= new MyButton(Like ,MyButton.BUTTON_LIKE,this);

			//setting up the button for upload
			upload = new MyButton(start, MyButton.BUTTON_UPLOAD,this);

			//setting up the button for edit
			edit= new MyButton(edit2, MyButton.BUTTON_EDIT,this);










			//set up the gridPane to stage the button onto
			pane = new GridPane();
			//setting the constraints of the gridpane 
			for (int i = 0; i < 8; i++) {
				ColumnConstraints column = new ColumnConstraints(35);
				pane.getColumnConstraints().add(column);
			}
			//ratio 1:2
			for(int j = 0; j < 16; j++){
				RowConstraints row = new RowConstraints(35);
				pane.getRowConstraints().add(row);
			}




			//setting back ground pictures for display
			ImageView imageView1 = new ImageView();
			imageView1.setImage(background1);





			//setting buttons onto the gridpane and adjust layout
			pane.setPrefSize(25, 35);
			//adding all the buttons onto the gridpane
			pane.add(imageView1,0 ,8);
			pane.add(upload,3,1);
			pane.add(dislike,6 ,15);
			pane.add(like, 1, 15);
			pane.add(edit, 3, 15);
			pane.setPadding(new Insets(10, 12, 40, 12));
			//makes the color black for gridPane and the edges of buttons
			pane.setStyle("-fx-background-color: #000000;");
			dislike.setStyle("-fx-background-color: #000000;");
			upload.setStyle("-fx-background-color: #000000;");
			like.setStyle("-fx-background-color: #000000;");
			edit.setStyle("-fx-background-color: #000000;");

			Scene scene = new Scene(pane);

			stage.setScene(scene);
			stage.setHeight(900);
			stage.setWidth(1600);
			stage.sizeToScene(); 
			stage.show();
			//allow the stage to show



		}




		catch(Exception e1){
			//catch the exception
			System.out.println("something went wrong");



		}
	}




}

