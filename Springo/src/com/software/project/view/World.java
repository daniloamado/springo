package com.software.project.view;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.software.project.SpringoGame;
import com.software.project.model.Coffee;
import com.software.project.model.Platform;
import com.software.project.model.Portal;
import com.software.project.model.Springo;
import com.software.project.model.TinyPlatform;
import com.software.project.utils.OverlapTester;

public class World {
	
	public interface WorldListener {
		public void jump ();

		public void highJump ();

		public void portal ();
	}
	
	public static final Vector2 gravity = new Vector2(0, -400);
	
	public static final float WORLD_WIDTH = Gdx.graphics.getWidth();
	public static final float WORLD_HEIGHT = Gdx.graphics.getHeight();
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;
	public static final int WORLD_STATE_GAME_COMPLETED = 3;
	
	public float heightSoFar;
	public int score;
	public int state;
	
	public final WorldListener listener;
	
	SpringoGame game;
	Springo springo;
	List<Platform> platforms;
	Portal portal;
	List<Coffee> coffees;
	WorldRenderer renderer;
	SpriteBatch batcher;
	
	public boolean lastLevel;
	
	public World(SpringoGame game, WorldListener listener, SpriteBatch batcher) {
		this.game = game;
		this.listener = listener;
		this.batcher = batcher;
		springo = new Springo(new Vector2(0, 60));
		platforms = new ArrayList<Platform>();
		coffees = new ArrayList<Coffee>();
		Gdx.input.setInputProcessor(new InputHandler(this));
		generateLevel(game.level);
		
	}
	
