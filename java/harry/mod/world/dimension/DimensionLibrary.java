package harry.mod.world.dimension;

import harry.mod.init.BiomeInit;
import harry.mod.init.DimensionInit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;

public class DimensionLibrary extends WorldProvider
{
	public DimensionLibrary() 
	{
		this.biomeProvider = new BiomeProviderSingle(BiomeInit.COPPER);
		this.hasSkyLight = false;
	}
	
	@Override
	public DimensionType getDimensionType() 
	{
		return DimensionInit.COPPER;
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() 
	{
		return new ChunkGeneratorNetherTemplate(world, true, world.getSeed());
	}
	
	@Override
	public boolean canRespawnHere() 
	{
		return false;
	}
	
	@Override
	public boolean isSurfaceWorld() 
	{
		return false;
	}
}
