package net.bukkitdev.cex.gui;

import java.util.HashSet;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

abstract public class MyGUI {

	public static final HashSet<MyGUI> guis = new HashSet<>();
	
	protected Inventory inv;
	protected Player player;
	protected final HashSet<MyButton> buttons = new HashSet<>();
	
	public MyGUI(Player player){
		this.player = player;
		this.inv = createInventory();
		guis.add(this);
	}
	
	public static boolean isMyGUI(Inventory inv){
		for(MyGUI gui : guis){
			if(gui.inv.equals(inv))
				return true;
		}
		return false;
	}
	
	public static MyGUI getMyGUI(Inventory inv){
		for(MyGUI gui : guis){
			if(gui.inv.equals(inv))
				return gui;
		}
		return null;
	}
	
	public static MyGUI getMyGUI(Player p){
		for(MyGUI gui : guis){
			if(gui.player.getName().equals(p.getName()))
				return gui;
		}
		return null;
	}

	public void addButton(MyButton button, int pos){
		this.buttons.add(button);
		button.gui = this;
		inv.setItem(pos, button.item);
		
	}
	
	abstract public Inventory createInventory();
	
	public void onClick(InventoryClickEvent e){
		for(MyButton button : buttons){
			if(button.wasClicked(e)){
				button.onClick(e);
				return;
			}
				
		}
	}
	
	public void onClose(InventoryCloseEvent e){
		removeMe();
		System.out.println("Now removed! Having " + guis.size() + " items in guis!");
	}
	
	public void removeMe(){
		guis.remove(this);
	}
	
	public void openMe(){
		player.openInventory(inv);
	}
	
}
