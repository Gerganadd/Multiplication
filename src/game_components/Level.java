package game_components;

import java.util.Map;

public class Level
{
	private String backgroundPicturePath;
	private String elementPicturePath;
	private String defaultElementPicturePath;
	private String cupPicturePath;

	private boolean playerPictureOrientation;
	
	private Map<Integer, Coordinate> tasksCoordinates;
	private Coordinate playerCoordinates;
	
	
	public Level(String backgroundPicturePath, String elementPicturePath, String defaultElementPicturePath,
			String cupPicturePath, Coordinate playerCoordinates, 
			boolean playerPictureOrientation, Map<Integer, Coordinate> tasksCoordinates)
	{
		if (backgroundPicturePath != null && !backgroundPicturePath.isBlank())
		{
			this.backgroundPicturePath = backgroundPicturePath;
		}
		
		if (elementPicturePath != null && !elementPicturePath.isBlank())
		{
			this.elementPicturePath = elementPicturePath;
		}
		
		if (defaultElementPicturePath != null && !defaultElementPicturePath.isBlank())
		{
			this.defaultElementPicturePath = defaultElementPicturePath;
		}
		
		if (cupPicturePath != null && !cupPicturePath.isBlank())
		{
			this.cupPicturePath = cupPicturePath;
		}
		
		if (playerCoordinates != null)
		{
			this.playerCoordinates = playerCoordinates;
		}
		
		this.playerPictureOrientation = playerPictureOrientation;
		
		if (tasksCoordinates != null)
		{
			this.tasksCoordinates = tasksCoordinates;
		}
	
	}
	
	public String getBackgroundPicturePath()
	{
		return this.backgroundPicturePath;
	}
	
	public String getElementPicturePath()
	{
		return this.elementPicturePath;
	}
	
	public String getDefaultElementPicturePath()
	{
		return this.defaultElementPicturePath;
	}
	
	public String getCupPicturePath()
	{
		return this.cupPicturePath;
	}
	
	public Map<Integer, Coordinate> getCordinates()
	{
		return this.tasksCoordinates;
	}
	
	public Coordinate getPlayerCoordinates()
	{
		return this.playerCoordinates;
	}
	
	public boolean getPlayerPictureOrientation()
	{
		return this.playerPictureOrientation;
	}
}
