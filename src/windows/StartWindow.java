package windows;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import constants.ViewConstants;
import game.*;
import game_components.Player;

public class StartWindow extends JPanel
{
	private final LayoutManager LAYOUT = new BoxLayout(this, BoxLayout.Y_AXIS);
	
	public StartWindow(Player player)
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
			
			String text = "Здравей, " + player.getName() + ViewConstants.START_WINDOW_TEXT;
			setText(pnlText, text);
			pnlInformation.add(pnlText);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		JPanel pnlBottom = new JPanel(); // change name
		pnlBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton btnStart = new JButton("Започни");
		btnStart.addActionListener(x ->
		{
			Game.getInstance().openGame();
		});
		pnlBottom.add(btnStart);
		
		this.add(pnlInformation);
		this.add(pnlBottom);
		
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
