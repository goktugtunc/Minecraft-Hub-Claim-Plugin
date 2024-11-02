package com.gotunc.hubClaimPlugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class HubClaimPlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // Your code here
        Player player = event.getPlayer();
        if (player.hasPermission("hubclaim.interact") || player.isOp()) {
            return;
        }
        if (HubClaimPlugin.isHubClaim(event.getClickedBlock().getChunk()))
        {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + (HubClaimPlugin.getPlayerLanguage(player.getName()).equalsIgnoreCase("TR") ? "Hubda blok etkileşimi için yetkiniz yok!" : "You don't have permission to interact with blocks here!"));
        }
    }

}