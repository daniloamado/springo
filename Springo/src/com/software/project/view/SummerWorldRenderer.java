package com.software.project.view;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.software.project.model.IcyPlatform;
import com.software.project.model.LongPlatform;
import com.software.project.model.MovingPlatform;
import com.software.project.model.Platform;
import com.software.project.model.Portal;
import com.software.project.model.Springo;

public class SummerWorldRenderer implements WorldRenderer{
	
	World world;
	SpriteBatch batch;
	Springo springo;
	List<Platform> platforms;
	Portal portal;
	MovingPlatform movingPlatform;
	LongPlatform longPlatform;
	OrthographicCamera cam;
	Texture springoTexture;
	Texture platformTexture;
	Texture longPlatformTexture;
	Texture icyPlatformTexture;
	Texture portalTexture;
	Texture movingPlatformTexture;
	Texture backgroundTexture;
	Texture icyBackgroundTexture;
	float width, height;
	
	public SummerWorldRenderer(SpriteBatch batch, World world) {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		this.world = world;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);
		cam.update();		
		
		this.batch = batch;
		batch.setProjectionMatrix(cam.combined);
		
		springoTexture = new Texture("data/springo.png");
		platformTexture = new Texture("data/platform.png");
		longPlatformTexture = new Texture("data/longPlatform.png");
		icyPlatformTexture = new Texture("data/icyPlatform.png");
		portalTexture = new Texture("data/portal.png");
		movingPlatformTexture = new Texture("data/movPlatform.png");
		backgroundTexture = new Texture("data/background.png");
		icyBackgroundTexture = new Texture("data/icyBackground.png");
	}
	
	public void render() {
		int scaleX = 1;
		int scaleY = 1;
		Gdx.gl.glClearColor( 1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		springo = world.getSpringo();
		platforms = world.getPlatforms();
		portal = world.getPortal();
		longPlatform = world.getLongPlatform();
		movingPlatform = world.getMovingPlatform();
		batch.begin();
		
		if (!platforms.isEmpty() && platforms.get(0) instanceof IcyPlatform) {
			batch.draw(icyBackgroundTexture, 0, 0, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, 1, 0, 0, 0, icyBackgroundTexture.getWidth(), icyBackgroundTexture.getHeight(), false, false);			
		} else {
			batch.draw(backgroundTexture, 0, 0, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, 1, 0, 0, 0, backgroundTexture.getWidth(), backgroundTexture.getHeight(), false, false);			
		}
		
		batch.draw(portalTexture, portal.getPosition().x, portal.getPosition().y, 0, 0, portal.getWidth(), portal.getHeight(), scaleX, scaleY, 0, 0, 0, portalTexture.getWidth(), portalTexture.getHeight(), false, false);
		for (Platform p : platforms) {
			if (p instanceof LongPlatform) {
				batch.draw(longPlatformTexture, p.getPosition().x, p.getPosition().y, 0, 0, p.getWidth() + 0.5f, p.getHeight() + 0.4f, scaleX, scaleY, 0, 0, 0, longPlatformTexture.getWidth(), longPlatformTexture.getHeight(), false, false);					
			} else if (p instanceof IcyPlatform) {
				batch.draw(icyPlatformTexture, p.getPosition().x, p.getPosition().y, 0, 0, p.getWidth() + 0.5f, p.getHeight() + 0.4f, scaleX, scaleY, 0, 0, 0, icyPlatformTexture.getWidth(), icyPlatformTexture.getHeight(), false, false);
			} else {
				batch.draw(platformTexture, p.getPosition().x, p.getPosition().y, 0, 0, p.getWidth() + 0.5f, p.getHeight() + 0.4f, scaleX, scaleY, 0, 0, 0, platformTexture.getWidth(), platformTexture.getHeight(), false, false);	
			}
		}
		batch.draw(springoTexture, springo.getPosition().x, springo.getPosition().y, 0, 0, springo.getWidth(), springo.getHeight(), scaleX, scaleY, springo.getRotation(), 0, 0, springoTexture.getWidth(), springoTexture.getHeight(), false, false);
		batch.end();
	}
	
	public void dispose() {
		batch.dispose();
		springoTexture.dispose();
		platformTexture.dispose();
		longPlatformTexture.dispose();
		icyPlatformTexture.dispose();
		portalTexture.dispose();
		movingPlatformTexture.dispose();
	}

}