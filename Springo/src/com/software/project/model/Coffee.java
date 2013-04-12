package com.software.project.model;

import com.badlogic.gdx.math.Vector2;

public class Coffee extends Entity{

	private boolean visible = true;
	
	public Coffee(Vector2 position) {
		super(position, 65, 90);
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}