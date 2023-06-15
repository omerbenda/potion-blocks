package com.potionblocks.block;

import com.potionblocks.PotionBlocks;
import com.potionblocks.block.custom.PotionBlock;
import com.potionblocks.item.ModItems;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
  public static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, PotionBlocks.MOD_ID);

  public static final RegistryObject<PotionBlock> SPEED_POTION_BLOCK =
      registerBlock(
          "speed_potion_block",
          () ->
              new PotionBlock(
                  BlockBehaviour.Properties.of(Material.METAL)
                      .strength(6f)
                      .requiresCorrectToolForDrops(),
                  MobEffects.MOVEMENT_SPEED,
                  3000));

  private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
    RegistryObject<T> toReturn = BLOCKS.register(name, block);
    registerBlockItem(name, toReturn);

    return toReturn;
  }

  private static <T extends Block> RegistryObject<Item> registerBlockItem(
      String name, RegistryObject<T> block) {
    return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
  }

  public static void register(IEventBus eventBus) {
    BLOCKS.register(eventBus);
  }
}
