package windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

import constants.ViewConstants;
import game.Coordinate;
import game.Game;
import game.Level;
import game.Player;
import game.Point;
import parser.Parser;

public class MainWindow extends JPanel //to-do: design 
{
	private final LayoutManager LAYOUT = new BoxLayout(this, BoxLayout.Y_AXIS);
	private final Color DEFAULT_COLOR = Color.GRAY;
	private final Color TEXT_COLOR = Color.BLACK;
	private final Color CURRECT_ANSWEARED_COLOR = Color.GREEN;

	private Graphics2D g;
	private BufferedImage image;
	
	private String picturePath = "src/resources/level_1_background.png";
	private String path = "src/resources/gameMap1.txt";
	
	private Level level;
	
	public MainWindow(Player player)
	{
		super();
		configurate();
		
		try 
		{
			JPanel pnlReward = new JPanel();
			pnlReward.setLayout(new BoxLayout(pnlReward, BoxLayout.X_AXIS));
			
			image = ImageIO.read(new File(picturePath));
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public MainWindow(Level level)
	{
		super();
		configurate();
		
		if (level != null)
			this.level = level;
		
		try 
		{
			JPanel pnlReward = new JPanel();
			pnlReward.setLayout(new BoxLayout(pnlReward, BoxLayout.X_AXIS));
			
			image = ImageIO.read(new File(picturePath));
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
    protected void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        this.g = (Graphics2D) g.create();
        
        if (image != null)
        {
            this.g.drawImage(image, 0, 0, this);
        }
        
        level.getCordinates().forEach((k, v) -> drawTask(k));
       
        this.g.dispose();
        
    }
	
	private void drawTask(int key)
	{
		int lastCurrectTask = Game.getInstance().getTaskIndex();
		
		if (key < lastCurrectTask)
		{
			g.setColor(CURRECT_ANSWEARED_COLOR);
		}
		else
		{
			g.setColor(DEFAULT_COLOR);
		}
		
		Coordinate c = level.getCordinates().get(key);
		
		g.drawOval(c.getX(), c.getY(), level.getTaskPixels(), level.getTaskPixels());
		g.fillOval(c.getX(), c.getY(), level.getTaskPixels(), level.getTaskPixels());
		
		g.setColor(TEXT_COLOR);
		String taskNumber = key + "";
		g.drawString(
				taskNumber,
				(int) (c.getX() + level.getTaskPixels() * 0.4),
				(int) (c.getY() + level.getTaskPixels() * 0.6));
	}
	
	private void configurate() 
	{
		this.setVisible(true);
		// to-do : da ne moje da se orazmerqva
		this.setLayout(LAYOUT);
		
		Game.setSize(this, ViewConstants.D_GAME_WINDOW);
	}

}
