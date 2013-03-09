package com.software.project.view;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.software.project.SpringoGame;
import com.software.project.model.IcyPlatform;
import com.software.project.model.LongPlatform;
import com.software.project.model.MovingPlatform;
import com.software.project.model.Platform;
import com.software.project.model.Portal;
import com.software.project.model.Springo;
import com.software.project.utils.OverlapTester;

public class World {
	
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
	
	SpringoGame game;
	Springo springo;
	List<Platform> platforms;
	MovingPlatform movingPlatform;
	LongPlatform longPlatform;
	IcyPlatform icyPlatform;
	Portal portal;
	boolean lastLevel;
	
	public World(SpringoGame game) {
		this.game = game;
		springo = new Springo(new Vector2(0, 60));
		platforms = new ArrayList<Platform>();
		Gdx.input.setInputProcessor(new InputHandler(this));
		generateLevel(game.level);
	}
	
	private void generateLevel(int level) {
		
		switch (level) {
		case 1: 
			portal = new Portal(new Vector2(1100, 570));
			platforms.add(new Platform(new Vector2(750, 490)));
			platforms.add(new Platform(new Vector2(100, 400)));
			platforms.add(new Platform(new Vector2(200, 300)));
			platforms.add(new Platform(new Vector2(730, 200)));
			platforms.add(new Platform(new Vector2(100, 100)));
			platforms.add(new LongPlatform(new Vector2(0, 0)));
			break;
		case 2:
			portal = new Portal(new Vector2(1100, 500));
			platforms.add(new Platform(new Vector2(700, 400)));
			platforms.add(new Platform(new Vector2(100, 300)));
			platforms.add(new Platform(new Vector2(700, 200)));
			platforms.add(new Platform(new Vector2(100, 100)));
			platforms.add(new LongPlatform(new Vector2(0, 0)));
			break;
		case 3:
			portal = new Portal(new Vector2(10, 560));
			platforms.add(new Platform(new Vector2(100, 470)));
			platforms.add(new Platform(new Vector2(750, 400)));
			platforms.add(new Platform(new Vector2(730, 300)));
			platforms.add(new Platform(new Vector2(200, 200)));
			platforms.add(new Platform(new Vector2(100, 100)));
			platforms.add(new LongPlatform(new Vector2(0, 0)));
			break;
		case 4:
			portal = new Portal(new Vector2(1100, 570));
			platforms.add(new IcyPlatform(new Vector2(750, 490)));
			platforms.add(new IcyPlatform(new Vector2(100, 400)));
			platforms.add(new IcyPlatform(new Vector2(200, 300)));
			platforms.add(new IcyPlatform(new Vector2(730, 200)));
			platforms.add(new IcyPlatform(new Vector2(100, 100)));
			platforms.add(new IcyPlatform(new Vector2(0, 0)));
			break;
		case 5:
			portal = new Portal(new Vector2(1100, 300));
			platforms.add(new IcyPlatform(new Vector2(800, 200)));
			platforms.add(new IcyPlatform(new Vector2(200, 100)));
			platforms.add(new IcyPlatform(new Vector2(0, 0)));
			lastLevel = true;
			break;		
		}
		
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
	
	public MovingPlatform getMovingPlatform() {
		return movingPlatform;
	}
	
	public LongPlatform getLongPlatform() {
		return longPlatform;
	}
	
	public void update(float deltaTime, float accelX) {
		updateSpringo(deltaTime, accelX);
		
		if (springo.state != Springo.SPRINGO_STATE_HIT) checkCollisions();
		checkGameOver();

	}
	
	private void updateSpringo (float deltaTime, float accelX) {
		if (springo.state != Springo.SPRINGO_STATE_HIT && springo.position.y <= 0.5f) springo.hitPlatform();
		if (springo.state != Springo.SPRINGO_STATE_HIT) springo.velocity.x = -accelX / 10 * Springo.SPRINGO_MOVE_VELOCITY;
		springo.update(deltaTime);
		heightSoFar = Math.max(springo.position.y, heightSoFar);
	}
	
	public void dispose() {
		
	}
	
	private void checkCollisions() {
		checkPlatformCollisions();
		checkPortalCollisions();
	}

	private void checkPlatformCollisions() {
		if (springo.velocity.y > 0) return;

		int len = platforms.size();
		for (int i = 0; i < len; i++) {
			Platform platform = platforms.get(i);
			if (springo.position.y > platform.position.y + 30) {
				if (OverlapTester.overlapRectangles(springo.bounds, platform.bounds)) {
					springo.hitPlatform();
					break;
				}
			}
		}
	}

	private void checkPortalCollisions() {
		if (OverlapTester.overlapRectangles(portal.bounds, springo.bounds)) {
			if (lastLevel) {
				state = WORLD_STATE_GAME_COMPLETED;
			} else {
				state = WORLD_STATE_NEXT_LEVEL;
			}
		}
	}
	
	private void checkGameOver() {
		if (heightSoFar - 300 > springo.position.y) {
			state = WORLD_STATE_GAME_OVER;
		}
	}
}