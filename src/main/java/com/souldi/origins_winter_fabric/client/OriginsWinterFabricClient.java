package com.souldi.origins_winter_fabric.client;

import net.fabricmc.api.ClientModInitializer;

/** GeckoLib 4 no longer needs explicit renderer‑registration because we supply a RenderProvider in the item itself. */
public final class OriginsWinterFabricClient implements ClientModInitializer {
    @Override public void onInitializeClient() {
        // nothing to do – but file kept so you can add key‑bindings etc. later
    }
}