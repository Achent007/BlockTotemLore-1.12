package be.achent.blocktotemlore;

import be.achent.blocktotemlore.Commands.Commands;
import be.achent.blocktotemlore.Event.Event;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockTotemLore extends JavaPlugin {
  
  public Commands commands;
  
  private Messages messages;
  
  public static BlockTotemLore plugin;
  
  public void onEnable() {
    plugin = this;
    this.messages = new Messages();
    this.commands = new Commands();
    saveDefaultConfig();
    this.messages.saveDefaultConfig();
    this.commands.init();
    getServer().getPluginManager().registerEvents(new Event(), (Plugin)this);
      }  
  
  
  public static BlockTotemLore getInstance() {
    return plugin;
  }
  
  public String getMessage(String path) {
    return ChatColor.translateAlternateColorCodes('&', this.messages.get().getString(path));
  }
  
  public void reloadMessages() {
    this.messages.reload();
  }
  
  public void saveDefaultsMessages() {
    this.messages.saveDefaultConfig();
  }
  
  public static boolean is18() {
    String version;
    try {
      version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    } catch (ArrayIndexOutOfBoundsException e) {
      return false;
    } 
    return version.equals("v1_8_R3");
  }
  
  public static boolean is116() {
    String version;
    try {
      version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    } catch (ArrayIndexOutOfBoundsException e) {
      return false;
    } 
    return (Integer.parseInt(version.split("_")[1]) >= 16);
  }


  public static BlockTotemLore getPlugin() {
	return plugin;
  }

  public ArrayList<String> getBlockedLoreList() {
    return (ArrayList<String>)getConfig().getStringList("block-lore");
  }

}