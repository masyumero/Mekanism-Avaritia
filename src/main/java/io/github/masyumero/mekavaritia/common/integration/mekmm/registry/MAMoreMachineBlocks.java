package io.github.masyumero.mekavaritia.common.integration.mekmm.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.api.tier.IMATier;
import io.github.masyumero.mekavaritia.common.block.attribute.MAAttributeTier;
import io.github.masyumero.mekavaritia.common.integration.mekmm.block.prefab.BlockMAMoreMachineFactory;
import io.github.masyumero.mekavaritia.common.integration.mekmm.content.blocktype.MAMoreMachineFactory;
import io.github.masyumero.mekavaritia.common.integration.mekmm.item.block.machine.ItemBlockMAMoreMachineFactory;
import io.github.masyumero.mekavaritia.common.integration.mekmm.tile.TileEntityMAMoreMachineFactory;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.util.MAEnumUtils;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class MAMoreMachineBlocks {

    private MAMoreMachineBlocks() {}

    public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister(MekanismAvaritia.MODID);

    private static final Table<MAFactoryTier, MoreMachineFactoryType, BlockRegistryObject<BlockMAMoreMachineFactory<?>, ItemBlockMAMoreMachineFactory>> FACTORIES = HashBasedTable.create();

    static {
        // factories
        for (MAFactoryTier tier : MAEnumUtils.MA_FACTORY_TIERS) {
            for (MoreMachineFactoryType type : MoreMachineEnumUtils.MM_FACTORY_TYPES) {
                FACTORIES.put(tier, type, registerFactory(MAMoreMachineBlockTypes.getMAMoreMachineFactory(tier, type)));
            }
        }
    }

    private static <TILE extends TileEntityMAMoreMachineFactory<?>> BlockRegistryObject<BlockMAMoreMachineFactory<?>, ItemBlockMAMoreMachineFactory> registerFactory(MAMoreMachineFactory<TILE> type) {
        return registerTieredBlock(type, "_" + type.getMoreMachineFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockMAMoreMachineFactory<>(type), ItemBlockMAMoreMachineFactory::new);
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(BlockType type, String suffix,
                                                                                                                      Supplier<? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        return registerTieredBlock(Objects.requireNonNull(type.get(MAAttributeTier.class)).tier(), suffix, blockSupplier, itemCreator);
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(IMATier tier, String suffix,
                                                                                                                      Supplier<? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        return BLOCKS.register(tier.getMATier().getLowerName() + suffix, blockSupplier, itemCreator);
    }

    /**
     * Retrieves a Factory with a defined tier and recipe type.
     *
     * @param tier - tier to add to the Factory
     * @param type - recipe type to add to the Factory
     *
     * @return factory with defined tier and recipe type
     */
    public static BlockRegistryObject<BlockMAMoreMachineFactory<?>, ItemBlockMAMoreMachineFactory> getMAMoreMachineFactory(@NotNull MAFactoryTier tier, @NotNull MoreMachineFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static BlockRegistryObject<BlockMAMoreMachineFactory<?>, ItemBlockMAMoreMachineFactory>[] getMAMoreMachineFactoryBlocks() {
        return FACTORIES.values().toArray(new BlockRegistryObject[0]);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
