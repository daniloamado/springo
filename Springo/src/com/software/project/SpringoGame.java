package com.software.project;

import com.badlogic.gdx.Game;
import com.software.project.screens.GameScreen;
import com.software.project.utils.Assets;
import com.software.project.utils.Settings;

public class SpringoGame extends Game {

	public int level = 1;
	public String user = "Guest";

	@Override
	public void create() {
		Settings.load();
		Assets.load();
		//setScreen(new SplashScreen(this));
		//setScreen(new MainMenu(this));
		setScreen(new GameScreen(this));
		//setScreen(new AboutScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
	
}