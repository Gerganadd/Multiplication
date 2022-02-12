package game;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.*;

import constants.GameConstants;
import constants.ViewConstants;

public class Task extends JDialog // to-do : make better design
{	
	private JTextField txtAnswear; //?
	private JButton btnOk; //?
	
	public Task(String title)
	{
		super();
		this.setVisible(true);
		this.setTitle(title);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // or Hide on close
		
		Random rand = new Random();
		int firstN = rand.nextInt(GameConstants.MAX_RANDOM_NUMBER); // 0 <= firstN <= 10
		int secondN = rand.nextInt(GameConstants.MAX_RANDOM_NUMBER);
		
		//lblTask
		JLabel lblTask = new JLabel(String.format("%s * %s = ", firstN, secondN));
		this.add(lblTask);
		
		//txtAnswear
		this.txtAnswear = new JTextField();
		Game.setSize(txtAnswear, ViewConstants.D_TASK_TXT_ANSWEAR);
		
		this.txtAnswear.addKeyListener(new KeyAdapter() 
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
		this.btnOk = new JButton("OK");
		this.btnOk.addActionListener(x -> 
		{
			validateAnswear(firstN, secondN, txtAnswear.getText());
		});
		this.add(btnOk);
		
		this.pack();
		this.repaint();
		
	}
	
	private void validateAnswear(int firstN, int secondN, String answear)
	{
		if (txtAnswear.getText() == null || txtAnswear.getText().isBlank())
			throw new IllegalArgumentException("Input result");
		
		int result = Integer.parseInt(txtAnswear.getText());
		
		if	((firstN * secondN) == result)
		{
			Game.getInstance().generateNewTask();
			
			this.dispose();
		}
		else
		{
			System.out.println("try again");
		}
	}
}
