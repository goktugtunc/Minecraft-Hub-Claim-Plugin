package com.gotunc.hubClaimPlugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class HubClaimBlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        // Your code here
        Player player = event.getPlayer();
        if (player.hasPermission("hubclaim.place") || player.isOp()) {
            return;
        }
        if (HubClaimPlugin.isHubClaim(event.getBlock().getChunk()))
        {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + (HubClaimPlugin.getPlayerLanguage(player.getName()).equalsIgnoreCase("TR") ? "Hubda blok yerleştirmek için yetkiniz yok!" : "You don't have permission to place blocks here!"));
        }
    }

}