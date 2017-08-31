package com.toasted.voyager;

public class Tile {
	public int x;
	public int y;
	public boolean land;
	public boolean discovered = false;
	public Island parentIsland;
	//event
	public Event event = null;
	public Tile(int x, int y){
		this.x = x;
		this.y = y;
	}
	public boolean hasEvent(){
		return event != null;
	}
	public Event getEvent(){
		return event;
	}
}
