package me.binamcoden.magicspells;

import me.binamcoden.magicspells.commands.CMD_spell;
import me.binamcoden.magicspells.listener.BlockFadeListener;
import me.binamcoden.magicspells.listener.EntityDamageByEntityListener;
import me.binamcoden.magicspells.listener.EntityDamageListener;
import me.binamcoden.magicspells.utilities.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class MagicSpells extends JavaPlugin {

    static MagicSpells instance;
    public Data data;

    public String prefix = "§5Spell §7×§f ";

    private CommandSender sender = Bukkit.getConsoleSender();

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
        data = new Data();
    }

    private void registerCommands() {

        getCommand("spell").setExecutor(new CMD_spell());
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
