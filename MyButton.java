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
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPaneBuilder;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage; 
/**
 * @author zizhao zhang ,Yijing Li
 * @pid: A92121287, A99108545
 * @compile: javac MyButton.java
 * @run: java Button
 *   MyButton class includes all the methods initialized and all the actions 
 *   triggered by the buttons:
 *   upload: 
 *   
 *    the button allows users to choose a picture image file within
 *    the local library,where contains all the proper sized images.and 
 *    display the image onto the screen on the "phone"; once the image 
 *    is uploaded successfully, the image should be blurred,fuzzy,and 
 *    unclear so that users are unable to visualize the desired pictures
 *    at first
 *            
 *   edit:   
 *   
 *    the button is designed to let the users to visualize the images more 
 *    clear and direct by hitting the button edit, the users can adjust 
 *    the position of the image around to the best visual position by 
 *    dragging the mouse to pull the picture on the phone screen, 
 *    meanwhile, hitting the edit button, the color of the images become 
 *    more and more clear and brighter immediately as the more times users 
 *    hitting the button edit.
 *            
 *   like:    
 *   
 *   the button allows users to show interest to the image shown on the screen
 *   if the button like is hit, the blurriness of the pictures will be eased
 *   and decrease; picture becomes more and more clear
 *   
 *   
 *   dislike:
 *   
 *   the button dislike allows to show dislike to the image shown on the 
 *   screen; if the button is hit, the blurriness of the pictures will be 
 *   increased; picture becomes more and more blurred
 *   
 */


//method extends from button class
public class MyButton extends Button {
	//integers to classify the functions of the buttons
	public static final int BUTTON_UPLOAD = 0;
	public static final int BUTTON_LIKE = 1;
	public static final int BUTTON_DISLIKE = 2;
	public static final int BUTTON_EDIT = 3;

	//declared as global variable for the use of parameter
	private Image buttonImg;//pictures passes into the button
	private int buttonFunction;//switch case number identifier 

	//integer that counts the number of likes for tracking
	private static int counterLike = 0;
	private static int BlurrIndex=5;// the default index of the blurry filter
	private static double filterIndex= 2.0;//the initial index for the filter
	private static int midWidth = 4;//midsize of the width of the scene
	private static int midHeight=4;//midsize of the height of the scene
	/**
	 * create the buttons
	 * passes into the image for the buttons
	 * classify the functions for the buttons
	 * @param Image buttonImg: The images passes into the buttons
	 * @param int buttonFunction: the integer for classifying the functions
	 * @param likeNshow mainController: the parameter referring back to 
	 * the main method.
	 */


	public MyButton(Image buttonImg, int buttonFunction, 
			likeNshow mainController){

		super();//call super constructor
		this.buttonImg= buttonImg;//passes in the image
		this.buttonFunction = buttonFunction;
		//assign the graphics to the button
		this.setGraphic(new ImageView(buttonImg));
		//mouse event handler for the functions of uploading 
		//and displaying the images
		this.setOnMouseClicked(new EventHandler<MouseEvent>(){



			@Override
			public void handle(MouseEvent event) {
				switch(buttonFunction){
				//case 1: upload
				case MyButton.BUTTON_UPLOAD: 
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("select an image");
					fileChooser.getExtensionFilters().addAll(
							new FileChooser.ExtensionFilter("All Images", "*.*"),
							new FileChooser.ExtensionFilter("JPG", "*.jpg"),
							new FileChooser.ExtensionFilter("GIF", "*.gif"),
							new FileChooser.ExtensionFilter("BMP", "*.bmp"),
							new FileChooser.ExtensionFilter("PNG", "*.png")
							);
					//filtering the type of the file selected
					//restriction are all pictures
					//local variable file
					File file;
					file = fileChooser.showOpenDialog(mainController.stage);
					String filePath;
					filePath= file.getPath();//opening the file
					Image base;//setting images
					base= new Image("file:"+filePath);

					mainController.IM = new ImageView(base);

					mainController.pane.add(mainController.IM,midWidth,midHeight);
					//always places the image onto the middle of the phone
					System.out.println(mainController.IM.fitHeightProperty());
					System.out.println(mainController.IM.fitHeightProperty());
					//applying the algorithm of gaussianblurr to make the 
					//image fuzzy 
					GaussianBlur gaussianBlur1 = new GaussianBlur();
					//apply the blurry filter
					mainController.IM.setEffect(gaussianBlur1);
					break;

					//case 2: like button
				case MyButton.BUTTON_LIKE:
					counterLike=counterLike - 1;
					System.out.println(counterLike);//show the index of the counterlike
					//manipulating the depth of the blurring filter
					//more clicked more clear
					GaussianBlur gaussianBlur = new GaussianBlur(BlurrIndex*counterLike);
					mainController.IM.setEffect(gaussianBlur);
					break;

					//case 3: dislike button
				case MyButton.BUTTON_DISLIKE:
					counterLike=counterLike + 1;
					System.out.println(counterLike);//show the index of the counterlike
					//manipulating the depth of the blurring filter
					//more clicked more blurred
					GaussianBlur Blur = new GaussianBlur(BlurrIndex*counterLike);
					mainController.IM.setEffect(Blur);



					break;
					//case 4: edit button
				case MyButton.BUTTON_EDIT:
					//apply the affect of glow filter
					//as the clicking number goes up, the effect increases instantly
					//the filter allows the user to adjust the depth of the color of the
					//imageview
					//the more times user click on the edit button,the brighter it is. 
					filterIndex= filterIndex-0.02	;
					mainController.IM.setEffect(new Glow(filterIndex));


					//adding the onMouseDrag method into the function of edit for
					//users to find the best position of placing the images
					mainController.IM.setOnMousePressed(new EventHandler<MouseEvent>() {

						public void handle(final MouseEvent mouseEvent) {

							mainController.imgX = mouseEvent.getX();
							mainController.imgY = mouseEvent.getY();
							mainController.initTranslateX = mainController.IM.getTranslateX();
							mainController.initTranslateY = mainController.IM.getTranslateY();
						}
					});
					//matching the position of the imageview on the grind pane with  
					//the position of the mouse
					mainController.IM.setOnMouseDragged(new EventHandler<MouseEvent>() {

						public void handle(final MouseEvent mouseEvent) {



							mainController.IM.setTranslateX(mainController.initTranslateX 
									+ mouseEvent.getX()- mainController.imgX);
							mainController.IM.setTranslateY(mainController.initTranslateY
									+ mouseEvent.getY()- mainController.imgY);

							mainController.initTranslateX = mainController.IM.getTranslateX();
							mainController.initTranslateY = mainController.IM.getTranslateY();
						}
					});


					break;

				}

			}


		});
	}
}















