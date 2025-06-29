package com.justnothing.noteblock_extension;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Environment(EnvType.CLIENT)
public class ClientNoteBlockExtension implements ClientModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("NoteBlockExtensionMain");

    @Override
    public void onInitializeClient() {
        LOGGER.info("NoteBlock Extension client module initialized");

    }
}