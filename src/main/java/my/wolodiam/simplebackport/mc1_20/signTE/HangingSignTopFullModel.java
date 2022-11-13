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
//import net.minecraft.client.model.ModelSign;

import net.minecraft.client.model.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class HangingSignTopFullModel extends ModelBase {
    public ModelRenderer signBoard;
    public ModelRenderer signChains[] = new ModelRenderer[2];

    public HangingSignTopFullModel() {
        // Main plate of sign
        this.signBoard = new ModelRenderer(this, 0, 0);
        this.signBoard.addBox(-12.0F, -5.0F, -1.0F, 24, 12, 2, 0.0F);
        // Two chain connectors of sign
        this.signChains[0] = new ModelRenderer(this, 0, 14);
        this.signChains[1] = new ModelRenderer(this, 0, 14);
        this.signChains[0].addBox(-12F, -5F, -1F, 7, 3, 2, 0.0F);
        this.signChains[1].addBox(-12F, 5F, -1F, 7, 3, 2, 0.0F);
    }

    public void renderConnector()
    {
        this.signChains[0].render(0.0625F);
        this.signChains[1].render(0.0625F);
    }
    public void renderSign()
    {
        this.signBoard.render(0.0625F);
    }
}
