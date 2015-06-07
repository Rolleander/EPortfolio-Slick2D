package com.eport.game.container;

import java.awt.Point;

import org.newdawn.fizzy.Body;
import org.newdawn.fizzy.CollisionEvent;
import org.newdawn.fizzy.Rectangle;
import org.newdawn.fizzy.StaticBody;
import org.newdawn.fizzy.World;
import org.newdawn.fizzy.WorldListener;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.eport.game.objects.ObstacleContainer;
import com.eport.game.objects.Projectile;
import com.eport.game.objects.Slingshot;
import com.eport.game.render.ObstaclesRender;
import com.eport.game.render.ProjectileRender;
import com.eport.game.render.SlingshotRender;

public class Game extends BasicGame {

	private final static String DISPLAY_NAME = "Test Game";

	private Projectile projectile;
	private Slingshot slingshot;
	private ObstacleContainer obstacles;
	private boolean slingshotGrabbed = false;

	private SlingshotRender slingshotRender;
	private ProjectileRender projectileRender;
	private ObstaclesRender obstaclesRender;
	private int groundY;
	
	private int score;
	private Image background;

	private World world;

	public Game() {

		super(DISPLAY_NAME);
	}

	@Override
	public void init(GameContainer container) throws SlickException {

		background=new Image("resource/background.jpg");
		
		projectile = new Projectile();
		obstacles = new ObstacleContainer();
		slingshot = new Slingshot(200, 350);

		projectileRender = new ProjectileRender();
		slingshotRender = new SlingshotRender(projectileRender);
		obstaclesRender=new ObstaclesRender();

		int w = container.getWidth();
		int h = container.getHeight();
		System.out.println(w + " " + h);

		world = new World(9.8f);

		int floorHeight = 20;

	    groundY = h - floorHeight;
		StaticBody floor = new StaticBody(new Rectangle(w, floorHeight), 0, groundY);
		floor.setRestitution(0.35f);
		world.add(floor);
		
		obstacles.init(700, groundY);

		for (Body body : obstacles.getBodies()) {
			world.add(body);
		}

		score=-3;
		world.addListener(new WorldListener() {		
			@Override
			public void separated(CollisionEvent event) {
			}
			
			@Override
			public void collided(CollisionEvent event) {
			   if(event.contains(floor)&&!event.contains(projectile.getBody()))
			   {
				   score++;
			   }
			}
		});
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {

		//ground
		background.draw();
		g.setColor(new Color(100,100,100));
		g.fillRect(0,groundY,1000,20);
		
		slingshotRender.render(g, slingshot);
		obstaclesRender.render(g, obstacles);
		projectileRender.render(g, projectile);
		
		//score
		g.setColor(new Color(50,50,50,150));
		g.fillRect(0,0,150,50);
		g.setColor(new Color(255,255,255));
		g.drawString("Score: "+score,10,30);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {

		// float floorDelta = ((float) delta + 1000) / 1000f;
		for (int i = 0; i < 10; i++) {
			world.update(0.0035f);
		}

		Input input = container.getInput();
		if (!projectile.isFired()) {
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {

				slingshotGrabbed = true;
				int mousex = input.getMouseX();
				int mousey = input.getMouseY();
				projectile.place(mousex, mousey);
				slingshot.calcPower(mousex, mousey);
			} else {
				if (slingshotGrabbed) {
					// fire
					slingshotGrabbed = false;
					projectile.fire();
					Body body = projectile.getBody();
					world.add(body);
					slingshot.fire(body);
				}
			}
		}

	}

}
