package me.binamcoden.magicspells.listener;

import me.binamcoden.magicspells.MagicSpells;
import me.binamcoden.magicspells.utilities.Data;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;

public class BlockFadeListener implements Listener {

    Data data = MagicSpells.getInstance().data;

    @EventHandler
    public void onFade(BlockFadeEvent event) {
        Block block = event.getBlock();

        if (block.getType().equals(Material.FIRE)) {
            if (data.magicfire.containsKey(block)) {
                data.magicfire.remove(block);
            }
        }
    }
}
