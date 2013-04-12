package com.software.project.view;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.software.project.SpringoGame;
import com.software.project.model.Coffee;
import com.software.project.model.MovingPlatform;
import com.software.project.model.Platform;
import com.software.project.model.Portal;
import com.software.project.model.Springo;
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
	MovingPlatform movingPlatform;
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
			platforms.add(new Platform(new Vector2(630, 200)));
			platforms.add(new Platform(new Vector2(100, 100)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(430, 0)));
			platforms.add(new Platform(new Vector2(860, 0)));
			break;
		case 2:
			renderer = new GrassWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(1100, 500));
			platforms.add(new Platform(new Vector2(700, 400)));
			platforms.add(new Platform(new Vector2(100, 300)));
			platforms.add(new Platform(new Vector2(670, 200)));
			platforms.add(new Platform(new Vector2(100, 100)));
			coffees.add(new Coffee(new Vector2(1000, 240)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(430, 0)));
			platforms.add(new Platform(new Vector2(860, 0)));
			break;
		case 3:
			renderer = new SandWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(10, 560));
			platforms.add(new Platform(new Vector2(100, 470), true));
			platforms.add(new Platform(new Vector2(750, 400)));
			platforms.add(new Platform(new Vector2(730, 300)));
			platforms.add(new Platform(new Vector2(200, 200)));
			platforms.add(new Platform(new Vector2(100, 100)));
			coffees.add(new Coffee(new Vector2(10, 400)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(430, 0)));
			platforms.add(new Platform(new Vector2(860, 0)));
			break;
		case 4:
			renderer = new SandWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(1100, 570));
			platforms.add(new Platform(new Vector2(750, 490), true));
			platforms.add(new Platform(new Vector2(100, 400), true));
			platforms.add(new Platform(new Vector2(200, 300)));
			platforms.add(new Platform(new Vector2(730, 200)));
			platforms.add(new Platform(new Vector2(100, 100)));
			coffees.add(new Coffee(new Vector2(1000, 80)));
			coffees.add(new Coffee(new Vector2(10, 80)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(485, 0)));
			platforms.add(new Platform(new Vector2(900, 0)));
			break;
		case 5:
			renderer = new IceWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(1100, 570));
			platforms.add(new Platform(new Vector2(660, 490)));
			platforms.add(new Platform(new Vector2(100, 400)));
			platforms.add(new Platform(new Vector2(200, 300), true));
			platforms.add(new Platform(new Vector2(730, 200), true));
			platforms.add(new Platform(new Vector2(100, 100)));
			coffees.add(new Coffee(new Vector2(1000, 80)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(485, 0)));
			platforms.add(new Platform(new Vector2(900, 0)));
			break;
		case 6:
			renderer = new IceWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(1100, 570));
			platforms.add(new Platform(new Vector2(750, 490), true));
			platforms.add(new Platform(new Vector2(100, 400), true));
			platforms.add(new Platform(new Vector2(200, 300), true));
			platforms.add(new Platform(new Vector2(730, 200)));
			platforms.add(new Platform(new Vector2(100, 100)));
			coffees.add(new Coffee(new Vector2(1000, 80)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(485, 0)));
			platforms.add(new Platform(new Vector2(900, 0)));
			break;
		case 7:
			renderer = new LavaWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(1100, 570));
			platforms.add(new Platform(new Vector2(750, 490), true));
			platforms.add(new Platform(new Vector2(100, 400)));
			platforms.add(new Platform(new Vector2(200, 300)));
			platforms.add(new Platform(new Vector2(730, 200)));
			platforms.add(new Platform(new Vector2(100, 100)));
			coffees.add(new Coffee(new Vector2(1000, 80)));
			coffees.add(new Coffee(new Vector2(10, 350)));
			coffees.add(new Coffee(new Vector2(1100, 300)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(485, 0)));
			platforms.add(new Platform(new Vector2(900, 0)));
			break;
		case 8:
			renderer = new LavaWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(1100, 570));
			platforms.add(new Platform(new Vector2(800, 490), true));
			platforms.add(new Platform(new Vector2(100, 400)));
			platforms.add(new Platform(new Vector2(200, 300), true));
			platforms.add(new Platform(new Vector2(730, 200), true));
			platforms.add(new Platform(new Vector2(100, 100)));
			coffees.add(new Coffee(new Vector2(1000, 80)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(485, 0)));
			platforms.add(new Platform(new Vector2(900, 0)));
			break;
		case 9:
			renderer = new SkyWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(1100, 570));
			platforms.add(new Platform(new Vector2(650, 490)));
			platforms.add(new Platform(new Vector2(100, 400)));
			platforms.add(new Platform(new Vector2(200, 300), true));
			platforms.add(new Platform(new Vector2(730, 200), true));
			platforms.add(new Platform(new Vector2(100, 100)));
			coffees.add(new Coffee(new Vector2(1000, 80)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(485, 0)));
			platforms.add(new Platform(new Vector2(900, 0)));
			break;
		case 10:
			renderer = new SkyWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(1100, 570));
			platforms.add(new Platform(new Vector2(700, 490)));
			platforms.add(new Platform(new Vector2(100, 400), true));
			platforms.add(new Platform(new Vector2(200, 300), true));
			platforms.add(new Platform(new Vector2(730, 200)));
			platforms.add(new Platform(new Vector2(100, 100)));
			coffees.add(new Coffee(new Vector2(1000, 80)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(485, 0)));
			platforms.add(new Platform(new Vector2(900, 0)));
			break;
		case 11:
			renderer = new WaterWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(1100, 570));
			platforms.add(new Platform(new Vector2(750, 490)));
			platforms.add(new Platform(new Vector2(100, 400), true));
			platforms.add(new Platform(new Vector2(200, 300), true));
			platforms.add(new Platform(new Vector2(730, 200)));
			platforms.add(new Platform(new Vector2(100, 100)));
			coffees.add(new Coffee(new Vector2(1000, 80)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(485, 0)));
			platforms.add(new Platform(new Vector2(900, 0)));
			break;
		case 12:
			renderer = new WaterWorldRenderer(batcher, this);
			portal = new Portal(new Vector2(50, 570));
			platforms.add(new Platform(new Vector2(-130, 450)));
			platforms.add(new Platform(new Vector2(800, 400), true));
			platforms.add(new Platform(new Vector2(400, 320), true));
			platforms.add(new Platform(new Vector2(800, 300)));
			platforms.add(new Platform(new Vector2(1200, 200)));
			platforms.add(new Platform(new Vector2(1200, 100)));
			coffees.add(new Coffee(new Vector2(100, 300)));
			platforms.add(new Platform(new Vector2(0, 0)));
			platforms.add(new Platform(new Vector2(485, 0)));
			platforms.add(new Platform(new Vector2(900, 0)));
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
	
	public MovingPlatform getMovingPlatform() {
		return movingPlatform;
	}
	
	public void update(float deltaTime, float accelX) {
		updateSpringo(deltaTime, accelX);
		updatePlatforms(deltaTime);
		
		if (springo.state != Springo.SPRINGO_STATE_HIT) checkCollisions();
		checkGameOver();

	}
	
	private void updateSpringo (float deltaTime, float accelX) {
		if (springo.state != Springo.SPRINGO_STATE_HIT && springo.position.y <= 0.5f) springo.hitPlatform();
		if (springo.state != Springo.SPRINGO_STATE_HIT) springo.velocity.x = -accelX / 10 * Springo.SPRINGO_MOVE_VELOCITY;
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
		for (Coffee coffee : coffees) {
			if (OverlapTester.overlapRectangles(coffee.bounds, springo.bounds)) {
				springo.hitCoffee();
				listener.highJump();
				coffee.setVisible(false);
			}
		}
	}
	
	private void checkGameOver() {
		if (heightSoFar - 300 > springo.position.y) {
			state = WORLD_STATE_GAME_OVER;
		}
	}
}