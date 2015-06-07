package com.eport.game.render;

import org.newdawn.fizzy.Body;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.eport.game.objects.Projectile;

public class ProjectileRender {

	private Image projectile;
	
	public ProjectileRender() {
		try {
			projectile=new Image("resource/projectile.png");
		} catch (SlickException e) {
			
			e.printStackTrace();
		}
	}

	public void render(Graphics g, float x, float y, float angle) {

		float r=Projectile.RADIUS;		
		projectile.setRotation((float)Math.toDegrees(angle));
		projectile.draw(x-r, y-r);
	}
	

	public void render(Graphics g, Projectile projectile) {

		if (projectile.isFired()) {
	
			Body body = projectile.getBody();
			float x = body.getX();
			float y = body.getY();
			float angle = body.getRotation();
		
			render(g, x,y, angle);
		} 
	}

}