	private void generateLevel(int level) {
		
		switch (level) {
		case 1: 
			renderer = new GrassWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(1100, 570));
			platforms.add(new Platform(new Vector2(650, 490)));
			platforms.add(new Platform(new Vector2(100, 400)));
			platforms.add(new Platform(new Vector2(200, 300)));
			platforms.add(new TinyPlatform(new Vector2(630, 200)));
			platforms.add(new Platform(new Vector2(100, 100)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(460, 0)));
			platforms.add(new Platform(new Vector2(920, 0)));
			break;
		case 2:
			renderer = new GrassWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(1150, 550));
			platforms.add(new Platform(new Vector2(700, 300)));
			platforms.add(new TinyPlatform(new Vector2(520, 360)));
			platforms.add(new TinyPlatform(new Vector2(380, 360)));
			platforms.add(new Platform(new Vector2(-180, 270)));
			platforms.add(new Platform(new Vector2(20, 180)));
			platforms.add(new TinyPlatform(new Vector2(680, 180)));
			platforms.add(new TinyPlatform(new Vector2(600, 100)));
			coffees.add(new Coffee(new Vector2(1150, 300)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(460, 0)));
			platforms.add(new Platform(new Vector2(920, 0)));
			break;
		case 3:
			renderer = new SandWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(10, 560));
			platforms.add(new Platform(new Vector2(100, 470), true));
			platforms.add(new Platform(new Vector2(1100, 380)));
			platforms.add(new Platform(new Vector2(1220, 300)));
			platforms.add(new Platform(new Vector2(1050, 220)));
			platforms.add(new TinyPlatform(new Vector2(700, 300)));
			platforms.add(new TinyPlatform(new Vector2(430, 220)));
			platforms.add(new TinyPlatform(new Vector2(200, 200)));
			platforms.add(new Platform(new Vector2(100, 100)));
			coffees.add(new Coffee(new Vector2(10, 400)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(460, 0)));
			platforms.add(new Platform(new Vector2(920, 0)));
			break;
		case 4:
			renderer = new SandWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(1100, 570));
			platforms.add(new Platform(new Vector2(750, 490), true));
			platforms.add(new Platform(new Vector2(100, 400), true));
			platforms.add(new TinyPlatform(new Vector2(200, 300)));
			platforms.add(new TinyPlatform(new Vector2(250, 200)));
			platforms.add(new Platform(new Vector2(630, 200)));
			platforms.add(new TinyPlatform(new Vector2(100, 100)));
			platforms.add(new TinyPlatform(new Vector2(500, 100)));
			platforms.add(new TinyPlatform(new Vector2(1220, 80)));
			platforms.add(new TinyPlatform(new Vector2(1120, 140)));
			coffees.add(new Coffee(new Vector2(300, 480)));
			coffees.add(new Coffee(new Vector2(900, 420)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(490, 0)));
			platforms.add(new Platform(new Vector2(1050, 0)));
			break;
		case 5:
			renderer = new IceWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(1100, 570));
			platforms.add(new Platform(new Vector2(750, 490), true));
			platforms.add(new TinyPlatform(new Vector2(250, 200)));
			platforms.add(new Platform(new Vector2(630, 200)));
			platforms.add(new TinyPlatform(new Vector2(100, 100)));
			platforms.add(new TinyPlatform(new Vector2(500, 100)));
			platforms.add(new TinyPlatform(new Vector2(1220, 80)));
			platforms.add(new TinyPlatform(new Vector2(1120, 140)));
			platforms.add(new TinyPlatform(new Vector2(1120, 300)));
			coffees.add(new Coffee(new Vector2(300, 480)));
			coffees.add(new Coffee(new Vector2(900, 420)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(505, 0)));
			platforms.add(new Platform(new Vector2(1050, 0)));
			break;
		case 6:
			renderer = new IceWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(1100, 570));
			platforms.add(new Platform(new Vector2(750, 490), true));
			platforms.add(new Platform(new Vector2(100, 400), true));
			platforms.add(new Platform(new Vector2(200, 300), true));
			platforms.add(new TinyPlatform(new Vector2(730, 200)));
			platforms.add(new Platform(new Vector2(100, 100)));
			coffees.add(new Coffee(new Vector2(1000, 80)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(505, 0)));
			platforms.add(new Platform(new Vector2(1050, 0)));
			break;
		case 7:
			renderer = new LavaWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(1100, 570));
			platforms.add(new TinyPlatform(new Vector2(750, 490), true));
			platforms.add(new Platform(new Vector2(100, 400)));
			platforms.add(new Platform(new Vector2(200, 300)));
			platforms.add(new Platform(new Vector2(730, 220)));
			platforms.add(new TinyPlatform(new Vector2(100, 180)));
			platforms.add(new TinyPlatform(new Vector2(140, 90)));
			platforms.add(new TinyPlatform(new Vector2(410, 100)));
			platforms.add(new TinyPlatform(new Vector2(100, 300)));
			platforms.add(new TinyPlatform(new Vector2(1090, 100)));
			platforms.add(new TinyPlatform(new Vector2(980, 130)));
			coffees.add(new Coffee(new Vector2(10, 400)));
			coffees.add(new Coffee(new Vector2(1130, 420)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(505, 0)));
			platforms.add(new Platform(new Vector2(1050, 0)));
			break;
		case 8:
			renderer = new LavaWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(1100, 570));
			platforms.add(new TinyPlatform(new Vector2(750, 490), true));
			platforms.add(new Platform(new Vector2(100, 400)));
			platforms.add(new Platform(new Vector2(200, 300)));
			platforms.add(new Platform(new Vector2(730, 220)));
			platforms.add(new TinyPlatform(new Vector2(100, 180)));
			platforms.add(new TinyPlatform(new Vector2(140, 90)));
			platforms.add(new TinyPlatform(new Vector2(410, 100)));
			platforms.add(new TinyPlatform(new Vector2(100, 300)));
			platforms.add(new TinyPlatform(new Vector2(1090, 100), true));
			platforms.add(new TinyPlatform(new Vector2(980, 130)));
			coffees.add(new Coffee(new Vector2(10, 400)));
			coffees.add(new Coffee(new Vector2(1130, 420)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(505, 0)));
			platforms.add(new Platform(new Vector2(1050, 0)));
			break;
		case 9:
			renderer = new SkyWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(1100, 570));
			platforms.add(new TinyPlatform(new Vector2(750, 490), true));
			platforms.add(new TinyPlatform(new Vector2(250, 200)));
			platforms.add(new Platform(new Vector2(630, 200)));
			platforms.add(new TinyPlatform(new Vector2(100, 100)));
			platforms.add(new TinyPlatform(new Vector2(500, 100)));
			platforms.add(new TinyPlatform(new Vector2(1220, 80)));
			platforms.add(new TinyPlatform(new Vector2(1120, 140)));
			platforms.add(new TinyPlatform(new Vector2(1120, 300)));
			platforms.add(new TinyPlatform(new Vector2(920, 300)));
			platforms.add(new Platform(new Vector2(420, 330)));
			platforms.add(new TinyPlatform(new Vector2(130, 340)));
			platforms.add(new TinyPlatform(new Vector2(5, 360)));
			platforms.add(new TinyPlatform(new Vector2(-30, 440)));
			coffees.add(new Coffee(new Vector2(400, 0)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(505, 0)));
			platforms.add(new Platform(new Vector2(1050, 0)));
			break;
		case 10:
			renderer = new SkyWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(10, 580));
			platforms.add(new TinyPlatform(new Vector2(750, 490), true));
			platforms.add(new TinyPlatform(new Vector2(250, 200), true));
			platforms.add(new Platform(new Vector2(630, 200)));
			platforms.add(new TinyPlatform(new Vector2(100, 100)));
			platforms.add(new TinyPlatform(new Vector2(500, 100)));
			platforms.add(new TinyPlatform(new Vector2(1220, 80)));
			platforms.add(new TinyPlatform(new Vector2(1120, 140)));
			platforms.add(new TinyPlatform(new Vector2(1120, 300)));
			platforms.add(new TinyPlatform(new Vector2(1220, 360)));
			platforms.add(new TinyPlatform(new Vector2(1050, 400)));
			platforms.add(new TinyPlatform(new Vector2(920, 300), true));
			platforms.add(new Platform(new Vector2(420, 330)));
			platforms.add(new TinyPlatform(new Vector2(130, 340)));
			platforms.add(new TinyPlatform(new Vector2(5, 360)));
			coffees.add(new Coffee(new Vector2(1222, 230)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(505, 0)));
			platforms.add(new Platform(new Vector2(1050, 0)));
			break;
		case 11:
			renderer = new WaterWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(500, 590));
			platforms.add(new TinyPlatform(new Vector2(0, 500), true));
			platforms.add(new TinyPlatform(new Vector2(750, 380), true));
			platforms.add(new TinyPlatform(new Vector2(250, 200)));
			platforms.add(new Platform(new Vector2(630, 200)));
			platforms.add(new TinyPlatform(new Vector2(100, 100)));
			platforms.add(new TinyPlatform(new Vector2(500, 100)));
			platforms.add(new TinyPlatform(new Vector2(1220, 80)));
			platforms.add(new TinyPlatform(new Vector2(1120, 140)));
			platforms.add(new TinyPlatform(new Vector2(1120, 300)));
			platforms.add(new TinyPlatform(new Vector2(920, 300)));
			platforms.add(new TinyPlatform(new Vector2(1220, 450)));
			platforms.add(new Platform(new Vector2(420, 330)));
			platforms.add(new TinyPlatform(new Vector2(130, 340)));
			platforms.add(new TinyPlatform(new Vector2(5, 360)));
			coffees.add(new Coffee(new Vector2(800, 0)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new TinyPlatform(new Vector2(650, 0)));
			platforms.add(new Platform(new Vector2(900, 0)));
			break;
		case 12:
			renderer = new WaterWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(1190, 590));
			platforms.add(new TinyPlatform(new Vector2(1000, 490), true));
			platforms.add(new TinyPlatform(new Vector2(250, 200)));
			platforms.add(new TinyPlatform(new Vector2(100, 100)));
			platforms.add(new TinyPlatform(new Vector2(500, 100)));
			platforms.add(new TinyPlatform(new Vector2(1220, 80)));
			platforms.add(new TinyPlatform(new Vector2(1120, 140)));
			platforms.add(new TinyPlatform(new Vector2(1120, 300)));
			platforms.add(new TinyPlatform(new Vector2(920, 300)));
			platforms.add(new TinyPlatform(new Vector2(440, 300), true));
			platforms.add(new TinyPlatform(new Vector2(5, 360), true));
			platforms.add(new TinyPlatform(new Vector2(-30, 435)));
			coffees.add(new Coffee(new Vector2(1220, 510)));
			platforms.add(new Platform(new Vector2(-300, 0)));
			platforms.add(new TinyPlatform(new Vector2(785, 0)));
			platforms.add(new Platform(new Vector2(1100, 0)));
			lastLevel = true;
			break;		
		}
		
	}
	
	public WorldRenderer getWorldRenderer() {
		return renderer;
	}
	
	public Springo getSpringo() {
		return springo;
	}
	
	public List<Platform> getPlatforms() {
		return platforms;
	}
	
	public Portal getPortal() {
		return portal;
	}
	
	public List<Coffee> getCoffees() {
		return coffees;
	}
	
	public void update(float deltaTime, float accelX) {
		updateSpringo(deltaTime, accelX);
		updatePlatforms(deltaTime);
		
		if (springo.state != Springo.SPRINGO_STATE_FELL_HOLE) checkCollisions();
		checkGameOver();

	}
	
	private void updateSpringo (float deltaTime, float accelX) {
		if (springo.state != Springo.SPRINGO_STATE_FELL_HOLE && springo.position.y <= 0.5f) springo.hitHole();
		if (springo.state != Springo.SPRINGO_STATE_FELL_HOLE) springo.velocity.x = -accelX / 10 * Springo.SPRINGO_MOVE_VELOCITY;
		springo.update(deltaTime);
		heightSoFar = Math.max(springo.position.y, heightSoFar);
	}
	
	private void updatePlatforms (float deltaTime) {
		int len = platforms.size();
		for (int i = 0; i < len; i++) {
			Platform platform = platforms.get(i);
			platform.update(deltaTime);
		}
	}
	
	public void dispose() {
		
	}
	
	private void checkCollisions() {
		checkPlatformCollisions();
		checkPortalCollisions();
		checkCoffeeCollisions();
	}

	private void checkPlatformCollisions() {
		if (springo.velocity.y > 0) return;

		int len = platforms.size();
		for (int i = 0; i < len; i++) {
			Platform platform = platforms.get(i);
			if (springo.position.y > platform.position.y + 30) {
				if (OverlapTester.overlapRectangles(springo.bounds, platform.bounds)) {
					springo.hitPlatform();
					listener.jump();
					heightSoFar = 0;
					break;
				}
			}
		}
	}

	private void checkPortalCollisions() {
		if (OverlapTester.overlapRectangles(portal.bounds, springo.bounds)) {
			listener.portal();
			state = WORLD_STATE_NEXT_LEVEL;
		}
	}
	
	private void checkCoffeeCollisions() {
		List<Coffee> coffeeList = new ArrayList<Coffee>(coffees);
		for (Coffee coffee : coffeeList) {
			if (OverlapTester.overlapRectangles(coffee.bounds, springo.bounds)) {
				springo.hitCoffee();
				listener.highJump();
				coffees.remove(coffee);
			}
		}
	}
	
	private void checkGameOver() {
		if (heightSoFar - 300 > springo.position.y || springo.state == Springo.SPRINGO_STATE_FELL_HOLE) {
			state = WORLD_STATE_GAME_OVER;
		}
	}
}