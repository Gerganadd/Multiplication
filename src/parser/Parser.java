package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.Coordinate;
import game.Point;

public class Parser 
{
	public static List<Point> parseGamePath1(String path)
	{
		List<Point> points = new ArrayList<>();
		
		try 
		{
			List<String> lines = Files.readAllLines(Paths.get(path));
			Point.pixels = Integer.parseInt(lines.get(0));
			lines.remove(0);
			for(String line : lines)
			{
				String[] args = line.split(" ");
				int x = Integer.parseInt(args[1]);
				int y = Integer.parseInt(args[2]);
				
				points.add(new Point(args[0], x, y));
			}
			
			
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
		return points;
	}
	
}
