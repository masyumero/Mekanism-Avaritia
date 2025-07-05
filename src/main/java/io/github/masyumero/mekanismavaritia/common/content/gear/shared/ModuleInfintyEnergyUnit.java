package io.github.masyumero.mekanismavaritia.common.content.gear.shared;

import mekanism.api.annotations.ParametersAreNotNullByDefault;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.gear.ICustomModule;
import mekanism.api.gear.IModule;
import mekanism.api.math.FloatingLong;
import net.minecraft.world.entity.player.Player;

@ParametersAreNotNullByDefault
public class ModuleInfintyEnergyUnit implements ICustomModule<ModuleInfintyEnergyUnit> {

    @Override
    public void tickServer(IModule<ModuleInfintyEnergyUnit> module, Player player) {
        IEnergyContainer energyContainer = module.getEnergyContainer();
        if (!energyContainer.getEnergy().equals(FloatingLong.MAX_VALUE)) {
            energyContainer.setEnergy(FloatingLong.MAX_VALUE);
        }
    }

    @Override
    public void onAdded(IModule<ModuleInfintyEnergyUnit> module, boolean first) {
        IEnergyContainer energyContainer = module.getEnergyContainer();
        energyContainer.setEnergy(FloatingLong.MAX_VALUE);
    }

    @Override
    public void onRemoved(IModule<ModuleInfintyEnergyUnit> module, boolean last) {
        IEnergyContainer energyContainer = module.getEnergyContainer();
        if (energyContainer != null) {
            energyContainer.setEnergy(energyContainer.getEnergy().min(energyContainer.getMaxEnergy()));
        }
    }
}