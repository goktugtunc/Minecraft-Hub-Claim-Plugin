package com.gotunc.hubClaimPlugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class HubClaimSpawnEntityEvent implements Listener {

    @EventHandler
    public void onSpawnEntityEvent(EntitySpawnEvent event) {
        // Your code here
        if (HubClaimPlugin.isHubClaim(event.getLocation().getChunk()))
            event.setCancelled(true);
    }
}