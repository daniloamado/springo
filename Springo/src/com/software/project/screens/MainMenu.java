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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.software.project.SoftwareProject2Game;

public class MainMenu implements Screen{
	
	SoftwareProject2Game game;
	Stage stage;
	BitmapFont white;
	BitmapFont black;
	TextureAtlas atlas;
	Skin skin;
	SpriteBatch batch;
	TextButton button;
	Label label;
	
	public MainMenu(SoftwareProject2Game game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor( 0, 0, 0, 1);
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
		
		button = new TextButton("Play", style);
		button.setWidth(200);
		button.setHeight(80);
		button.setX(Gdx.graphics.getWidth() / 2 - button.getWidth() / 2);
		button.setY(Gdx.graphics.getHeight() / 2 - button.getHeight() / 2);
		
		button.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				
				System.out.println("touch up");
				game.setScreen(new GameScreen(game));
				
			}
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				
				System.out.println("touch down");
				return true;
			}
		});
		
		LabelStyle ls = new LabelStyle(white, Color.YELLOW);
		label = new Label("Springo", ls);
		label.setX(0);
		label.setY(Gdx.graphics.getHeight() / 2 + 100);
		label.setWidth(width);
		label.setAlignment(Align.center);
		
		stage.addActor(button);
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
		dispose();
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
