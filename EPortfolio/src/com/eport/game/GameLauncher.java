package com.eport.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import com.eport.game.container.Game;

public class GameLauncher {

	
	public static void main(String[] args) {	
		try {
			AppGameContainer game = new AppGameContainer(new Game());
			game.setTargetFrameRate(60);
			game.setDisplayMode(1000, 600, false);
			game.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
}
