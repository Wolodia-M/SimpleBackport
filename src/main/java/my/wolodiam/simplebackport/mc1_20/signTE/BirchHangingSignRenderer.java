/*
    Simple backport of new minecraft features to mc 1.12.2
    Copyright (C) 2022 WolodiaM

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

// Java package
package my.wolodiam.simplebackport.mc1_20.signTE;
// import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
// Import minecraft classes
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.text.*;
import net.minecraft.block.*;
// Import Java classes
import java.util.*;
// Import mod classes
import my.wolodiam.simplebackport.api.signs.SignTileEntity;
import my.wolodiam.simplebackport.utils.DATA;
import my.wolodiam.simplebackport.utils.registry.*;

public class BirchHangingSignRenderer extends TileEntitySpecialRenderer<SignTileEntity>
{
    private static final ResourceLocation TEXTURE_BIRCH_HANGING_SIGN = new ResourceLocation(DATA.MODID + ":" +"textures/sign/birch_hanging_sign.png");
    private final HangingSignTopFullModel MODEL_HANGING_SIGN_TOP_FULL = new HangingSignTopFullModel();
    private final HangingSignSideModel MODEL_HANGING_SIGN_SIDE = new HangingSignSideModel();
    @Override
    public void render(SignTileEntity tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        Block block = tileEntity.getBlockType();
        GlStateManager.pushMatrix();
        float f = 0.6666667F;

        if (block == BlockRegister.BIRCH_HANGING_SIGN_TOP_FULL)
        {
            GlStateManager.translate((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
            float f1 = (float)(tileEntity.getBlockMetadata() * 360) / 16.0F;
            GlStateManager.rotate(-f1, 0.0F, 1.0F, 0.0F);
        }
        else // Block == BlockRegister.BIRCH_HANGING_SIGN_SIDE
        {
            int k = tileEntity.getBlockMetadata();
            float f2 = 0.0F;

            if (k == 2)
            {
                f2 = 180.0F;
            }

            if (k == 4)
            {
                f2 = 90.0F;
            }

            if (k == 5)
            {
                f2 = -90.0F;
            }

            GlStateManager.translate((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
            GlStateManager.rotate(-f2, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(0.0F, -0.3125F, -0.4375F);
        }

        if (destroyStage >= 0)
        {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 2.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }
        else
        {
            this.bindTexture(TEXTURE_BIRCH_HANGING_SIGN);
        }
        if (block == BlockRegister.BIRCH_HANGING_SIGN_TOP_FULL)
        {
            GlStateManager.enableRescaleNormal();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.6666667F, -0.6666667F, -0.6666667F);
            this.MODEL_HANGING_SIGN_TOP_FULL.renderSign();
            GlStateManager.rotate(90, 0, 0, 90);
            this.MODEL_HANGING_SIGN_TOP_FULL.renderConnector();
            GlStateManager.popMatrix();
            FontRenderer fontrenderer = this.getFontRenderer();
            float f3 = 0.010416667F;
            GlStateManager.translate(0.0F, 0.33333334F, 0.046666667F);
            GlStateManager.scale(0.010416667F, -0.010416667F, 0.010416667F);
            GlStateManager.glNormal3f(0.0F, 0.0F, -0.010416667F);
            GlStateManager.depthMask(false);
            int i = 0;

            if (destroyStage < 0) {
                for (int j = 0; j < tileEntity.signText.length; ++j) {
                    if (tileEntity.signText[j] != null) {
                        ITextComponent itextcomponent = tileEntity.signText[j];
                        List<ITextComponent> list = GuiUtilRenderComponents.splitText(itextcomponent, 90, fontrenderer, false, true);
                        String s = list != null && !list.isEmpty() ? ((ITextComponent) list.get(0)).getFormattedText() : "";

                        if (j == tileEntity.lineBeingEdited) {
                            s = "> " + s + " <";
                            fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, (j * 10 - tileEntity.signText.length * 5) + 35, 0);
                        } else {
                            fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, (j * 10 - tileEntity.signText.length * 5) + 35, 0);
                        }
                    }
                }
            }

            GlStateManager.depthMask(true);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.popMatrix();
        }
        else // Block == BlockRegister.BIRCH_HANGING_SIGN_SIDE
        {
            GlStateManager.enableRescaleNormal();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.6666667F, -0.6666667F, -0.6666667F);
            GlStateManager.rotate(90, 0, 90, 0);
            this.MODEL_HANGING_SIGN_SIDE.renderSign();
            this.MODEL_HANGING_SIGN_SIDE.renderStick();
            //GlStateManager.rotate(90, 0, 0, 90);
            GlStateManager.rotate(90, 0, 0, 90);
            this.MODEL_HANGING_SIGN_SIDE.renderConnector();
            GlStateManager.popMatrix();
            GlStateManager.rotate(90, 0, -90, 0);
            GlStateManager.translate(0.55F, 0, 0);
            FontRenderer fontrenderer = this.getFontRenderer();
            GlStateManager.translate(0.0F, 0.33333334F, 0.046666667F);
            GlStateManager.scale(0.010416667F, -0.010416667F, 0.010416667F);
            GlStateManager.glNormal3f(0.0F, 0.0F, -0.010416667F);
            GlStateManager.depthMask(false);
            int i = 0;

            if (destroyStage < 0) {
                for (int j = 0; j < tileEntity.signText.length; ++j) {
                    if (tileEntity.signText[j] != null) {
                        ITextComponent itextcomponent = tileEntity.signText[j];
                        List<ITextComponent> list = GuiUtilRenderComponents.splitText(itextcomponent, 90, fontrenderer, false, true);
                        String s = list != null && !list.isEmpty() ? ((ITextComponent) list.get(0)).getFormattedText() : "";

                        if (j == tileEntity.lineBeingEdited) {
                            s = "> " + s + " <";
                            fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, (j * 10 - tileEntity.signText.length * 5) + 35, 0);
                        } else {
                            fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, (j * 10 - tileEntity.signText.length * 5) + 35, 0);
                        }
                    }
                }
            }

            GlStateManager.depthMask(true);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.popMatrix();
        }
        if (destroyStage >= 0)
        {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }

    }

}
