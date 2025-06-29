package com.justnothing.noteblock_extension;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;


import static com.justnothing.noteblock_extension.NoteBlockExtensionMain.MOD_ID;

class ModBlocks {

	public static final Block HIGH_OCTAVE_NOTE_BLOCK = new HigherNoteBlock(
			Block.Settings.copy(Blocks.NOTE_BLOCK)
					.sounds(BlockSoundGroup.WOOD)
					.strength(1.0f)
	);
	public static final Block LOW_OCTAVE_NOTE_BLOCK = new LowerNoteBlock(
			Block.Settings.copy(Blocks.NOTE_BLOCK)
					.sounds(BlockSoundGroup.WOOD)
					.strength(1.0f)
	);
	public static void registerBlocks() {
		Registry.register(
				Registries.BLOCK,
				Identifier.of(MOD_ID, "high_octave_note_block"),
				ModBlocks.HIGH_OCTAVE_NOTE_BLOCK
		);

		Registry.register(
				Registries.BLOCK,
				Identifier.of(MOD_ID, "low_octave_note_block"),
				ModBlocks.LOW_OCTAVE_NOTE_BLOCK
		);
	}

}

class ModItems {
	public static final BlockItem HIGH_OCTAVE_NOTE_BLOCK = new BlockItem(
			ModBlocks.HIGH_OCTAVE_NOTE_BLOCK,
			new Item.Settings()
	);
	public static final BlockItem LOW_OCTAVE_NOTE_BLOCK = new BlockItem(
			ModBlocks.LOW_OCTAVE_NOTE_BLOCK,
			new Item.Settings()
	);
	public static void registerItems() {
		Registry.register(
				Registries.ITEM,
				Identifier.of(MOD_ID, "high_octave_note_block"),
				HIGH_OCTAVE_NOTE_BLOCK
		);
		Registry.register(
				Registries.ITEM,
				Identifier.of(MOD_ID, "low_octave_note_block"),
				LOW_OCTAVE_NOTE_BLOCK
		);
	}
}

public class NoteBlockExtensionMain implements ModInitializer {
	public static final String MOD_ID = "noteblock-extension";
	public static final ItemGroup NOTEBLOCK_GROUP = (
			FabricItemGroup.builder()
			.icon(() -> new ItemStack(ModBlocks.HIGH_OCTAVE_NOTE_BLOCK))
			.displayName(Text.translatable("itemGroup.noteblock-extension.group"))
			.entries((context, entries) -> {
				entries.add(ModBlocks.HIGH_OCTAVE_NOTE_BLOCK);
				entries.add(ModBlocks.LOW_OCTAVE_NOTE_BLOCK);
			})
			.build());

	@Override
	public void onInitialize() {
		ModBlocks.registerBlocks();
		ModItems.registerItems();
		Registry.register(
				Registries.ITEM_GROUP,
				Identifier.of(MOD_ID, "noteblocks"),
				NOTEBLOCK_GROUP
		);

		RegistryKey<ItemGroup> redstoneGroupKey = RegistryKey.of(
				RegistryKeys.ITEM_GROUP, Identifier.of("redstone_blocks"));

		ItemGroupEvents.modifyEntriesEvent(redstoneGroupKey).register(
			entries -> {
				// 把这俩东西加在原版音符盒的后边
				entries.addAfter(
						Items.NOTE_BLOCK,
						ModBlocks.HIGH_OCTAVE_NOTE_BLOCK,
						ModBlocks.LOW_OCTAVE_NOTE_BLOCK
				);
			}
		);
	}
}