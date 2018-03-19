package harry.mod.init;

import harry.mod.util.handlers.ConfigHandler;
import harry.mod.world.dimension.DimensionLibrary;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class DimensionInit 
{
	public static final DimensionType COPPER = DimensionType.register("Copper", "_copper", ConfigHandler.DIMENSION_COPPER_ID, DimensionLibrary.class, false);
	
	public static void registerDimensions()
	{
		DimensionManager.registerDimension(ConfigHandler.DIMENSION_COPPER_ID, COPPER);
	}
}
