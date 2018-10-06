package me.binamcoden.magicspells.spells;

import me.binamcoden.magicspells.MagicSpells;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class Heiltotem {

    Player wizzard;
    BukkitTask healtask;
    BukkitTask particletask;
    ArmorStand armorStand;

    public Heiltotem(Player wizzard) {

        this.wizzard = wizzard;
    }

    /*Create "Heiltotem"*/
    public void conjure() {
        armorStand = (ArmorStand) wizzard.getWorld().spawnEntity(wizzard.getLocation(), EntityType.ARMOR_STAND);
        armorStand.setCustomName("Heiltotem");
        armorStand.setCustomNameVisible(true);
        startSelfDestruction(armorStand);
        startHealProgress();
        startParticle(wizzard.getLocation());
    }

    /*Countdown for self-destruction*/
    private void startSelfDestruction(ArmorStand armorStand) {
        new BukkitRunnable() {
            @Override
            public void run() {
                healtask.cancel();
                armorStand.remove();
                particletask.cancel();
            }
        }.runTaskLater(MagicSpells.getInstance(), 20 * 20);
    }

    /*Task for heal progress*/
    private void startHealProgress() {
        healtask = new BukkitRunnable() {
            @Override
            public void run() {
                if (!armorStand.isDead()) {
                    for (Player player : getTargets()) {
                        if (player.getHealth() <= 19) {
                            player.setHealth(player.getHealth() + 1);
                            player.getWorld().playEffect(player.getEyeLocation().add(0, 0.5, 0), Effect.HEART, 1);
                            player.getWorld().playEffect(player.getEyeLocation().add(0, 0.5, 0), Effect.HAPPY_VILLAGER, 1);

                        }
                    }
                } else {
                    cancel();
                    particletask.cancel();
                }
            }
        }.runTaskTimer(MagicSpells.getInstance(), 0, 20);
    }

    /*Returns all entities within 20 blocks*/
    private List<Player> getTargets() {
        List<Player> targets = new ArrayList<>();
        for (Entity entity : armorStand.getNearbyEntities(20, 20, 20)) {
            if (entity instanceof Player) {
                Player target = (Player) entity;
                targets.add(target);
            }
        }
        return targets;
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

    /*Task for starting particle-circle*/
    private void startParticle(Location center) {
        particletask = new BukkitRunnable() {
            @Override
            public void run() {
                for (Location location : getCircle(center, 20, 200)) {
                    location.getWorld().playEffect(location, Effect.SPELL, 1);
                }
            }
        }.runTaskTimer(MagicSpells.getInstance(), 0, 20);
    }


}
