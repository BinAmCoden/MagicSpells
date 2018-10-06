package me.binamcoden.magicspells.listener;

import me.binamcoden.magicspells.MagicSpells;
import me.binamcoden.magicspells.utilities.Data;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    Data data = MagicSpells.getInstance().data;

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Player) {
            Player player = (Player) entity;
            if (event.getCause().equals(EntityDamageEvent.DamageCause.FIRE) || event.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
                if (player.getLocation().getBlock().getType().equals(Material.FIRE)) {
                    Block fire = player.getLocation().getBlock();
                    if (data.magicfire.containsKey(fire)) {
                        Player fireowner = data.magicfire.get(fire);
                        if (!fireowner.equals(player)) {
                            if (data.hitbyfire.containsKey(player) && (data.hitbyfire.get(player).equals(fireowner))) {
                                event.setCancelled(true);
                                player.setFireTicks(0);
                            } else if (!data.hitbyfire.containsKey(player) || !data.hitbyfire.get(player).equals(fireowner)) {
                                event.setDamage(4);
                                data.hitbyfire.put(player, fireowner);
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
