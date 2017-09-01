package com.toasted.voyager;

import java.util.ArrayList;

public class Island extends ArrayList<Tile>{
	public String name;
	public boolean add(Tile t){
		t.parentIsland = this;
		return super.add(t);
	}
	public boolean isSpotted(){
		for(Tile t: this){
			if(t.discovered) return true;
		}
		return false;
	}
}
