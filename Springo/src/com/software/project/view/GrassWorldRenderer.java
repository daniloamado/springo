package com.software.project.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class GrassWorldRenderer extends WorldRenderer{

	public GrassWorldRenderer(SpriteBatch batch, World world) {
		super(batch, world);
		
		platformTexture = new Texture("data/platform.png");
		backgroundTexture = new Texture("data/background.png");
	}
	
}