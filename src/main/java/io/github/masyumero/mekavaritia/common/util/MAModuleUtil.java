package io.github.masyumero.mekavaritia.common.util;

import mekanism.api.gear.ICustomModule;
import mekanism.api.gear.IModule;
import mekanism.api.gear.IModuleHelper;
import mekanism.api.providers.IModuleDataProvider;
import mekanism.common.content.gear.Module;
import mekanism.common.content.gear.ModuleHelper;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MAModuleUtil {

    public static <MODULE extends ICustomModule<MODULE>> @Nullable IModule<MODULE> getModule(ItemStack stack, IModuleDataProvider<MODULE> typeProvider) {
        return IModuleHelper.INSTANCE.load(stack, typeProvider);
    }

    public static List<Module<?>> getModules(ItemStack stack) {
        return ModuleHelper.get().loadAll(stack);
    }
}
