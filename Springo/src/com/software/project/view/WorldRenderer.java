package com.software.project.view;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.software.project.model.Coffee;
import com.software.project.model.Platform;
import com.software.project.model.Portal;
import com.software.project.model.Springo;

public abstract class WorldRenderer {
	
	OrthographicCamera cam;
	World world;
	SpriteBatch batch;
	Springo springo;
	List<Platform> platforms;
	List<Coffee> coffees;
	Portal portal;
	
	Texture springoTexture;
	Texture portalTexture;
	Texture coffeeTexture;
	Texture platformTexture;
	Texture backgroundTexture;
	
	float width, height;
	
	public WorldRenderer (SpriteBatch batch, World world) {
		
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		this.world = world;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);
		cam.update();		
		
		this.batch = batch;
		batch.setProjectionMatrix(cam.combined);

		springoTexture = new Texture("data/springo.png");
		portalTexture = new Texture("data/portal.png");
		coffeeTexture = new Texture("data/highjump.png");
		
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
		coffees = world.getCoffees();
		batch.begin();

		batch.draw(backgroundTexture, 0, 0, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, 1, 0, 0, 0, backgroundTexture.getWidth(), backgroundTexture.getHeight(), false, false);			
		
		for (Coffee coffee : coffees) {
			if (coffee.isVisible()) {
				batch.draw(coffeeTexture, coffee.getPosition().x, coffee.getPosition().y, 0, 0, coffee.getWidth(), coffee.getHeight(), scaleX, scaleY, 0, 0, 0, coffeeTexture.getWidth(), coffeeTexture.getHeight(), false, false);
			}	
		}
		
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
		coffeeTexture.dispose();
		backgroundTexture.dispose();
	}

}
