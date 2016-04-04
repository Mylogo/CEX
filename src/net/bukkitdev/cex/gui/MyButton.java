package net.bukkitdev.cex.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MyButton {

	protected ItemStack item;
	protected MyGUI gui;
	
	public MyButton(ItemStack item){
		this.item = item;
	}
	
	public void onClick(InventoryClickEvent e){}
	
	public boolean wasClicked(InventoryClickEvent e){
		return e.getCurrentItem() != null ? e.getCurrentItem().equals(item) && e.getRawSlot() < gui.inv.getSize() : false;
	}
	
}
