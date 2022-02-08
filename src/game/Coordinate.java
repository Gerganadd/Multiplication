package game;

public class Coordinate 
{
	private int row;
	private int col;
	
	public Coordinate(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	
	public int getRow()
	{
		return this.row;
	}
	
	public int getCol()
	{
		return this.col;
	}
	
	public String toString()
	{
		return String.format("[%d;%d]", getRow(), getCol());
	}
}
