package com.software.project.model;

import com.badlogic.gdx.math.Vector2;
import com.software.project.view.World;

public class Springo extends MoveableEntity{
	
	public static final int SPRINGO_STATE_JUMP = 0;
	public static final int SPRINGO_STATE_FALL = 1;
	public static final int SPRINGO_STATE_HIT = 2;
	public static final float SPRINGO_JUMP_VELOCITY = 250;
	public static final float SPRINGO_MOVE_VELOCITY = 400;
	
	public int state;
	float stateTime;
	
	public Springo(Vector2 position) {
		super(5f, 0, 46, 60, position);
		state = SPRINGO_STATE_FALL;
		stateTime = 0;
	}

	@Override
	public void update(float deltaTime) {
		
		velocity.add(World.gravity.x * deltaTime, World.gravity.y * deltaTime);
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x - bounds.width / 4;
		bounds.y = position.y - bounds.height / 6;

		if (velocity.y > 0 && state != SPRINGO_STATE_HIT) {
			if (state != SPRINGO_STATE_JUMP) {
				state = SPRINGO_STATE_JUMP;
				stateTime = 0;
			}
		}

		if (velocity.y < 0 && state != SPRINGO_STATE_HIT) {
			if (state != SPRINGO_STATE_FALL) {
				state = SPRINGO_STATE_FALL;
				stateTime = 0;
			}
		}

		if (position.x < 0) position.x = World.WORLD_WIDTH;
		if (position.x > World.WORLD_WIDTH) position.x = 0;

		stateTime += deltaTime;
		
	}

	public void hitPlatform() {
		velocity.y = SPRINGO_JUMP_VELOCITY;
		state = SPRINGO_STATE_JUMP;
		stateTime = 0;
	}

	public void hitCoffee() {
		velocity.y = SPRINGO_JUMP_VELOCITY * 1.5f;
		state = SPRINGO_STATE_JUMP;
		stateTime = 0;
	}

}