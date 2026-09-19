package io.github.masyumero.mekavaritia.common.integration.mekaf.regisrty;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.api.tier.IMATier;
import io.github.masyumero.mekavaritia.common.block.attribute.MAAttributeTier;
import io.github.masyumero.mekavaritia.common.integration.mekaf.block.prefab.BlockMAAdvancedFactory;
import io.github.masyumero.mekavaritia.common.integration.mekaf.content.blocktype.MAAdvancedFactory;
import io.github.masyumero.mekavaritia.common.integration.mekaf.item.block.machine.ItemBlockMAAdvancedFactory;
import io.github.masyumero.mekavaritia.common.integration.mekaf.tile.factory.base.TileEntityMAAdvancedFactoryBase;
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

public class MAAdvancedFactoryBlocks {

    private MAAdvancedFactoryBlocks() {}

    public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister(MekanismAvaritia.MODID);

    private static final Table<MAFactoryTier, AdvancedFactoryType, BlockRegistryObject<BlockMAAdvancedFactory<?>, ItemBlockMAAdvancedFactory>> FACTORIES = HashBasedTable.create();

    static {
        for (MAFactoryTier tier : MAEnumUtils.MA_FACTORY_TIERS) {
            for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
                FACTORIES.put(tier, type, registerFactory(MAAdvancedFactoryBlockTypes.getMAAdvancedFactory(tier, type)));
            }
        }
    }

    private static <TILE extends TileEntityMAAdvancedFactoryBase<?>> BlockRegistryObject<BlockMAAdvancedFactory<?>, ItemBlockMAAdvancedFactory> registerFactory(MAAdvancedFactory<TILE> type) {
        return registerTieredBlock(type, "_" + type.getAdvancedFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockMAAdvancedFactory<>(type), ItemBlockMAAdvancedFactory::new);
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
    public static BlockRegistryObject<BlockMAAdvancedFactory<?>, ItemBlockMAAdvancedFactory> getMAAdvancedFactory(@NotNull MAFactoryTier tier, @NotNull AdvancedFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static BlockRegistryObject<BlockMAAdvancedFactory<?>, ItemBlockMAAdvancedFactory>[] getMAAdvancedFactoryBlocks() {
        return FACTORIES.values().toArray(new BlockRegistryObject[0]);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
