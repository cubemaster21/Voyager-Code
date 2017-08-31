package com.toasted.voyager;

import java.util.Random;
import java.util.Stack;

public class MapGenerator {
	public static Map generateMap(int size){
		Map map = new Map();
		map.size = size;
		map.data = new Tile[size][size];
		Random r = new Random();
		
		for(int i = 0;i < size;i++){
			for(int j = 0;j < size;j++){
				map.data[i][j] = new Tile(i, j);
				map.data[i][j].land = r.nextFloat() < .25f;
			}
		}
		
		int iterations = 2;
		for(int z = 0;z < iterations;z++){
			for(int i = 0;i < size;i++){
				for(int j = 0;j < size;j++){
					int n = 0;
					if(map.isLand(i - 1, j)) n++;
					if(map.isLand(i + 1, j)) n++;
					if(map.isLand(i, j - 1)) n++;
					if(map.isLand(i, j + 1)) n++;
					if(n < 2){
						map.data[i][j].land = false;
					}
				}
			}
		}
		Tile t = null;
		while((t = map.data[r.nextInt(size)][r.nextInt(size)]).land){}
		t.event = new EventKraken(t);
		
		detectIslands(map);
		
		return map;
	}
	private static void detectIslands(Map m){
		Stack<Tile> lands = new Stack<Tile>();
		for(int i = 0;i < m.size;i++){
			for(int j = 0;j < m.size;j++){
				Tile t = m.getTile(i, j);
				if(!t.land || t.parentIsland != null) continue; // if tile isn't water OR it already belongs to an island
				Island newIsland = new Island();
				lands.push(t);
				Tile z;
				while(!lands.isEmpty()){
					z = lands.pop();
					newIsland.add(z);
					if(m.isLand(z.x - 1, z.y)) lands.push(m.getTile(z.x - 1, z.y));
					if(m.isLand(z.x + 1, z.y)) lands.push(m.getTile(z.x + 1, z.y));
					if(m.isLand(z.x, z.y - 1)) lands.push(m.getTile(z.x, z.y - 1));
					if(m.isLand(z.x, z.y + 1)) lands.push(m.getTile(z.x, z.y + 1));
				}
			}
		}
	}
}
