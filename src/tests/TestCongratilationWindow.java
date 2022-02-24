package tests;

import javax.swing.JFrame;

import constants.ViewConstants;
import game.Game;
import windows.CongratulationWindow;

public class TestCongratilationWindow {

	public static void main(String[] args)
	{
		JFrame w = new JFrame();
		w.setVisible(true);
		Game.setSize(w, ViewConstants.D_INFORMATION_WINDOW);
		w.add(new CongratulationWindow());
		w.pack();
		w.repaint();

	}

}
