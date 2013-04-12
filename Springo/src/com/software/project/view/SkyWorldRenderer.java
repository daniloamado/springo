package com.software.project.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class SkyWorldRenderer extends WorldRenderer{

	
	public SkyWorldRenderer(SpriteBatch batch, World world) {
		super(batch, world);
		platformTexture = new Texture("data/skyPlatform.png");
		backgroundTexture = new Texture("data/skyBackground.png");
	}
	
}