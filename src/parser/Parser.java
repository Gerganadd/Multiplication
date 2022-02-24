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

import game_components.Coordinate;
import game_components.Level;

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
	        	boolean playerPictureOrientation = ((Element) nCategory)
	        			.getAttribute("playerPictureOrientation")
	        			.contains("left");
	            
		        if (nCategory.getNodeType() == Node.ELEMENT_NODE) 
		        {
		            Element eCategory = (Element) nCategory;
		               
		            String backgroundPicturePath = eCategory
			                .getElementsByTagName("background")
			                .item(0)
			                .getTextContent()
			                .trim();
		            
		            String elementPicturePath = eCategory
			                .getElementsByTagName("element")
			                .item(0)
			                .getTextContent()
			                .trim();
		            
		            String defaultElementPicturePath = eCategory
			                .getElementsByTagName("defaultElement")
			                .item(0)
			                .getTextContent()
			                .trim();
		            
		            String cupPicturePath = eCategory
		            		.getElementsByTagName("cup")
			                .item(0)
			                .getTextContent()
			                .trim();
		            
		            Element ePlayer = (Element) eCategory.getElementsByTagName("playerCoordinates").item(0);
		            int xPlayer = Integer.parseInt(ePlayer.getAttribute("x"));
		            int yPlayer = Integer.parseInt(ePlayer.getAttribute("y"));
		            
		            Coordinate playerCoordinates = new Coordinate(xPlayer, yPlayer);
		            
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
		            
		            levels.add(new Level(backgroundPicturePath, elementPicturePath, defaultElementPicturePath,
		            			cupPicturePath, playerCoordinates, playerPictureOrientation, tasksCoordinates));
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
