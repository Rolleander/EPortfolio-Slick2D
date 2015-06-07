package com.eport.game.render;


import org.newdawn.fizzy.Body;
import org.newdawn.fizzy.Rectangle;
import org.newdawn.fizzy.Shape;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Transform;

import com.eport.game.objects.ObstacleContainer;

public class ObstaclesRender {

		
	public ObstaclesRender() {
		
		
	}
	
	public void render(Graphics g, ObstacleContainer obstacles)
	{
		for(Body body: obstacles.getBodies())
		{
			Shape shape=body.getShape();
			if(shape instanceof Rectangle)
			{
				drawRectangle(g, body, (Rectangle) shape);
			}
		}
	}
	
	private void drawRectangle(Graphics g, Body body, Rectangle shape) {
		
			float x=body.getX();
		float y=body.getY();
		float w=shape.getWidth();
		float h=shape.getHeight();
		float angle=body.getRotation();
		org.newdawn.slick.geom.Shape content=new org.newdawn.slick.geom.Rectangle(x,y,w,h);
		content=content.transform(Transform.createRotateTransform(angle, x,y));

		/*	float xOffset=shape.getXOffset();
		float yOffset=shape.getYOffset();
		float angleOffset=shape.getAngleOffset();
		content=content.transform(Transform.createTranslateTransform(xOffset, yOffset));
		content=content.transform(Transform.createRotateTransform(angleOffset,x,y));	*/
		
		Color endCol=new Color(100,30,20);
		Color startCol=new Color(180,50,30);
		ShapeFill fill=new GradientFill(x, y, startCol, x+w, y+h, endCol);	
		g.fill(content,fill);
		
		g.setColor(new Color(0,0,0));
		g.draw(content);
	}
	

}
