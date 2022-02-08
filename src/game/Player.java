package game;

public class Player 
{	
	private String name;
	private String picturePath;
	
	public Player(String name, String path)
	{
		setName(name);
		setPath(path);
	}

	private void setPath(String path) 
	{
		if (path != null && !path.isBlank())
		{
			this.picturePath = path;
		}
	}

	private void setName(String name)
	{
		if (name != null && !name.isBlank())
		{
			this.name = name;
		}
	}
	
	public String getPicturePath() 
	{
		return this.picturePath;
	}

	public String getName() 
	{
		return this.name;
	}
	
	public String toString()
	{
		return String.format("name: %s, path: %s", name, picturePath);
	}
}
