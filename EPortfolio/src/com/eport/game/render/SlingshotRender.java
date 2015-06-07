package com.eport.game.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.eport.game.objects.Slingshot;

public class SlingshotRender {

	private ProjectileRender projectileRender;
	
	public SlingshotRender(ProjectileRender projectileRender) {
		this.projectileRender=projectileRender;
	}
	
	public void render(Graphics g, Slingshot slingshot)
	{
		
		float x=slingshot.getXpos();
		float y=slingshot.getYpos();
		
		int h=230;
		int w=10;
		
		g.setColor(new Color(100,100,100));
		g.fillRect(x-w/2,y,w,h);
		
		if(!slingshot.isFired())
		{
		
		//draw line
		g.setColor(new Color(100,100,250));
		
		float angle=slingshot.getAngle();
		float power=slingshot.getPower();
		float x2=(float) (x+Math.cos(angle)*power);
		float y2=(float) (y+Math.sin(angle)*power);
		g.drawLine(x, y, x2, y2);
		
		//draw projectile in slingshot
	
			projectileRender.render(g, x2, y2, (float) (Math.PI-angle));
		}
	}
	
}
