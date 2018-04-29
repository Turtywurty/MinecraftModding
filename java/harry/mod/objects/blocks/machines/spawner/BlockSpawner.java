package harry.mod.objects.blocks.machines.spawner;

import harry.mod.objects.blocks.BlockRedstoneMachine;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;

public class BlockSpawner extends BlockRedstoneMachine {

	public BlockSpawner(String name, Material material) {
		super(name, material);
		this.usesTE = false;
	}

	@Override
	protected boolean acceptsRedstonePower(EnumFacing facing) {
		return facing == EnumFacing.EAST || facing == EnumFacing.WEST;
	}

	@Override
	protected void execute() {
		System.out.println("Got me some Redstone power!!");
		
	}

}
