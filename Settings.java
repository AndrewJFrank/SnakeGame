import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class Settings
{
    private static boolean esc;
    private static boolean enter;
    private static Stage window = new Stage();
    public static void display()
    {
        window.setTitle("Settings - Andrew Frank");
        //window.initModality(Modality.APPLICATION_MODAL);
        VBox settings = new VBox(10);
        Button saveAndClose = new Button("Save and Close");
        Button cancel = new Button("Cancel");
        Text setText = new Text("Settings");
        setText.setFont(Font.font(15));
        Text diffText = new Text("Difficulty");
        ChoiceBox<String> difficulty = new ChoiceBox<String>();
        difficulty.getItems().addAll("Super Easy", "Easy", "Medium", "Hard", "INSANE");
        difficulty.setValue(Game.difficulty);
        Text s1cText = new Text("Snake 1 Color");
        ChoiceBox<String> snake1color = new ChoiceBox<String>();
        snake1color.getItems().addAll("White", "Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Brown");
        snake1color.setValue(Game.snake1color);
        Text s2cText = new Text("Snake 2 Color (2 Player Only)");
        ChoiceBox<String> snake2color = new ChoiceBox<String>();
        snake2color.getItems().addAll("White", "Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Brown");
        snake2color.setValue(Game.snake2color);
        Text powerText = new Text("Power Ups");
        CheckBox powerups = new CheckBox();
        powerups.setSelected(Game.powerups);
        Text ptwText = new Text("Points To Win (2 Player Only)");
        ChoiceBox<Integer> pointsToWin = new ChoiceBox<Integer>();
        pointsToWin.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        pointsToWin.setValue(Game.pointsToWin);
        Text gpfText = new Text("Grow Per Food");
        ChoiceBox<Integer> growPerFood = new ChoiceBox<Integer>();
        growPerFood.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        growPerFood.setValue(Game.growPerFood);
        Text widText = new Text("Width of Game");
        ChoiceBox<Integer> width = new ChoiceBox<Integer>();
        width.getItems().addAll(70, 60, 50, 40, 30, 20, 10);
        width.setValue(Game.width);
        Text heiText = new Text("Height of Game");
        ChoiceBox<Integer> height = new ChoiceBox<Integer>();
        height.getItems().addAll(40, 30, 20, 10);
        height.setValue(Game.height);
        saveAndClose.setOnAction(e ->
        {
            Game.difficulty = difficulty.getValue();
            if(difficulty.getValue().equals("Super Easy"))
            {
                Game.originalSpeed = 90_000_000;
            }
            if(difficulty.getValue().equals("Easy"))
            {
                Game.originalSpeed = 70_000_000;
            }
            if(difficulty.getValue().equals("Medium"))
            {
                Game.originalSpeed = 50_000_000;
            }
            if(difficulty.getValue().equals("Hard"))
            {
                Game.originalSpeed = 30_000_000;
            }
            if(difficulty.getValue().equals("INSANE"))
            {
                Game.originalSpeed = 10_000_000;
            }
            Game.snake1color = snake1color.getValue();
            if(snake1color.getValue().equals("White"))
            {
                Game.snake1Color = Color.WHITE;
            }
            if(snake1color.getValue().equals("Red"))
            {
                Game.snake1Color = Color.RED;
            }
            if(snake1color.getValue().equals("Orange"))
            {
                Game.snake1Color = Color.ORANGE;
            }
            if(snake1color.getValue().equals("Yellow"))
            {
                Game.snake1Color = Color.YELLOW;
            }
            if(snake1color.getValue().equals("Green"))
            {
                Game.snake1Color = Color.GREEN;
            }
            if(snake1color.getValue().equals("Blue"))
            {
                Game.snake1Color = Color.BLUE;
            }
            if(snake1color.getValue().equals("Purple"))
            {
                Game.snake1Color = Color.PURPLE;
            }
            if(snake1color.getValue().equals("Brown"))
            {
                Game.snake1Color = Color.BROWN;
            }
            Game.snake2color = snake2color.getValue();
            if(snake2color.getValue().equals("White"))
            {
                Game.snake2Color = Color.WHITE;
            }
            if(snake2color.getValue().equals("Red"))
            {
                Game.snake2Color = Color.RED;
            }
            if(snake2color.getValue().equals("Orange"))
            {
                Game.snake2Color = Color.ORANGE;
            }
            if(snake2color.getValue().equals("Yellow"))
            {
                Game.snake2Color = Color.YELLOW;
            }
            if(snake2color.getValue().equals("Green"))
            {
                Game.snake2Color = Color.GREEN;
            }
            if(snake2color.getValue().equals("Blue"))
            {
                Game.snake2Color = Color.BLUE;
            }
            if(snake2color.getValue().equals("Purple"))
            {
                Game.snake2Color = Color.PURPLE;
            }
            if(snake2color.getValue().equals("Brown"))
            {
                Game.snake2Color = Color.BROWN;
            }
            if(powerups.isSelected())
            {
                Game.powerups = true;
            }
            else
            {
                Game.powerups = false;
            }
            Game.pointsToWin = pointsToWin.getValue();
            Game.growPerFood = growPerFood.getValue();
            Game.width = width.getValue();
            Game.height = height.getValue();
            window.close();
        });
        cancel.setOnAction(e ->
        {
            window.close();
        });
        HBox close = new HBox(45);
        close.getChildren().addAll(saveAndClose, cancel);
        close.setPadding(new Insets(10, 0, 0, 0));
        settings.getChildren().addAll(setText, diffText, difficulty, s1cText, snake1color, s2cText, snake2color, ptwText, pointsToWin, powerText, powerups, gpfText, growPerFood, widText, width, heiText, height, close);
        settings.setPadding(new Insets(10));
        Scene scene = new Scene(settings);
        window.setScene(scene);
        window.show();
    }
}