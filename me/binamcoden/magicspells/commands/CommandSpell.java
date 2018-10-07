package me.binamcoden.magicspells.commands;

import me.binamcoden.magicspells.MagicSpells;
import me.binamcoden.magicspells.spells.HealingTotem;
import me.binamcoden.magicspells.spells.Spread;
import me.binamcoden.magicspells.spells.FireNova;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpell implements CommandExecutor {

    private String prefix = MagicSpells.getInstance().prefix;

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
                            new FireNova(player).conjure();
                            break;
                        case 2:
                            new Spread(player).conjure();
                            break;
                        case 3:
                            new HealingTotem(player).conjure();
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
