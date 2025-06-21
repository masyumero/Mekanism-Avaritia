package io.github.masyumero.mekanismavaritia.common.block.attribute;

import io.github.masyumero.mekanismavaritia.api.tier.MATier;
import mekanism.common.block.states.BlockStateHelper;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class MAAttributeUpgradeable implements MAAttribute {
    private final Supplier<BlockRegistryObject<?, ?>> upgradeBlock;

    public MAAttributeUpgradeable(Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        this.upgradeBlock = upgradeBlock;
    }

    @NotNull
    public BlockState upgradeResult(@NotNull BlockState current, @NotNull MATier tier) {
        return BlockStateHelper.copyStateData(current, upgradeBlock.get());
    }
}
