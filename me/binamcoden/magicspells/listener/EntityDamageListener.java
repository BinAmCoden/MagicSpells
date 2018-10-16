package me.binamcoden.magicspells.listener;

import me.binamcoden.magicspells.MagicSpells;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class EntityDamageListener implements Listener {

    private HashMap<Block, Player> magicFire = MagicSpells.getInstance().magicFire;
    private HashMap<Player, Player> hitByFire = MagicSpells.getInstance().hitByFire;

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Player) {
            Player player = (Player) entity;
            if (event.getCause().equals(EntityDamageEvent.DamageCause.FIRE) || event.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
                if (player.getLocation().getBlock().getType().equals(Material.FIRE)) {
                    Block fire = player.getLocation().getBlock();
                    if (magicFire.containsKey(fire)) {
                        Player fireowner = magicFire.get(fire);
                        if (!fireowner.equals(player)) {
                            if (hitByFire.containsKey(player) && (hitByFire.get(player).equals(fireowner))) {
                                event.setCancelled(true);
                                player.setFireTicks(0);
                            } else if (!hitByFire.containsKey(player) || !hitByFire.get(player).equals(fireowner)) {
                                event.setDamage(MagicSpells.SPREAD_FIRE_DAMAGE);
                                hitByFire.put(player, fireowner);
                                player.setFireTicks(0);
                            }
                        } else {
                            event.setCancelled(true);
                            player.setFireTicks(0);
                        }
                    }
                }
            }
        }
    }
}
