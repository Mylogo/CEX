package net.bukkitdev.cex.expression;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Results {

	private final List<Result> results = new ArrayList<>();
	
	public void addResult(Result r){
		this.results.add(r);
	}
	
	public float getFloat(int index){
		return results.get(index).getFloat(); 
	}
	
	public Double getDouble(int index){
		return results.get(index).getDouble(); 
	}
	
	public Player getPlayer(int index){
		return results.get(index).getPlayer(); 
	}
	
	public int getInt(int index){
		return results.get(index).getInt(); 
	}
	
	public String getString(int index){
		return results.get(index).getString(); 
	}
	
	public long getLong(int index){
		return results.get(index).getLong();
	}
	
	
	public int size(){
		return results.size();
	}
	
	public List<Result> results(){
		return results;
	}
	
}
