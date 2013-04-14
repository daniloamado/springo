package com.software.project.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class LavaWorldRenderer extends WorldRenderer{

	
	public LavaWorldRenderer(SpriteBatch batch, World world) {
		super(batch, world);
		tinyPlatformTexture = new Texture("data/tinyLavaPlatform.png");
		platformTexture = new Texture("data/lavaPlatform.png");
		backgroundTexture = new Texture("data/lavaBackground.png");
	}
	
}