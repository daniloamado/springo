package com.software.project.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class WaterWorldRenderer extends WorldRenderer{

	
	public WaterWorldRenderer(SpriteBatch batch, World world) {
		super(batch, world);
		tinyPlatformTexture = new Texture("data/tinyWaterPlatform.png");
		platformTexture = new Texture("data/waterPlatform.png");
		backgroundTexture = new Texture("data/waterBackground.png");
	}
	
}