package net.bukkitdev.cex.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e){
		
		MyGUI gui = MyGUI.getMyGUI((Player) e.getPlayer());
		
		if(gui != null){
			gui.onClose(e);
		}
		
	}
	
}
