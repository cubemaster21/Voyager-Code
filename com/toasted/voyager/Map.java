package com.toasted.voyager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Map {
	public Tile[][] data;
	public int size;
	private ShapeRenderer s = new ShapeRenderer();
	public boolean isLand(int x, int y){
		while(x < 0) x += size;
		while(y < 0) y += size;
		x %= size;
		y %= size;
		return data[x][y].land;
	}
	public void print(){
		for(int i = 0;i < size;i++){
			for(int j = 0;j < size;j++){
				System.out.print(isLand(j, i) ?  "O" : "~");
			}
			System.out.print("\n");
		}
	}
	public void draw(SpriteBatch b) {
		s.begin(ShapeType.Filled);
		
		for(int i = 0;i < size;i++){
			for(int j = 0;j < size;j++){
				
				s.setColor(data[i][j].land ? Color.GREEN : Color.BLUE);
				s.rect(i * 5, j * 5, 5, 5);
			}
		}
		s.end();
	}
}
