package de.zannagh.armorhider.net;

import de.zannagh.armorhider.resources.PlayerConfig;
import de.zannagh.armorhider.resources.ServerConfigStore;
import net.minecraft.server.MinecraftServer;

import java.util.UUID;

public final class ServerRuntime {
    public static ServerConfigStore store;
    public static MinecraftServer server;

    public static void init(MinecraftServer s) {
        server = s;
        store = ServerConfigStore.open();
    }
    public static PlayerConfig get(UUID id) { return store.get(id); }
    public static void put(UUID id, PlayerConfig c) { store.put(id, c); }
    public static void flushLater() { server.execute(store::save); }
}
