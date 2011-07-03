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
	
	void mainLoop(Location src, Location dst) {
		for (int i = 0; i < 2; i++) {
			Layer srcLayer = new Layer();
			srcLayer.Start(src);
			Layer dstLayer = new Layer();
			dstLayer.Start(dst);
			
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

class Layer {
	void Start (Location loc) {
		int x = (int) loc.getBlockX();
		int y = (int) loc.getBlockY();
		int z = (int) loc.getBlockZ();
		World world = loc.getWorld();
		North = world.getBlockAt(x,y,z);
		South = world.getBlockAt(x,y,z);
		East = world.getBlockAt(x,y,z);
		West = world.getBlockAt(x,y,z);
	}
	Block North = null;
	Block South = null;
	Block East = null;
	Block West = null;
}