import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
public class Game extends Application
{
    //settings start
    public static int width = 70;
    public static int height = 40;
    public static int growPerFood = 1;
    public static boolean powerups = true;
    public static String difficulty = "Medium";
    public static long originalSpeed = 50_000_000;
    public static String snake1color = "White";
    public static Color snake1Color = Color.WHITE;
    public static String snake2color = "White";
    public static Color snake2Color = Color.WHITE;
    public static int pointsToWin = 2;
    //settings end
    private static boolean esc = false;
    private static Stage window;
    public static void main(String[] args)
    {
        launch(args);
    }
    public void start(Stage window)
    {
        this.window = window;
        display();
    }
    public static void display()
    {
        //Tile Screen
        window.setTitle("Snake Game - Andrew Frank");
        BorderPane mainTitleLayout = new BorderPane();
        mainTitleLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        Text title = new Text("SNAKE");
        title.setFont(Font.font(20));
        title.setTextAlignment(TextAlignment.CENTER);
        HBox top = new HBox();
        top.getChildren().add(title);
        top.setAlignment(Pos.CENTER);
        ImageView snakePic = new ImageView("https://i.pinimg.com/736x/2a/df/81/2adf81f6d5055e808b99081f7871237a--python-reptiles.jpg");
        HBox options = new HBox(150);
        Button singlePlayer = new Button("1 Player");
        Button multiPlayer = new Button("2 Player");
        Button settings = new Button("Settings");
        singlePlayer.setOnAction(e ->
        {
            SinglePlayer.display(width, height, growPerFood, powerups, originalSpeed, snake1Color);
        });
        multiPlayer.setOnAction(e ->
        {
            MultiPlayer.display(width, height, growPerFood, powerups, originalSpeed, snake1Color, snake2Color, pointsToWin, 0, 0, 0, true);
        });
        settings.setOnAction(e ->
        {
            Settings.display();
        });
        options.getChildren().addAll(singlePlayer, settings, multiPlayer);
        options.setAlignment(Pos.CENTER);
        mainTitleLayout.setTop(top);
        mainTitleLayout.setCenter(snakePic);
        mainTitleLayout.setBottom(options);
        Insets insets = new Insets(20);
        mainTitleLayout.setMargin(top, insets);
        mainTitleLayout.setMargin(options, insets);
        Scene titleScene = new Scene(mainTitleLayout, 736, 750);
        mainTitleLayout.setOnKeyPressed(e ->
        {
            switch(e.getCode())
            {
                case ESCAPE: esc = true; break;
            }
        });
        window.setScene(titleScene);
        window.show();
        AnimationTimer timer = new AnimationTimer()
        {
            public void handle(long now)
            {
                if(esc)
                {
                    System.exit(0);
                }
            }
        };
        timer.start();
    }
}