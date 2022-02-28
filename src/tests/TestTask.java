package tests;


import java.awt.FlowLayout;

import javax.swing.JFrame;

import constants.ViewConstants;
import game_components.Task;

public class TestTask {

	public static void main(String[] args) 
	{
		Task t = new Task(ViewConstants.TASK_TITLE + "5 :");
		JFrame w = new JFrame();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setVisible(true);
		w.setLayout(new FlowLayout());
		
		w.add(t);
		
		w.pack();
		w.repaint();
	}

}
