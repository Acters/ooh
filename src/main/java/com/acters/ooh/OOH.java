package com.acters.ooh;

import java.util.ArrayList;
import java.util.List;

import com.acters.ooh.client.FirstPersonRenderHandler;
import com.acters.ooh.client.HotbarOverlayHandler;
import com.acters.ooh.client.ThirdPersonRenderHandler;
import com.acters.ooh.packethandler.PacketPipeline;
import com.acters.ooh.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@Mod (modid = OOH.modid, useMetadata = true, name = "OntheOtherHand", version = "0.0d.pre")
public class OOH
{
	// TO DO
	// - weird click after disengaging dual wield
	// - right arm still renders at times
	// - doesn't swing @ times

	@Instance (OOH.modid)
	@SidedProxy (clientSide = "com.acters.ooh.proxy.ClientProxy", serverSide = "com.acters.ooh.proxy.CommonProxy")

	public static OOH	       instance;
	private static CommonProxy	proxy;
	public static final String modid = "OOH";
	public static final boolean	   isObfuscated	 = false;
	public static final  List <Item> illegalItems	= new ArrayList<Item>();
	private static final PacketPipeline packetPipeline = new PacketPipeline();

	@EventHandler
	public void initialise(FMLInitializationEvent evt) {
	    packetPipeline.initialise();
	}

	@EventHandler
	public void postInitialise(FMLPostInitializationEvent evt) {
	    packetPipeline.postInitialise();
	}


	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		proxy.registerKeyBinds();

		if (isClient())
		{
			GameSettings settings = Minecraft.getMinecraft().gameSettings;
			settings.keyBindAttack = new KeyBinding("key.attack", -100,"key.categories.gameplay");
			settings.keyBindUseItem = new KeyBinding("key.use", -99,"key.categories.gameplay");

			MinecraftForge.EVENT_BUS.register(new ThirdPersonRenderHandler());
			MinecraftForge.EVENT_BUS.register(new FirstPersonRenderHandler());
			MinecraftForge.EVENT_BUS.register(new HotbarOverlayHandler());
		}

		//TickRegistry.registerTickHandler(new PlayerTickHandler(), Side.CLIENT);
		//TickRegistry.registerTickHandler(new PlayerTickHandler(), Side.SERVER);
		//GameRegistry.registerPlayerTracker(new PlayerTracker());
		illegalItems.add(Items.map);
	}




	public static void log (Object message)
	{
		System.out.println(message);
	}




	public static boolean isClient ()
	{
		return FMLCommonHandler.instance().getEffectiveSide().isClient();
	}




	public static boolean isServer ()
	{
		return FMLCommonHandler.instance().getEffectiveSide().isServer();
	}




	public static boolean idMetaDamageMatch (ItemStack stack1, ItemStack stack2)
	{
		return stack1 == stack2 && stack1.getItemDamage() == stack2.getItemDamage() && stack1.stackSize == stack2.stackSize;
	}




	public static int getArmSwingAnimationEnd (EntityPlayer player)
	{
		return player.isPotionActive(Potion.digSpeed) ? 6 - (1 + player.getActivePotionEffect(Potion.digSpeed).getAmplifier()) : (player.isPotionActive(Potion.digSlowdown) ? 6 + (1 + player.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2 : 6);
	}
}
