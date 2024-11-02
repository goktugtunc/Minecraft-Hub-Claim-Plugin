package com.gotunc.hubClaimPlugin;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;

public class HubClaimBlockExplodeEvent implements Listener {

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        // Your code here
        for (Block block : event.blockList()) {
            if (HubClaimPlugin.isHubClaimList(block.getChunk())) {
                event.setCancelled(true);
                break;
            }
        }
    }

}