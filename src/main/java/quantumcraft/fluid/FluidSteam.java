package quantumcraft.fluid;

import net.minecraftforge.fluids.Fluid;
import quantumcraft.core.QuantumCraft;

public class FluidSteam extends Fluid{
    public static final FluidSteam fluid = new FluidSteam();

    public FluidSteam() {
        super("Steam");
        setUnlocalizedName("Steam");
        setGaseous(true);
        QuantumCraft.logHandler.debugPrint("Icon for FluidSteam " + getIcon());
    }
}
