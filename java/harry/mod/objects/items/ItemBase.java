package harry.mod.objects.items;

import harry.mod.Main;
import harry.mod.init.ItemInit;
import harry.mod.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item
{
	public ItemBase(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.TUTORIAL);
		
		ItemInit.ITEMS.add(this);
	}

}
