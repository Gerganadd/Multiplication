package game_components;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.*;

import constants.GameConstants;
import constants.ViewConstants;
import game.Game;

public class Task extends JPanel // to-do : make better design
{	
	private static final int START_X = 5;
	private static final int START_Y = 0;
	
	private static final int SPACE = 5;
	
	private JLabel lblTitle;
	private JTextField txtAnswear; 
	private String title;
	
	public Task(String title)
	{
		super();
		
		this.setVisible(true);
		this.setLayout(null);
		this.setBackground(ViewConstants.BACKGROUND);
	
		Game.setSize(this, ViewConstants.D_TASK);
		
		if (title != null)
			this.title = title;
		
		createTask();
		
	}
	
	private void createTask()
	{
		createLblTitle();
		
		Random rand = new Random();
		int firstN = rand.nextInt(GameConstants.MAX_RANDOM_NUMBER); // 0 <= firstN <= 10
		int secondN = rand.nextInt(GameConstants.MAX_RANDOM_NUMBER);
		
		//lblTask
		JLabel lblTask = new JLabel(String.format("%s * %s = ", firstN, secondN));
		int lblTaskWidth = (int) ((ViewConstants.D_TASK.getWidth() / 2 - START_X - 2 * SPACE) - ViewConstants.D_TASK_TXT_ANSWEAR.getWidth()); 
		int lblTaskHeight = (int) (ViewConstants.D_TASK_TXT_ANSWEAR.getHeight());
		lblTask.setBounds(lblTitle.getX(),
				lblTitle.getHeight(),
				lblTaskWidth,
				lblTaskHeight);
		this.add(lblTask);
		
		//txtAnswear
		this.txtAnswear = new JTextField();
		txtAnswear.setBounds(lblTask.getWidth() + lblTask.getX(),
				lblTitle.getHeight(), 
				(int) (ViewConstants.D_TASK_TXT_ANSWEAR.getWidth()),
				(int) (ViewConstants.D_TASK_TXT_ANSWEAR.getHeight()));
		
		txtAnswear.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent arg0) 
			{
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					validateAnswear(firstN, secondN, txtAnswear.getText());
				}
			}
		});
		this.add(txtAnswear);
		
		//btnOk
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(lblTask.getX() + lblTask.getWidth() + txtAnswear.getWidth() + SPACE,
				lblTask.getY(),
				(int) ViewConstants.D_TASK.getWidth() / 2,
				txtAnswear.getHeight());
		
		btnOk.addActionListener(x -> 
		{
			validateAnswear(firstN, secondN, txtAnswear.getText());
		});
		this.add(btnOk);
	}
	
	private void createLblTitle()
	{
		lblTitle = new JLabel(title);
		int lblTitleWidth = (int) (ViewConstants.D_TASK.getWidth()) - START_X; 
		int lblTitleHeight = (int) ((ViewConstants.D_TASK.getHeight() - ViewConstants.D_TASK_TXT_ANSWEAR.getHeight() - SPACE)); 
		lblTitle.setBounds(START_X,
				START_Y,
				lblTitleWidth,
				lblTitleHeight);
		this.add(lblTitle);
	}
	
	private void validateAnswear(int firstN, int secondN, String answear)
	{
		if (txtAnswear.getText() == null || txtAnswear.getText().isBlank())
			wrongAnswear(ViewConstants.NO_INPUT_TEXT);
		
		try 
		{
			int result = Integer.parseInt(txtAnswear.getText().trim());
			
			if	((firstN * secondN) == result)
			{
				this.setBackground(ViewConstants.BACKGROUND);
				Game.getInstance().change();
			}
			else
			{
				wrongAnswear(ViewConstants.WRONG_ANSWEAR_TEXT);
			}
		}
		catch(NumberFormatException e)
		{
			wrongAnswear(ViewConstants.INVALID_INPUT_TEXT);
		}
	}
	
	private void wrongAnswear(String text)
	{
		this.setBackground(ViewConstants.WRONG_ANSWEAR_COLOR);
		
		this.title = text;
		this.remove(lblTitle);
		createLblTitle();
		
		this.repaint();
	}
	
	public void change(String newTitle)
	{
		if (newTitle != null)
			this.title = newTitle;
		
		this.removeAll();
		
		createTask();
	}
}
