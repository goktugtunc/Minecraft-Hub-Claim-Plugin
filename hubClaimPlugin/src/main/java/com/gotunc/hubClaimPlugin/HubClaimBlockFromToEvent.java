package com.gotunc.hubClaimPlugin;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class HubClaimBlockFromToEvent implements Listener {

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        // Your code here
        if (event.getBlock().getType().equals(Material.LAVA))
        {
            if (HubClaimPlugin.isHubClaimList(event.getToBlock().getChunk()))
                event.setCancelled(true);
        }
    }

}