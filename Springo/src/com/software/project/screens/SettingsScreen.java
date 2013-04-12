package com.software.project.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.software.project.SpringoGame;
import com.software.project.utils.Assets;

public class SettingsScreen implements Screen{
	
	
	private static final float MIN_VOLUME = 0;
	private static final float MAX_VOLUME = 1;
	private static final float INCREASE_VOLUME = 0.01f;
	
	OrthographicCamera cam;
	SpringoGame game;
	Texture backgroundTexture;
	SpriteBatch batch;
	Skin skin;
	TextureAtlas atlas;
	
	Stage stage;
	BitmapFont white;
	TextButton btnBack;
	
	public SettingsScreen(SpringoGame game) {
		
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
		
		Gdx.gl.glClearColor( 0, 0, 0.2f, 0.5f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
//		backgroundTexture = new Texture("data/about.png");
		batch.enableBlending();
		
		batch.begin();
		stage.draw();		
		batch.end();
		
		batch.begin();
		
		Assets.font.draw(batch, "Springo Game Settings", 500 , 600);
		Assets.font.draw(batch, "Background Sound:", 300 , 520);
		Assets.font.draw(batch, "Sound Effects:", 300 , 320);
		
		Assets.font.draw(batch, "Low", 300 , 420);
		Assets.font.draw(batch, "High", 860 , 420);
		
		Assets.font.draw(batch, "Low", 300 , 230);
		Assets.font.draw(batch, "High", 860 , 230);
		
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
		
		final Slider sliderBackgroundSound = new Slider(MIN_VOLUME, MAX_VOLUME, INCREASE_VOLUME, false, Assets.skin);
		sliderBackgroundSound.setColor(Color.GREEN);
		sliderBackgroundSound.setWidth(600);
		sliderBackgroundSound.setX(300);
		sliderBackgroundSound.setY(440);
		sliderBackgroundSound.setValue(Assets.soundBackgroundVolume);
		
		sliderBackgroundSound.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				Assets.soundBackgroundVolume = sliderBackgroundSound.getValue();
				Assets.music.setVolume(Assets.soundBackgroundVolume);
			}
		});
		
		final Slider sliderSoundEffects = new Slider(MIN_VOLUME, MAX_VOLUME, INCREASE_VOLUME, false, Assets.skin);
		sliderSoundEffects.setColor(Color.GREEN);
		sliderSoundEffects.setWidth(600);
		sliderSoundEffects.setX(300);
		sliderSoundEffects.setY(250);
		sliderSoundEffects.setValue(Assets.soundEffectsVolume);
		
		sliderSoundEffects.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				Assets.soundEffectsVolume = sliderSoundEffects.getValue();
			}
		});
		
		stage.addActor(btnBack);
		stage.addActor(sliderSoundEffects);
		stage.addActor(sliderBackgroundSound);
		
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