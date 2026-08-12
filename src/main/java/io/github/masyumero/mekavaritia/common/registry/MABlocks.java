package io.github.masyumero.mekavaritia.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.api.tier.IMATier;
import io.github.masyumero.mekavaritia.common.block.attribute.MAAttributeTier;
import io.github.masyumero.mekavaritia.common.block.prefab.BlockMAFactoryMachine;
import io.github.masyumero.mekavaritia.common.content.blocktype.MAFactory;
import io.github.masyumero.mekavaritia.common.content.blocktype.MAMachine;
import io.github.masyumero.mekavaritia.common.item.block.machine.ItemBlockMAFactory;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.tile.factory.TileEntityMAFactory;
import io.github.masyumero.mekavaritia.common.tile.machine.TileEntityElectricNeutronCollector;
import io.github.masyumero.mekavaritia.common.util.MAEnumUtils;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.item.block.machine.ItemBlockMachine;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.util.EnumUtils;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class MABlocks {

    public static final BlockDeferredRegister BLOCK = new BlockDeferredRegister(MekanismAvaritia.MODID);

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(String tierName, String suffix, Supplier<? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        return BLOCK.register(tierName + suffix, blockSupplier, itemCreator);
    }

    private static final Table<MAFactoryTier, FactoryType, BlockRegistryObject<BlockMAFactoryMachine.BlockMAFactory<?>, ItemBlockMAFactory>> FACTORIES = HashBasedTable.create();

    static {
        // factories
        for (MAFactoryTier tier : MAEnumUtils.MA_FACTORY_TIERS) {
            for (FactoryType type : EnumUtils.FACTORY_TYPES) {
                FACTORIES.put(tier, type, registerFactory(MABlockTypes.getMAFactory(tier, type)));
            }
        }
    }

    public static final BlockRegistryObject<BlockTile.BlockTileModel<TileEntityElectricNeutronCollector, MAMachine<TileEntityElectricNeutronCollector>>, ItemBlockMachine> ELECTRIC_NEUTRON_COLLECTOR = BLOCK.register("electric_neutron_collector", () -> new BlockTile.BlockTileModel<>(MABlockTypes.ELECTRIC_NEUTRON_COLLECTOR, properties -> properties.mapColor(MapColor.METAL)), ItemBlockMachine::new);

    private static <TILE extends TileEntityMAFactory<?>> BlockRegistryObject<BlockMAFactoryMachine.BlockMAFactory<?>, ItemBlockMAFactory> registerFactory(MAFactory<TILE> type) {
        IMATier tier = Objects.requireNonNull(type.get(MAAttributeTier.class)).tier();
        return registerTieredBlock(tier.getMATier().getLowerName(), "_" + type.getFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockMAFactoryMachine.BlockMAFactory<>(type), ItemBlockMAFactory::new);
    }

    public static BlockRegistryObject<BlockMAFactoryMachine.BlockMAFactory<?>, ItemBlockMAFactory>  getMAFactory(@NotNull MAFactoryTier tier, @NotNull FactoryType type) {
        return FACTORIES.get(tier, type);
    }

    public static void register(IEventBus eventBus) {
        BLOCK.register(eventBus);
    }
}
