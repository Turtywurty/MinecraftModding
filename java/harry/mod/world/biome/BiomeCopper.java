package harry.mod.world.biome;

import harry.mod.entity.EntityCentaur;
import harry.mod.init.BlockInit;
import harry.mod.objects.blocks.BlockPlank;
import harry.mod.util.handlers.EnumHandler;
import net.minecraft.world.biome.Biome;

public class BiomeCopper extends Biome 
{
	public BiomeCopper() 
	{
		super(new BiomeProperties("Copper").setBaseHeight(1.0F).setHeightVariation(1.0F).setRainDisabled().setTemperature(1.0F));
		
		topBlock = BlockInit.PLANKS.getDefaultState().withProperty(BlockPlank.VARIANT, EnumHandler.EnumType.COPPER);
		fillerBlock = BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockPlank.VARIANT, EnumHandler.EnumType.COPPER);	
		
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		
		this.spawnableCreatureList.add(new SpawnListEntry(EntityCentaur.class, 5, 1, 5));
	}
}
