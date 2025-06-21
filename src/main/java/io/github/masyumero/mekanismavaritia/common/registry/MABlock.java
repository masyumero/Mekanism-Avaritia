package io.github.masyumero.mekanismavaritia.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.mojang.logging.LogUtils;
import io.github.masyumero.mekanismavaritia.MekanismAvaritia;
import io.github.masyumero.mekanismavaritia.api.tier.IMATier;
import io.github.masyumero.mekanismavaritia.common.block.attribute.MAAttributeTier;
import io.github.masyumero.mekanismavaritia.common.block.prefab.BlockMAFactoryMachine;
import io.github.masyumero.mekanismavaritia.common.content.blocktype.MAFactory;
import io.github.masyumero.mekanismavaritia.common.content.blocktype.MAFactoryType;
import io.github.masyumero.mekanismavaritia.common.item.block.machine.ItemBlockMAFactory;
import io.github.masyumero.mekanismavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekanismavaritia.common.tile.factory.TileEntityMAFactory;
import io.github.masyumero.mekanismavaritia.common.util.MAEnumUtils;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class MABlock {

    private static final Logger LOGGER = LogUtils.getLogger();
    public static final BlockDeferredRegister BLOCK = new BlockDeferredRegister(MekanismAvaritia.MODID);

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(String tierName, String suffix, Supplier<? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        return BLOCK.register(tierName + suffix, blockSupplier, itemCreator);
    }

    private static final Table<MAFactoryTier, MAFactoryType, BlockRegistryObject<BlockMAFactoryMachine.BlockMAFactory<?>, ItemBlockMAFactory>> FACTORIES = HashBasedTable.create();

    static {
        // factories
        for (MAFactoryTier tier : MAEnumUtils.MA_FACTORY_TIERS) {
            for (MAFactoryType type : MAEnumUtils.MA_FACTORY_TYPES) {
                if (type == MAFactoryType.ALLOYING) {
                    if(ModList.get().isLoaded("evolvedmekanism")) {
                        FACTORIES.put(tier, type, registerFactory(MABlockType.getMAFactory(tier, type)));
                    }
                } else {
                    FACTORIES.put(tier, type, registerFactory(MABlockType.getMAFactory(tier, type)));
                }
            }
        }
    }

    private static <TILE extends TileEntityMAFactory<?>> BlockRegistryObject<BlockMAFactoryMachine.BlockMAFactory<?>, ItemBlockMAFactory> registerFactory(MAFactory<TILE> type) {
        IMATier tier = Objects.requireNonNull(type.get(MAAttributeTier.class)).tier();
        return registerTieredBlock(tier.getMATier().getLowerName(), "_" + type.getFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockMAFactoryMachine.BlockMAFactory<>(type), ItemBlockMAFactory::new);
    }

    public static BlockRegistryObject<BlockMAFactoryMachine.BlockMAFactory<?>, ItemBlockMAFactory>  getMAFactory(@NotNull MAFactoryTier tier, @NotNull MAFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    public static void register(IEventBus eventBus) {
        BLOCK.register(eventBus);
    }
}
