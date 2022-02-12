package windows;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import constants.ViewConstants;
import game.Coordinate;
import game.Game;
import game.Level;

public class MainWindow extends JPanel
{
	private Graphics2D g;
	private BufferedImage image;
	
	private Level level;
	
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
			
			image = ImageIO.read(new File(level.getBackgroundPicturePath()));
			
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
			g.setColor(ViewConstants.MAIN_WINDOW_CURRECT_ANSWEARED_COLOR);
		}
		else
		{
			g.setColor(ViewConstants.MAIN_WINDOW_DEFAULT_COLOR);
		}
		
		Coordinate c = level.getCordinates().get(key);
		
		g.drawOval(c.getX(), c.getY(), level.getTaskPixels(), level.getTaskPixels());
		g.fillOval(c.getX(), c.getY(), level.getTaskPixels(), level.getTaskPixels());
		
		g.setColor(ViewConstants.MAIN_WINDOW_TEXT_COLOR);
		String taskNumber = key + "";
		g.drawString(
				taskNumber,
				(int) (c.getX() + level.getTaskPixels() * 0.4),
				(int) (c.getY() + level.getTaskPixels() * 0.6));
	}
	
	private void configurate() 
	{
		// to-do : da ne moje da se orazmerqva
		this.setVisible(true);
		this.setLayout(ViewConstants.LAYOUT);
		
		Game.setSize(this, ViewConstants.D_GAME_WINDOW);
	}

}
