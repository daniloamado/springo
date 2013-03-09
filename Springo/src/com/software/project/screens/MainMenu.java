package com.software.project.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.software.project.SpringoGame;

public class MainMenu implements Screen{
	
	private static final int BTN_WIDTH = 220;
	private static final int BTN_HEIGHT = 60;	
	
	SpringoGame game;
	Stage stage;
	BitmapFont white;
	BitmapFont black;
	TextureAtlas atlas;
	Skin skin;
	SpriteBatch batch;
	TextButton btnPlay;
	TextButton btnSettings;
	TextButton btnScoreboard;
	TextButton btnAbout;
	Label label;
	
	public MainMenu(SpringoGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor( 0, 0, 0.2f, 0.5f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		batch.begin();
		stage.draw();
		//white.draw(batch, "Springo", Gdx.graphics.getWidth() / 2 - 15, Gdx.graphics.getHeight() + 100);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
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
		
		btnPlay = new TextButton("Play", style);
		btnPlay.setWidth(BTN_WIDTH);
		btnPlay.setHeight(BTN_HEIGHT);
		btnPlay.setX(Gdx.graphics.getWidth() / 2 - btnPlay.getWidth() / 2);
		btnPlay.setY(Gdx.graphics.getHeight() / 1.35f - btnPlay.getHeight() / 2);
		
		btnPlay.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new GameScreen(game));
			}
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}
		});
		
		btnScoreboard = new TextButton("Scoreboard", style);
		btnScoreboard.setWidth(BTN_WIDTH);
		btnScoreboard.setHeight(BTN_HEIGHT);
		btnScoreboard.setX(Gdx.graphics.getWidth() / 2 - btnScoreboard.getWidth() / 2);
		btnScoreboard.setY(Gdx.graphics.getHeight() / 1.9f - btnScoreboard.getHeight() / 2);
		
		btnScoreboard.addListener(new InputListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new GameScreen(game));
				
			}
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}	
		});
		
		btnSettings = new TextButton("Settings", style);
		btnSettings.setWidth(BTN_WIDTH);
		btnSettings.setHeight(BTN_HEIGHT);
		btnSettings.setX(Gdx.graphics.getWidth() / 2 - btnSettings.getWidth() / 2);
		btnSettings.setY(Gdx.graphics.getHeight() / 3.2f - btnSettings.getHeight() / 2);
		
		btnSettings.addListener(new InputListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new GameScreen(game));
				
			}
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}
		});
		
		btnAbout = new TextButton("About", style);
		btnAbout.setWidth(BTN_WIDTH);
		btnAbout.setHeight(BTN_HEIGHT);
		btnAbout.setX(Gdx.graphics.getWidth() / 2 - btnSettings.getWidth() / 2);
		btnAbout.setY(Gdx.graphics.getHeight() / 10 - btnSettings.getHeight() / 2);
		
		btnAbout.addListener(new InputListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new AboutScreen(game));
				
			}
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}
		});
		
		LabelStyle ls = new LabelStyle(white, Color.BLUE);
		label = new Label("Springo - Main Menu", ls);
		label.setX(0);
		label.setY(Gdx.graphics.getHeight() / 2 + 250);
		label.setWidth(width);
		label.setAlignment(Align.center);
		
		stage.addActor(btnPlay);
		stage.addActor(btnScoreboard);
		stage.addActor(btnSettings);
		stage.addActor(btnAbout);
		stage.addActor(label);
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		skin = new Skin();
		atlas = new TextureAtlas("data/test.atlas");
		skin.addRegions(atlas);
		white = new BitmapFont(Gdx.files.internal("data/font.fnt"), false);
		
	}

	@Override
	public void hide() {
		//dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		batch.dispose();
		skin.dispose();
		atlas.dispose();
		white.dispose();
		stage.dispose();
	}

}
