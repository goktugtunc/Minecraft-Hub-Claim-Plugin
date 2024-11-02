package com.gotunc.hubClaimPlugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class HubClaimBlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        // Your code here
        Player player = event.getPlayer();
        if (player.hasPermission("hubclaim.break") || player.isOp()) {
            return;
        }
        if (HubClaimPlugin.isHubClaim(event.getBlock().getChunk()))
        {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + (HubClaimPlugin.getPlayerLanguage(player.getName()).equalsIgnoreCase("TR") ? "Hubdaki blokları kırmak için yetkiniz yok!" : "You don't have permission to break blocks here!"));
        }
    }

}