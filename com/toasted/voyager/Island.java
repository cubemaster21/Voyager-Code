package com.toasted.voyager;

import java.util.ArrayList;

public class Island extends ArrayList<Tile>{
	public boolean add(Tile t){
		t.parentIsland = this;
		return super.add(t);
	}
}
