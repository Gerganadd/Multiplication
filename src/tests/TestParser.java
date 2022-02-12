package tests;


import java.util.List;

import game.Level;
import parser.Parser;

public class TestParser {

	public static void main(String[] args) 
	{
		List<Level> levels = Parser.levels("src/resources/levels.xml");
		System.out.println(levels.size());
	}

}
