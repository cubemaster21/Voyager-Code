package com.toasted.voyager;

import java.util.Random;

public class MapGenerator {
	public static Map generateMap(int size){
		Map map = new Map();
		map.size = size;
		map.data = new Tile[size][size];
		Random r = new Random();
		
		for(int i = 0;i < size;i++){
			for(int j = 0;j < size;j++){
				map.data[i][j] = new Tile(i, j);
				map.data[i][j].land = r.nextFloat() < .35f;
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
		return map;
	}
}
