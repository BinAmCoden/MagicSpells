package me.binamcoden.magicspells.commands;

import me.binamcoden.magicspells.MagicSpells;
import me.binamcoden.magicspells.spells.Ausbreiten;
import me.binamcoden.magicspells.spells.Feuernova;
import me.binamcoden.magicspells.spells.Heiltotem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_spell implements CommandExecutor {

    String prefix = MagicSpells.getInstance().prefix;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0 || args.length > 1) {
                player.sendMessage(prefix + "§cUsage: §7/spell <1,2,3>");
            } else {
                try {
                    int spell = Integer.valueOf(args[0]);
                    switch (spell) {
                        case 1:
                            new Feuernova(player).conjure();
                            break;
                        case 2:
                            new Ausbreiten(player).conjure();
                            break;
                        case 3:
                            new Heiltotem(player).conjure();
                            break;
                    }
                } catch (NumberFormatException expetion) {
                    player.sendMessage(prefix + "§cPlease enter a valid number");
                }
            }
        }
        return false;
    }
}
