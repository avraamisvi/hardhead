package com.pyx.games.hardhead.core;

public class Timer {

	private static long initialTime;
	private static long last = 0;

	public static void start() {
		initialTime = System.currentTimeMillis();
	}

	
	public static long getTimePassed() {
		last = System.currentTimeMillis() - initialTime;
		initialTime = System.currentTimeMillis();
		return last;
	}
	
	public static long getLast() {
		return last;
	}
}
