package com.acters.ambidexterity.keyHandler;

import java.util.Arrays;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

import com.acters.ambidexterity.eventHandler.ambidexterityEventHandler;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class ambidexterityKeyHandler {
    public static boolean fly = false;
    public static boolean secondHandEnabled = false;
	/** Key index for easy handling */
	public static int FLY = 0;
	/** Key descriptions; use a language file to localize the description later */
	//private static final String[] desc = { "1" };
	/** Default key values */
	//private static final int keyValues = Keyboard.KEY_F;
	private KeyBinding keys = new KeyBinding("tutorial desc", Keyboard.KEY_F , "TEST");
	private KeyBinding secondHand = new KeyBinding("tutorial key", Keyboard.KEY_P , "ambidexterity");
	public void KeyHandler() {
/*		for (int i = 0; i < desc.length; ++i) {
			keys[i] = new KeyBinding(desc[i], keyValues[i],"key.tutorial.category");
			ClientRegistry.registerKeyBinding(keys[i]);
		}*/
	}

	/**
	 * KeyInputEvent is in the FML package, so we must register to the FML event
	 * bus
	 */
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		//System.out.println("1");
		if (keys.isPressed())
		{
			fly = !fly;
			System.out.println(fly);
		}
		if (secondHand.isPressed())
		{
			secondHandEnabled = !secondHandEnabled;
		}
	}
}
