package me.Massacrer.GodPack;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

class GPLadderHandler {
	static GodPack plugin;
	
	GPLadderHandler(GodPack plugin) {
		GPLadderHandler.plugin = plugin;
	}
	
	void createLadder(ProjectileHitEvent event) {
		Player player = (Player) ((Projectile) event.getEntity()).getShooter();
		removeLadder(player);
		Location loc = event.getEntity().getLocation();
		Vector vec = loc.getDirection();
		System.out.println("vector info: x: " + (Double) vec.getX() + ", y: "
				+ (Double) vec.getY() + ", z: " + (Double) vec.getZ());
		System.out.println("location info before vector operation:\nx: "
				+ (Double) loc.getX() + ", y: " + (Double) loc.getY() + ", z: "
				+ (Double) loc.getZ());
		loc.subtract(vec.getX() / 2, vec.getY() / 2, vec.getZ() / 2);
		System.out.println("location info after vector operation:\nx: "
				+ (Double) loc.getX() + ", y: " + (Double) loc.getY() + ", z: "
				+ (Double) loc.getZ());
		
		Block block = loc.getBlock();
		int height = 0;
		byte directionByte = getDirectionByte(loc);
		Material material = Material.LADDER;
		material.getNewData(directionByte);
		
		// getBlockRelative(directionByte, block).setTypeId(42);
		loc.getBlock().setTypeId(20);
		/*
		 * while (loc.getBlock().isEmpty() && (!(getBlockRelative(directionByte,
		 * loc).isEmpty()))) { loc.getBlock().setType(material);
		 * loc.getBlock().setData(directionByte); // Not sure if //
		 * Material.getNewData() // properly sets the // material data to apply
		 * to // the material, so we do it // manually here as well height++;
		 * loc = loc.subtract(0, 1, 0); }
		 * plugin.hmPlayerConfigs.get(player).lastLadder = new GPLadder(height,
		 * loc);
		 */
	}
	
	Block getBlockRelative(byte direction, Block block) {
		int x = block.getX();
		int y = block.getY();
		int z = block.getZ();
		switch (direction) {
			case 0x2:
				// west
				return block.getWorld().getBlockAt(x, y, z + 1);
			case 0x3:
				// east
				return block.getWorld().getBlockAt(x, y, z - 1);
			case 0x4:
				// north
				return block.getWorld().getBlockAt(x - 1, y, z);
			case 0x5:
				// south
				return block.getWorld().getBlockAt(x + 1, y, z);
		}
		System.out.println("Gpack: getBlockRelative defaulting");
		return block;
	}
	
	void removeLadder(Player player) {
		if (!(plugin.hasConfig(player)))
			return;
		if (plugin.hmPlayerConfigs.get(player).lastLadder == null)
			return;
		GPLadder ladder = plugin.hmPlayerConfigs.get(player).lastLadder;
		Location loc = ladder.top;
		for (int i = 0; i < ladder.height; i++) {
			if (loc.getBlock().getType() == Material.LADDER) {
				loc.getBlock().setTypeId(0);
			}
			loc.subtract(0, 1, 0);
		}
		plugin.hmPlayerConfigs.get(player).lastLadder = null;
	}
	
	byte getDirectionByte(Location loc) {
		
		double rotation = (loc.getYaw() - 90) % 360;
		byte data = (byte) 0x0;
		if (rotation < 0) {
			rotation += 360.0;
		}
		if (0 <= rotation && rotation < 45.0) {
			data = 0x5;
			// direction is North
		} else if (45.0 <= rotation && rotation < 135.0) {
			data = 0x3;
			// direction is East
		} else if (135.0 <= rotation && rotation < 225.0) {
			data = 0x5;
			// direction is South
		} else if (225.0 <= rotation && rotation < 315.0) {
			data = 0x2;
			// direction is West
		} else if (315.0 <= rotation && rotation < 360.0) {
			data = 0x4;
			// direction is North
		}
		return data;
	}
	
	void removeLadders() {
		for (Player p : plugin.hmPlayerConfigs.keySet()) {
			removeLadder(p);
		}
	}
}

class GPLadder {
	GPLadder(int h, Location l) {
		this.height = h;
		this.top = l;
	}
	
	int height = 0;
	Location top = null;
}