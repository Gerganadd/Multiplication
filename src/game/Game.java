package game;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import constants.GameConstants;
import constants.ViewConstants;
import game_components.Coordinate;
import game_components.Level;
import game_components.Player;
import game_components.Task;
import listeners.GameListener;
import parser.Parser;
import windows.*;

public class Game implements GameListener
{
	private static final LayoutManager WINDOW_LAYOUT = new FlowLayout(FlowLayout.CENTER, 0, 0);

	private static Game game = null;
	
	private static List<Level> levels = Parser.levels(GameConstants.FILE_PATH);
	
	private Player player;
	private JPanel mainWindow = null; // or gameWindow
	private JFrame window;
	
	private int currentLevelIndex = 0;
	private int currentTaskIndex = 0;

	private Task currentTask;
	
	private Game()
	{
		System.setProperty("file.encoding", "UTF-8");
		
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(WINDOW_LAYOUT);
		window.setResizable(false);
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
		
		window.setLocation(x, y);
		
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
		
		int n = currentTaskIndex + 1;
		this.currentTask = new Task(ViewConstants.TASK_TITLE + n);;
		this.currentTaskIndex++;

		window.getContentPane().removeAll();
		
		this.mainWindow = new MainWindow(levels.get(currentLevelIndex));
		window.add(mainWindow);
		
		window.pack();
		window.repaint();
		
		int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - window.size().getWidth()) / 2;
		int y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - window.size().getHeight()) / 2;
		
		window.setLocation(x, y);
		
		window.setVisible(true);
	}
	
	private void generateTask() 
	{	
		if (currentTaskIndex < GameConstants.GAME_QUESTIONS)
		{
			int n = currentTaskIndex + 1;
			currentTask.change(ViewConstants.TASK_TITLE + n);
			
			window.repaint();
		}
		if (currentTaskIndex == GameConstants.GAME_QUESTIONS)
		{
			if (currentLevelIndex < levels.size() - 1)
			{
				++currentLevelIndex;
				
				window.getContentPane().removeAll();
				
				mainWindow = new CongratulationWindow();
				
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
		this.window.getContentPane().removeAll();
		
		this.mainWindow = new EndWindow();
		this.window.add(mainWindow);
		
		this.window.pack();
		this.window.repaint();
		
		this.currentLevelIndex = 0;
		this.currentTaskIndex = -1;
	}

	@Override
	public void change() 
	{
		generateTask();
		this.mainWindow.repaint();
	}

	@Override
	public void generateNewTask() 
	{
		generateTask();
	}
	
	public Task getCurrentTask()
	{
		return this.currentTask;
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
