package com.toasted.voyager;

public class EventKraken extends Event{

	public EventKraken(Tile t) {
		super(t);
	}

	public void activate(Ship s, Map m) {
		System.out.println("Game Over man");
	}

}
