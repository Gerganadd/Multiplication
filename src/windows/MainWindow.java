package windows;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import constants.ViewConstants;
import game.Game;
import game_components.Coordinate;
import game_components.Level;
import game_components.Task;

public class MainWindow extends JPanel
{
	private Graphics2D g;
	private BufferedImage backgroundImage;
	private BufferedImage elementImage;
	private BufferedImage defaultElementImage;
	private BufferedImage cupImage;
	
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
			
			backgroundImage = ImageIO.read(new File(level.getBackgroundPicturePath()));
			elementImage = ImageIO.read(new File(level.getElementPicturePath()));
			defaultElementImage = ImageIO.read(new File(level.getDefaultElementPicturePath()));
			cupImage = ImageIO.read(new File(level.getCupPicturePath()));
			
			Dimension d = new Dimension(backgroundImage.getWidth(), backgroundImage.getHeight());
			Game.setSize(this, d);
			
			//to-do: remove mouseListener
			this.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					System.out.println(arg0.getLocationOnScreen());
				}
			});
			
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
        
        if (backgroundImage != null)
        {
            this.g.drawImage(backgroundImage, 0, 0, this);
        }
        
        if (elementImage != null && cupImage != null && defaultElementImage != null)
        {
        	level.getCordinates().forEach((k, v) -> drawElement(k));
        }
        
        BufferedImage playerImage = Game.getInstance().getPlayer().getPicture();
        
        if (playerImage != null)
        {
        	this.g.drawImage(playerImage, level.getPlayerCoordinates().getX(), level.getPlayerCoordinates().getY(), this);
        }
        
        Task task = Game.getInstance().getCurrentTask();
        task.setBounds(level.getTaskCoordinates().getX(), level.getTaskCoordinates().getY(), 
        		(int) (ViewConstants.D_TASK.getWidth()), (int) (ViewConstants.D_TASK.getHeight()));
        this.add(task);
        
        this.g.dispose();
        
    }
	
	private void drawElement(int key)
	{
		Coordinate c = level.getCordinates().get(key);
		
		if (key == level.getCordinates().size())
		{
			this.g.drawImage(cupImage, c.getX(), c.getY(), this);
		}
		else
		{
			if (key < Game.getInstance().getTaskIndex())
			{
				this.g.drawImage(elementImage, c.getX(), c.getY(), this);
			}
			else
			{
				this.g.drawImage(defaultElementImage, c.getX(), c.getY(), this); 
			}
		}
	}
	
	private void configurate() 
	{
		this.setVisible(true);
		this.setLayout(null); 
	}

}
