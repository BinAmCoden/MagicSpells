package me.binamcoden.magicspells.listener;

import me.binamcoden.magicspells.MagicSpells;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class BlockFadeListener implements Listener {

    private HashMap<Block, Player> magicFire = MagicSpells.getInstance().magicFire;

    @EventHandler
    public void onFade(BlockFadeEvent event) {
        Block block = event.getBlock();

        if (block.getType().equals(Material.FIRE)) {
            if (magicFire.containsKey(block)) {
                magicFire.remove(block);
            }
        }
    }
}
