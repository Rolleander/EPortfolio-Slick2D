package com.eport.game.objects;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.fizzy.Body;
import org.newdawn.fizzy.Circle;
import org.newdawn.fizzy.DynamicBody;
import org.newdawn.fizzy.Rectangle;

public class ObstacleContainer {

	private List<Body> bodies=new ArrayList<Body>();
	
	public ObstacleContainer() {
				
	}
	
	public void init(int startX, int groundY)
	{
		
		DynamicBody wall;
		
		
		float restitution = Rectangle.DEFAULT_RESTIUTION; // 0.9
		float density =Rectangle.DEFAULT_DENSITY; // 25
		float friction = Rectangle.DEFAULT_FRICTION; // 0.1
	
		restitution=0.15f; //energieerhaltung
		density=30; //dichte
		friction=10; //reibung
		
		int wallH=70;		
		for(int i=0; i<7; i++)
		{		
		wall=new DynamicBody(new Rectangle(20,wallH) , startX, groundY-wallH);
		setValues(wall, restitution, density, friction);
		bodies.add(wall);
		wall=new DynamicBody(new Rectangle(20,wallH) , startX+100, groundY-wallH);
		setValues(wall, restitution, density, friction);
		bodies.add(wall);
		wall=new DynamicBody(new Rectangle(20,wallH) , startX+200, groundY-wallH);
		setValues(wall, restitution, density, friction);
		bodies.add(wall);
		
		wall=new DynamicBody(new Rectangle(220,20) , startX, groundY-wallH-20);
		setValues(wall, restitution, density, friction);
		bodies.add(wall);
		
		groundY-=wallH+20;
		}
		
		
	}
	
	
	
	private void setValues(Body body, float restitution, float density, float friction)
	{
		body.setRestitution(restitution);
		body.setDensity(density);
		body.setFriction(friction);
	}
	
	public List<Body> getBodies() {
		return bodies;
	}
	
	
	
}
