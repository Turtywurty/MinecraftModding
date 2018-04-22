package harry.mod.objects.blocks.machines.generator;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileGenerator extends TileEntity implements ITickable{

	int COOLDOWN = 0;
	int BURNTIME = 0;
	BlockPos ABOVE;
	EnergyStorage ENERGY = new EnergyStorage(400, 20, 20);
	boolean INITIALIZED = false;
	
	@Override
	public void update() {
		if(!INITIALIZED)
			initialize();
		if(!(this.ENERGY.getMaxEnergyStored() == this.ENERGY.getEnergyStored()))
		if(COOLDOWN == 0) {
		TileEntity above = world.getTileEntity(ABOVE);
		if(above.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN)) {
			IItemHandler itemHandler = above.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
			for(int i = 0; i < itemHandler.getSlots(); i++) {
				ItemStack stack = itemHandler.getStackInSlot(i);
				if(!stack.isEmpty() && COOLDOWN == 0) {
					i = ForgeEventFactory.getItemBurnTime(new ItemStack(stack.getItem(), 1, stack.getMetadata()));
					if(i > 0 ) {
						int i2 = itemHandler.extractItem(i, 1, false).getCount();
						BURNTIME = i * i2;
						COOLDOWN = 20 * i2;
					}
				}
			}
		}
		} else if(BURNTIME > 0) {
			world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX(), pos.getX(), pos.getZ(), Math.random() * 2, Math.random() * 3, Math.random() * 2);
			this.ENERGY.receiveEnergy(20, false);
			BURNTIME--;
		} else {
			COOLDOWN--;
		}
	}
	
	public void initialize() {
		ABOVE = this.pos.add(0,1,0);
		
	}
}
