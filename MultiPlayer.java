import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;

public class MultiPlayer
{
    //settings start
    private static int width, height;
    private static int squareLength;
    private static int growPerFood;
    private static boolean powerups;
    private static long originalSpeed;
    private static int pointsToWin;
    //settings end
    private static long currentSpeed;
    private static long currentSpeed2;
    private static Stage window;
    private static int queue;
    private static int queue2;
    private static Rectangle[][] rectArray;
    private static ArrayList<Snake> snake;
    private static ArrayList<Snake> snake2;
    private static ArrayList<PowerUp> powerup;
    private static int slowPowerUpLength;
    private static int fastPowerUpLength;
    private static boolean up;
    private static boolean down;
    private static boolean left;
    private static boolean right;
    private static boolean up2;
    private static boolean down2;
    private static boolean left2;
    private static boolean right2;
    private static boolean esc;
    private static boolean f;
    private static boolean godMode;
    private static boolean grow;
    private static boolean dead;
    private static boolean tie;
    private static boolean slow;
    private static boolean slow2;
    private static int slowTimer;
    private static int slowCounter;
    private static boolean fast;
    private static boolean fast2;
    private static int fastTimer;
    private static int fastCounter;
    private static boolean reverse;
    private static ArrayList<Food> food;
    private static long lastUpdate;
    private static int dx, dy;
    private static boolean grow2;
    private static boolean dead2;
    private static boolean slow22;
    private static int slowTimer2;
    private static int slowCounter2;
    private static boolean fast22;
    private static int fastTimer2;
    private static int fastCounter2;
    private static long lastUpdate2;
    private static int snakeScore;
    private static int snake2Score;
    private static int round;
    private static Color snake1Color;
    private static Color snake2Color;
    public static void display(int width2, int height2, int growPerFood2, boolean powerups2, long originalSpeed2, Color snakeColor2, Color snake2Color2, int pointsToWin2, int snakeScore2, int snake2Score2, int round2, boolean newgame)
    {
        pointsToWin = pointsToWin2;
        snakeScore = snakeScore2;
        snake2Score = snake2Score2;
        round = round2;
        if(newgame)
        {
            snakeScore = 0;
            snake2Score = 0;
            round = 0;
        }
        snake1Color = snakeColor2;
        snake2Color = snake2Color2;
        width = width2;
        height = height2;
        squareLength = 25;
        growPerFood = growPerFood2;
        powerups = powerups2;
        originalSpeed = originalSpeed2;
        currentSpeed = originalSpeed;
        currentSpeed2 = originalSpeed;
        window = new Stage();
        queue = 0;
        rectArray = new Rectangle[height][width];
        snake = new ArrayList<Snake>();
        snake2 = new ArrayList<Snake>();
        powerup = new ArrayList<PowerUp>();
        slowPowerUpLength = 30;
        fastPowerUpLength = 70;
        up = false;
        down = false;
        left = false;
        right = false;
        up2 = false;
        down2 = false;
        left2 = false;
        right2 = false;
        esc = false;
        f = false;
        godMode = false;
        grow = false;
        dead = false;
        tie = false;
        slow = false;
        slow2 = false;
        slowTimer = 0;
        slowCounter = 0;
        fast = false;
        fast2 = false;
        fastCounter = 0;
        fastTimer = 0;
        reverse = false;
        food = new ArrayList<Food>();
        lastUpdate = 0;
        dx = 0;
        dy = 1;
        grow2 = false;
        dead2 = false;
        slow22 = false;
        slowTimer2 = 0;
        slowCounter2 = 0;
        fast22 = false;
        fastCounter2 = 0;
        fastTimer2 = 0;
        lastUpdate2 = 0;
        snake.add(new Snake(height / 2, 0, "right"));
        snake2.add(new Snake(height / 2, width - 1, "left"));
        window.setTitle("2 Player Snake - Andrew Frank");
        BorderPane mainLayout = new BorderPane();
        HBox topLayout = new HBox();
        GridPane layout = new GridPane();
        for(int x = 0; x < height; x++)
        {
            for(int y = 0; y < width; y++)
            {
                rectArray[x][y] = new Rectangle(squareLength, squareLength);
                rectArray[x][y].setFill(Color.BLACK);
            }
        }
        for(int x = 0; x < 5; x++)
        {
            spawnFood();
        }
        for(int x = 0; x < height; x++)
        {
            for(int y = 0; y < width; y++)
            {
                layout.add(rectArray[x][y], y, x);
            }
        }
        mainLayout.setTop(topLayout);
        mainLayout.setCenter(layout);
        Scene mainScene = new Scene(mainLayout, width * squareLength, height * squareLength);
        mainScene.setOnKeyPressed(e ->
        {
            switch(e.getCode())
            {
                case W: up = true; break;
                case A: left = true; break;
                case S: down = true; break;
                case D: right = true; break;
                case UP: up2 = true; break;
                case LEFT: left2 = true; break;
                case DOWN: down2 = true; break;
                case RIGHT: right2 = true; break;
                case ESCAPE: esc = true; break;
                case F: f = true; break;
                case G: godMode = !(godMode); break;
                case P: powerups = !(powerups); break;
            }
        });
        mainScene.setOnKeyReleased(e ->
        {
            switch(e.getCode())
            {
                case W: up = false; break;
                case A: left = false; break;
                case S: down = false; break;
                case D: right = false; break;
                case UP: up2 = false; break;
                case LEFT: left2 = false; break;
                case DOWN: down2 = false; break;
                case RIGHT: right2 = false; break;
                case ESCAPE: esc = false; break;
                case F: f = false; break;
            }
        });
        window.setScene(mainScene);
        window.show();
        AnimationTimer timer = new AnimationTimer()
        {
            public void handle(long now)
            {
                grow = true;
                if(now - lastUpdate >= currentSpeed)
                {
                    //System.out.println(currentSpeed);
                    if(slow2 == true && slowCounter < slowTimer)
                    {
                        slowCounter++;
                    }
                    else
                    {
                        if(slow2 == false && fast2 == true)
                        {

                        }
                        else
                        {
                            slow2 = false;
                            slowCounter = 0;
                            currentSpeed = originalSpeed;
                        }
                    }
                    if(fast2 == true && fastCounter < fastTimer)
                    {
                        fastCounter++;
                    }
                    else
                    {
                        if(slow2 == true && fast2 == false)
                        {

                        }
                        else
                        {
                            fast2 = false;
                            fastCounter = 0;
                            currentSpeed = originalSpeed;
                        }
                    }
                    if(powerups)
                    {
                        if(slow == false)
                        {
                            spawnPowerUp("slow");
                            slow = true;
                        }
                        if(fast == false)
                        {
                            spawnPowerUp("fast");
                            fast = true;
                        }
                        if(reverse == false)
                        {
                            spawnPowerUp("reverse");
                            reverse = true;
                        }
                    }
                    else
                    {
                        powerup = new ArrayList<PowerUp>();
                    }
                    if(godMode)
                    {
                        if(f)
                        {
                            eatFood(snake.get(snake.size() - 1).x(), snake.get(snake.size() - 1).y(), snake.get(snake.size() - 1).getDir(), snake, food.get(0));
                        }
                    }
                    for(int x = 0; x < snake.size(); x++)
                    {
                        for(int y = 0; y < food.size(); y++)
                        {
                            if(snake.get(x).x() == food.get(y).x() && snake.get(x).y() == food.get(y).y())
                            {
                                eatFood(snake.get(snake.size() - 1).x(), snake.get(snake.size() - 1).y(), snake.get(snake.size() - 1).getDir(), snake, food.get(y));
                                food.remove(y);
                                queue = queue + growPerFood - 1;
                                grow = false;
                                spawnFood();
                            }
                        }
                    }
                    if(grow && queue > 0)
                    {
                        eatFood(snake.get(snake.size() - 1).x(), snake.get(snake.size() - 1).y(), snake.get(snake.size() - 1).getDir(), snake, food.get(0));
                        queue--;
                    }
                    if(snake.size() > 1)
                    {
                        for(int x = snake.size() - 1; x > 0; x--)
                        {
                            snake.get(x).setDir(snake.get(x - 1).getDir());
                        }
                    }
                    if(up)
                    {
                        if(snake.get(0).getDir().equals("down"))
                        {

                        }
                        else
                        {
                            snake.get(0).setDir("up");
                        }
                    }
                    else if(down)
                    {
                        if(snake.get(0).getDir().equals("up"))
                        {

                        }
                        else
                        {
                            snake.get(0).setDir("down");
                        }
                    }
                    else if(right)
                    {
                        if(snake.get(0).getDir().equals("left"))
                        {

                        }
                        else
                        {
                            snake.get(0).setDir("right");
                        }
                    }
                    else if(left)
                    {
                        if(snake.get(0).getDir().equals("right"))
                        {

                        }
                        else
                        {
                            snake.get(0).setDir("left");
                        }
                    }
                    else
                    {
                        if(snake.size() > 1)
                        {
                            snake.get(0).setDir(snake.get(1).getDir());
                        }
                    }
                    if(esc)
                    {
                        stop();
                        window.close();
                        Game.display();
                    }
                    if(powerups)
                    {
                        for (int x = 0; x < snake.size(); x++)
                        {
                            for (int y = 0; y < powerup.size(); y++)
                            {
                                if (snake.get(x).x() == powerup.get(y).x() && snake.get(x).y() == powerup.get(y).y())
                                {
                                    getPower(powerup.get(y).getType(), snake, 1);
                                    powerup.remove(y);
                                }
                            }
                        }
                    }
                    moveSnake(snake, 1);
                    if(!godMode)
                    {
                        if(snake.get(0).x() == snake2.get(0).x() && snake.get(0).y() == snake2.get(0).y() && ((snake.get(0).getDir().equals("up") && snake2.get(0).getDir().equals("down")) || (snake.get(0).getDir().equals("down") && snake2.get(0).getDir().equals("up")) || (snake.get(0).getDir().equals("left") && snake2.get(0).getDir().equals("right")) || (snake.get(0).getDir().equals("right") && snake2.get(0).getDir().equals("left"))))
                        {
                            if(snake.size() > snake2.size())
                            {
                                dead2 = true;
                            }
                            else if(snake.size() < snake2.size())
                            {
                                dead = true;
                            }
                            else
                            {
                                tie = true;
                            }
                        }
                        for(int x = 1; x < snake2.size(); x++)
                        {
                            if(snake.get(0).x() == snake2.get(x).x() && snake.get(0).y() == snake2.get(x).y())
                            {
                                dead = true;
                            }
                        }
                    }
                    lastUpdate = now;
                }
            }
        };
        AnimationTimer timer2 = new AnimationTimer()
        {
            public void handle(long now)
            {
                grow2 = true;
                if(now - lastUpdate2 >= currentSpeed2)
                {
                    //System.out.println(currentSpeed2);
                    if(slow22 == true && slowCounter2 < slowTimer2)
                    {
                        slowCounter2++;
                    }
                    else
                    {
                        if(slow22 == false && fast22 == true)
                        {

                        }
                        else
                        {
                            slow22 = false;
                            slowCounter2 = 0;
                            currentSpeed2 = originalSpeed;
                        }
                    }
                    if(fast22 == true && fastCounter2 < fastTimer2)
                    {
                        fastCounter2++;
                    }
                    else
                    {
                        if(slow22 == true && fast22 == false)
                        {

                        }
                        else
                        {
                            fast22 = false;
                            fastCounter2 = 0;
                            currentSpeed2 = originalSpeed;
                        }
                    }
                    if(powerups)
                    {
                        if(slow == false)
                        {
                            spawnPowerUp("slow");
                            slow = true;
                        }
                        if(fast == false)
                        {
                            spawnPowerUp("fast");
                            fast = true;
                        }
                        if(reverse == false)
                        {
                            spawnPowerUp("reverse");
                            reverse = true;
                        }
                    }
                    else
                    {
                        powerup = new ArrayList<PowerUp>();
                    }
                    if(godMode)
                    {
                        if(f)
                        {
                            eatFood(snake2.get(snake2.size() - 1).x(), snake2.get(snake2.size() - 1).y(), snake2.get(snake2.size() - 1).getDir(), snake2, food.get(0));
                        }
                    }
                    for(int x = 0; x < snake2.size(); x++)
                    {
                        for(int y = 0; y < food.size(); y++)
                        {
                            if(snake2.get(x).x() == food.get(y).x() && snake2.get(x).y() == food.get(y).y())
                            {
                                eatFood(snake2.get(snake2.size() - 1).x(), snake2.get(snake2.size() - 1).y(), snake2.get(snake2.size() - 1).getDir(), snake2, food.get(y));
                                food.remove(y);
                                queue2 = queue2 + growPerFood - 1;
                                grow2 = false;
                                spawnFood();
                            }
                        }
                    }
                    if(grow2 && queue2 > 0)
                    {
                        eatFood(snake2.get(snake2.size() - 1).x(), snake2.get(snake2.size() - 1).y(), snake2.get(snake2.size() - 1).getDir(), snake2, food.get(0));
                        queue2--;
                    }
                    if(snake2.size() > 1)
                    {
                        for(int x = snake2.size() - 1; x > 0; x--)
                        {
                            snake2.get(x).setDir(snake2.get(x - 1).getDir());
                        }
                    }
                    if(up2)
                    {
                        if(snake2.get(0).getDir().equals("down"))
                        {

                        }
                        else
                        {
                            snake2.get(0).setDir("up");
                        }
                    }
                    else if(down2)
                    {
                        if(snake2.get(0).getDir().equals("up"))
                        {

                        }
                        else
                        {
                            snake2.get(0).setDir("down");
                        }
                    }
                    else if(right2)
                    {
                        if(snake2.get(0).getDir().equals("left"))
                        {

                        }
                        else
                        {
                            snake2.get(0).setDir("right");
                        }
                    }
                    else if(left2)
                    {
                        if(snake2.get(0).getDir().equals("right"))
                        {

                        }
                        else
                        {
                            snake2.get(0).setDir("left");
                        }
                    }
                    else
                    {
                        if(snake2.size() > 1)
                        {
                            snake2.get(0).setDir(snake2.get(1).getDir());
                        }
                    }
                    if(esc)
                    {
                        stop();
                        window.close();
                        Game.display();
                    }
                    if(powerups)
                    {
                        for (int x = 0; x < snake2.size(); x++)
                        {
                            for (int y = 0; y < powerup.size(); y++)
                            {
                                if (snake2.get(x).x() == powerup.get(y).x() && snake2.get(x).y() == powerup.get(y).y())
                                {
                                    getPower(powerup.get(y).getType(), snake2, 2);
                                    powerup.remove(y);
                                }
                            }
                        }
                    }
                    moveSnake(snake2, 2);
                    if(!godMode)
                    {
                        if(snake.get(0).x() == snake2.get(0).x() && snake.get(0).y() == snake2.get(0).y() && ((snake.get(0).getDir().equals("up") && snake2.get(0).getDir().equals("down")) || (snake.get(0).getDir().equals("down") && snake2.get(0).getDir().equals("up")) || (snake.get(0).getDir().equals("left") && snake2.get(0).getDir().equals("right")) || (snake.get(0).getDir().equals("right") && snake2.get(0).getDir().equals("left"))))
                        {
                            if(snake.size() > snake2.size())
                            {
                                dead2 = true;
                            }
                            else if(snake.size() < snake2.size())
                            {
                                dead = true;
                            }
                            else
                            {
                                tie = true;
                            }
                        }
                        for(int x = 0; x < snake.size(); x++)
                        {
                            if(snake2.get(0).x() == snake.get(x).x() && snake2.get(0).y() == snake.get(x).y())
                            {
                                dead2 = true;
                            }
                        }
                    }
                    if(dead || dead2 || tie)
                    {
                        stop();
                        die();
                    }
                    lastUpdate2 = now;
                }
            }
        };
        timer.start();
        timer2.start();
    }
    public static void moveSnake(ArrayList<Snake> snake, int whichSnake)
    {
        Color snakeColor;
        if(whichSnake == 1)
        {
            snakeColor = snake1Color;
        }
        else
        {
            snakeColor = snake2Color;
        }
        for(int x = 0; x < snake.size(); x++)
        {
            if(snake.get(x).getDir().equals("up"))
            {
                dx = -1;
                dy = 0;
            }
            if(snake.get(x).getDir().equals("down"))
            {
                dx = 1;
                dy = 0;
            }
            if(snake.get(x).getDir().equals("left"))
            {
                dx = 0;
                dy = -1;
            }
            if(snake.get(x).getDir().equals("right"))
            {
                dx = 0;
                dy = 1;
            }
            if(godMode)
            {
                if(snake.get(x).x() + dx > height - 1)
                {
                    rectArray[snake.get(x).x()][snake.get(x).y()].setFill(Color.BLACK);
                    snake.get(x).setX(0);
                    snake.get(x).setY(snake.get(x).y() + dy);
                    rectArray[snake.get(x).x()][snake.get(x).y()].setFill(snakeColor);
                }
                else if(snake.get(x).x() + dx < 0)
                {
                    rectArray[snake.get(x).x()][snake.get(x).y()].setFill(Color.BLACK);
                    snake.get(x).setX(height - 1);
                    snake.get(x).setY(snake.get(x).y() + dy);
                    rectArray[snake.get(x).x()][snake.get(x).y()].setFill(snakeColor);
                }
                else if(snake.get(x).y() + dy > width - 1)
                {
                    rectArray[snake.get(x).x()][snake.get(x).y()].setFill(Color.BLACK);
                    snake.get(x).setX(snake.get(x).x() + dx);
                    snake.get(x).setY(0);
                    rectArray[snake.get(x).x()][snake.get(x).y()].setFill(snakeColor);
                }
                else if(snake.get(x).y() + dy < 0)
                {
                    rectArray[snake.get(x).x()][snake.get(x).y()].setFill(Color.BLACK);
                    snake.get(x).setX(snake.get(x).x() + dx);
                    snake.get(x).setY(width - 1);
                    rectArray[snake.get(x).x()][snake.get(x).y()].setFill(snakeColor);
                }
                else
                {
                    rectArray[snake.get(x).x()][snake.get(x).y()].setFill(Color.BLACK);
                    snake.get(x).setX(snake.get(x).x() + dx);
                    snake.get(x).setY(snake.get(x).y() + dy);
                    rectArray[snake.get(x).x()][snake.get(x).y()].setFill(snakeColor);
                }
            }
            else
            {
                if(snake.get(x).x() + dx > height - 1 || snake.get(x).x() + dx < 0 || snake.get(x).y() + dy > width - 1 || snake.get(x).y() + dy < 0)
                {
                    if(whichSnake == 1)
                    {
                        dead = true;
                    }
                    else
                    {
                        dead2 = true;
                    }
                }
                else
                {
                    rectArray[snake.get(x).x()][snake.get(x).y()].setFill(Color.BLACK);
                    snake.get(x).setX(snake.get(x).x() + dx);
                    snake.get(x).setY(snake.get(x).y() + dy);
                    rectArray[snake.get(x).x()][snake.get(x).y()].setFill(snakeColor);
                }
                for(int y = 0; y < snake.size(); y++)
                {
                    if(snake.get(x).x() == snake.get(y).x() && snake.get(x).y() == snake.get(y).y())
                    {
                        if(x == y)
                        {

                        }
                        else
                        {
                            if(whichSnake == 1)
                            {
                                dead = true;
                            }
                            else
                            {
                                dead2 = true;
                            }
                        }
                    }
                }
            }
        }
    }
    public static void eatFood(int x, int y, String dir, ArrayList<Snake> snake, Food food)
    {
        if(f == true || (grow && queue > 0))
        {

        }
        else
        {
            rectArray[food.x()][food.y()].setFill(Color.BLACK);
        }
        if(dir.equals("up"))
        {
            snake.add(new Snake(x + 1, y, "up"));
        }
        if(dir.equals("down"))
        {
            snake.add(new Snake(x - 1, y, "down"));
        }
        if(dir.equals("left"))
        {
            snake.add(new Snake(x, y + 1, "left"));
        }
        if(dir.equals("right"))
        {
            snake.add(new Snake(x, y - 1, "right"));
        }
    }
    public static void spawnFood()
    {
        int x = (int)(Math.random() * height);
        int y = (int)(Math.random() * width);
        food.add(new Food(x, y));
        rectArray[x][y].setFill(Color.WHITE);
    }
    public static void spawnPowerUp(String type)
    {
        int x = (int)(Math.random() * height);
        int y = (int)(Math.random() * width);
        while(!check(x, y))
        {
            x = (int)(Math.random() * height);
            y = (int)(Math.random() * width);
        }
        powerup.add(new PowerUp(x, y, type));
        if(type.equals("slow"))
        {
            rectArray[x][y].setFill(Color.RED);
        }
        if(type.equals("fast"))
        {
            rectArray[x][y].setFill(Color.GREEN);
        }
        if(type.equals("reverse"))
        {
            rectArray[x][y].setFill(Color.BLUE);
        }
    }
    public static void getPower(String type, ArrayList<Snake> snake, int whichSnake)
    {
        if(whichSnake == 1)
        {
            if(type.equals("slow"))
            {
                slow2 = true;
                if(slowCounter == 0)
                {
                    currentSpeed = originalSpeed + 40_000_000;
                }
                slowTimer += slowPowerUpLength;
                slow = false;
            }
            if(type.equals("fast"))
            {
                fast2 = true;
                if(fastCounter == 0)
                {
                    currentSpeed = originalSpeed - 30_000_000;
                }
                fastTimer += fastPowerUpLength;
                fast = false;
            }
            if(type.equals("reverse"))
            {
                ArrayList<String> dirs = new ArrayList<String>();
                for(int x = 0; x < snake.size(); x++)
                {
                    dirs.add(snake.get(x).getDir());
                }
                Collections.reverse(snake);
                for(int x = 0; x < dirs.size() - 1; x++)
                {
                    if(!(dirs.get(x).equals(dirs.get(x + 1))))
                    {
                        dirs.set(x, dirs.get(x + 1));
                    }
                    if(dirs.get(x).equals("up"))
                    {
                        dirs.set(x, "down");
                    }
                    else if(dirs.get(x).equals("down"))
                    {
                        dirs.set(x, "up");
                    }
                    else if(dirs.get(x).equals("right"))
                    {
                        dirs.set(x, "left");
                    }
                    else if(dirs.get(x).equals("left"))
                    {
                        dirs.set(x, "right");
                    }
                }
                if(dirs.get(dirs.size() - 1).equals("up"))
                {
                    dirs.set(dirs.size() - 1, "down");
                }
                else if(dirs.get(dirs.size() - 1).equals("down"))
                {
                    dirs.set(dirs.size() - 1, "up");
                }
                else if(dirs.get(dirs.size() - 1).equals("right"))
                {
                    dirs.set(dirs.size() - 1, "left");
                }
                else if(dirs.get(dirs.size() - 1).equals("left"))
                {
                    dirs.set(dirs.size() - 1, "right");
                }
                Collections.reverse(dirs);
                for(int x = 0; x < snake.size(); x++)
                {
                    snake.get(x).setDir(dirs.get(x));
                }
                reverse = false;
            }
        }
        else
        {
            if(type.equals("slow"))
            {
                slow22 = true;
                if(slowCounter2 == 0)
                {
                    currentSpeed2 = originalSpeed + 40_000_000;
                }
                slowTimer2 += slowPowerUpLength;
                slow = false;
            }
            if(type.equals("fast"))
            {
                fast22 = true;
                if(fastCounter2 == 0)
                {
                    currentSpeed2 = originalSpeed - 30_000_000;
                }
                fastTimer2 += fastPowerUpLength;
                fast = false;
            }
            if(type.equals("reverse"))
            {
                ArrayList<String> dirs = new ArrayList<String>();
                for(int x = 0; x < snake.size(); x++)
                {
                    dirs.add(snake.get(x).getDir());
                }
                Collections.reverse(snake);
                for(int x = 0; x < dirs.size() - 1; x++)
                {
                    if(!(dirs.get(x).equals(dirs.get(x + 1))))
                    {
                        dirs.set(x, dirs.get(x + 1));
                    }
                    if(dirs.get(x).equals("up"))
                    {
                        dirs.set(x, "down");
                    }
                    else if(dirs.get(x).equals("down"))
                    {
                        dirs.set(x, "up");
                    }
                    else if(dirs.get(x).equals("right"))
                    {
                        dirs.set(x, "left");
                    }
                    else if(dirs.get(x).equals("left"))
                    {
                        dirs.set(x, "right");
                    }
                }
                if(dirs.get(dirs.size() - 1).equals("up"))
                {
                    dirs.set(dirs.size() - 1, "down");
                }
                else if(dirs.get(dirs.size() - 1).equals("down"))
                {
                    dirs.set(dirs.size() - 1, "up");
                }
                else if(dirs.get(dirs.size() - 1).equals("right"))
                {
                    dirs.set(dirs.size() - 1, "left");
                }
                else if(dirs.get(dirs.size() - 1).equals("left"))
                {
                    dirs.set(dirs.size() - 1, "right");
                }
                Collections.reverse(dirs);
                for(int x = 0; x < snake.size(); x++)
                {
                    snake.get(x).setDir(dirs.get(x));
                }
                reverse = false;
            }
        }
    }
    public static boolean check(int x, int y)
    {
        boolean yes = true;
        for(int z = 0; z < powerup.size(); z++)
        {
            if((x == powerup.get(z).x() && y == powerup.get(z).y()))
            {
                yes = false;
            }
        }
        for(int z = 0; z < snake.size(); z++)
        {
            if((x == snake.get(z).x() && y == snake.get(z).y()))
            {
                yes = false;
            }
        }
        for(int z = 0; z < snake2.size(); z++)
        {
            if((x == snake2.get(z).x() && y == snake2.get(z).y()))
            {
                yes = false;
            }
        }
        for(int z = 0; z < food.size(); z++)
        {
            if((x == food.get(z).x() &&  y == food.get(z).y()))
            {
                yes = false;
            }
        }
        return yes;
    }
    public static void die()
    {
        //System.out.println();
        //System.out.println(tie);
        //System.out.println(dead);
        //System.out.println(dead2);
        round++;
        if((dead && dead2) || tie)
        {
            //System.out.println("this");
            YouDiedBox.display("tie", snakeScore, snake2Score, round);
        }
        else if(dead)
        {
            //System.out.println("that");
            snake2Score++;
            if(snake2Score >= pointsToWin)
            {
                YouDiedBox.display("snake2win2", snakeScore, snake2Score, round);
            }
            else
            {
                YouDiedBox.display("snake2win", snakeScore, snake2Score, round);
            }
        }
        else if(dead2)
        {
            //System.out.println("yes");
            snakeScore++;
            if(snakeScore >= pointsToWin)
            {
                YouDiedBox.display("snake1win2", snakeScore, snake2Score, round);
            }
            else
            {
                YouDiedBox.display("snake1win", snakeScore, snake2Score, round);
            }
        }
        window.close();
    }
}