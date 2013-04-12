package com.software.project.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.software.project.SpringoGame;
import com.software.project.tweenacessors.SpriteTween;
import com.software.project.utils.Assets;
import com.software.project.utils.ScoreHelper;
import com.software.project.utils.Settings;

public class ScoreBoardScreen implements Screen{
	
	OrthographicCamera cam;
	SpringoGame game;
	Texture backgroundTexture;
	SpriteBatch batch;
	Skin skin;
	TextureAtlas atlas;
	
	Stage stage;
	BitmapFont white;
	TextButton btnBack;
	
	TweenManager manager;
	
	int levelCounter = 0;
	Map<String, List<ScoreHelper>> scores = new HashMap<String, List<ScoreHelper>>();
	
	public ScoreBoardScreen(SpringoGame game) {
		
		this.game = game;
		batch = new SpriteBatch();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();
		
		HttpRequest httpGet = new HttpRequest(HttpMethods.GET);
		httpGet.setUrl(Settings.serverAddress + "cmd=RetrieveAll");
		
		Gdx.app.getNet().sendHttpRequest(httpGet, new HttpResponseListener() {
			public void handleHttpResponse(HttpResponse httpResponse) {
				String response = httpResponse.getResultAsString();
				scores = getScoresFromResponse(response);
			}
			
			//response example = "1|Danilo|10.5|10/10/2012;1|Danilo|10.5|10/10/2012--1|Danilo|10.5|10/10/2012;1|Danilo|10.5|10/10/2012";
			
			private Map<String, List<ScoreHelper>> getScoresFromResponse(
					String response) {
				
				Map<String, List<ScoreHelper>> map = new HashMap<String, List<ScoreHelper>>();
				
				String [] levels = response.split("--");
				for (String l : levels) {
					String [] scores = l.split(";");
					List<ScoreHelper> list = new ArrayList<ScoreHelper>();
					String level = "";
					for (String s : scores) {
						StringTokenizer token = new StringTokenizer(s, "|");
						level = token.nextToken();
						list.add(new ScoreHelper(level, token.nextToken(), token.nextToken(), token.nextToken()));
					}
					map.put(level, list);
				}
				
				return map;
				
			}
			public void failed(Throwable t) {
				System.out.println(t.getMessage());
			}
		});
		
	}
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor( 0, 0, 0.2f, 0.5f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		manager.update(delta);
		batch.enableBlending();
		
		batch.begin();
		stage.draw();
		batch.end();
		
		batch.begin();
		
		Assets.font.draw(batch, "Scoreboard - Top 5 players by level", 300 , 600);
		
		if (scores.get(String.valueOf(levelCounter)) == null) {
			
			Assets.font.draw(batch, "Loading...", 500 , 400);
			
		} else {
			
			Assets.font.draw(batch, "Level: " + levelCounter, 300 , 470);
			
			int linePos = 350;
			
			Assets.font.draw(batch, "Name:", 300 , linePos);
			Assets.font.draw(batch, "Achieved Time: (seconds)", 600 , linePos);
			Assets.font.draw(batch, "Date played:", 900 , linePos);
		
			for (ScoreHelper s : scores.get(String.valueOf(levelCounter))) {
				linePos = linePos - 50;
				Assets.font.draw(batch, s.getPlayer(), 300 , linePos);
				Assets.font.draw(batch, s.getTime(), 600 , linePos);
				Assets.font.draw(batch, s.getDate(), 900 , linePos);
			}
		}
		
		batch.end();
		
	}
	//second called
	@Override
	public void resize(int width, int height) {
		
		System.out.println("resize");
		
		if (backgroundTexture == null) {
			backgroundTexture = new Texture("data/scoreboardScreen.png");
		}
		
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
		
		Image image = new Image(backgroundTexture);
		
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
		
		stage.addActor(image);
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
		
		Tween.registerAccessor(SpriteBatch.class, new SpriteTween());
		
		manager = new TweenManager();

		TweenCallback cb = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				tweenCompleted();
			}
		};

		Tween.to(batch, SpriteTween.ALPHA, 0f).target(0)
				.ease(TweenEquations.easeInQuad).repeatYoyo(1000, 5f)
				.setCallback(cb).setCallbackTriggers(TweenCallback.START)
				.start(manager);
		
	}
	
	private void tweenCompleted() {
		levelCounter++;
		if (levelCounter > scores.size()) {
			levelCounter = 1;	
		}
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