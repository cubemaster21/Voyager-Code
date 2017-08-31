package com.toasted.voyager;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Voyager extends ApplicationAdapter {
	public static Graphics G; //literally only exists so I don't have to rewrite "Gdx.graphics" every other line
	SpriteBatch uiBatch;
	Texture img;
	OrthographicCamera uiCam;
	
	Map map;
	@Override
	public void create () {
		G = Gdx.graphics;
		uiCam = new OrthographicCamera();
		uiCam.setToOrtho(false, G.getWidth(), G.getHeight());
		uiBatch = new SpriteBatch();
		uiBatch.setProjectionMatrix(uiCam.combined);
		//img = new Texture("badlogic.jpg");
		map = MapGenerator.generateMap(100);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		uiBatch.begin();
		//uiBatch.draw(img, 0, 0);
		uiBatch.end();
		map.draw(uiBatch);
	}
	
	@Override
	public void dispose () {
		uiBatch.dispose();
		//img.dispose();
	}
}
