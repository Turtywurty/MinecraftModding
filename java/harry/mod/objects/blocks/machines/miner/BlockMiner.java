/**
 * 
 */
package harry.mod.objects.blocks.machines.miner;

import harry.mod.objects.blocks.BlockBase;
import harry.mod.objects.blocks.machines.miner.te.TileEntityMiner;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Ewan Arends
 *
 */
public class BlockMiner extends BlockBase implements ITileEntityProvider{

	/**
	 * @param name
	 * @param material
	 */
	public BlockMiner(String name, Material material) {
		super(name, material);
		setHardness(1.0f);
		setHarvestLevel("pickaxe", 1);
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityMiner();
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityMiner();
	}

}
