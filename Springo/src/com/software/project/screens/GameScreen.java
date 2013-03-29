package com.software.project.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.software.project.SpringoGame;
import com.software.project.model.IcyPlatform;
import com.software.project.utils.Assets;
import com.software.project.utils.OverlapTester;
import com.software.project.utils.Settings;
import com.software.project.view.SummerWorldRenderer;
import com.software.project.view.WinterWorldRenderer;
import com.software.project.view.World;
import com.software.project.view.WorldRenderer;

public class GameScreen implements Screen {
	
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 4;
	static final int GAME_COMPLETED = 5;
	static final int GAME_USER_REQUESTED = 6;
	static final String USER_REQUIRED = "USER_REQUIRED";
	
	float counter = 0;
	SpringoGame game;
	World world;
	WorldRenderer renderer;
	SpriteBatch batcher;
	int state;
	OrthographicCamera guiCam;
	Vector3 touchPoint;
	Rectangle pauseBounds;
	Rectangle resumeBounds;
	Rectangle quitBounds;
	int lastScore;
	String levelString;
	Stage stage;
	TextField txtUserName;
	
	public GameScreen(SpringoGame game) {
		this.game = game;
		world = new World(game);
		
		state = GAME_READY;
		guiCam = new OrthographicCamera(320, 480);
		guiCam.position.set(320 / 40, 480 / 40, 0);
		touchPoint = new Vector3();
		batcher = new SpriteBatch();
		
		
		pauseBounds = new Rectangle(140, 200, 30, 64);
		resumeBounds = new Rectangle(-120, 60, 300, 60);
		quitBounds = new Rectangle(-60, 0, 140, 60);
		lastScore = 0;
		
		levelString = "Level: " + game.level;
		renderer = new SummerWorldRenderer(batcher, world);
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}
	
	public void draw (float deltaTime) {
		GLCommon gl = Gdx.gl;
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		renderer.render();

		guiCam.update();
		
		batcher.setProjectionMatrix(guiCam.combined);
		batcher.enableBlending();
		batcher.begin();
		switch (state) {
		case GAME_READY:
			presentReady();
			break;
		case GAME_RUNNING:
			presentRunning(deltaTime);
			break;
		case GAME_PAUSED:
			presentPaused();
			break;
		case GAME_LEVEL_END:
			presentLevelEnd();
			break;
		case GAME_COMPLETED:
			presentGameCompleted();
			break;			
		case GAME_OVER:
			presentGameOver();
			break;
		}
		batcher.end();
	}
	
	private void presentReady () {
		batcher.draw(Assets.ready, -50, 15, 130, 50);
		levelString = "Level: " + game.level;
	}

	private void presentRunning (float delta) {
		batcher.draw(Assets.pause, 140, 200, 30, 64);
		Assets.font.draw(batcher, levelString, - 145, 240);
		
		counter = counter + delta;
		String counterOut = String.valueOf(counter);
		
		Assets.font.draw(batcher, "Time: " + counterOut.substring(0, counterOut.indexOf('.') + 2), 0, 240);
		
	}

	private void presentPaused () {
		batcher.draw(Assets.pauseMenu, -60, 0, 140, 120);
		Assets.font.draw(batcher, levelString, -145, 240);
	}

	private void presentLevelEnd () {
		
		final String counterOut = String.valueOf(counter).substring(0, String.valueOf(counter).indexOf('.') + 2);
		
		HttpRequest httpGet = new HttpRequest(HttpMethods.GET);
		httpGet.setUrl("http://daniloandradesp.appspot.com/scoreboard?cmd=LevelEnd&level=" + game.level + "&time=" + counterOut);
		
		Gdx.net.sendHttpRequest(httpGet, new HttpResponseListener() {
			public void handleHttpResponse(HttpResponse httpResponse) {
				String response = httpResponse.getResultAsString();
				
				if (response.equals(GAME_USER_REQUESTED)) { 
					state = GAME_USER_REQUESTED;
					Gdx.input.setInputProcessor(stage);
					stage.draw();					
				} else {
					Assets.font.draw(batcher, "Congratulations!", -90,  100);
					Assets.font.draw(batcher, "Your time was: " + counterOut, - 80,  40);
					Assets.font.draw(batcher, "Level played: " + game.level, -80 , 0);
					Assets.font.draw(batcher, "Click to continue!", -90 , -60);
				}
				
			}
			public void failed(Throwable t) {
				
			}
		});

	}
	
	private void presentGameCompleted () {
//		String topText = "congratulations ...";
//		String bottomText = "loading next level!";
//		Assets.font.draw(batcher, topText, - 140,  80);
//		Assets.font.draw(batcher, bottomText, -140 , 40);
		
		game.level = 1;
		state = GAME_READY;
		
		game.setScreen(new CongratulationsScreen(game));
	}

	private void presentGameOver () {
		batcher.draw(Assets.gameOver, -80, 0, 160, 96);
		float scoreWidth = Assets.font.getBounds(levelString).width;
		Assets.font.draw(batcher, levelString, 160 - scoreWidth / 2, 480 - 20);
	}

	@Override
	public void resize(int width, int height) {
		if (stage == null) {
			stage = new Stage(width, height, true);
		}
	
		mountStage();
	}
	
