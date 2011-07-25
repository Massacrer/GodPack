package me.Massacrer.GodPack;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.ProjectileHitEvent;

class GPEntityHandler extends EntityListener {
	protected GodPack plugin;
	protected GPLadderHandler ladderHandler;
	
	GPEntityHandler(GodPack plugin) {
		this.plugin = plugin;
		this.ladderHandler = new GPLadderHandler(plugin);
	}
	
	public void onProjectileHit(ProjectileHitEvent event) {
		LivingEntity entity = ((Projectile) event.getEntity()).getShooter();
		if(!(entity instanceof Player)) return;
		if(!(event.getEntity() instanceof Arrow)) return;
		Player player = (Player) entity;
		if(!(plugin.hmPlayerConfigs.get(player) instanceof GPPlayerConfig)) player.sendMessage("You have no GPack config");
		if(plugin.hmPlayerConfigs.get(player).ladderArrowMode == true) {
			ladderHandler.createLadder(event);
		}
	}
}