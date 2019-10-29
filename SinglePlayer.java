import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;

public class SinglePlayer
{
    //settings start
    private static int width, height;
    private static int squareLength;
    private static int growPerFood;
    private static boolean powerups;
    private static long originalSpeed;
    private static long currentSpeed;
    //settings end
    private static Stage window;
    private static int queue;
    private static Rectangle[][] rectArray;
    private static ArrayList<Snake> snake;
    private static ArrayList<PowerUp> powerup;
    private static int slowPowerUpLength;
    private static int fastPowerUpLength;
    private static boolean up;
    private static boolean down;
    private static boolean left;
    private static boolean right;
    private static boolean esc;
    private static boolean f;
    private static boolean godMode;
    private static boolean grow;
    private static boolean dead;
    private static boolean slow;
    private static boolean slow2;
    private static int slowTimer;
    private static int slowCounter;
    private static boolean fast;
    private static boolean fast2;
    private static int fastTimer;
    private static int fastCounter;
    private static boolean reverse;
    private static Food food;
    private static long lastUpdate;
    private static int dx, dy;
    private static int score;
    private static Color snakeColor;
    public static void display(int width2, int height2, int growPerFood2, boolean powerups2, long originalSpeed2, Color snakeColor2)
    {
        snakeColor = snakeColor2;
        width = width2;
        height = height2;
        squareLength = 25;
        growPerFood = growPerFood2;
        powerups = powerups2;
        originalSpeed = originalSpeed2;
        currentSpeed = originalSpeed;
        window = new Stage();
        queue = 0;
        rectArray = new Rectangle[height][width];
        snake = new ArrayList<Snake>();
        powerup = new ArrayList<PowerUp>();
        slowPowerUpLength = 30;
        fastPowerUpLength = 70;
        up = false;
        down = false;
        left = false;
        right = false;
        esc = false;
        f = false;
        godMode = false;
        grow = false;
        dead = false;
        slow = false;
        slow2 = false;
        slowTimer = 0;
        slowCounter = 0;
        fast = false;
        fast2 = false;
        fastCounter = 0;
        fastTimer = 0;
        reverse = false;
        lastUpdate = 0;
        dx = 0;
        dy = 1;
        score = 5;
        snake.add(new Snake(height / 2, 0, "right"));
        window.setTitle("1 Player Snake - Andrew Frank");
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
        spawnFood();
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
                case UP: up = true; break;
                case LEFT: left = true; break;
                case DOWN: down = true; break;
                case RIGHT: right = true; break;
                case ESCAPE: esc = true; break;
                case F: f = true; break;
                case G: godMode = !(godMode); break;
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
                case UP: up = false; break;
                case LEFT: left = false; break;
                case DOWN: down = false; break;
                case RIGHT: right = false; break;
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
                            eatFood(snake.get(snake.size() - 1).x(), snake.get(snake.size() - 1).y(), snake.get(snake.size() - 1).getDir());
                        }
                    }
                    for(int x = 0; x < snake.size(); x++)
                    {
                        if(snake.get(x).x() == food.x() && snake.get(x).y() == food.y())
                        {
                            eatFood(snake.get(snake.size() - 1).x(), snake.get(snake.size() - 1).y(), snake.get(snake.size() - 1).getDir());
                            queue = queue + growPerFood - 1;
                            grow = false;
                            spawnFood();
                        }
                    }
                    if(grow && queue > 0)
                    {
                        eatFood(snake.get(snake.size() - 1).x(), snake.get(snake.size() - 1).y(), snake.get(snake.size() - 1).getDir());
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
                                    getPower(powerup.get(y).getType());
                                    powerup.remove(y);
                                }
                            }
                        }
                    }
                    moveSnake();
                    if(dead)
                    {
                        stop();
                        die();
                    }
                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }
    public static void moveSnake()
    {
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
                    dead = true;
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
                            dead = true;
                        }
                    }
                }
            }
        }
    }
    public static void eatFood(int x, int y, String dir)
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
            score += 5;
        }
        if(dir.equals("down"))
        {
            snake.add(new Snake(x - 1, y, "down"));
            score += 5;
        }
        if(dir.equals("left"))
        {
            snake.add(new Snake(x, y + 1, "left"));
            score += 5;
        }
        if(dir.equals("right"))
        {
            snake.add(new Snake(x, y - 1, "right"));
            score += 5;
        }
    }
    public static void spawnFood()
    {
        food = new Food((int)(Math.random() * height), (int)(Math.random() * width));
        rectArray[food.x()][food.y()].setFill(Color.WHITE);
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
    public static void getPower(String type)
    {
        if(type.equals("slow"))
        {
            slow2 = true;
            if(slowCounter == 0)
            {
                currentSpeed = originalSpeed + 40_000_000;
            }
            //System.out.println("slow");
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
            //System.out.println("fast");
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
    public static boolean check(int x, int y)
    {
        boolean yes = true;
        for(int z = 0; z < powerup.size(); z++)
        {
            if((x == powerup.get(z).x() && y == powerup.get(z).y()) || (x == food.x() &&  y == food.y()))
            {
                yes = false;
            }
        }
        for(int z = 0; z < snake.size(); z++)
        {
            if((x == snake.get(z).x() && y == snake.get(z).y()) || (x == food.x() &&  y == food.y()))
            {
                yes = false;
            }
        }
        return yes;
    }
    public static void die()
    {
        YouDiedBox.display(score);
        window.close();
    }
}