package game;

import java.util.Map;

public class Level
{
	private String backgroundPicturePath;
	private int taskPixels;
	private Map<Integer, Coordinate> tasksCoordinates;
	
	public Level(String backgroundPicturePath, int taskPixels, Map<Integer, Coordinate> tasksCoordinates)
	{
		if (backgroundPicturePath != null && !backgroundPicturePath.isBlank())
		{
			this.backgroundPicturePath = backgroundPicturePath;
		}
		
		if (taskPixels > 0)
		{
			this.taskPixels = taskPixels;
		}
		
		if(tasksCoordinates != null)
		{
			this.tasksCoordinates = tasksCoordinates;
		}
	}
	
	public String getBackgroundPicturePath()
	{
		return this.backgroundPicturePath;
	}
	
	public int getTaskPixels()
	{
		return this.taskPixels;
	}
	
	public Map<Integer, Coordinate> getCordinates()
	{
		return this.tasksCoordinates;
	}
}
