package constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;

public class ViewConstants 
{
	//Task
	public static final Dimension D_TASK = new Dimension(60, 70);
	public static final Dimension D_TASK_TXT_ANSWEAR = new Dimension(30, 20);
	
	//StartWindow
	public static final Dimension D_INFORMATION_WINDOW = new Dimension(320, 250);
	public static final Dimension D_START_WINDOW_PNL_BOTTOM = new Dimension(D_INFORMATION_WINDOW.width, (int) (D_INFORMATION_WINDOW.height * 0.2));
	public static final Dimension D_START_WINDOW_PNL_TEXT = new Dimension((int) (D_INFORMATION_WINDOW.getWidth() / 2), D_INFORMATION_WINDOW.height - D_START_WINDOW_PNL_BOTTOM.height);

	public static String START_WINDOW_TEXT = " реши вярно всички задачи и преведи своя герой по пътя към света на забавленията";
	
	
	//all
	public static final Color BACKGROUND = null;
	public static final Dimension D_GAME_WINDOW = new Dimension(428, 645);
	
	
}
