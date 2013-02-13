package com.software.project.view;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.software.project.model.MovingPlatform;
import com.software.project.model.Platform;
import com.software.project.model.Portal;
import com.software.project.model.Springo;

public class WorldRenderer {
	
	World world;
	SpriteBatch batch;
	Springo springo;
	List<Platform> platforms;
	Portal portal;
	MovingPlatform movingPlatform;
	OrthographicCamera cam;
	Texture springoTexture;
	Texture platformTexture;
	Texture portalTexture;
	Texture movingPlatformTexture;
	Texture backgroundTexture;
	float width, height;
	
	public WorldRenderer(SpriteBatch batch, World world) {
		width = Gdx.graphics.getWidth() / 30;
		height = Gdx.graphics.getHeight() / 30;
		this.world = world;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);
		cam.update();		
		
		this.batch = batch;
		batch.setProjectionMatrix(cam.combined);
		
		springoTexture = new Texture("data/springo.png");
		springoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		platformTexture = new Texture("data/platform.png");
		platformTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		portalTexture = new Texture("data/portal.png");
		portalTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		movingPlatformTexture = new Texture("data/movPlatform.png");
		movingPlatformTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		backgroundTexture = new Texture("data/background.png");
		backgroundTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
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
		movingPlatform = world.getMovingPlatform();
		batch.begin();
		batch.draw(backgroundTexture, 0, 0, 0, 0, 20, 13, 1, 1, 0, 0, 0, 3, 3, false, false);
		//batch.draw(movingPlatformTexture, movingPlatform.getPosition().x, movingPlatform.getPosition().y, 0, 0, movingPlatform.getWidth(), movingPlatform.getHeight(), scaleX, scaleY, 0, 0, 0, movingPlatformTexture.getWidth(), movingPlatformTexture.getHeight(), false, false);
		batch.draw(portalTexture, portal.getPosition().x, portal.getPosition().y, 0, 0, portal.getWidth(), portal.getHeight(), scaleX, scaleY, 0, 0, 0, portalTexture.getWidth(), portalTexture.getHeight(), false, false);
		for (Platform p : platforms) {
			batch.draw(platformTexture, p.getPosition().x, p.getPosition().y, 0, 0, p.getWidth() + 0.5f, p.getHeight() + 0.4f, scaleX, scaleY, 0, 0, 0, platformTexture.getWidth(), platformTexture.getHeight(), false, false);	
		}
		batch.draw(springoTexture, springo.getPosition().x, springo.getPosition().y, 0, 0, springo.getWidth(), springo.getHeight(), scaleX, scaleY, springo.getRotation(), 0, 0, springoTexture.getWidth(), springoTexture.getHeight(), false, false);
		batch.end();
	}
	
	public void dispose() {
		batch.dispose();
		springoTexture.dispose();
		platformTexture.dispose();
		portalTexture.dispose();
		movingPlatformTexture.dispose();
	}

}