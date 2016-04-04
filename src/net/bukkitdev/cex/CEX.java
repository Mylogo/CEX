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
	
	@Command(format="/sucks <player>")
	public void test(CommandSender sender, Results res){
		res.getPlayer(0).sendMessage("you suck!");
	}
	
	@Command(format="/sucks <string>")
	public void fallback(CommandSender sender, Results res){
		sender.sendMessage("The right syntax is: /sucks <player>");
	}
	
	@Command(format="/sucks <ANY>")
	public void anyMethod(CommandSender sender, Results res){
		for(Result r : res.results()){
			sender.sendMessage(r.getString());
		}
	}
	
}
