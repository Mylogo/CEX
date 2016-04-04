package net.bukkitdev.cex;

import java.util.ArrayList;

import net.bukkitdev.cex.expression.Results;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CEX extends JavaPlugin {
	
	public static final ArrayList<CEXer> EXECUTORS = new ArrayList<>();
	
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(new CEXCommandListener(), this);
		CEX.registerExecutor(this);
	}
	
	public static void registerExecutor(Object o){
		try {
			CEXer cexer = new CEXer(o);
			EXECUTORS.add(cexer);
		} catch (WrongParameterException e) {
			System.out.println(" === FATAL ERROR: Error while registering executor");
			e.printStackTrace();
		}
	}
	
	
	
	@Command(format="/punish <constant=player> <player> <double>")
	public void onPunishPlayer(CommandSender sender, Results res){
		Player toPunish = res.getPlayer(1);
		double damage = res.getDouble(2);
		toPunish.damage(damage);
		toPunish.sendMessage("You were punished! (" +damage/2 + " hearts)");
	}
	
	@Command(format="/punish <rest>")
	public void onCommandFallback(CommandSender sender, Results res){
		sender.sendMessage("Correct usage: /punish player <playername> <value>");
	}
	
}
