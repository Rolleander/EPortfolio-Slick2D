package com.eport.game.objects;

import org.newdawn.fizzy.Body;
import org.newdawn.fizzy.Circle;
import org.newdawn.fizzy.DynamicBody;

public class Projectile {

	public final static float RADIUS = 16;

	private Body body;
	private boolean fired = false;
	private float xpos, ypos;

	public Projectile() {

	}

	public void place(int x, int y) {
		xpos = x;
		ypos = y;
		fired = false;
	}

	public void fire() {
		
		float restitution = Circle.DEFAULT_RESTIUTION;
		float density =100;
		float friction = Circle.DEFAULT_FRICTION;
		Circle shape = new Circle(RADIUS);
		body = new DynamicBody(shape,xpos,ypos);
		body.setDensity(density);
		body.setRestitution(restitution);
		body.setFriction(friction);
		System.out.println("Fire "+xpos+" "+ypos);
		fired = true;
	}
	
	public boolean isFired() {
		return fired;
	}
	
	public float getStartXpos() {
		return xpos;
	}
	
	public float getStartYpos() {
		return ypos;
	}

	public Body getBody() {
		return body;
	}

}
