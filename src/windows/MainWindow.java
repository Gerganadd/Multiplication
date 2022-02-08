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

import javax.imageio.ImageIO;
import javax.swing.*;

import constants.ViewConstants;
import game.Game;
import game.Player;
import game.Point;
import parser.Parser;

public class MainWindow extends JPanel //to-do: design 
{
	private final LayoutManager LAYOUT = new BoxLayout(this, BoxLayout.Y_AXIS);

	private Graphics2D g;
	private String picturePath = "src/resources/tree1.png";
	private String path = "src/resources/gameMap1.txt";
	private BufferedImage image;
	
	public MainWindow(Player player)
	{
		super();
		configurate();
		
		try 
		{
			JPanel pnlReward = new JPanel();
			pnlReward.setLayout(new BoxLayout(pnlReward, BoxLayout.X_AXIS));
			//pnlReward.setBackground(Color.black);
			image = ImageIO.read(new File(picturePath));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		this.repaint();
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
        
        List<Point> points = Parser.parseGamePath1(path);
        for(Point p : points)
        {
        	drawTask(p);
        }
        
        this.g.dispose();
    }
	
	private void drawTask(Point p)
	{
		g.setColor(Color.GRAY);
		g.drawOval(p.getX(), p.getY(), Point.pixels, Point.pixels);
		g.fillOval(p.getX(), p.getY(), Point.pixels, Point.pixels);
		
		g.setColor(Color.BLACK);
	
		//g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		//System.out.println(g.getFontMetrics().getHeight());
		g.drawString(p.getLevelNumber(),(int) (p.getX() + Point.pixels * 0.4),(int) (p.getY() + Point.pixels * 0.6));
		
	}
	
	private void change()
	{
		
	}
	
	private void configurate() 
	{
		this.setVisible(true);
		// to-do : da ne moje da se orazmerqva
		this.setLayout(LAYOUT);
		
		Game.setSize(this, ViewConstants.D_GAME_WINDOW);
	}

}
