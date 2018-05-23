package thetdw2000.sonarmod.items;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class Sonar extends Item {

	public Block searchedFor;
	
	public Sonar(String name, Block block) {
		super();
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.TOOLS);
		searchedFor = block;
	}
	
	@Override
	 public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
		boolean coalFound = false;
		
		if (playerIn.isCreative()) { // Checks to see if the player is in creative mode
		coalFound = true;
		}else if (playerIn.inventory.hasItemStack(new ItemStack(Items.COAL, 1, 1))) { // Else, checks for charcoal
			coalFound = true;
			playerIn.inventory.clearMatchingItems(Items.COAL, 1, 1, null); // Removes charcoal if found
		}else if (playerIn.inventory.hasItemStack(new ItemStack(Items.COAL, 1))) { // Else, checks for regular coal
			coalFound = true;
			playerIn.inventory.clearMatchingItems(Items.COAL, 0, 1, null); // Removes coal if found
		}
		
		if (coalFound) {
		// Starts the thread to search the surrounding blocks
		SonarThread thread = new SonarThread("Sonar Thread", worldIn, searchedFor, playerIn);
		thread.start();
		}
		else {
		playerIn.playSound(SoundEvents.BLOCK_NOTE_BASS, 5F, 0.2F);
	}	
		playerIn.getCooldownTracker().setCooldown(this, 20);
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
	
	
	
	
}
