
package de.zannagh.armorhider;

import java.util.UUID;

public class PlayerConfig {
    
    public static PlayerConfig FromPacket(double helmet, double chest, double legs, double boots, String uuid, String playerName){
        return new PlayerConfig(helmet, chest, legs, boots, uuid, playerName);
    }
    public PlayerConfig(double helmet, double chest, double legs, double boots, String uuid, String name){
        helmetTransparency = helmet;
        chestTransparency = chest;
        legsTransparency = legs;
        bootsTransparency = boots;
        playerId = UUID.fromString(uuid);
        playerName = name;
    }
    public PlayerConfig(UUID uuid, String name) {
        playerId = uuid;
        playerName = name;
    }
    public double helmetTransparency = 0.0;
    public double chestTransparency = 0.0;
    public double legsTransparency = 0.90;
    public double bootsTransparency = 0.0;
    public UUID playerId;
    public String playerName;
    public static PlayerConfig defaults(UUID playerId, String playerName) { return new PlayerConfig(playerId, playerName); }
}