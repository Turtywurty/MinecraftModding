package harry.mod.init;

import harry.mod.world.dimension.library.DimensionGreatLibrary;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class DimensionInit
{
	public static final DimensionType LIBRARY = DimensionType.register("Library", "_library", 2, DimensionGreatLibrary.class, false);
	
	public static void registerDimensions()
	{
		DimensionManager.registerDimension(2, LIBRARY);
	}
}
