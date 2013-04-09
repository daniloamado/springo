package com.software.project.tweenacessors;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteTween implements TweenAccessor<SpriteBatch>{
	
	public static final int ALPHA = 1;
	
	@Override
	public int getValues(SpriteBatch target, int tweenType, float[] returnValues) {
		switch(tweenType) {
		case ALPHA:
			returnValues[0] = target.getColor().a;
			return 1;
		default:
			return 0;
		}
		
	}

	@Override
	public void setValues(SpriteBatch target, int tweenType, float[] returnValues) {
		switch(tweenType){
		case ALPHA:
			target.setColor(1, 1, 1, returnValues[0]);
			break;
		}
	}
}