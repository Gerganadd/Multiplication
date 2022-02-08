package windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import constants.ViewConstants;
import game.*;

public class InitializeWindow extends JPanel 
{
	private final LayoutManager LAYOUT = new FlowLayout();
	
	private final Dimension D_WINDOW = new Dimension(320, 250); 
	private final Dimension D_TXT_NAME = new Dimension(100, 20);
	
	private final String TEXT = "Избери герой:"; //change name
	private final String TEXT1 = "Въведи име:"; //change name
	private final String BTN_TEXT = "Запази";
	
	private final Border DEFAULT_BORDER = BorderFactory.createLineBorder(Color.black);
	private final Border SELECT_BORDER = BorderFactory.createLineBorder(Color.blue);
	private final Border USER_SELECT_BORDER = BorderFactory.createLineBorder(Color.green);
	
	private String girlPicturePath = "src/resources/child-girl.png";
	private String boyPicturePath = "src/resources/child-boy.png";
	
	private boolean isGirlPicClicked = false;
	private boolean isBoyPicClicked = false;
	
	public InitializeWindow()
	{
		super();
		configurate();
		
		JLabel lblText = new JLabel(TEXT);
		this.add(lblText);
		
		JPanel pnlPictures = new JPanel();
		pnlPictures.setLayout(new FlowLayout());
		formatPictures(pnlPictures);
		
		JLabel lblText1 = new JLabel(TEXT1);
		this.add(lblText1);
		
		JTextField txtName = new JTextField();
		Game.setSize(txtName, D_TXT_NAME);
		this.add(txtName);
		
		JButton btnSave = new JButton(BTN_TEXT);
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
		this.setLayout(LAYOUT);
		
		Game.setSize(this, D_WINDOW);
	}
	
	private void formatPictures(JPanel pnlPictures)
	{
		try 
		{
			BufferedImage girlPic = ImageIO.read(new File(girlPicturePath));
			BufferedImage boyPic = ImageIO.read(new File(boyPicturePath));
			
			JLabel lblGirlPic = new JLabel(new ImageIcon(girlPic));
			lblGirlPic.setBorder(DEFAULT_BORDER);
			JLabel lblBoyPic = new JLabel(new ImageIcon(boyPic));
			lblBoyPic.setBorder(DEFAULT_BORDER);
			
			lblGirlPic.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					if (isBoyPicClicked)
					{
						isBoyPicClicked = false;
						lblBoyPic.setBorder(DEFAULT_BORDER);
					}
					
					lblGirlPic.setBorder(USER_SELECT_BORDER);
					isGirlPicClicked = true;
				}

				@Override
				public void mouseEntered(MouseEvent arg0) 
				{
					lblGirlPic.setBorder(SELECT_BORDER);	
				}

				@Override
				public void mouseExited(MouseEvent arg0)
				{
					if (!isGirlPicClicked)
					{
						lblGirlPic.setBorder(DEFAULT_BORDER);
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
						lblGirlPic.setBorder(DEFAULT_BORDER);
					}
					
					lblBoyPic.setBorder(USER_SELECT_BORDER);
					isBoyPicClicked = true;
				}

				@Override
				public void mouseEntered(MouseEvent arg0) 
				{
					lblBoyPic.setBorder(SELECT_BORDER);	
				}

				@Override
				public void mouseExited(MouseEvent arg0)
				{
					if (!isBoyPicClicked)
					{
						lblBoyPic.setBorder(DEFAULT_BORDER);
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
