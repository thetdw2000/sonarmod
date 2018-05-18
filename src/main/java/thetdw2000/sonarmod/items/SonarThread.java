package thetdw2000.sonarmod.items;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Random;

import ibxm.Player;
import net.minecraft.block.Block;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.util.SoundEvent;

public class SonarThread extends Thread{
	
	Block searchedFor; // The block we're searching for (some kind of ore)
	Block secondaryTarget; // The block we search for as an alternative (Currently only used for Redstone Ore)
    final int xPos; // X position of player at start of thread
	final int yPos; // Y position
	final int zPos; // Z position
	final int maxX; // Maximum X value to search
	final int maxY; // Maximum Y
	final int maxZ; // Maximum Z
	final int minX; // Minimum X value to search
	final int minY; // Minimum Y
	final int minZ; // Minimum Z
	int veins; // The number of veins found
	World world; // The world, man
	EntityPlayer playerIn; // The Player that activates the Sonar
    boolean[][][] checkBlock; // Stores whether or not the block should be checked (false means it's already been checked)
    
	
	public SonarThread(String name, World worldIn, Block block, EntityPlayer player) {
		super(name);
		searchedFor = block;
		playerIn = player;
		xPos = (int) playerIn.posX;
		yPos = (int) playerIn.posY;
		zPos = (int) playerIn.posZ;
		minX = xPos - 10;
		maxX = xPos + 10;
		minY = yPos - 10;
		maxY = yPos;
		minZ = zPos - 10;
		maxZ = zPos + 10;
		world = worldIn;
		checkBlock = new boolean[21][11][21];
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 11; j++) {
				for (int k = 0; k < 21; k++) {
					checkBlock[i][j][k] = true;
				}
			}
		}
		if (searchedFor.equals(Blocks.REDSTONE_ORE))
			secondaryTarget = Blocks.LIT_REDSTONE_ORE;
		else
			secondaryTarget = searchedFor;
	}
	
	public void run() {
		
		veins = 0;
		int arrayX = 0;
		int arrayY = 0;
		int arrayZ = 0;
		
		
		//Searches through each value from min to max, incrementing by 1 every time
		int x = minX;
		while (x <= maxX) {
		arrayX = (x - minX);	
			
		int y = minY;
		while (y < maxY) {
		arrayY = (y - minY);
			
		int z = minZ;
		while (z <= maxZ) {
		arrayZ = (z - minZ);	
		
		if (checkBlock[arrayX][arrayY][arrayZ]) {
			// Checks if the block at the coordinates matches the block we're searching for
			if (world.getBlockState(new BlockPos(x, y, z)).getBlock().equals(searchedFor)
					|| world.getBlockState(new BlockPos(x, y, z) ).getBlock().equals(secondaryTarget)) {
				veins++;
				searchAround(x, y, z);
			}
		}
		
		z++;
		}
		y++;
		}
		x++;
		}
		playerIn.playSound(SoundEvents.BLOCK_NOTE_XYLOPHONE
				, 1F, (float)((veins + 1) * .3F));
}
	
	// Method to mark all surrounding blocks of the same type so they aren't counted again
	private void searchAround(int x, int y, int z) {
		if (!(minX <= x && x <= maxX)) { // Checks if the block is in range
			return;						 //
		}								 //
		if (!(minY <= y && y <= maxY)) { //
			return;						 //
		}								 //
		if (!(minZ <= z && z <= maxZ)) { //
			return;
		}
			
		if (!checkBlock[x - minX][y - minY][z - minZ]) { // Checks if the block should be checked in the first place
			return;
		}
		
		if (world.getBlockState(new BlockPos(x, y, z)).getBlock().equals(searchedFor)
		|| world.getBlockState(new BlockPos(x, y, z)).getBlock().equals(secondaryTarget)) { // Calls this method on the six orthogonally adjacent blocks 
			checkBlock[x - minX][y - minY][z - minZ] = false; // Makes sure this block never gets counted again
			searchAround(x + 1, y, z);
			searchAround(x - 1, y, z);
			searchAround(x, y + 1, z);
			searchAround(x, y - 1, z);
			searchAround(x, y, z + 1);
			searchAround(x, y, z - 1);
		}
	}
}
