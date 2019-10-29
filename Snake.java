public class Snake
{
    private int x;
    private int y;
    private String dir;
    public Snake(int x, int y, String dir)
    {

        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    public String getDir()
    {
        return dir;
    }
    public void setDir(String dir)
    {
        this.dir = dir;
    }
    public int x()
    {
        return x;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public int y()
    {
        return y;
    }
    public void setY(int y)
    {
        this.y = y;
    }
}