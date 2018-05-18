package thetdw2000.sonarmod.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thetdw2000.sonarmod.Main;
import thetdw2000.sonarmod.items.Sonar;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModItems {

	static Item coalSonar;
	static Item ironSonar;
	static Item goldSonar;
	static Item redstoneSonar;
	static Item diamondSonar;
	static Item lapisSonar;
	
	public static void init() {
		coalSonar = new Sonar("coal_sonar", Blocks.COAL_ORE);
		ironSonar = new Sonar("iron_sonar", Blocks.IRON_ORE);
		goldSonar = new Sonar("gold_sonar", Blocks.GOLD_ORE);
		redstoneSonar = new Sonar("redstone_sonar", Blocks.REDSTONE_ORE);
		diamondSonar = new Sonar("diamond_sonar", Blocks.DIAMOND_ORE);
		lapisSonar = new Sonar("lapis_sonar", Blocks.LAPIS_ORE);
		
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(coalSonar, ironSonar, goldSonar, redstoneSonar, diamondSonar, lapisSonar);
		ModelLoader.setCustomModelResourceLocation(coalSonar, 0, new
				ModelResourceLocation(coalSonar.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(ironSonar, 0, new
				ModelResourceLocation(ironSonar.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(goldSonar, 0, new
				ModelResourceLocation(goldSonar.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(redstoneSonar, 0, new
				ModelResourceLocation(redstoneSonar.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(diamondSonar, 0, new
				ModelResourceLocation(diamondSonar.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(lapisSonar, 0, new
				ModelResourceLocation(lapisSonar.getRegistryName(), "inventory"));
	}
}
