package com.acters.ambidexterity;

import net.minecraftforge.common.MinecraftForge;

import com.acters.ambidexterity.eventHandler.ambidexterityEventHandler;
import com.acters.ambidexterity.keyHandler.ambidexterityKeyHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = Ambidexterity.MODID, version = Ambidexterity.VERSION)
public class Ambidexterity {
    public static final String MODID = "ambidexterity";
    public static final String VERSION = "1.0";
    

    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
    	FMLCommonHandler.instance().bus().register(new ambidexterityKeyHandler());
        MinecraftForge.EVENT_BUS.register(new ambidexterityEventHandler());
    }
}
