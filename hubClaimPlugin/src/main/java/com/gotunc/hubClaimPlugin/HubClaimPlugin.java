package com.gotunc.hubClaimPlugin;

import net.md_5.bungee.api.chat.ScoreComponent;
import org.bukkit.Chunk;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public final class HubClaimPlugin extends JavaPlugin {

    public static Connection connection;
    public static List<ChunkClass> claimList = new ArrayList<>();
    private void connectDatabase() {
        // Your code here
        try {
            connection = DriverManager.getConnection("jdbc:mysql://xxx.xxx:xxx/xxx?useSSL=xxx", "xxx", "xxx");
            getLogger().info("Connected to database!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getPlayerLanguage(String playerName)
    {
        String query = "SELECT Language FROM PlayerInformations WHERE GameName = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, playerName);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return rs.getString("Language");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return "EN";
    }

    public static boolean isHubClaim(Chunk chunk) {
        String query = "SELECT * FROM HubClaims WHERE X = ? AND Z = ? AND WorldName = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, chunk.getX());
            statement.setInt(2, chunk.getZ());
            statement.setString(3, chunk.getWorld().getName());
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isHubClaimList(Chunk chunk) {
        for (ChunkClass claim : claimList)
            if (claim.equals(chunk))
                return true;
        return false;
    }

    private void getClaims()
    {
        // Your code here
        try
        {
            String query = "SELECT * FROM HubClaims";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                int X = rs.getInt("X");
                int Z = rs.getInt("Z");
                String WorldName = rs.getString("WorldName");
                ChunkClass claim = new ChunkClass(X, Z, WorldName);
                claimList.add(claim);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin enabled!");
        connectDatabase();
        getClaims();
        getServer().getPluginManager().registerEvents(new HubClaimBlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new HubClaimBlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new HubClaimPlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new HubClaimPlayerHitListener(), this);
        getServer().getPluginManager().registerEvents(new HubClaimSpawnEntityEvent(), this);
        getServer().getPluginManager().registerEvents(new HubClaimBlockIngitEvent(), this);
        getServer().getPluginManager().registerEvents(new HubClaimBlockBurnEvent(), this);
        getServer().getPluginManager().registerEvents(new HubClaimBlockExplodeEvent(), this);
        getServer().getPluginManager().registerEvents(new HubClaimBlockFromToEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin disabled!");
        if (connection != null) {
            try {
                connection.close();
                getLogger().info("Disconnected from database!");
            }
            catch (SQLException e) {
                e.printStackTrace();
                getLogger().info("Failed to disconnect from database!");
            }
        }
    }
}
