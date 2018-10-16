package me.binamcoden.magicspells.spells;

import me.binamcoden.magicspells.MagicSpells;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Spread extends Spell{

    private Player wizzard;

    public Spread(Player wizzard) {

        this.wizzard = wizzard;
    }

    /*Creates growing fire-circle*/
    public void conjure() {
        new BukkitRunnable() {
            int r = 1;

            @Override
            public void run() {
                if (r <= MagicSpells.MAX_FIRE_RADIUS) {
                    cylinder(wizzard.getLocation(), Material.FIRE, r);
                } else {
                    cancel();
                }
                r++;

            }
        }.runTaskTimer(MagicSpells.getInstance(), 0, 10);
    }

    /*Returns list with locations to create a fire-circle*/
    public void cylinder(Location loc, Material mat, int r) {
        int cx = loc.getBlockX();
        int cy = loc.getBlockY();
        int cz = loc.getBlockZ();
        World w = loc.getWorld();
        int rSquared = r * r;
        for (int x = cx - r; x <= cx + r; x++) {
            for (int z = cz - r; z <= cz + r; z++) {
                if ((cx - x) * (cx - x) + (cz - z) * (cz - z) <= rSquared) {
                    Block fire = w.getHighestBlockAt(new Location(w, x, cy, z).add(0, 1, 0));
                    fire.setType(mat);
                    MagicSpells.getInstance().magicFire.put(fire, wizzard);
                }
            }
        }
    }


}
