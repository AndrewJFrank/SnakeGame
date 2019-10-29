public class PowerUp
{
    private int x;
    private int y;
    private String type;
    public PowerUp(int x, int y, String type)
    {

        this.x = x;
        this.y = y;
        this.type = type;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String dir)
    {
        this.type = dir;
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