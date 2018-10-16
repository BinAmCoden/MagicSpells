package me.binamcoden.magicspells;

import me.binamcoden.magicspells.commands.CommandSpell;
import me.binamcoden.magicspells.listener.BlockFadeListener;
import me.binamcoden.magicspells.listener.EntityDamageByEntityListener;
import me.binamcoden.magicspells.listener.EntityDamageListener;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class MagicSpells extends JavaPlugin {

    private static MagicSpells instance;
    private CommandSender sender = Bukkit.getConsoleSender();

    public String prefix = "§5Spell §7×§f ";

    public static final int MAX_ARGUMENT_LENGTH = 1;
    public static final int FIRE_NOVA = 1;
    public static final int SPREAD = 2;
    public static final int HEALING_TOTEM = 3;
    public static final int MAX_FIREBALLS = 5;
    public static final int MAX_FIRE_RADIUS = 10;
    public static final int SPREAD_FIRE_DAMAGE = 4;
    public static final int FIREBALL_FIRE_DAMAGE = 2;

    public HashMap<Block, Player> magicFire = new HashMap<>();
    public HashMap<Player, Player> hitByFire = new HashMap<>();
    public ArrayList<Fireball> fireball = new ArrayList<>();

    @Override
    public void onEnable() {

        init();
        registerCommands();
        registerListener();

        sender.sendMessage(prefix + "§aPlugin was loaded successfully");
    }

    @Override
    public void onDisable() {

        sender.sendMessage(prefix + "§aPlugin was loaded unsuccessfully");
    }

    /*Initialize Variables*/
    private void init() {
        instance = this;
    }

    private void registerCommands() {

        getCommand("spell").setExecutor(new CommandSpell());
    }

    private void registerListener() {
        getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
        getServer().getPluginManager().registerEvents(new BlockFadeListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);

    }

    public static MagicSpells getInstance() {

        return instance;
    }


}