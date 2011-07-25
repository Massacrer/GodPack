package me.Massacrer.GodPack;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class GPPlayerListener extends PlayerListener {
	private GodPack plugin = null;

	public GPPlayerListener (GodPack plugin) {
		this.plugin  = plugin;
	}
	public void onPlayerJoin(PlayerJoinEvent event) {
		plugin.registerPlayer(event.getPlayer());
	}
	public void onPlayerQuit(PlayerQuitEvent event) {
		plugin.registerPlayer(event.getPlayer());
	}
	public void onPlayerKick(PlayerKickEvent event) {
		plugin.registerPlayer(event.getPlayer());
	}
 
}
