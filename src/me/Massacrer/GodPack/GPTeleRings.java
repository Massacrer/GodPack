package me.Massacrer.GodPack;

import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class GPTeleRings {
	private GodPack plugin = null;
	private World world = null;
	private Location loc = null;
	private ArrayList<Block> affectedBlocks = new ArrayList<Block>();
	
	public GPTeleRings(World world, Location loc, GodPack plugin) {
		this.world = world;
		this.loc = loc;
		this.plugin = plugin;
	}
	
	public void pillarDestructor() {
		determineBlocks();
		createPillars();
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				destroyPillars();
			}
		}, 20L);
	}
	
	private void determineBlocks() {
		Block currentBlock = null;
		// north blocks
		for (int i = 0; i < 3; i++) {
			currentBlock = world.getBlockAt(loc.getBlockX(), loc.getBlockY()
					+ i, loc.getBlockZ() + 1);
			if (isEmpty(currentBlock)) {
				affectedBlocks.add(currentBlock);
			}
		}
		// south blocks
		for (int i = 0; i < 3; i++) {
			currentBlock = world.getBlockAt(loc.getBlockX(), loc.getBlockY()
					+ i, loc.getBlockZ() - 1);
			if (isEmpty(currentBlock)) {
				affectedBlocks.add(currentBlock);
			}
		}
		// east blocks
		for (int i = 0; i < 3; i++) {
			currentBlock = world.getBlockAt(loc.getBlockX() + 1,
					loc.getBlockY() + i, loc.getBlockZ());
			if (isEmpty(currentBlock)) {
				affectedBlocks.add(currentBlock);
			}
		}
		// west blocks
		for (int i = 0; i < 3; i++) {
			currentBlock = world.getBlockAt(loc.getBlockX() - 1,
					loc.getBlockY() + i, loc.getBlockZ());
			if (isEmpty(currentBlock)) {
				affectedBlocks.add(currentBlock);
			}
		}
		// top block
		currentBlock = world.getBlockAt(loc.getBlockX(), loc.getBlockY() + 2,
				loc.getBlockZ());
		if (isEmpty(currentBlock)) {
			affectedBlocks.add(currentBlock);
		}
	} // End of determineBlocks code
	
	private void createPillars() {
		for (Block block : affectedBlocks) {
			block.setType(Material.GLOWSTONE);
		}
	}
	
	private void destroyPillars() {
		for (Block block : affectedBlocks) {
			block.setTypeId(0);
		}
	}
	
	private boolean isEmpty(Block block) {
		if (block.getTypeId() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
