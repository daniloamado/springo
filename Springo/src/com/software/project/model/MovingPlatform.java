package com.software.project.model;

import com.badlogic.gdx.math.Vector2;

public class MovingPlatform extends MoveableEntity {

	private boolean forward = true;
	
	public MovingPlatform(Vector2 position, float width, float height, float rotation, float SPEED) {
		super(SPEED, rotation, width, height, position);
		
		this.getVelocity().x = 0.06f;
		
	}

	@Override
	public void update(float delta) {
		if (position.x < 3 && forward) {
			//error after updating the lib
			//position.add(velocity.tmp().mul(Gdx.graphics.getDeltaTime() * SPEED));
			forward = false;
		} else {
			//error after updating the lib
			//position.sub(velocity.tmp().mul(Gdx.graphics.getDeltaTime() * SPEED));
			if (position.x < 0.2) {
				forward = true;
			}
		}
	}
}