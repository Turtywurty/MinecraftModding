package harry.mod.objects.blocks.machines.miner.te;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityMiner extends TileEntity implements ITickable {

	int x, y, z, tick;
	boolean initalized = false;

	@Override
	public void update() {
		System.out.println("Ticking!!" + tick);
		if (!initalized)
			init();
		
		tick++;
		if(tick == 40) {
			tick = 0;
			if(y > 10)
			execute();
		}
	}
	
	private void execute() {
		int index=0;
		Block[] blocksRemoved= new Block[9];
		for(int x = 0; x < 3; x++) {
			for(int z = 0; z < 3; z++) {
				BlockPos posToBreak = new BlockPos(this.x + x, this.y, this.z + z);
				blocksRemoved[index] = this.world.getBlockState(posToBreak).getBlock();
				world.setBlockToAir(posToBreak);
				index++;
			}
		}
		
		for(Block block : blocksRemoved) {
			if(!world.isRemote)
			InventoryHelper.spawnItemStack(this.world, this.x + 1, this.pos.getY() + 2, this.z + 1, new ItemStack(block));
		}
		this.y--;
	}

	private void init() {
		initalized = true;
		x = this.pos.getX() - 1;
		y = this.pos.getY() - 1;
		z = this.pos.getZ() - 1;
		tick = 0;
	}

}
