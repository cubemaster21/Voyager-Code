package com.toasted.voyager;

import java.util.Random;
import java.util.Stack;

public class MapGenerator {
	public static Map generateMap(int size){
		long startTime = System.currentTimeMillis();
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
		long timeToGenerate = System.currentTimeMillis() - startTime;
		System.out.println("Time to generate: " + timeToGenerate / 1000.0 + " seconds");
		System.out.println("Map has "  + map.islandCount + " islands");
		return map;
	}
	private static void detectIslands(Map m){
		Stack<Tile> lands = new Stack<Tile>();
		for(int i = 0;i < m.size;i++){
			for(int j = 0;j < m.size;j++){
				Tile t = m.getTile(i, j);
				if(!t.land || t.parentIsland != null) continue; // if tile isn't water OR it already belongs to an island
				Island newIsland = new Island();
				m.islandCount++;
				lands.push(t);
				Tile z;
				while(!lands.isEmpty()){
					System.out.println(lands.size());
					z = lands.pop();
					newIsland.add(z);
					
					for(int e = -1;e < 2;e++){
						for(int f = -1;f < 2;f++){
							if(e == f) continue;
							Tile neighbor = m.getTile(z.x + e, z.y + f);
							if(neighbor.land && neighbor.parentIsland == null){
								lands.push(neighbor);
							}
						}
					}
				}
			}
		}
	}
}
