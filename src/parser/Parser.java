package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import game.Coordinate;
import game.Level;

public class Parser 
{	
	public static List<Level> levels(String path)
	{
		List<Level> levels = new ArrayList<>();
		
		try 
		{
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(path);
	        doc.getDocumentElement().normalize();
	        
	        NodeList nList = doc.getElementsByTagName("level");
	        
	        for (int temp = 0; temp < nList.getLength(); temp++)
	        {
	        	Node nCategory = nList.item(temp);
	            
		        if (nCategory.getNodeType() == Node.ELEMENT_NODE) 
		        {
		            Element eCategory = (Element) nCategory;
		               
		            String backgroundPicturePath = eCategory
			                .getElementsByTagName("background")
			                .item(0)
			                .getTextContent()
			                .trim();
		             
		            int pixels = Integer.parseInt(
		            		eCategory
		 	                .getElementsByTagName("pixels")
			                .item(0)
			                .getTextContent()
			                .trim());
		               
		            NodeList coordinates = eCategory.getElementsByTagName("coordinate");
		            
		            Map<Integer, Coordinate> tasksCoordinates = new HashMap<>();
		            
		            for(int i = 0; i < coordinates.getLength(); i++)
		            {
		            	Node node = coordinates.item(i);
		            	
		            	if (node.getNodeType() == Node.ELEMENT_NODE)
		            	{
		            		Element e = (Element) node;
		            		int task = Integer.parseInt(e.getAttribute("task"));
		            		int x = Integer.parseInt(e.getAttribute("x"));
		            		int y = Integer.parseInt(e.getAttribute("y"));
		            		
		            		if (!tasksCoordinates.containsKey(task))
		            		{
		            			tasksCoordinates.put(task, new Coordinate(x, y));
		            		}
		            	}
		            }
		            
		            levels.add(new Level(backgroundPicturePath, pixels, tasksCoordinates));
		        }
	        }
		}
		catch (Exception e) 
		{
			e.printStackTrace();
	    }
		
		return levels;
	}
	
}
