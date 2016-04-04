package net.bukkitdev.cex;

import java.util.ArrayList;

import net.bukkitdev.cex.expression.Result;
import net.bukkitdev.cex.expression.Results;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CEX extends JavaPlugin {
	
	public static final ArrayList<CEXer> EXECUTORS = new ArrayList<>();
	
	public void onEnable(){
		
		Bukkit.getServer().getPluginManager().registerEvents(new CEXCommandListener(), this);
		registerExecutor(this);
	}
	
	public static void registerExecutor(Object o){
		try {
			CEXer cexer = new CEXer(o);
			EXECUTORS.add(cexer);
		} catch (WrongParameterException e) {
			System.out.println(" ===FATAL ERROR: Error while registering executor");
			e.printStackTrace();
		}
	}
	
}
