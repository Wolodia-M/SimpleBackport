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
package my.wolodiam.simplebackport.mc1_14.signTE;

import my.wolodiam.simplebackport.api.signs.SignTileEntity;
import my.wolodiam.simplebackport.utils.DATA;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.model.ModelSign;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class SignRenderer extends TileEntitySpecialRenderer<SignTileEntity> {
    private final ResourceLocation TEXTURE;
    private final ModelSign MODEL_SIGN;
    private final Block TOP_VARIANT;
    private final Block SIDE_VARIANT;

    public SignRenderer(String texture, Block top_sign, Block side_sign) {
        TEXTURE = new ResourceLocation(DATA.MODID + ":" + texture);
        MODEL_SIGN = new ModelSign();
        TOP_VARIANT = top_sign;
        SIDE_VARIANT = side_sign;
    }

    @Override
    public void render(SignTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        Block block = te.getBlockType();
        GlStateManager.pushMatrix();
        float f = 0.6666667F;

        if (block == this.TOP_VARIANT)
        {
            GlStateManager.translate((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
            float f1 = (float)(te.getBlockMetadata() * 360) / 16.0F;
            GlStateManager.rotate(-f1, 0.0F, 1.0F, 0.0F);
            this.MODEL_SIGN.signStick.showModel = true;
        }
        else
        {
            int k = te.getBlockMetadata();
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
            this.MODEL_SIGN.signStick.showModel = false;
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
            this.bindTexture(this.TEXTURE);
        }

        GlStateManager.enableRescaleNormal();
        GlStateManager.pushMatrix();
        GlStateManager.scale(0.6666667F, -0.6666667F, -0.6666667F);
        this.MODEL_SIGN.renderSign();
        GlStateManager.popMatrix();
        FontRenderer fontrenderer = this.getFontRenderer();
        float f3 = 0.010416667F;
        GlStateManager.translate(0.0F, 0.33333334F, 0.046666667F);
        GlStateManager.scale(0.010416667F, -0.010416667F, 0.010416667F);
        GlStateManager.glNormal3f(0.0F, 0.0F, -0.010416667F);
        GlStateManager.depthMask(false);
        int i = 0;

        if (destroyStage < 0)
        {
            for (int j = 0; j < te.signText.length; ++j)
            {
                if (te.signText[j] != null)
                {
                    ITextComponent itextcomponent = te.signText[j];
                    List<ITextComponent> list = GuiUtilRenderComponents.splitText(itextcomponent, 90, fontrenderer, false, true);
                    String s = list != null && !list.isEmpty() ? ((ITextComponent)list.get(0)).getFormattedText() : "";

                    if (j == te.lineBeingEdited)
                    {
                        s = "> " + s + " <";
                        fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, j * 10 - te.signText.length * 5, 0);
                    }
                    else
                    {
                        fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, j * 10 - te.signText.length * 5, 0);
                    }
                }
            }
        }

        GlStateManager.depthMask(true);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();

        if (destroyStage >= 0)
        {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }

    }
}
