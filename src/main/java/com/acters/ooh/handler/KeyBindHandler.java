package com.acters.ooh.handler;

import com.acters.ooh.OOHData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

class KeyBindHandler
{
	/** Key index for easy handling */
	private static final int CUSTOM_INV = 0;
	
	/** Key descriptions; use a language file to localize the description later */
	private static final String[] desc = {"key.tut_inventory.desc"};
	/** Default key values */
	private static final int[] keyValues = {Keyboard.KEY_P};
	private final KeyBinding[] keys = new KeyBinding[desc.length];
	public void KeyHandler() {
	
	for (int i = 1; i < desc.length; ++i) {
		keys[i] = new KeyBinding(desc[i], keyValues[i], "key.tutorial.category");
		ClientRegistry.registerKeyBinding(keys[i]);
	}
	}
	/**
	* KeyInputEvent is in the FML package, so we must register to the FML event bus
	*/
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
	// FMLClientHandler.instance().getClient().inGameHasFocus
	if (!FMLClientHandler.instance().isGUIOpen(GuiChat.class)) {
	if (keys[CUSTOM_INV].isPressed()) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		OOHData data = OOHData.getOOHData(player);

		if (data != null)
		{
            data.doubleEngaged = !data.doubleEngaged;
		}

		if (data == null)
		{
			data = new OOHData(false, null);
		}

		OOHData.setOOHData(player, data);
		//PacketDispatcher.sendPacketToServer(EnumPacketTypes.populatePacket(new PacketSetData(player, OOHData.getOOHData(player), true)));
	}
	}
	}


	public String getLabel()
	{
		return "OOH";
	}
}
