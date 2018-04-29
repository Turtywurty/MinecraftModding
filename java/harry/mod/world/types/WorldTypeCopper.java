package harry.mod.world.types;

import harry.mod.init.BiomeInit;
import harry.mod.world.types.biome.provider.BiomeProviderMultiple;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;

public class WorldTypeCopper extends WorldType
{
	public WorldTypeCopper() 
	{
		super("Copper");
	}
	
	@Override
	public BiomeProvider getBiomeProvider(World world) 
	{
		BiomeProvider provider = new BiomeProviderSingle(BiomeInit.COPPER);
		return provider;
	}
}
