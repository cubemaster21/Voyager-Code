package com.toasted.voyager;

import java.awt.Point;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Voyager extends ApplicationAdapter implements InputProcessor{
	public static Graphics G; //literally only exists so I don't have to rewrite "Gdx.graphics" every other line
	SpriteBatch uiBatch;
	Texture img;
	OrthographicCamera uiCam;
	Ship ship;
	Map map;
	float tickSpeed = .25f;
	
	public static final int NORTH = 1,
							SOUTH = 2,
							EAST  = 3,
							WEST  = 4;
	
	@Override
	public void create () {
		G = Gdx.graphics;
		uiCam = new OrthographicCamera();
		uiCam.setToOrtho(false, G.getWidth(), G.getHeight());
		uiBatch = new SpriteBatch();
		uiBatch.setProjectionMatrix(uiCam.combined);
		img = new Texture("ship.png");
		map = MapGenerator.generateMap(1000);
		Gdx.input.setInputProcessor(this);
		
		ship = new Ship();
	}

	@Override
	public void render () {
		
		tickSpeed -= G.getDeltaTime();
		if(tickSpeed <= 0){
			tickSpeed = .25f;
			move(ship.heading);
		}
		
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		map.draw(uiBatch);
		uiBatch.begin();
		uiBatch.draw(img, ship.x * 5, ship.y * 5, 5, 5);
		uiBatch.end();
	}
	
	@Override
	public void dispose () {
		uiBatch.dispose();
		//img.dispose();
	}
	public void moveTo(Ship s, int x, int y){
		Point p = map.getMapCoordinates(x, y);
		if(map.isLand(p.x, p.y)){
			s.heading = 0;
			return;
		}
		
		ship.x = p.x;
		ship.y = p.y;
		Tile t = map.getTile(ship.x, ship.y);
		//discovery!
		for(int i = -s.visionRadius;i < s.visionRadius + 1;i++){
			for(int j = -s.visionRadius;j < s.visionRadius + 1;j++){
				Tile d = map.getTile(ship.x + i, ship.y + j);
				
				//island discovery?
				if(d.land && !d.discovered){
					if(!d.parentIsland.isSpotted()){
						Event e  = new EventIslandDiscovery(d);
						e.activate(ship, map);
					}
//					for(Tile z: d.parentIsland){
//						z.discovered = true;
//					}
				}
				
				d.discovered = true;
			}
		}
		if(t.hasEvent()){
			Event e = t.getEvent();
			if(!e.triggered){
				//do something
				e.activate(ship, map);
			}
		}
	}
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	public void move(int direction){
		switch(direction){
		case NORTH:
			moveTo(ship, ship.x, ship.y + 1);
			break;
		case SOUTH:
			moveTo(ship, ship.x, ship.y - 1);
			break;
		case WEST:
			moveTo(ship, ship.x - 1, ship.y);
			break;
		case EAST:
			moveTo(ship, ship.x + 1, ship.y);
			break;
		}
		//ship.heading = direction;
	}
	@Override
	public boolean keyUp(int keycode) {
		switch(keycode){
		case Keys.UP:
			ship.heading = NORTH;
//			move(NORTH);
			break;
		case Keys.DOWN:
			ship.heading = SOUTH;
//			move(SOUTH);
			break;
		case Keys.LEFT:
			ship.heading = WEST;
//			move(WEST);
			break;
		case Keys.RIGHT:
			ship.heading = EAST;
//			move(EAST);
			break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
