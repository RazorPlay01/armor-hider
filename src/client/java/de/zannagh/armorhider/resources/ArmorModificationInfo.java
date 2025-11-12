package de.zannagh.armorhider.resources;

import net.minecraft.entity.EquipmentSlot;

public class ArmorModificationInfo {
    private PlayerConfig playerConfig;
    private EquipmentSlot equipmentSlot;
    public ArmorModificationInfo(EquipmentSlot slot, PlayerConfig config){
        equipmentSlot = slot;
        playerConfig = config;
    }
    
    public double GetTransparency(){
        return switch (equipmentSlot) {
            case HEAD -> playerConfig.helmetTransparency;
            case CHEST -> playerConfig.chestTransparency;
            case LEGS -> playerConfig.legsTransparency;
            case FEET -> playerConfig.bootsTransparency;
            default -> 1.0;
        };
    }
    
    public boolean ShouldHide(){
        double transparency = switch (equipmentSlot) {
            case HEAD -> playerConfig.helmetTransparency;
            case CHEST -> playerConfig.chestTransparency;
            case LEGS -> playerConfig.legsTransparency;
            case FEET -> playerConfig.bootsTransparency;
            default -> 1.0;
        };
        return transparency < 0.1;
    }
    
    public boolean ShouldModify(){
        double transparency = switch (equipmentSlot) {
            case HEAD -> playerConfig.helmetTransparency;
            case CHEST -> playerConfig.chestTransparency;
            case LEGS -> playerConfig.legsTransparency;
            case FEET -> playerConfig.bootsTransparency;
            default -> 1.0;
        };
        return transparency < 0.95;
    }
    
     public String GetSlotName(){
        return switch (equipmentSlot) {
            case HEAD -> "head";
            case CHEST -> "chest";
            case LEGS -> "legs";
            case FEET -> "feet";
            default -> "none";
        };
     }
}
