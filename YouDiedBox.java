import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class YouDiedBox
{
    private static boolean esc;
    private static boolean enter;
    public static void display(int score)
    {
        esc = false;
        enter = false;
        Stage window = new Stage();
        window.setTitle(":( - Andrew Frank");
        window.initModality(Modality.APPLICATION_MODAL);
        HBox topLayout = new HBox(30);
        topLayout.setPadding(new Insets(30, 30, 30, 30));
        topLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        ImageView snakePic2 = new ImageView("http://www.sisterhoodofthesensiblemoms.com/wp-content/uploads/2012/05/Snake-cartoon-Eating-Self-Graphic.jpg");
        VBox layout = new VBox(30);
        layout.setPadding(new Insets(30, 0, 30, 0));
        layout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        layout.setAlignment(Pos.TOP_CENTER);
        Text youDied = new Text("You Died");
        youDied.setFont(Font.font(20));
        Text finalScore = new Text("Score: " + score);
        finalScore.setFont(Font.font(15));
        Button playAgain = new Button("Play Again");
        playAgain.setOnAction(e ->
        {
            window.close();
            SinglePlayer.display(Game.width, Game.height, Game.growPerFood, Game.powerups, Game.originalSpeed, Game.snake1Color);
        });
        Button menu = new Button("Main Menu");
        menu.setOnAction(e ->
        {
            window.close();
            Game.display();
        });
        layout.getChildren().addAll(youDied, finalScore, playAgain, menu);
        topLayout.getChildren().addAll(layout, snakePic2);
        Scene scene = new Scene(topLayout, Color.WHITE);
        scene.setOnKeyPressed(e ->
        {
            switch(e.getCode())
            {
                case ESCAPE: esc = true; break;
                case ENTER: enter = true; break;
            }
        });
        window.setScene(scene);
        window.show();
        AnimationTimer timer = new AnimationTimer()
        {
            public void handle(long now)
            {
                if(enter)
                {
                    stop();
                    window.close();
                    SinglePlayer.display(Game.width, Game.height, Game.growPerFood, Game.powerups, Game.originalSpeed, Game.snake1Color);
                }
                if(esc)
                {
                    stop();
                    window.close();
                    Game.display();
                }
            }
        };
        timer.start();
    }
    public static void display(String whatHappened, int snake1Score, int snake2Score, int round)
    {
        esc = false;
        enter = false;
        Stage window = new Stage();
        window.setTitle("Snake ~~~~~ - Andrew Frank");
        window.initModality(Modality.APPLICATION_MODAL);
        Scene scene;
        if(whatHappened.equals("snake1win") || whatHappened.equals("snake2win") || whatHappened.equals("tie"))
        {
            BorderPane mainLayout = new BorderPane();
            mainLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            VBox roundSum = new VBox(10);
            roundSum.setAlignment(Pos.CENTER);
            Text roundNum = new Text("Summary of round " + round + ":");
            roundNum.setTextAlignment(TextAlignment.CENTER);
            roundNum.setFont(Font.font(15));
            Text snakeWins;
            if(whatHappened.equals("snake1win"))
            {
                snakeWins = new Text("Snake 1 won!");
            }
            else if(whatHappened.equals("snake2win"))
            {
                snakeWins = new Text("Snake 2 won!");
            }
            else
            {
                snakeWins = new Text("Tie!");
            }
            snakeWins.setTextAlignment(TextAlignment.CENTER);
            snakeWins.setFont(Font.font(20));
            roundSum.getChildren().addAll(roundNum, snakeWins);
            VBox snake1Report = new VBox(10);
            snake1Report.setAlignment(Pos.TOP_CENTER);
            Text snake1 = new Text("Snake 1:");
            snake1.setTextAlignment(TextAlignment.CENTER);
            snake1.setFont(Font.font(15));
            Text snake1S = new Text("" + snake1Score);
            snake1S.setTextAlignment(TextAlignment.CENTER);
            snake1Report.getChildren().addAll(snake1, snake1S);
            VBox snake2Report = new VBox(10);
            snake2Report.setAlignment(Pos.TOP_CENTER);
            Text snake2 = new Text("Snake 2:");
            snake2.setTextAlignment(TextAlignment.CENTER);
            snake2.setFont(Font.font(15));
            Text snake2S = new Text("" + snake2Score);
            snake2S.setTextAlignment(TextAlignment.CENTER);
            snake2Report.getChildren().addAll(snake2, snake2S);
            Button button = new Button("Continue to round " + (round + 1));
            button.setAlignment(Pos.CENTER);
            button.setOnAction(e ->
            {
                window.close();
                MultiPlayer.display(Game.width, Game.height, Game.growPerFood, Game.powerups, Game.originalSpeed, Game.snake1Color, Game.snake2Color, Game.pointsToWin, snake1Score, snake2Score, round, false);
            });
            HBox cont = new HBox();
            cont.setAlignment(Pos.CENTER);
            cont.getChildren().addAll(button);
            ImageView centerPic = new ImageView("http://cliparting.com/wp-content/uploads/2016/06/Writing-clipart-frpic.png");
            centerPic.setFitHeight(200);
            centerPic.setFitWidth(200);
            centerPic.setPreserveRatio(true);
            mainLayout.setTop(roundSum);
            mainLayout.setBottom(cont);
            mainLayout.setLeft(snake1Report);
            mainLayout.setRight(snake2Report);
            mainLayout.setCenter(centerPic);
            mainLayout.setMargin(centerPic, new Insets(20, 0, 20, 20));
            mainLayout.setPadding(new Insets(20));
            scene = new Scene(mainLayout);
        }
        else //if one snake wins game
        {
            BorderPane mainLayout = new BorderPane();
            mainLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            VBox roundSum = new VBox(10);
            roundSum.setAlignment(Pos.CENTER);
            Text roundNum = new Text("Summary of Game:");
            roundNum.setFont(Font.font(15));
            roundNum.setTextAlignment(TextAlignment.CENTER);
            Text snakeWins;
            if(whatHappened.equals("snake1win2"))
            {
                snakeWins = new Text("Snake 1 won!");
            }
            else if(whatHappened.equals("snake2win2"))
            {
                snakeWins = new Text("Snake 2 won!");
            }
            else
            {
                snakeWins = new Text("Tie! (This shouldn't happen)");
            }
            snakeWins.setTextAlignment(TextAlignment.CENTER);
            snakeWins.setFont(Font.font(20));
            roundSum.getChildren().addAll(roundNum, snakeWins);
            VBox snake1Report = new VBox(10);
            snake1Report.setAlignment(Pos.TOP_CENTER);
            Text snake1 = new Text("Snake 1:");
            snake1.setTextAlignment(TextAlignment.CENTER);
            snake1.setFont(Font.font(15));
            Text snake1S = new Text("" + snake1Score);
            snake1S.setTextAlignment(TextAlignment.CENTER);
            snake1Report.getChildren().addAll(snake1, snake1S);
            VBox snake2Report = new VBox(10);
            snake2Report.setAlignment(Pos.TOP_CENTER);
            Text snake2 = new Text("Snake 2:");
            snake2.setFont(Font.font(15));
            snake2.setTextAlignment(TextAlignment.CENTER);
            Text snake2S = new Text("" + snake2Score);
            snake2S.setTextAlignment(TextAlignment.CENTER);
            snake2Report.getChildren().addAll(snake2, snake2S);
            Button button = new Button("Main Menu");
            button.setAlignment(Pos.CENTER);
            button.setOnAction(e ->
            {
                window.close();
                Game.display();
            });
            HBox cont = new HBox();
            cont.setAlignment(Pos.CENTER);
            cont.getChildren().addAll(button);
            ImageView centerPic = new ImageView("http://cliparting.com/wp-content/uploads/2016/07/Trophy-clipart-first-place-trophy-emoji-clipart-kid.png");
            centerPic.setFitHeight(200);
            centerPic.setFitWidth(200);
            centerPic.setPreserveRatio(true);
            mainLayout.setTop(roundSum);
            mainLayout.setBottom(cont);
            mainLayout.setLeft(snake1Report);
            mainLayout.setRight(snake2Report);
            mainLayout.setCenter(centerPic);
            mainLayout.setMargin(centerPic, new Insets(20, 20, 20, 20));
            mainLayout.setPadding(new Insets(20));
            scene = new Scene(mainLayout);
        }
        scene.setOnKeyPressed(e ->
        {
            switch(e.getCode())
            {
                case ESCAPE: esc = true; break;
                case ENTER: enter = true; break;
            }
        });
        window.setScene(scene);
        window.show();
        AnimationTimer timer = new AnimationTimer()
        {
            public void handle(long now)
            {
                if(esc)
                {
                    stop();
                    window.close();
                    Game.display();
                }
                if(enter)
                {
                    stop();
                    window.close();
                    if(whatHappened.equals("snake1win") || whatHappened.equals("snake2win") || whatHappened.equals("tie"))
                    {
                        MultiPlayer.display(Game.width, Game.height, Game.growPerFood, Game.powerups, Game.originalSpeed, Game.snake1Color, Game.snake2Color, Game.pointsToWin, snake1Score, snake2Score, round, false);
                    }
                    else
                    {
                        Game.display();
                    }
                }
            }
        };
        timer.start();
    }
}