package io.github.masyumero.mekavaritia.common.item;

import io.github.masyumero.mekavaritia.api.tier.MAAlloyTier;
import net.minecraft.world.item.Item;

public class ItemMAAlloy extends Item {

    private final MAAlloyTier tier;

    public ItemMAAlloy(MAAlloyTier tier, Properties properties) {
        super(properties);
        this.tier = tier;
    }

//    @NotNull
//    @Override
//    public InteractionResult useOn(UseOnContext context) {
//        Player player = context.getPlayer();
//        if (player != null && MekanismConfig.general.transmitterAlloyUpgrade.get()) {
//            Level world = context.getLevel();
//            BlockPos pos = context.getClickedPos();
//            BlockEntity tile = WorldUtils.getTileEntity(world, pos);
//            LazyOptional<IAlloyInteraction> capability = CapabilityUtils.getCapability(tile, Capabilities.ALLOY_INTERACTION, context.getClickedFace());
//            if (capability.isPresent()) {
//                if (!world.isClientSide) {
//                    capability.orElseThrow(MekanismUtils.MISSING_CAP_ERROR).onAlloyInteraction(player, context.getItemInHand(), tier);
//                }
//                return InteractionResult.sidedSuccess(world.isClientSide);
//            }
//        }
//        return InteractionResult.PASS;
//    }

    public MAAlloyTier getMATier() {
        return tier;
    }
}