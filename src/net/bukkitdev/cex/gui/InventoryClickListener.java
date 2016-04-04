package net.bukkitdev.cex.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		
		MyGUI gui = MyGUI.getMyGUI((Player)e.getWhoClicked());
		
		ItemStack clicked = e.getCurrentItem();
		if(clicked == null){
			e.setCancelled(true);
			return;
		}
		
		if(gui != null){
			gui.onClick(e);
		}
		
		
	}
	
}
