package com.software.project.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.software.project.SpringoGame;
import com.software.project.utils.Assets;

public class MainMenuScreen implements Screen{
	
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
	Texture backgroundTexture;
	
	Label label;
	
	public MainMenuScreen(SpringoGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor( 0, 0, 0.2f, 0.5f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		batch.begin();
		stage.draw();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		if (stage == null) {
			stage = new Stage(width, height, true);
		}
		
		if (backgroundTexture == null) {
			backgroundTexture = new Texture("data/menuScreen.png");
		}
		
		stage.clear();
		//collects the touch down events
		Gdx.input.setInputProcessor(stage);
		
		Image background = new Image(backgroundTexture);
		
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("input/button_normal");
		style.down = skin.getDrawable("input/buttons_pressed");
		style.font = white;
		
		btnPlay = new TextButton("Play", style);
		btnPlay.setWidth(BTN_WIDTH);
		btnPlay.setHeight(BTN_HEIGHT);
		btnPlay.setX(Gdx.graphics.getWidth() / 2 - btnPlay.getWidth() / 2);
		btnPlay.setY(Gdx.graphics.getHeight() / 2 + 30);
		
		btnPlay.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Assets.playSound(Assets.clickSound);
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
		btnScoreboard.setY(Gdx.graphics.getHeight() / 2 - 85);
		
		btnScoreboard.addListener(new InputListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new ScoreBoardScreen(game));
				
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
		btnSettings.setY(Gdx.graphics.getHeight() / 2 - 200);
		
		btnSettings.addListener(new InputListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new SettingsScreen(game));
				
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
		btnAbout.setY(Gdx.graphics.getHeight() / 10 - btnSettings.getHeight() / 2 + 5);
		
		btnAbout.addListener(new InputListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new AboutScreen(game));
				
			}
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}
		});
		
//		
//		TextFieldStyle textStyle = new TextFieldStyle();
//		textStyle.font = Assets.font;
//		textStyle.fontColor = Color.BLUE;
//		
//		
//		TextField textfield = new TextField("", textStyle);
//		textfield.setWidth(200);
//		textfield.setHeight(40);
//		textfield.setX(10);
//		textfield.setY(10);
		
//		
//		textfield.setTextFieldListener(new TextFieldListener() {
//			 @Override
//			 public void keyTyped (TextField textField, char key) {
//				 if (key == '\n') textField.getOnscreenKeyboard().show(false);
//			         
//			     }
//			 });
		
//		LabelStyle ls = new LabelStyle(white, Color.BLUE);
//		label = new Label("Springo - Main Menu", ls);
//		label.setX(0);
//		label.setY(Gdx.graphics.getHeight() / 2 + 250);
//		label.setWidth(width);
//		label.setAlignment(Align.center);
		
		stage.addActor(background);
		stage.addActor(btnPlay);
		stage.addActor(btnScoreboard);
		stage.addActor(btnSettings);
		stage.addActor(btnAbout);
		//stage.addActor(label);
//		stage.addActor(textfield);
//		
//		stage.setKeyboardFocus(textfield);
		
		
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
