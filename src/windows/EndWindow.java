package windows;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import constants.ViewConstants;
import game.Game;
import game_components.Player;

public class EndWindow extends JPanel
{
	private final LayoutManager LAYOUT = new BoxLayout(this, BoxLayout.Y_AXIS);
	private Player player = Game.getInstance().getPlayer();
	
	public EndWindow()
	{
		super();
		configurate();
		
		JPanel pnlInformation = new JPanel(); // change name
		pnlInformation.setLayout(new FlowLayout());
		
		try 
		{
			BufferedImage playerImage = ImageIO.read(new File(player.getPicturePath()));
			JLabel lblplayer = new JLabel(new ImageIcon(playerImage));
			pnlInformation.add(lblplayer);
			
			JPanel pnlText = new JPanel();
			pnlText.setLayout(new FlowLayout(FlowLayout.LEFT));
			Game.setSize(pnlText, ViewConstants.D_START_WINDOW_PNL_TEXT);
			
			String text = "Поздравления " + player.getName() + ViewConstants.END_WINDOW_TEXT;
			setText(pnlText, text);
			pnlInformation.add(pnlText);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		JPanel pnlButtons = new JPanel(); 
		pnlButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton btnNewGame = new JButton(ViewConstants.BTN_NEW_GAME_TEXT);
		btnNewGame.addActionListener(x ->
		{
			//to-do: level = 0;
			Game.getInstance().openGame();
		});
		pnlButtons.add(btnNewGame);
		
		JButton btnExit = new JButton(ViewConstants.BTN_EXIT_TEXT);
		btnExit.addActionListener(x ->
		{
			System.exit(0);
		});
		pnlButtons.add(btnExit);
		
		this.add(pnlInformation);
		this.add(pnlButtons);
		
		this.repaint();
	}
	
	private void setText(JPanel pnl, String text) 
	{
		String words[] = text.split(" ");
		for (String word : words)
		{
			JLabel lbl = new JLabel(word);
			pnl.add(lbl);
		}	
	}

	private void configurate() 
	{
		this.setVisible(true);
		this.setBackground(ViewConstants.BACKGROUND);
		this.setLayout(LAYOUT);
	}
	
	
}
