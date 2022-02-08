package game;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.*;

import constants.GameConstants;
import listeners.GameListener;
import windows.*;

public class Game implements GameListener
{
	private final LayoutManager LAYOUT = new FlowLayout();

	private static Game game = null;
	
	private Player player;
	private JFrame window;
	
	private int index = 0;
	
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
	
	public static void setSize(Component c, Dimension d)
	{
		c.setSize(d);
		c.setPreferredSize(d);
		c.setMinimumSize(d);
		c.setMaximumSize(d);
	}
	
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
		window.add(new MainWindow(player));
		
		window.pack();
		window.repaint();
		
		generateTask();
	}
	
	public void generateTask()
	{
		if (index < GameConstants.GAME_QUESTIONS)
		{
			int n = index + 1;
			Task t = new Task("Задача №" + n);
			index++;
		}
		else
		{
			end();
		}
	}

	public void end() 
	{
		System.out.println("congratulations!!!");
	}

	@Override
	public void change() 
	{
		
	}

	@Override
	public void generateNewTask() {
		// TODO Auto-generated method stub
		
	}
}
