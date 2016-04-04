package net.bukkitdev.cex;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class CEXCommandListener implements Listener {

	@EventHandler
	public void onCommandPreprocess(ServerCommandEvent e){
		System.out.println(e.getCommand());
		onCommand(e, e.getSender(), e.getCommand());
	}
	
	@EventHandler
	public void onCommandPreprocess(PlayerCommandPreprocessEvent e){
		onCommand(e, e.getPlayer(), e.getMessage());
	}
	
	public void onCommand(Cancellable canc, CommandSender sender, String message){
		
		
//		if(message.startsWith("/")){
//			message = message.substring(1);
//			System.out.println("corrected message. now:" + message);
//		}
		
		if(!message.startsWith("/"))
			message = "/" + message;
			
		String[] p = message.split(" ");
		
		for(CEXer cexer : CEX.EXECUTORS){
			System.out.println("Iterating!");
			for(CEXMethod cexm : cexer.getMethods()){
				System.out.println("Iteraring2!");
				if(cexm.reactsTo(sender, p)){
					canc.setCancelled(true);
					cexm.react(sender, p);
					return;
				}
			}
			
		}
		
	}
	
}
