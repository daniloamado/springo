package com.software.project.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class IceWorldRenderer extends WorldRenderer{

	
	public IceWorldRenderer(SpriteBatch batch, World world) {
		super(batch, world);
		platformTexture = new Texture("data/icyPlatform.png");
		backgroundTexture = new Texture("data/icyBackground.png");
	}
	
}