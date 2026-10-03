package io.github.masyumero.mekavaritia.common.capabilities.fluid.item;

import io.github.masyumero.mekavaritia.common.tier.MAFTTier;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.fluid.IExtendedFluidTank;
import mekanism.common.capabilities.fluid.BasicFluidTank;
import mekanism.common.capabilities.fluid.VariableCapacityFluidTank;
import mekanism.common.capabilities.fluid.item.ItemStackMekanismFluidHandler;

import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

@NothingNullByDefault
public class MARateLimitFluidHandler extends ItemStackMekanismFluidHandler {

    public static MARateLimitFluidHandler create(IntSupplier rate, IntSupplier capacity) {
        return create(rate, capacity, BasicFluidTank.alwaysTrueBi, BasicFluidTank.alwaysTrueBi, BasicFluidTank.alwaysTrue);
    }

    public static MARateLimitFluidHandler create(IntSupplier rate, IntSupplier capacity, BiPredicate<@NotNull FluidStack, @NotNull AutomationType> canMAct,
                                                    BiPredicate<@NotNull FluidStack, @NotNull AutomationType> canInsert, Predicate<@NotNull FluidStack> isValid) {
        Objects.requireNonNull(rate, "Rate supplier cannot be null");
        Objects.requireNonNull(capacity, "Capacity supplier cannot be null");
        Objects.requireNonNull(canMAct, "MAction validity check cannot be null");
        Objects.requireNonNull(canInsert, "Insertion validity check cannot be null");
        Objects.requireNonNull(isValid, "Gas validity check cannot be null");
        return new MARateLimitFluidHandler(listener -> new RateLimitFluidTank(rate, capacity, canMAct, canInsert, isValid, listener));
    }

    public static MARateLimitFluidHandler create(MAFTTier tier) {
        Objects.requireNonNull(tier, "Fluid tank tier cannot be null");
        return new MARateLimitFluidHandler(listener -> new FluidTankRateLimitFluidTank(tier, listener));
    }

    private final IExtendedFluidTank tank;

    private MARateLimitFluidHandler(Function<IContentsListener, IExtendedFluidTank> tankProvider) {
        tank = tankProvider.apply(this);
    }

    @Override
    protected List<IExtendedFluidTank> getInitialTanks() {
        return Collections.singletonList(tank);
    }

    public static class RateLimitFluidTank extends VariableCapacityFluidTank {

        private final IntSupplier rate;

        public RateLimitFluidTank(IntSupplier rate, IntSupplier capacity, @Nullable IContentsListener listener) {
            this(rate, capacity, alwaysTrueBi, alwaysTrueBi, alwaysTrue, listener);
        }

        public RateLimitFluidTank(IntSupplier rate, IntSupplier capacity, BiPredicate<@NotNull FluidStack, @NotNull AutomationType> canMAct,
                                  BiPredicate<@NotNull FluidStack, @NotNull AutomationType> canInsert, Predicate<@NotNull FluidStack> isValid, @Nullable IContentsListener listener) {
            super(capacity, canMAct, canInsert, isValid, listener);
            this.rate = rate;
        }

        @Override
        protected int getRate(@Nullable AutomationType automationType) {
            // Allow unknown or manual interaction to bypass rate limit for the item
            return automationType == null || automationType == AutomationType.MANUAL ? super.getRate(automationType) : rate.getAsInt();
        }
    }

    private static class FluidTankRateLimitFluidTank extends RateLimitFluidTank {

        private final boolean isCreative;

        private FluidTankRateLimitFluidTank(MAFTTier tier, @Nullable IContentsListener listener) {
            super(tier::getOutput, tier::getStorage, BasicFluidTank.alwaysTrueBi, BasicFluidTank.alwaysTrueBi, BasicFluidTank.alwaysTrue, listener);
            // isCreative = tier == MAFluidTankTier.CREATIVE;
            isCreative = false;
        }

        @Override
        public FluidStack insert(FluidStack stack, Action action, AutomationType automationType) {
            return super.insert(stack, action.combine(!isCreative), automationType);
        }

        @Override
        public FluidStack extract(int amount, Action action, AutomationType automationType) {
            return super.extract(amount, action.combine(!isCreative), automationType);
        }

        /**
         * {@inheritDoc}
         *
         * Note: We are only patching {@link #setStackSize(int, Action)}, as both {@link #growStack(int, Action)} and
         * {@link #shrinkStack(int, Action)} are wrapped
         * through this method.
         */
        @Override
        public int setStackSize(int amount, Action action) {
            return super.setStackSize(amount, action.combine(!isCreative));
        }
    }
}