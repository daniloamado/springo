package com.software.project.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.software.project.SpringoGame;
import com.software.project.utils.Assets;

public class AboutScreen implements Screen{
	
	OrthographicCamera cam;
	SpringoGame game;
	Texture splashTexture;
	SpriteBatch batch;
	Skin skin;
	TextureAtlas atlas;
	
	Stage stage;
	BitmapFont white;
	TextButton btnBack;
	
	public AboutScreen(SpringoGame game) {
		
		System.out.println("Gdx Width: " + Gdx.graphics.getWidth());
		System.out.println("Gdx Height: " + Gdx.graphics.getHeight());
		
		this.game = game;
		batch = new SpriteBatch();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();
		
	}
	
	@Override
	public void render(float delta) {
		
		System.out.println("render");
		
		splashTexture = new Texture("data/about.png");
		batch.enableBlending();
		
		batch.begin();
		batch.draw(splashTexture, 0, 0, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, 1, 0, 0, 0, splashTexture.getWidth(), splashTexture.getHeight(), false, false);
		batch.end();
		
		batch.begin();
		stage.draw();		
		batch.end();
		
		
		
	}
	//second called
	@Override
	public void resize(int width, int height) {
		
		System.out.println("resize");
		
		if (stage == null) {
			stage = new Stage(width, height, true);
		}
		
		stage.clear();
		//collects the touch down events
		Gdx.input.setInputProcessor(stage);
		
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("input/button_normal");
		style.down = skin.getDrawable("input/buttons_pressed");
		style.font = white;
		
		btnBack = new TextButton("Back", style);
		btnBack.setWidth(220);
		btnBack.setHeight(60);
		btnBack.setX(Gdx.graphics.getWidth() - 1260);
		btnBack.setY(Gdx.graphics.getHeight() - 80);
		
		btnBack.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new MainMenuScreen(game));
			}
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}
		});
		
		stage.addActor(btnBack);
		
	}

	//first called
	@Override
	public void show() {
		
		System.out.println("show");

		batch = new SpriteBatch();
		skin = new Skin();
		atlas = new TextureAtlas("data/test.atlas");
		skin.addRegions(atlas);
		white = new BitmapFont(Gdx.files.internal("data/font.fnt"), false);
		
		batch.setProjectionMatrix(cam.combined);
		
	}
		
	@Override
	public void hide() {
		System.out.println("hide");
	}

	@Override
	public void pause() {
		System.out.println("pause");
	}

	@Override
	public void resume() {
		System.out.println("resume");
	}

	@Override
	public void dispose() {
		System.out.println("dispose");
	}
}