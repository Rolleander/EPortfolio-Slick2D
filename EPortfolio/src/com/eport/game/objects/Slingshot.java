package com.eport.game.objects;

import java.awt.Point;

import org.newdawn.fizzy.Body;

public class Slingshot {

	private final static float MAX_POWER = 150;

	private float angle;
	private float power;
	private int xpos, ypos;
	private boolean fired = false;

	public Slingshot(int xpos, int ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
	}

	public void fire(Body body) {
		float x=(float) (xpos+Math.cos(angle)*power);
		float y=(float) (ypos+Math.sin(angle)*power);
		
		body.setPosition(x, y);
		fired = true;
		float p=power*170;
		float xpow=(float) -(Math.cos(angle)*p);
		float ypow=(float) -(Math.sin(angle)*p);
		System.out.println(xpow+" "+ypow);
		body.applyImpulse(xpow, ypow);
		
	}

	public void reset() {
		fired = false;
	}

	public boolean isFired() {
		return fired;
	}

	public void calcPower(int mouseX, int mouseY) {
		power = (float) Point.distance(mouseX, mouseY, xpos, ypos);
		if (power > MAX_POWER) {
			power = MAX_POWER;
		}
		angle = (float) Math.atan2(mouseY - ypos, mouseX - xpos);
	}

	public float getAngle() {
		return angle;
	}

	public float getPower() {
		return power;
	}

	public int getXpos() {
		return xpos;
	}

	public int getYpos() {
		return ypos;
	}
}
