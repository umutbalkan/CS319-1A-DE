package gui;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PlayScreen extends VBox {

	private int width, height;
	private Canvas canvas;
	private GraphicsContext g;
	private GraphicsContext mini_g_left, mini_g_middle, mini_g_right;
	private Canvas minicanvas_left, minicanvas_right,minicanvas_middle;
	private int canvas_w, canvas_h;
	private int minicanvas_w_left, minicanvas_w_middle, minicanvas_w_right, minicanvas_h;
	private Image littleShip_img,bomb_img,playBG_img;
	private HBox topHBox;
	private Pane botPane;
	private Group root;
	private ImageView shipView;
	
	public PlayScreen(int w, int h,Image littleShip, Image bomb, Image bg, ImageView shipv) {
		super();
		width = w;
		height = h;
		minicanvas_w_left = width/4;
		minicanvas_w_middle = width/2;
		minicanvas_w_right = width/4;
		minicanvas_h = 120;
		canvas_w = width;
		canvas_h = height - minicanvas_h;
		
		littleShip_img = littleShip;
		bomb_img = bomb;
		playBG_img = bg;
		shipView = shipv;
		// create a canvas 
        canvas = new Canvas(canvas_w, canvas_h);
        minicanvas_left = new Canvas(minicanvas_w_left, minicanvas_h);
        minicanvas_middle = new Canvas(minicanvas_w_middle, minicanvas_h);
        minicanvas_right = new Canvas(minicanvas_w_right, minicanvas_h);
        
        
		
        // set GraphicsContext object
        g = canvas.getGraphicsContext2D();
        mini_g_left = minicanvas_left.getGraphicsContext2D();
        mini_g_middle = minicanvas_middle.getGraphicsContext2D();
        mini_g_right = minicanvas_right.getGraphicsContext2D();
        
       
        
        // set colors for graphics contexts for all canvas
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, canvas_w, canvas_h);
        
        mini_g_left.setFill(Color.BLACK);
        mini_g_left.fillRect(0, 0, minicanvas_w_left, minicanvas_h);
        
        
        mini_g_right.setFill(Color.BLACK);
        mini_g_right.fillRect(0, 0, minicanvas_w_right, minicanvas_h);
        
        
        mini_g_middle.setFill(Color.BLACK);
        mini_g_middle.fillRect(0, 0, minicanvas_w_middle, minicanvas_h);
        mini_g_middle.setStroke(Color.GRAY);
        mini_g_middle.setLineWidth(4);
        mini_g_middle.strokeLine(0, 0, 0, minicanvas_h);
        mini_g_middle.strokeLine(minicanvas_w_middle, 0, minicanvas_w_middle, minicanvas_h);

        

        
        // draw Graphics
        mini_g_left.drawImage(littleShip_img, 20, 20);
        mini_g_left.drawImage(littleShip_img, 80, 20);
        mini_g_left.drawImage(littleShip_img, 140, 20);
        
        mini_g_left.drawImage(bomb_img, minicanvas_w_left-48,minicanvas_h-32 );
        mini_g_left.drawImage(bomb_img, minicanvas_w_left-48,minicanvas_h-32-32);
        mini_g_left.drawImage(bomb_img, minicanvas_w_left-48,minicanvas_h-32-32-32);
        
        // draw background and horizontal gray line
        g.drawImage(playBG_img, 0, 0,width,height);
        g.setStroke(Color.GRAY);
        g.setLineWidth(4);
        g.strokeLine(0, 0, width, 0);
        
        // draw mountains
		g.setStroke(Color.BROWN);
        g.setLineWidth(2);
        g.strokeLine(0, canvas_h-100,100,canvas_h-100);
        g.strokeLine(100, canvas_h-100,200,canvas_h-canvas_h/2 + 100);
        g.strokeLine(200,canvas_h-canvas_h/2 + 100,300, canvas_h-100);
        g.strokeLine(300,canvas_h-100,350, canvas_h-100);
        
        g.strokeLine(350,canvas_h-100,400, canvas_h-canvas_h/2 + 150);
        g.strokeLine(400,canvas_h-canvas_h/2 + 150,450, canvas_h-canvas_h/2 + 200);
        
        g.strokeLine(450,canvas_h-canvas_h/2 + 200,canvas_w-200, canvas_h-canvas_h/2 + 200);
        g.strokeLine(canvas_w-200,canvas_h-canvas_h/2 + 200,canvas_w, canvas_h-canvas_h/2 + 150);
 
        
        // draw ship
        int x = 100;
        int y = 250;
        //shipView.setTranslateX(100);
        shipView.setTranslateX(x);
        shipView.setTranslateY(y);
        
        
        // push in layout
		topHBox = new HBox();
		botPane = new Pane();
		root = new Group(shipView);
		
        topHBox.getChildren().addAll(minicanvas_left,minicanvas_middle,minicanvas_right);
        botPane.getChildren().addAll(canvas,root);
        getChildren().addAll(topHBox,botPane);
        
        
        
	}

}
