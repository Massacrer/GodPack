package me.Massacrer.GodPack;

import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GodPack extends JavaPlugin {
	Logger log = Logger.getLogger("Minecraft");
	PluginManager pluginmanager = null;
	GPPlayerListener playerListener = new GPPlayerListener(this);
	GPEntityHandler entityHandler = new GPEntityHandler(this);
	GPBlockListener blockListener = new GPBlockListener(this);
	HashMap<Player, GPPlayerConfig> hmPlayerConfigs = new HashMap<Player, GPPlayerConfig>();
	
	boolean debug = false;
	
	public void onDisable() {
		entityHandler.ladderHandler.removeLadders();
		log.info("The God Pack is now inactive");
	}
	
	public void onEnable() {
		log.info("The God Pack is now active");
		pluginmanager = getServer().getPluginManager();
		pluginmanager.registerEvent(Event.Type.PLAYER_INTERACT, playerListener,
				Priority.Normal, this);
		pluginmanager.registerEvent(Event.Type.PROJECTILE_HIT, entityHandler,
				Priority.Normal, this);
		pluginmanager.registerEvent(Event.Type.PLAYER_JOIN, playerListener,
				Priority.Normal, this);
		pluginmanager.registerEvent(Event.Type.PLAYER_QUIT, playerListener,
				Priority.Normal, this);
		pluginmanager.registerEvent(Event.Type.PLAYER_KICK, playerListener,
				Priority.Normal, this);
		pluginmanager.registerEvent(Event.Type.BLOCK_DAMAGE, blockListener,
				Priority.Normal, this);
		for (Player player : getServer().getOnlinePlayers())
			registerPlayer(player);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("This command can only be used by a player");
			return true;
		}
		Player player = (Player) sender;
		if (!(commandLabel).equalsIgnoreCase("gpack")) {
			return true;
		}
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("spawn")) {
				Location spawn = player.getWorld().getSpawnLocation();
				while (spawn.getBlock().getType() != Material.AIR) {
					spawn = player
							.getWorld()
							.getBlockAt(spawn.getBlockX(),
									spawn.getBlockY() + 1, spawn.getBlockZ())
							.getLocation();
				}
				double x = spawn.getX();
				double y = spawn.getY();
				double z = spawn.getZ();
				x += 0.5;
				z += 0.5;
				Location dest = new Location(player.getWorld(), x, y, z);
				teleport(player, dest);
				return true;
			}
			if (args[0].equalsIgnoreCase("debug")) {
				this.debug = !this.debug;
				return true;
			}
			if (args[0].equalsIgnoreCase("test")) {

			}
			if (args[0].equalsIgnoreCase("ladder")) {
				this.hmPlayerConfigs.get(player).toggleLadderArrowMode();
			}
		}
		return true;
	}
	
	void registerPlayer(Player player) {
		hmPlayerConfigs.put(player, new GPPlayerConfig(player, this));
	}
	
	boolean hasConfig(Player player) {
		return hmPlayerConfigs.containsKey(player);
	}
	
	void teleport(Player player, Location dst) {
		GPTeleRings playerRing = new GPTeleRings(player.getWorld(),
				player.getLocation(), this);
		GPTeleRings dstRing = new GPTeleRings(player.getWorld(), dst, this);
		player.teleport(dst);
		if (debug)
			log.info("GP: player " + player.getName() + "teleported.");
	}
}