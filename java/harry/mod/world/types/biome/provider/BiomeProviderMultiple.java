package harry.mod.world.types.biome.provider;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.storage.WorldInfo;

public class BiomeProviderMultiple extends BiomeProvider {

	public BiomeProviderMultiple(Biome... biomes) {
		super();
		this.allowedBiomes = Lists.newArrayList(biomes);
	}

	public BiomeProviderMultiple(WorldInfo info) {
		super(info);
	}

}
