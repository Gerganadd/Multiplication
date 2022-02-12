package windows;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import constants.ViewConstants;
import game.*;
import game_components.Player;

public class InitializeWindow extends JPanel 
{	
	private String girlPicturePath = "src/resources/child-girl.png";
	private String boyPicturePath = "src/resources/child-boy.png";
	
	private boolean isGirlPicClicked = false;
	private boolean isBoyPicClicked = false;
	
	public InitializeWindow()
	{
		super();
		configurate();
		
		JLabel lblText = new JLabel(ViewConstants.ININTIALIZE_WINDOW_TEXT);
		this.add(lblText);
		
		JPanel pnlPictures = new JPanel();
		pnlPictures.setLayout(new FlowLayout());
		formatPictures(pnlPictures);
		
		JLabel lblText1 = new JLabel(ViewConstants.ININTIALIZE_WINDOW_TEXT1);
		this.add(lblText1);
		
		JTextField txtName = new JTextField();
		Game.setSize(txtName, ViewConstants.ININTIALIZE_WINDOW_D_TXT_NAME);
		this.add(txtName);
		
		JButton btnSave = new JButton(ViewConstants.ININTIALIZE_WINDOW_BTN_TEXT);
		btnSave.addActionListener(x -> 
		{
			Game.getInstance().setPlayer(
					new Player(txtName.getText(), 
							isGirlPicClicked? girlPicturePath : boyPicturePath));
		});
		this.add(btnSave);
	
	}

	private void configurate() 
	{
		this.setVisible(true);
		this.setBackground(ViewConstants.BACKGROUND);
		this.setLayout(ViewConstants.LAYOUT);
		
		Game.setSize(this, ViewConstants.D_INFORMATION_WINDOW);
	}
	
	private void formatPictures(JPanel pnlPictures)
	{
		try 
		{
			BufferedImage girlPic = ImageIO.read(new File(girlPicturePath));
			BufferedImage boyPic = ImageIO.read(new File(boyPicturePath));
			
			JLabel lblGirlPic = new JLabel(new ImageIcon(girlPic));
			lblGirlPic.setBorder(ViewConstants.ININTIALIZE_WINDOW_DEFAULT_BORDER);
			JLabel lblBoyPic = new JLabel(new ImageIcon(boyPic));
			lblBoyPic.setBorder(ViewConstants.ININTIALIZE_WINDOW_DEFAULT_BORDER);
			
			lblGirlPic.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					if (isBoyPicClicked)
					{
						isBoyPicClicked = false;
						lblBoyPic.setBorder(ViewConstants.ININTIALIZE_WINDOW_DEFAULT_BORDER);
					}
					
					lblGirlPic.setBorder(ViewConstants.ININTIALIZE_WINDOW_USER_SELECT_BORDER);
					isGirlPicClicked = true;
				}

				@Override
				public void mouseEntered(MouseEvent arg0) 
				{
					lblGirlPic.setBorder(ViewConstants.ININTIALIZE_WINDOW_SELECT_BORDER);	
				}

				@Override
				public void mouseExited(MouseEvent arg0)
				{
					if (!isGirlPicClicked)
					{
						lblGirlPic.setBorder(ViewConstants.ININTIALIZE_WINDOW_DEFAULT_BORDER);
					}
				}
			});
			
			lblBoyPic.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					if (isGirlPicClicked)
					{
						isGirlPicClicked = false;
						lblGirlPic.setBorder(ViewConstants.ININTIALIZE_WINDOW_DEFAULT_BORDER);
					}
					
					lblBoyPic.setBorder(ViewConstants.ININTIALIZE_WINDOW_USER_SELECT_BORDER);
					isBoyPicClicked = true;
				}

				@Override
				public void mouseEntered(MouseEvent arg0) 
				{
					lblBoyPic.setBorder(ViewConstants.ININTIALIZE_WINDOW_SELECT_BORDER);	
				}

				@Override
				public void mouseExited(MouseEvent arg0)
				{
					if (!isBoyPicClicked)
					{
						lblBoyPic.setBorder(ViewConstants.ININTIALIZE_WINDOW_DEFAULT_BORDER);
					}
				}
			});
			
			pnlPictures.add(lblGirlPic);
			pnlPictures.add(lblBoyPic);
			
			this.add(pnlPictures);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
