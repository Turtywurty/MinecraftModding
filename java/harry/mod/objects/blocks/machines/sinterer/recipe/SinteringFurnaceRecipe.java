package harry.mod.objects.blocks.machines.sinterer.recipe;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import it.unimi.dsi.fastutil.Arrays;
import net.minecraft.item.ItemStack;
import scala.collection.Iterator;

public class SinteringFurnaceRecipe {
	ItemStack[] INPUTS = new ItemStack[2];
	private final ItemStack OUTPUT;
	private final float XP;

	public SinteringFurnaceRecipe(@Nonnull ItemStack[] inputs, ItemStack output, float experience)
			throws IllegalArgumentException {
		if (inputs.length != 2)
			throw new IllegalArgumentException("You should give two inputs!!",
					new IllegalArgumentException(inputs.toString()));
		this.INPUTS[0] = inputs[0];
		this.INPUTS[1] = inputs[1];
		this.OUTPUT = output;
		this.XP = experience;
	}

	public ItemStack[] getInputs() {
		return INPUTS;
	}

	public ItemStack getOutput() {
		return OUTPUT;
	}

	public float getExperience() {
		return XP;
	}
	
	public boolean matches(ItemStack[] inputs) {
		int index = 0;
		boolean flag = inputs.length == 2;
		for (ItemStack stack : inputs) {
			if(flag) {
				flag = (stack.getItem() == this.INPUTS[index].getItem() ? stack.getCount() <= this.INPUTS[index].getCount() : false); 
			}
		}
		return flag;
	}

}
