package com.justnothing.noteblock_extension;

import net.minecraft.block.Block;

public class LowerNoteBlock extends CustomNoteBlock {
    public LowerNoteBlock(Block.Settings settings) {
        super(settings, -24); // -24 半音 (低两个八度)
    }
}