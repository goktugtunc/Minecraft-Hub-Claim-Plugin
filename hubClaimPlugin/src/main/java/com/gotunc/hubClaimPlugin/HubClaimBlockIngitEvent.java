package com.gotunc.hubClaimPlugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

public class HubClaimBlockIngitEvent implements Listener {

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        // Your code here
        try {
            if (event.getPlayer().isOp())
                return;
        }
        catch (NullPointerException e) {
            // Do nothing
        }
        if (HubClaimPlugin.isHubClaimList(event.getBlock().getChunk()))
            event.setCancelled(true);
    }

}