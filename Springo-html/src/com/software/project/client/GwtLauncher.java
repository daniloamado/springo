package com.software.project.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.software.project.SpringoGame;

public class GwtLauncher extends GwtApplication {
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(1280, 720);
		cfg.useDebugGL = true;
		cfg.fps = 65;
		return cfg;
	}

	@Override
	public ApplicationListener getApplicationListener () {
		return new SpringoGame();		
	}
}