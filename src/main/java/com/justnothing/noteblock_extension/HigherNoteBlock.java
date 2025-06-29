package com.justnothing.noteblock_extension;

import net.minecraft.block.Block;

public class HigherNoteBlock extends CustomNoteBlock {
    public HigherNoteBlock(Block.Settings settings) {
        super(settings, 24); // +24 半音 (高两个八度)
    }
}