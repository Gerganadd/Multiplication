package tests;

import javax.swing.*;

import game.*;
import windows.*;

public class TestGame {

	public static void main(String[] args) 
	{	
		Game.getInstance().start();
		
		/*
		JFrame w = new JFrame();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setLayout(new BoxLayout(w.getContentPane(), BoxLayout.X_AXIS));
		w.setVisible(true);
		w.add(new MainWindow(new Player("Ema", "src/resources/child-girl.png")));
		w.pack();
		w.repaint();
		*/
	}

}
