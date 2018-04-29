package harry.mod.proxy;

import harry.mod.util.handlers.TileEntityHandler;
import net.minecraft.item.Item;

public class CommonProxy {
	public void registerItemRenderer(Item item, int meta, String id) {
	}

	public void registerVariantRenderer(Item item, int meta, String filename, String id) {
	}

	public void registerTileEntities() {
		TileEntityHandler.registerTileEntities();
	}

	public void Register(Object o) {
	}
}
