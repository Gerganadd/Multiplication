package tests;

import javax.swing.JFrame;

import constants.ViewConstants;
import game.Game;
import windows.CongratilationWindow;

public class TestCongratilationWindow {

	public static void main(String[] args)
	{
		JFrame w = new JFrame();
		w.setVisible(true);
		Game.setSize(w, ViewConstants.D_INFORMATION_WINDOW);
		w.add(new CongratilationWindow());
		w.pack();
		w.repaint();

	}

}
