package dev.hugge123123.dupeclient;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DupeClient implements ClientModInitializer {
    public static final String MOD_ID = "dupeclient";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("DupeClient loaded for Minecraft 1.21.11.");
        LOGGER.info("Safe starter build: no server exploit, anti-cheat bypass, or ban-evasion features.");
    }
}
