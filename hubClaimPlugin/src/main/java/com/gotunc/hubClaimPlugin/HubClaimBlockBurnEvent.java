package com.gotunc.hubClaimPlugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;

public class HubClaimBlockBurnEvent implements Listener {

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        // Your code here
        if (HubClaimPlugin.isHubClaimList(event.getBlock().getChunk()))
            event.setCancelled(true);
    }

}