package harry.mod.objects.blocks;

import java.util.HashMap;

import harry.mod.objects.blocks.tile.TileRedstoneMachine;
import harry.mod.util.helpers.EnumHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockRedstoneMachine extends BlockBase{

	HashMap<EnumFacing, Boolean> REDSTONE_ACCECPTANCE =  new HashMap<>(6);
	protected boolean usesTE = false;
	
	public BlockRedstoneMachine(String name, Material material) {
		super(name, material);
		initRedstoneSides();
	}
	
	protected abstract boolean acceptsRedstonePower(EnumFacing facing);

	private void initRedstoneSides(){
		for(EnumFacing facing : EnumFacing.VALUES) {
			this.REDSTONE_ACCECPTANCE.put(facing, this.acceptsRedstonePower(facing));
		}
	}
	
	protected abstract void execute();
	
	protected boolean canExecute(World world, IBlockState state) {
		return true;
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		boolean canExecute = this.canExecute(worldIn, state) && this.acceptsRedstonePower(EnumHelper.getFacingFromOffset(pos, fromPos));
		
		if(canExecute) {
			if(worldIn.getRedstonePower(pos, null) < 0) {
				if(this.usesTE) {
					TileEntity te = worldIn.getTileEntity(pos);
					if(te instanceof TileRedstoneMachine) {
						((TileRedstoneMachine) te).execute();
					}
				} else this.execute();
			}
		}
	}
}
