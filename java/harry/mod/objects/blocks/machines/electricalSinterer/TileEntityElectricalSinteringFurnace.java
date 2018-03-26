package harry.mod.objects.blocks.machines.electricalSinterer;

import static harry.mod.util.helpers.EnumHelper.getOffsetFacingWithProperty;
import static harry.mod.util.helpers.NBTHelper.*;
import harry.mod.objects.blocks.machines.sinterer.SinteringFurnaceRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.*;
import net.minecraftforge.items.*;

/**
 * @author Ewan Arends date : 26th march 2018
 * 
 *         This is the Tile Entity for the
 *         {@link BlockElectricalSinteringFurnace}.
 **/
public class TileEntityElectricalSinteringFurnace extends TileEntity implements ITickable {

	int PROCESS = 0;
	ItemStack PROCESSING;
	EnergyStorage ENERGY = new EnergyStorage(2000, 200, 20);
	Capability<IEnergyStorage> ENERGY_CAPABILITY = CapabilityEnergy.ENERGY;
	Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
	ItemStackHandler INPUT = new ItemStackHandler(2);
	ItemStackHandler OUTPUT = new ItemStackHandler(1);

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTBase ENERGY = ENERGY_CAPABILITY.writeNBT(this.ENERGY, null);
		NBTBase INPUT = ITEM_HANDLER_CAPABILITY.writeNBT(this.INPUT, null);
		NBTBase OUTPUT = ITEM_HANDLER_CAPABILITY.writeNBT(this.OUTPUT, null);
		compound.setTag("energy_storage", ENERGY);
		compound.setTag("input_inventory", INPUT);
		compound.setTag("output_inventory", OUTPUT);
		if(PROCESSING != null)
		compound.setTag("product", toNBT(PROCESSING));
		super.writeToNBT(compound);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		ENERGY_CAPABILITY.readNBT(ENERGY, null, compound.getTag("energy_storage"));
		ITEM_HANDLER_CAPABILITY.readNBT(INPUT, null, compound.getTag("input_inventory"));
		ITEM_HANDLER_CAPABILITY.readNBT(OUTPUT, null, compound.getTag("output_inventory"));
		if(compound.hasKey("product"))
			PROCESSING = (ItemStack) fromNBT((NBTTagCompound)compound.getTag("product"));
		super.readFromNBT(compound);
	}

	private EnumFacing getFacing() {
		return world.getBlockState(pos).getValue(BlockElectricalSinteringFurnace.FACING);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == ENERGY_CAPABILITY || capability == ITEM_HANDLER_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == ITEM_HANDLER_CAPABILITY) {
			return (T) (facing == getOffsetFacingWithProperty(EnumFacing.EAST, getFacing())
					|| facing == getOffsetFacingWithProperty(EnumFacing.WEST, getFacing()) ? INPUT
							: facing == EnumFacing.DOWN ? OUTPUT : null);
		} else if (capability == ENERGY_CAPABILITY) {
			return (T) ENERGY;
		}
		return null;
	}

	@Override
	public void update() {
		ItemStack[] inputs = new ItemStack[] { INPUT.getStackInSlot(0), INPUT.getStackInSlot(1) };

		// gets if enough energy is stored
		if (ENERGY.getEnergyStored() > 20) {

			// gets if something is smelting already
			if (PROCESSING != null && PROCESS > 0) {
				ENERGY.extractEnergy(20, false);
				PROCESS++;

				// after adding 1 to the process, gets if it's 100 (100%) and outputs the item.
				if (PROCESS == 100) {
					// outputs the item into the inventory
					OUTPUT.insertItem(0, PROCESSING, false);

					// resets everything for a new process and skips this tick
					PROCESSING = null;
					PROCESS = 0;
					return;
				}
				// if nothing was smelting already:
			} else {
				// gets if the inputs both aren't empty
				if (!inputs[0].isEmpty() && !inputs[1].isEmpty()) {
					SinteringFurnaceRecipes RECIPES = SinteringFurnaceRecipes.getInstance();

					// gets the result of the items in the furnace will give ItemStack.EMPTY if
					// theres no result.
					ItemStack output = RECIPES.getSinteringResult(inputs[0], inputs[1]);

					// gets if the output isn't empty, meaning that this recipe is registered.
					if (!output.isEmpty()) {
						// sets the processing stack to the result of the recipe, with a process of 1
						// (1%)
						PROCESSING = output;
						PROCESS++;

						// decreases the itemstacks in the input slots by 1, removing the items for the
						// recipe.
						inputs[0].shrink(1);
						inputs[1].shrink(1);
						INPUT.setStackInSlot(0, inputs[0]);
						INPUT.setStackInSlot(1, inputs[1]);

						// extracting 20 energy for in-machine process
						ENERGY.extractEnergy(20, false);
					}
				}
			}
		}
	}

}
