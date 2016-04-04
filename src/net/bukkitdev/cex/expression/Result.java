package net.bukkitdev.cex.expression;

import org.bukkit.entity.Player;

public class Result {

	private int intVal;
	private String stringVal;
	private Player playerVal;
	private double doubleVal;
	private float floatVal;
	private long longVal;
	
	public Result(int intVal) {
		super();
		this.intVal = intVal;
	}

	public Result(String stringVal) {
		super();
		this.stringVal = stringVal;
	}

	public Result(Player playerVal) {
		super();
		this.playerVal = playerVal;
	}

	public Result(double doubleVal) {
		super();
		this.doubleVal = doubleVal;
	}

	public Result(float floatVal) {
		super();
		this.floatVal = floatVal;
	}
	
	public Result(long longVal){
		super();
		this.longVal = longVal;
	}
	
	public int getInt() {
		return intVal;
	}

	public String getString() {
		return stringVal;
	}

	public Player getPlayer() {
		return playerVal;
	}

	public double getDouble() {
		return doubleVal;
	}

	public float getFloat() {
		return floatVal;
	}
	
	public long getLong(){
		return longVal;
	}
	
}
