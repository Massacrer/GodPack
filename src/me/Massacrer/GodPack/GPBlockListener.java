package me.Massacrer.GodPack;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;

public class GPBlockListener extends BlockListener {
	private GodPack plugin;
	
	public GPBlockListener(GodPack plugin) {
		this.plugin = plugin;
	}
	
	public void onBlockDamage(BlockDamageEvent event) {
		if (plugin.hmPlayerConfigs.get(event.getPlayer()).ladderArrowMode) {
			Block block = event.getBlock();
			System.out
					.println("Block damage registered. Block coordinates:\nx: "
							+ block.getX() + ", y: " + block.getY() + ", z: "
							+ block.getZ());
		}
	}
}
