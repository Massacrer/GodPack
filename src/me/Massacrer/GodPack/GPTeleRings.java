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
		Layer[] srcLayers = new Layer[3];
		Layer[] dstLayers = new Layer[3];
		for (int i = 0; i < 2; i++) {
			srcLayers[i] = new Layer(src);
			dstLayers[i] = new Layer(dst);
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
	Layer(Location loc) {
		// Define blocks here
	}
	Location North = null;
	Location South = null;
	Location East = null;
	Location West = null;
}