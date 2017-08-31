package com.toasted.voyager;

public abstract class Event {
	public Tile location;
	public boolean triggered = false;
	public Event(Tile t){
		this.location = t;
	}
	public abstract void activate(Ship s, Map m);
}
