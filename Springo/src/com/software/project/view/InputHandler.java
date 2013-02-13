package com.software.project.view;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.software.project.model.Springo;

public class InputHandler implements InputProcessor{

	World world;
	Springo springo;
	
	public InputHandler(World world) {
		this.world = world;	
	}
	
	@Override
	public boolean keyDown(int keycode) {
		springo = world.getSpringo();
		switch(keycode) {
			case Keys.W:
				springo.getVelocity().y = 1;
				break;
			case Keys.S:
				springo.getVelocity().y = -1;
				break;
			case Keys.A:
				springo.getVelocity().x = -1;
				break;
			case Keys.D:
				springo.getVelocity().x = 1;
				break;
			default:
				break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		springo = world.getSpringo();
		switch(keycode) {
			case Keys.W:
				if (springo.getVelocity().y == 1)
				springo.getVelocity().y = 0;
				break;
			case Keys.S:
				if (springo.getVelocity().y == -1)
				springo.getVelocity().y = 0;
				break;
			case Keys.A:
				if (springo.getVelocity().x == -1)
				springo.getVelocity().x = 0;
				break;
			case Keys.D:
				if (springo.getVelocity().x == 1)
				springo.getVelocity().x = 0;
				break;
			default:
				break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
		
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
		
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
		
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
		
	}

}
