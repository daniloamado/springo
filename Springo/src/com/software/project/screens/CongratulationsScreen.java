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

public class CongratulationsScreen implements Screen{
	
	OrthographicCamera cam;
	SpringoGame game;
	Texture splashTexture;
	SpriteBatch batch;
	Skin skin;
	TextureAtlas atlas;
	
	Stage stage;
	BitmapFont white;
	TextButton btnAgain;
	
	public CongratulationsScreen(SpringoGame game) {
		
		this.game = game;
		batch = new SpriteBatch();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();
		
	}
	
	@Override
	public void render(float delta) {
		
		splashTexture = new Texture("data/congratulations.png");
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
		
		btnAgain = new TextButton("Play Again", style);
		btnAgain.setWidth(220);
		btnAgain.setHeight(60);
		btnAgain.setX(Gdx.graphics.getWidth() - 1260);
		btnAgain.setY(Gdx.graphics.getHeight() - 80);
		
		btnAgain.addListener(new InputListener() {
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
		
		stage.addActor(btnAgain);
		
	}

	//first called
	@Override
	public void show() {
		
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