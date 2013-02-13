package com.software.project;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Springo Game";
		cfg.useGL20 = true;
		cfg.width = 512;
		cfg.height = 288;
		
		new LwjglApplication(new SoftwareProject2Game(), cfg);
	}
}
