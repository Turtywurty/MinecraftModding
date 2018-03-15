package harry.mod.objects.items.food;

import harry.mod.Main;
import harry.mod.init.ItemInit;
import harry.mod.util.interfaces.IHasModel;
import net.minecraft.item.ItemFood;

public class ItemCustomFood extends ItemFood implements IHasModel
{
	public ItemCustomFood(String name, int amount, boolean isWolfFood) 
	{
		super(amount, isWolfFood);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.TUTORIAL);
		
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
