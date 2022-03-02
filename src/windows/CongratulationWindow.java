package windows;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import constants.GameConstants;
import constants.ViewConstants;
import game.Game;

public class CongratulationWindow extends JPanel
{
	private final LayoutManager LAYOUT = new FlowLayout(FlowLayout.CENTER);
	private JButton btnNextLevel;
	private JButton btnExit;
	
	public CongratulationWindow()
	{
		super();
		configurate();
		
		JLabel lblCongratulation = new JLabel(ViewConstants.CONGRATULATION_WINDOW_TEXT);
		this.add(lblCongratulation);
		
		formatPicture();
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new FlowLayout());
		this.btnNextLevel = new JButton(ViewConstants.BTN_NEXT_LEVEL_TEXT);
		btnNextLevel.addActionListener(x -> 
		{
			Game.getInstance().newLevel();
		});
		pnlButtons.add(btnNextLevel);
		
		this.btnExit = new JButton(ViewConstants.BTN_EXIT_TEXT);
		this.btnExit.addActionListener(x ->
		{
			System.exit(0);
		});
		pnlButtons.add(btnExit);
		
		this.add(pnlButtons);
	}

	private void formatPicture() 
	{
		try 
		{
			BufferedImage image = ImageIO.read(new File(GameConstants.FIREWORKS_PICTURE_PATH));
			JLabel lblImage = new JLabel(new ImageIcon(image));
			
			JPanel pnlPicture = new JPanel();
			pnlPicture.add(lblImage);
			this.add(pnlPicture);
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	private void configurate() 
	{
		this.setLayout(LAYOUT);
		Game.setSize(this, ViewConstants.D_INFORMATION_WINDOW);
	}
	
}
