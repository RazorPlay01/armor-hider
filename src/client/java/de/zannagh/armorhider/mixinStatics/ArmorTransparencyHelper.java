package de.zannagh.armorhider.mixinStatics;

import de.zannagh.armorhider.resources.ArmorModificationInfo;

import java.util.UUID;

public final class ArmorTransparencyHelper {
    private static final ThreadLocal<ArmorModificationInfo> currentArmorSlot = ThreadLocal.withInitial(() -> null);
    private static final ThreadLocal<UUID> currentPlayerUuid = ThreadLocal.withInitial(() -> null);

    public static void setCurrentSlotInfo(ArmorModificationInfo slotInfo) {
        currentArmorSlot.set(slotInfo);
    }

    public static ArmorModificationInfo getCurrentSlotInfo() {
        return currentArmorSlot.get();
    }

    public static void clearCurrentArmorSlot() {
        currentArmorSlot.remove();
    }

    public static void setCurrentPlayerUuid(UUID uuid) {
        currentPlayerUuid.set(uuid);
    }

    public static UUID getCurrentPlayerUuid() {
        return currentPlayerUuid.get();
    }

    public static void clearCurrentPlayerContext() {
        currentPlayerUuid.remove();
    }
}
