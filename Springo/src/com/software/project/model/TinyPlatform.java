package com.software.project.model;

import com.badlogic.gdx.math.Vector2;

public class TinyPlatform extends Platform {

	private static int TINY_PLATFORM_WIDTH = 80;
	
	public TinyPlatform(Vector2 position) {
		super(position, TINY_PLATFORM_WIDTH, PLATFORM_HEIGHT, PLATFORM_STILL, PLATFORM_ROTATION);
	}
	
	public TinyPlatform(Vector2 position, boolean moving) {
		super(position, TINY_PLATFORM_WIDTH, PLATFORM_HEIGHT, moving, PLATFORM_ROTATION);
	}

}
