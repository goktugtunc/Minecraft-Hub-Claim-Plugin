package com.gotunc.hubClaimPlugin;

import org.bukkit.Chunk;

public class ChunkClass {

    public int x;
    public int z;
    public String worldName;

    public ChunkClass(int x, int z, String worldName) {
        this.x = x;
        this.z = z;
        this.worldName = worldName;
    }

    public boolean equals(Chunk chunk) {
        if (chunk != null && chunk.getWorld().getName().equalsIgnoreCase(worldName) && chunk.getX() == x && chunk.getZ() == z)
            return true;
        return false;
    }

}