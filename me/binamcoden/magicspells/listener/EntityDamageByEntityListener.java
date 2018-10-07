package me.binamcoden.magicspells.listener;

import me.binamcoden.magicspells.MagicSpells;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();

        if (entity instanceof Player) {
            Player player = (Player) entity;
            if (damager instanceof Fireball) {
                if(MagicSpells.getInstance().data.fireball.contains(damager)) {
                    event.setDamage(2);
                    MagicSpells.getInstance().data.fireball.remove(damager);
                }
            }
        }
    }
}
