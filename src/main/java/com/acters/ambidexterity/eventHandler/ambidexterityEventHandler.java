package com.acters.ambidexterity.eventHandler;

import com.acters.ambidexterity.keyHandler.ambidexterityKeyHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;


public class ambidexterityEventHandler {

	@SubscribeEvent
    public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entity;
            ItemStack heldItem = player.getHeldItem();
            if (heldItem != null && heldItem.getItem() == Items.arrow && ambidexterityKeyHandler.fly == true) {
            	player.capabilities.allowFlying = true;
            	//System.out.println("flying enabled");
            }
            else {
            	player.capabilities.allowFlying = player.capabilities.isCreativeMode ? true : false;
            }
            if (heldItem != null && ambidexterityKeyHandler.secondHandEnabled == true) {
            	player.capabilities.allowFlying = true;
            	
            }
        }
    }
}
