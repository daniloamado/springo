package com.software.project.view;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.software.project.SoftwareProject2Game;
import com.software.project.model.MovingPlatform;
import com.software.project.model.Platform;
import com.software.project.model.Portal;
import com.software.project.model.Springo;
import com.software.project.utils.OverlapTester;

public class World {
	
	public static final Vector2 gravity = new Vector2(0, -12);
	
	public static final float WORLD_WIDTH = 18;
	public static final float WORLD_HEIGHT = 20;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;
	public static final float SPRINGO_WIDTH = 0.7f;
	public static final float SPRINGO_HEIGHT = 0.7f;
	public static final float SPRINGO_ROTATION = 0;
	
	public float heightSoFar;
	public int score;
	public int state;
	
	SoftwareProject2Game game;
	Springo springo;
	List<Platform> platforms;
	MovingPlatform movingPlatform;
	Portal portal;
	
	public World(SoftwareProject2Game game) {
		this.game = game;
		springo = new Springo(new Vector2(0, 1), SPRINGO_WIDTH, SPRINGO_HEIGHT, SPRINGO_ROTATION, 5f);
		platforms = new ArrayList<Platform>();
		//movingPlatform = new MovingPlatform(new Vector2(2, 5), 2.5f, 0.5f, 0, 5f);
		Gdx.input.setInputProcessor(new InputHandler(this));
		generateLevel(game.level);
	}
	
	private void generateLevel(int level) {
		
		switch (level) {
		case 1: 
			portal = new Portal(new Vector2(13, 7), 0.9f, 1);
			platforms.add(new Platform(new Vector2(9, 6), 5, 0.5f));
			platforms.add(new Platform(new Vector2(1.5f, 5), 5, 0.5f));
			platforms.add(new Platform(new Vector2(10, 4), 5, 0.5f));
			platforms.add(new Platform(new Vector2(2, 3), 5, 0.5f));
			platforms.add(new Platform(new Vector2(9, 2), 5, 0.5f));
			platforms.add(new Platform(new Vector2(1.5f, 1), 5, 0.5f));	
			break;
		case 2:
			portal = new Portal(new Vector2(13, 3), 0.9f, 1);
			platforms.add(new Platform(new Vector2(9, 2), 5, 0.5f));
			platforms.add(new Platform(new Vector2(1.5f, 1), 5, 0.5f));
			break;
		case 3:
			portal = new Portal(new Vector2(13, 7), 0.9f, 1);
			platforms.add(new Platform(new Vector2(9, 6), 5, 0.5f));
			platforms.add(new Platform(new Vector2(1.5f, 5), 5, 0.5f));
			platforms.add(new Platform(new Vector2(10, 4), 5, 0.5f));
			platforms.add(new Platform(new Vector2(2, 3), 5, 0.5f));
			platforms.add(new Platform(new Vector2(9, 2), 5, 0.5f));
			platforms.add(new Platform(new Vector2(1.5f, 1), 5, 0.5f));	
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
	
	public void update(float deltaTime, float accelX) {
		updateSpringo(deltaTime, accelX);
		
		if (springo.state != Springo.SPRINGO_STATE_HIT) checkCollisions();
		checkGameOver();
		
		//springo.update(deltaTime);
		//movingPlatform.update(deltaTime);
		
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
			if (springo.position.y > platform.position.y) {
				if (OverlapTester.overlapRectangles(springo.bounds, platform.bounds)) {
					springo.hitPlatform();
					break;
				}
			}
		}
	}

	private void checkPortalCollisions() {
		if (OverlapTester.overlapRectangles(portal.bounds, springo.bounds)) {
			state = WORLD_STATE_NEXT_LEVEL;
		}
	}
	
	private void checkGameOver() {
		if (heightSoFar - 7.5f > springo.position.y) {
			state = WORLD_STATE_GAME_OVER;
		}
	}
}