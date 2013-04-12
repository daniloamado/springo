package com.software.project.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class WaterWorldRenderer extends WorldRenderer{

	
	public WaterWorldRenderer(SpriteBatch batch, World world) {
		super(batch, world);
		platformTexture = new Texture("data/waterPlatform.png");
		backgroundTexture = new Texture("data/waterBackground.png");
	}
	
}