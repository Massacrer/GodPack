package me.Massacrer.GodPack;

import org.bukkit.entity.Player;

public class GPPlayerConfig {
	GodPack plugin = null;
	Player player = null;
	boolean ladderArrowMode = false;
	GPLadder lastLadder = null;
	
	public GPPlayerConfig(Player player, GodPack plugin) {
		this.player = player;
		this.plugin = plugin;
	}
	
	void toggleLadderArrowMode() {
		ladderArrowMode = !ladderArrowMode;
		player.sendMessage(ladderArrowMode ? "Now in ladder arrow mode"
				: "Now in normal mode");
		if(!ladderArrowMode) plugin.entityHandler.ladderHandler.removeLadder(player);
	}
	
}