	private void mountStage() {
	
		final int fontScale = 2;
		stage.clear();
		
		Label lblCongratulations = new Label("Congratulations! A new record was achieved!", new LabelStyle(Assets.font, Color.WHITE));
		lblCongratulations.setX(150);
		lblCongratulations.setY(400);
		lblCongratulations.setFontScaleX(fontScale);
		lblCongratulations.setFontScaleY(fontScale);
		
		Label lblInstruction = new Label("Type name for the scoreboard:", new LabelStyle(Assets.font, Color.WHITE));
		lblInstruction.setX(150);
		lblInstruction.setY(300);
		lblInstruction.setFontScaleX(fontScale);
		lblInstruction.setFontScaleY(fontScale);
		
		TextFieldStyle textfieldStyle = new TextFieldStyle();
		textfieldStyle.font = Assets.font;
		textfieldStyle.fontColor = Color.WHITE;
		
		txtUserName = new TextField("", textfieldStyle);
		txtUserName.setX(150);
		txtUserName.setY(250);
		txtUserName.setWidth(250);
		txtUserName.setMaxLength(15);
		
		Label lblClickToContinue = new Label("After typing your name, click on the screen to continue", new LabelStyle(Assets.font, Color.WHITE));
		lblClickToContinue.setX(150);
		lblClickToContinue.setY(200);
		
		stage.addActor(lblCongratulations);
		stage.addActor(lblInstruction);
		stage.addActor(txtUserName);
		stage.addActor(lblClickToContinue);
		stage.setKeyboardFocus(txtUserName);
		
	}

	@Override
	public void show() {
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
		world.dispose();
		stage.dispose();
	}
	
	public void update (float deltaTime) {
		if (deltaTime > 0.1f) deltaTime = 0.1f;

		switch (state) {
		case GAME_READY:
			updateReady();
			break;
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		case GAME_PAUSED:
			updatePaused();
			break;
		case GAME_LEVEL_END:
			updateLevelEnd();
			break;
		case GAME_COMPLETED:
			updateGameCompleted();
			break;
		case GAME_OVER:
			updateGameOver();
			break;
		case GAME_USER_REQUESTED:
			updateUserRequested();
			break;
		}

	}
	
	private void updateReady () {
		if (Gdx.input.justTouched()) {
			state = GAME_RUNNING;
		}
	}

	private void updateRunning (float deltaTime) {
		if (Gdx.input.justTouched()) {
			
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (OverlapTester.pointInRectangle(pauseBounds, touchPoint.x, touchPoint.y)) {
				//Assets.playSound(Assets.clickSound);
				state = GAME_PAUSED;
				return;
			}
		}
		
		ApplicationType appType = Gdx.app.getType();
		
		// should work also with Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)
		if (appType == ApplicationType.Android || appType == ApplicationType.iOS) {
			world.update(deltaTime, Gdx.input.getAccelerometerX());
		} else {
			float accel = 0;
			if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) accel = 5f;
			if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) accel = -5f;
			world.update(deltaTime, accel);
		}
		if (world.score != lastScore) {
			lastScore = world.score;
			levelString = "SCORE: " + lastScore;
		}
		if (world.state == World.WORLD_STATE_NEXT_LEVEL) {
			state = GAME_LEVEL_END;
		}
		if (world.state == World.WORLD_STATE_GAME_COMPLETED) {
			state = GAME_COMPLETED;
		}
		if (world.state == World.WORLD_STATE_GAME_OVER) {
			state = GAME_OVER;
			if (lastScore >= Settings.highscores[4])
				levelString = "NEW HIGHSCORE: " + lastScore;
			else
				levelString = "SCORE: " + lastScore;
			Settings.addScore(lastScore);
			Settings.save();
		}
	}

	private void updatePaused () {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (OverlapTester.pointInRectangle(resumeBounds, touchPoint.x, touchPoint.y)) {
				//Assets.playSound(Assets.clickSound);
				state = GAME_RUNNING;
				return;
			}

			if (OverlapTester.pointInRectangle(quitBounds, touchPoint.x, touchPoint.y)) {
				//Assets.playSound(Assets.clickSound);
				game.setScreen(new MainMenu(game));
				return;
			}
		}
	}
	
	private void updateUserRequested() {
		
		if (Gdx.input.justTouched() && !txtUserName.getText().isEmpty()) {
		
			final String counterOut = String.valueOf(counter).substring(0, String.valueOf(counter).indexOf('.') + 2);
			HttpRequest httpGet = new HttpRequest(HttpMethods.GET);
			httpGet.setUrl("http://daniloandradesp.appspot.com/scoreboard?cmd=AddScore&level=" + game.level + "&time=" + counterOut + "&user=" + txtUserName.getText());
			
			Gdx.net.sendHttpRequest(httpGet, new HttpResponseListener() {
				public void handleHttpResponse(HttpResponse httpResponse) {
					
				}
				public void failed(Throwable t) {
					
				}
			});
			
			state = GAME_LEVEL_END;
			Gdx.input.setInputProcessor(null);
		
		}
	}

	private void updateLevelEnd () {
		if (Gdx.input.justTouched()) {
			game.level++;
			world = new World(game);
			if (world.getPlatforms().get(0) instanceof IcyPlatform) {
				renderer = new WinterWorldRenderer(batcher, world);	
			} else {
				renderer = new SummerWorldRenderer(batcher, world);
			}
			world.score = lastScore;
			state = GAME_READY;
			counter = 0;
		}
	}
	
	private void updateGameCompleted () {
		game.level = 1;
		state = GAME_READY;
	}
	
	private void updateGameOver () {
		if (Gdx.input.justTouched()) {
			game.setScreen(new MainMenu(game));
		}
	}

}