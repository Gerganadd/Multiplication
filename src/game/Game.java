package game;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import constants.GameConstants;
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
		window.setVisible(true);
		
		window.add(new InitializeWindow());
		
		window.pack();
		window.repaint();
	}

	public void setPlayer(Player player) 
	{
		this.player = player;
		
		window.getContentPane().removeAll();
		window.add(new StartWindow(player));
		
		window.pack();
		window.repaint();
	}
	
	public void openGame() 
	{
		window.getContentPane().removeAll();
		
		this.mainWindow = new MainWindow(levels.get(currentLevelIndex));
		window.add(mainWindow);
		
		window.pack();
		window.repaint();
		
		generateTask();
	}
	
	private void generateTask()
	{
		if (currentTaskIndex <= GameConstants.GAME_QUESTIONS) 
		{
			if (currentTaskIndex < GameConstants.GAME_QUESTIONS)
			{
				int n = currentTaskIndex + 1;
				Task t = new Task("Задача №" + n);
			}
			change();
			currentTaskIndex++;
		}
		else
		{
			if (currentLevelIndex < levels.size())
			{
				//++currentLevelIndex;
				//openGame();
			}
			end();
		}
	}

	@Override
	public void end() 
	{
		System.out.println("congratulations!!!");
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
}
