package me.binamcoden.magicspells.spells;

import me.binamcoden.magicspells.MagicSpells;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class Feuernova {

    Player wizzard;

    public Feuernova(Player wizzard) {

        this.wizzard = wizzard;
    }

    /*Starts shooting fireballs*/
    public void conjure() {
        for (Player target : getaffectedPlayer()) {
            System.out.println(target.getName());
            new BukkitRunnable() {
                int count = 1;

                @Override
                public void run() {

                    if (count <= 5) {
                        playConjureEffect();
                        Location from = wizzard.getLocation().add(0, 2, 0);
                        Vector vector = target.getEyeLocation().toVector().subtract(from.toVector());
                        Fireball fireball = wizzard.launchProjectile(Fireball.class, vector);
                        fireball.setInvulnerable(false);
                        fireball.setFireTicks(0);
                    } else {
                        cancel();
                    }

                    count++;
                }
            }.runTaskTimer(MagicSpells.getInstance(), 0, 20);


        }
    }

    /*Returns list with all possible targets*/
    private ArrayList<Player> getaffectedPlayer() {
        ArrayList<Player> affectedPlayer = new ArrayList<Player>();

        for (Entity entity : wizzard.getNearbyEntities(10, 10, 10)) {
            if (entity instanceof Player) {
                Player target = (Player) entity;
                affectedPlayer.add(target);
            }
        }

        return affectedPlayer;
    }

    /*Returns list with locations to create a circle*/
    private ArrayList<Location> getCircle(Location center, double r, int amount) {
        World world = center.getWorld();
        double increment = (2 * Math.PI) / amount;
        ArrayList<Location> locations = new ArrayList<Location>();
        for (int i = 0; i < amount; i++) {
            double angle = i * increment;
            double x = center.getX() + (r * Math.cos(angle));
            double z = center.getZ() + (r * Math.sin(angle));
            locations.add(new Location(world, x, center.getY(), z));
        }
        return locations;
    }

    /*Plays a circle effect*/
    private void playConjureEffect() {
        for (Location location : getCircle(wizzard.getLocation(), 1.5, 30)) {
            location.getWorld().playEffect(location, Effect.SPELL, 2);
        }
    }


}
