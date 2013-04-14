package com.software.project.model;

import com.badlogic.gdx.math.Vector2;
import com.software.project.view.World;

public class Platform extends MoveableEntity{

	protected static int PLATFORM_SPEED = 80;
	protected static int PLATFORM_ROTATION = 0;
	protected static int PLATFORM_WIDTH = 400;
	protected static int PLATFORM_HEIGHT = 60;
	protected static boolean PLATFORM_STILL = false;
	private boolean moving;
	float stateTime;
	
	public Platform(Vector2 position) {
		super(PLATFORM_SPEED, PLATFORM_ROTATION, PLATFORM_WIDTH, PLATFORM_HEIGHT, position);
	}
	
	public Platform(Vector2 position, int width, int height) {
		super(PLATFORM_SPEED, PLATFORM_ROTATION, width, height, position);
	}
	
	public Platform(Vector2 position, int width, int height, boolean moving, int rotation) {
		super(PLATFORM_SPEED, rotation, width, height, position);
		this.moving = moving;
		this.stateTime = 0;
		if (moving) {
			velocity.x = PLATFORM_SPEED;
		}
	}
	
	public Platform(Vector2 position, boolean moving) {
		super(PLATFORM_SPEED, PLATFORM_ROTATION, PLATFORM_WIDTH, PLATFORM_HEIGHT, position);
		this.moving = moving;
		this.stateTime = 0;
		if (moving) {
			velocity.x = PLATFORM_SPEED;
		}
	}
	
	public void update (float deltaTime) {
		if (isMoving()) {
			position.add(velocity.x * deltaTime, 0);
			bounds.x = position.x;
			bounds.y = position.y;

			if (position.x < width / 2) {
				velocity.x = -velocity.x;
				position.x = width / 2;
			}
			if (position.x > World.WORLD_WIDTH - width) {
				velocity.x = -velocity.x;
				position.x = World.WORLD_WIDTH - width;
			}
		}

		stateTime += deltaTime;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

}
