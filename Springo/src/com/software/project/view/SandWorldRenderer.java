package com.software.project.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class SandWorldRenderer extends WorldRenderer{

	
	public SandWorldRenderer(SpriteBatch batch, World world) {
		super(batch, world);
		tinyPlatformTexture = new Texture("data/tinySandPlatform.png");
		platformTexture = new Texture("data/sandPlatform.png");
		backgroundTexture = new Texture("data/sandBackground.png");
	}
	
}