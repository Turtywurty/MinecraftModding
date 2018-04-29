package harry.mod.objects.blocks.machines.generator;

import harry.mod.objects.blocks.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGenerator extends BlockBase {

	public BlockGenerator(String name, Material material) {
		super(name, material);
		this.setHarvestLevel("pickaxe", 1);
		this.setHardness(2.3f);
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return state.getBlock().hasTileEntity();
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileGenerator();
	}
}
