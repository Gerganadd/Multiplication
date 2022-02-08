package game;

public class Point 
{
	public static int pixels = 0;
	
	private String n;
	private int x;
	private int y;
	
	public Point(String levelNumber, int x, int y)
	{
		setN(levelNumber);
		setX(x);
		setY(y);
	}

	private void setN(String levelNumber) 
	{
		if (levelNumber != null && !levelNumber.isEmpty())
			this.n = levelNumber;
	}

	private void setX(int x)
	{
		this.x = x;
	}
	
	private void setY(int y) 
	{
		this.y = y;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public String getLevelNumber()
	{
		return this.n;
	}
}
