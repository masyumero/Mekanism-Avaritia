package io.github.masyumero.mekanismavaritia.common.util;

import mekanism.api.gear.ICustomModule;
import mekanism.api.gear.IModule;
import mekanism.api.gear.IModuleHelper;
import mekanism.api.providers.IModuleDataProvider;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class MAModuleUtil {

    public static <MODULE extends ICustomModule<MODULE>> @Nullable IModule<MODULE> getModule(ItemStack stack, IModuleDataProvider<MODULE> typeProvider) {
        return IModuleHelper.INSTANCE.load(stack, typeProvider);
    }

}
