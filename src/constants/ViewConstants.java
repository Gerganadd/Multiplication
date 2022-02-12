package constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.border.Border;

public class ViewConstants 
{
	//Task
	public static final Dimension D_TASK = new Dimension(60, 70);
	public static final Dimension D_TASK_TXT_ANSWEAR = new Dimension(30, 20);
	
	//InitializeWindow
	public static final Dimension D_INFORMATION_WINDOW = new Dimension(320, 250); // same as a StartWindow 
	public static final Dimension ININTIALIZE_WINDOW_D_TXT_NAME = new Dimension(100, 20);
	
	public static final String ININTIALIZE_WINDOW_TEXT = "Избери герой:"; //change name
	public static final String ININTIALIZE_WINDOW_TEXT1 = "Въведи име:"; //change name
	public static final String ININTIALIZE_WINDOW_BTN_TEXT = "Запази";
	
	public static final Border ININTIALIZE_WINDOW_DEFAULT_BORDER = BorderFactory.createLineBorder(Color.black);
	public static final Border ININTIALIZE_WINDOW_SELECT_BORDER = BorderFactory.createLineBorder(Color.blue);
	public static final Border ININTIALIZE_WINDOW_USER_SELECT_BORDER = BorderFactory.createLineBorder(Color.green);
	
	//StartWindow
	public static final Dimension D_START_WINDOW_PNL_BOTTOM = new Dimension(D_INFORMATION_WINDOW.width, (int) (D_INFORMATION_WINDOW.height * 0.2));
	public static final Dimension D_START_WINDOW_PNL_TEXT = new Dimension((int) (D_INFORMATION_WINDOW.getWidth() / 2), D_INFORMATION_WINDOW.height - D_START_WINDOW_PNL_BOTTOM.height);

	public static String START_WINDOW_TEXT = " реши вярно всички задачи и преведи своя герой по пътя към света на забавленията";
	
	//MainWindow
	public static final Color MAIN_WINDOW_DEFAULT_COLOR = Color.GRAY;
	public static final Color MAIN_WINDOW_TEXT_COLOR = Color.BLACK;
	public static final Color MAIN_WINDOW_CURRECT_ANSWEARED_COLOR = Color.GREEN;
	
	//all
	public static final Color BACKGROUND = null;
	public static final Dimension D_GAME_WINDOW = new Dimension(428, 645);
	public static final LayoutManager LAYOUT = new FlowLayout();
	
	
}
