package com.justnothing.noteblock_extension.mixin;

import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundSystem;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(SoundSystem.class)
public class SoundSystemMixin {
    @Inject(
            method = "getAdjustedPitch",
            at = @At("RETURN"),
            cancellable = true
    )
    private void getAdjustedPitch(SoundInstance sound, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(MathHelper.clamp(sound.getPitch(), (float)0.0, (float)999.9));
    }
}
