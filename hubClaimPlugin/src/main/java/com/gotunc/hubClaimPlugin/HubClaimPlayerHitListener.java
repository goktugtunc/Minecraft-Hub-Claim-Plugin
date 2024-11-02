package com.gotunc.hubClaimPlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class HubClaimPlayerHitListener implements Listener {

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        // Your code here
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player damager = (Player) event.getDamager();
            Player damaged = (Player) event.getEntity();
            if (damager.hasPermission("hubclaim.hit") || damager.isOp()) {
                return;
            }
            if (HubClaimPlugin.isHubClaim(damaged.getLocation().getChunk())) {
                event.setCancelled(true);
                damager.sendMessage(HubClaimPlugin.getPlayerLanguage(damager.getName()).equalsIgnoreCase("TR") ? "Bu oyuncuya vurmak için yetkiniz yok!" : "You don't have permission to hit this player!");
            }
        }
    }
}