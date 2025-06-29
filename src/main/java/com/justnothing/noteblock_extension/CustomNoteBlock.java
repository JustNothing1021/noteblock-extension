package com.justnothing.noteblock_extension;

import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Unique;


public abstract class CustomNoteBlock extends NoteBlock {
    @Unique
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger("noteblock-extension");

    protected final int pitchOffset;

    public CustomNoteBlock(Settings settings, int pitchOffset) {
        super(settings);
        this.pitchOffset = pitchOffset;
    }

    @Override
    public boolean onSyncedBlockEvent(BlockState state, World world, BlockPos pos, int type, int data) {
        try {
            // 事实证明，写代码最高效的办法是Ctrl+C和Ctrl+V
            int note = state.get(NoteBlock.NOTE);
            NoteBlockInstrument noteBlockInstrument = (NoteBlockInstrument)state.get(INSTRUMENT);
            float pitch = 1.0F;
            if (noteBlockInstrument.canBePitched()) {
                int i = (Integer)state.get(NOTE);
                pitch = (float)Math.pow((double)2.0F, (double)(note - 12 + this.pitchOffset) / (double)12.0F);
                world.addParticle(ParticleTypes.NOTE, (double)pos.getX() + (double)0.5F, (double)pos.getY() + 1.2, (double)pos.getZ() + (double)0.5F, (double)i / (double)24.0F, (double)0.0F, (double)0.0F);
            }
            RegistryEntry<SoundEvent> registryEntry;
            if (noteBlockInstrument.hasCustomSound()) {
                return false;
            } else {
                registryEntry = noteBlockInstrument.getSound();
            }
            world.playSound(
                    (PlayerEntity)null,
                    (double)pos.getX() + (double)0.5F, (double)pos.getY() + (double)0.5F, (double)pos.getZ() + (double)0.5F,
                    registryEntry, SoundCategory.RECORDS, 3.0F, pitch, world.random.nextLong());
            return true;

        } catch (Exception e) {
            LOGGER.info(e.toString());
            return super.onSyncedBlockEvent(state, world, pos, type, data);
        }
    }
}