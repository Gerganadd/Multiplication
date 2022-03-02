package game_components;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import constants.GameConstants;
import game.Game;

public class Player 
{	
	private String name;
	private String picturePath;
	private BufferedImage picture;
	
	public Player(String name, String path)
	{
		setName(name);
		setPath(path);
	}

	private void setPath(String path) 
	{
		boolean isLeft = Game.getInstance().getCurrentLevel().getPlayerPictureOrientation();
		
		if (path != null && !path.isBlank())
		{
			if (path.contains("girl"))
			{
				picturePath = (isLeft ? GameConstants.GIRL_LEFT_PICTURE_PATH : GameConstants.GIRL_RIGHT_PICTURE_PATH);
			}
			else if	(path.contains("boy"))
			{
				picturePath = (isLeft ? GameConstants.BOY_LEFT_PICTURE_PATH : GameConstants.BOY_RIGHT_PICTURE_PATH);
			}
			
			try
			{
				this.picture = ImageIO.read(new File(picturePath));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

	private void setName(String name)
	{
		if (name != null && !name.isBlank())
		{
			this.name = name;
		}
		else
		{
			this.name = GameConstants.DEFAULT_PLAYER_NAME;
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
	
	public BufferedImage getPicture()
	{
		return this.picture;
	}
	
	public String toString()
	{
		return String.format("name: %s, path: %s", name, picturePath);
	}
}
