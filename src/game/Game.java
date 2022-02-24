package game;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import constants.GameConstants;
import constants.ViewConstants;
import game_components.Level;
import game_components.Player;
import game_components.Task;
import listeners.GameListener;
import parser.Parser;
import windows.*;

public class Game implements GameListener
{
	private final LayoutManager LAYOUT = new FlowLayout();

	private static Game game = null;
	
	private static List<Level> levels = Parser.levels("src/resources/levels.xml");
	//to-do : add path into GameConstants
	
	private Player player;
	private JPanel mainWindow = null; // or gameWindow
	private JFrame window;
	
	private int currentLevelIndex = 0;
	private int currentTaskIndex = 0;
	
	private Game()
	{
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(LAYOUT);
		//window.setResizable(false);
	}
	
	public static Game getInstance()
	{
		if (game == null)
		{
			game = new Game();
		}
		return game;
	}
	
	public int getTaskIndex()
	{
		return currentTaskIndex;
	}
	
	public static void setSize(Component c, Dimension d)
	{
		c.setSize(d);
		c.setPreferredSize(d);
		c.setMinimumSize(d);
		c.setMaximumSize(d);
	}
	
	@Override
	public void start()
	{
		window.add(new InitializeWindow());
		
		window.pack();
		window.repaint();
		
		int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - window.size().getWidth()) / 2;
		int y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - window.size().getHeight()) / 2;
		
		//window.setLocation(x, y);
		
		window.setVisible(true);

	}

	public void setPlayer(Player player) 
	{
		this.player = player;
		
		window.getContentPane().removeAll();
		window.add(new StartWindow(player));
		
		window.pack();
		window.repaint();
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	
	public void openGame() 
	{
		//this.player = new Player("Ema", GameConstants.GIRL_LEFT_PICTURE_PATH); //to-do remove
		changePlayerPicturePosition();

		window.getContentPane().removeAll();
		
		this.mainWindow = new MainWindow(levels.get(currentLevelIndex));
		window.add(mainWindow);
		
		window.pack();
		window.repaint();
		
		int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - window.size().getWidth()) / 2;
		int y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - window.size().getHeight()) / 2;
		
		//window.setLocation(x, y);
		
		window.setVisible(true);
		
		generateTask();
	}
	
	private void generateTask() // to-do: fix bugs
	{	
		if (currentTaskIndex < GameConstants.GAME_QUESTIONS)
		{
			int n = currentTaskIndex + 1;
			Task t = new Task(ViewConstants.TASK_TITLE + n);
			
			change();
		}
		if (currentTaskIndex == GameConstants.GAME_QUESTIONS)
		{
			if (currentLevelIndex < levels.size())
			{
				++currentLevelIndex;
				
				window.getContentPane().removeAll();
				
				mainWindow = new CongratilationWindow();
				
				window.add(mainWindow);
				
				window.pack();
				window.repaint();
				
				currentTaskIndex = -1;
			}
			else
			{
				end();
			}
		}
		
		currentTaskIndex++;
	}
	
	private void changePlayerPicturePosition()
	{
		this.player = new Player(player.getName(), player.getPicturePath());
	}

	@Override
	public void end() 
	{
		System.out.println("congratulations!!!");
		System.exit(0);
	}

	@Override
	public void change() 
	{
		this.mainWindow.repaint();
	}

	@Override
	public void generateNewTask() 
	{
		generateTask();
	}
	
	public Level getCurrentLevel()
	{
		return levels.get(currentLevelIndex);
	}
	
	public void newLevel()
	{
		if(currentLevelIndex == levels.size())
		{
			end();
		}
		else
		{
			changePlayerPicturePosition();
			
			openGame();
		}
	}
}
