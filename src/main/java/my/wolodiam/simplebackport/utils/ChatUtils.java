/*
    Simple backport of new minecraft feathures to mc 1.12.2
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
package my.wolodiam.simplebackport.utils;
// Import minecraft classes
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

public class ChatUtils {
    /**
     * Print message to chat
     *
     * @param player player (EntityPlayer)
     * @param msg Message (String)
     */
    public static void writeToChat(EntityPlayer player, String msg)
    {
        player.sendMessage(new TextComponentString(msg));
    }
}
