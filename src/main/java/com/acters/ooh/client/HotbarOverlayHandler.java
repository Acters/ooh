package com.acters.ooh.client;

import com.acters.ooh.OOHData;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class HotbarOverlayHandler
{
	private static final ResourceLocation	OVERLAY	   = new ResourceLocation("ooh", "textures/gui/overlay.png");
	private static final ResourceLocation	WIDGITS	   = new ResourceLocation("textures/gui/widgets.png");
	private static final RenderItem	  itemRenderer	= new RenderItem();
	private final int	                          zLevel	   = 100;




	@SubscribeEvent
	public void renderOverlay (RenderGameOverlayEvent.Post event)
	{
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		OOHData data = OOHData.getOOHData(player);

		if (data != null && event.type == ElementType.HOTBAR && data.doubleEngaged)
		{
			GL11.glColor3f(1, 1, 1);
			
			int height = event.resolution.getScaledHeight();
			int width = event.resolution.getScaledWidth();

			int slot = (player.inventory.currentItem - 1 < 0) ? 8 : player.inventory.currentItem - 1;

			if (slot != 8)
			{
				Minecraft.getMinecraft().renderEngine.bindTexture(OVERLAY);
				drawTexturedModalRect(width / 2 - 92 - 0 + slot * 20, height - 23, 22, 44, 22);
			}
			else
			{
				Minecraft.getMinecraft().renderEngine.bindTexture(WIDGITS);
		        drawTexturedModalRect(width / 2 - 91 - 1 + slot * 20, height - 22 - 1, 22, 24, 22);
			}
			
			Minecraft.getMinecraft().renderEngine.bindTexture(OVERLAY);
	        drawTexturedModalRect(width / 2 - 91 - 1 + (slot) * 20, height - 22 - 1, 46, 24, 46);
	        drawTexturedModalRect(width / 2 - 91 - 1 + (player.inventory.currentItem) * 20, height - 22 - 1, 70, 24, 70);

			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			RenderHelper.enableGUIStandardItemLighting();

			for (int i = 0; i < 9; ++i)
			{
				int x = width / 2 - 90 + i * 20 + 2;
				int z = height - 16 - 3;
				renderInventorySlot(i, x, z, event.partialTicks);
			}

			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		}
	}




	void drawTexturedModalRect(int par1, int par2, int par4, int par5, int par6)
	{
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV((double) (par1 + 0), (double) (par2 + par6), (double) this.zLevel, (double) ((float) (0 + 0) * f), (double) ((float) (par4 + par6) * f1));
		tessellator.addVertexWithUV((double) (par1 + par5), (double) (par2 + par6), (double) this.zLevel, (double) ((float) (0 + par5) * f), (double) ((float) (par4 + par6) * f1));
		tessellator.addVertexWithUV((double) (par1 + par5), (double) (par2 + 0), (double) this.zLevel, (double) ((float) (0 + par5) * f), (double) ((float) (par4 + 0) * f1));
		tessellator.addVertexWithUV((double) (par1 + 0), (double) (par2 + 0), (double) this.zLevel, (double) ((float) (0 + 0) * f), (double) ((float) (par4 + 0) * f1));
		tessellator.draw();
	}




	void renderInventorySlot(int par1, int par2, int par3, float par4)
	{
		ItemStack itemstack = Minecraft.getMinecraft().thePlayer.inventory.mainInventory[par1];

		if (itemstack != null)
		{
			itemRenderer.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().getTextureManager(), itemstack, par2, par3);
		}
	}
}
