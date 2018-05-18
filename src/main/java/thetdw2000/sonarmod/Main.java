package thetdw2000.sonarmod;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thetdw2000.sonarmod.init.ModItems;


@Mod(modid = Main.MODID,name = Main.NAME , version = Main.VERSION)
public class Main {
	public static final String MODID = "sonarmod";
	public static final String NAME = "Sonar Mod";
	public static final String VERSION = "1.0";
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModItems.init();
	}
	
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
	
	}
}
