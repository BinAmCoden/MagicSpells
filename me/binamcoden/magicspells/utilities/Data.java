package me.binamcoden.magicspells.utilities;

import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {
    /* Fire, Owner */
    public HashMap<Block, Player> magicfire = new HashMap<>();
    /* affectedPlayer, Fire owner */
    public HashMap<Player, Player> hitbyfire = new HashMap<>();

    public ArrayList<Fireball> fireball = new ArrayList<>();

}
