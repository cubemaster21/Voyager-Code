package com.toasted.voyager;

public class EventIslandDiscovery extends Event{

	public EventIslandDiscovery(Tile t) {
		super(t);
	}

	@Override
	public void activate(Ship s, Map m) {
		triggered = true;
		System.out.println("new Island spotted");
	}

}
