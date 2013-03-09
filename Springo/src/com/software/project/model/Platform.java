package com.software.project.model;

import com.badlogic.gdx.math.Vector2;

public class Platform extends Entity{

	public Platform(Vector2 position) {
		super(position, 487, 69);
	}
	
	public Platform(Vector2 position, int width, int height) {
		super(position, width, height);
	}

}
